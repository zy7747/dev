package com.example.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.UserConvert;
import com.example.system.dal.dto.menu.MenuQueryDTO;
import com.example.system.dal.dto.user.LoginDTO;
import com.example.system.dal.dto.user.LogoutDTO;
import com.example.system.dal.dto.user.UserQueryDTO;
import com.example.system.dal.dto.user.UserSaveDTO;
import com.example.system.dal.entity.MenuEntity;
import com.example.system.dal.entity.RoleEntity;
import com.example.system.dal.entity.UserEntity;
import com.example.system.dal.entity.UserOnlineEntity;
import com.example.system.dal.vo.user.*;
import com.example.system.mapper.MenuMapper;
import com.example.system.mapper.UserMapper;
import com.example.system.service.UserService;
import com.example.system.utils.JwtUtil;
import com.example.system.utils.OnlineUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Resource
    UserMapper userMapper;

    @Resource
    MenuMapper menuMapper;


    /**
     * 获取列表分页
     *
     * @param user 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<UserPageVO>> userPage(UserQueryDTO user) {

        return Result.success(UserConvert.INSTANCE.page(userMapper.queryPage(user)));
    }

    /**
     * 获取列表
     *
     * @param user 入参
     * @return 列表
     */
    @Override
    public Result<List<UserListVO>> userList(UserQueryDTO user) {
        return Result.success(UserConvert.INSTANCE.list(userMapper.queryList(user)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<UserDetailVO> userDetail(Long id) {
        ArrayList<Long> roles = new ArrayList<>();
        ArrayList<Long> permissions = new ArrayList<>();

        userMapper.selectUserRole(id).forEach(item -> roles.add(item.getRoleId()));
        userMapper.selectUserPermission(id).forEach(item -> permissions.add(item.getPermissionId()));

        UserDetailVO user = UserConvert.INSTANCE.detail(userMapper.selectById(id));
        user.setRoles(roles);
        user.setPermissions(permissions);

        return Result.success(user);
    }

    /**
     * 新增/修改
     *
     * @param user 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<UserEntity> userSave(UserSaveDTO user) {
        UserEntity userData = UserConvert.INSTANCE.save(user);

        List<UserEntity> userList = userMapper.queryList(new UserQueryDTO());

        Result<UserEntity> valid = userMapper.onlyValid(userData, userList);

        if (valid.getCode() == 200) {
            this.saveOrUpdate(userData);

            //先删除所有user下的角色
            userMapper.deleteUserRole(userData.getId());
            if (user.getRoles() != null && user.getRoles().size() > 0) {
                //循环角色列表
                for (Long role : user.getRoles()) {
                    //将角色数据塞进去
                    userMapper.insertUserRole(userData.getId(), role);
                }
            }

            //先删除所有user下的角色
            userMapper.deleteUserPermission(userData.getId());
            if (user.getPermissions() != null && user.getPermissions().size() > 0) {
                //循环角色列表
                for (Long permission : user.getPermissions()) {
                    //将角色数据塞进去
                    userMapper.insertUserPermission(userData.getId(), permission);
                }
            }
            return Result.success(userData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param user 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<UserEntity>> userSaveList(List<UserSaveDTO> user) {
        List<UserEntity> userData = UserConvert.INSTANCE.saveList(user);
        Result<List<UserEntity>> valid = userMapper.onlyValidList(userData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(userData);
            return Result.success(userData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,user 入参
     */
    @Override
    public void userExport(HttpServletResponse response, UserQueryDTO user) throws IOException {
        ExcelUtils.export(response, "用户.xlsx", "用户", UserExportVO.class, UserConvert.INSTANCE.export(userMapper.queryList(user)));
    }

    /**
     * 登录接口
     *
     * @param loginInfo 登录用户信息
     * @return 用户信息
     */
    @Override
    public Result<UserInfoVO> login(LoginDTO loginInfo) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();

        wrapper.eq("account", loginInfo.getAccount()).and(i -> i.eq("password", loginInfo.getPassword()));

        //查询数据
        UserEntity userInfo = userMapper.selectOne(wrapper);

        //登录成功
        if (userInfo != null) {
            //1.生成Token
            String Token = new JwtUtil().getToken(userInfo);
            //2.记录登录
            UserOnlineEntity userOnline = new OnlineUtil().setUserOnlineInfoSuccess(userInfo, loginInfo);

            UserInfoVO userInfoVO = userInfo(loginInfo.getLoginSystem(), Token).getData();

            userInfoVO.setLoginId(userOnline.getId());

            userInfoVO.setLoginTime(userOnline.getCreateTime());
            //返回用户信息和Token
            return Result.success(userInfoVO);
        } else {
            return Result.fail("登录失败,请检查账号密码是否正确");
        }
    }

    /**
     * 登出接口
     *
     * @param logoutInfo 登出信息
     * @return 登出
     */
    @Override
    public Result<Object> logout(@Valid LogoutDTO logoutInfo) {
        new OnlineUtil().Logout(logoutInfo);

        return Result.success("登出成功");
    }


    /**
     * 通过token获取用户信息
     *
     * @return 用户信息
     */
    @Override
    public Result<UserInfoVO> userInfo(String loginSystem, String token) {
        //1.获取Token中用户id
        Object userId = JwtUtil.getTokenInfo(token);
        //2.通过id找出用户信息
        UserEntity userInfo = userMapper.selectById(userId.toString());
        //3.实例化返回参数
        UserInfoVO userInfoVo = new UserInfoVO();
        //4.注入token
        userInfoVo.setToken(token);
        //5.注入用户基础信息
        userInfoVo.setUserInfo(UserConvert.INSTANCE.UserBaseInfoVO(userInfo));
        //6.通过userId获取角色
        List<RoleEntity> roles = userMapper.selectRole(userInfo.getId());
        userInfoVo.setRoles(roles);
        //7.获取角色对应菜单
        //判断是否有admin角色
        boolean isAdmin = roles.stream().anyMatch(role -> "admin".equals(role.getRoleCode()));
        if (isAdmin) {
            //7.1全部菜单
            List<MenuEntity> menuList = menuMapper.queryList(new MenuQueryDTO());

            userInfoVo.setMenuList(menuList);
        } else {
            //7.2获取多用户拥有的所有菜单
            List<MenuEntity> roleMenu = new ArrayList<>();
            roles.forEach(role -> roleMenu.addAll(userMapper.selectMenu(role.getId())));
            //需要返回的菜单去重
            List<MenuEntity> menuRes = roleMenu.stream()
                    .distinct()
                    .sorted(Comparator.comparing(MenuEntity::getId))
                    .collect(Collectors.toList());

            userInfoVo.setMenuList(menuRes);
        }
        return Result.success(userInfoVo);
    }
}