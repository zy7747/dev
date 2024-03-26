package com.example.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.UserConvert;
import com.example.system.dal.dto.user.LoginDTO;
import com.example.system.dal.dto.user.UserQueryDTO;
import com.example.system.dal.dto.user.UserSaveDTO;
import com.example.system.dal.entity.MenuEntity;
import com.example.system.dal.entity.RoleEntity;
import com.example.system.dal.entity.UserEntity;
import com.example.system.dal.entity.UserRoleEntity;
import com.example.system.dal.vo.user.*;
import com.example.system.mapper.MenuMapper;
import com.example.system.mapper.RoleMapper;
import com.example.system.mapper.UserMapper;
import com.example.system.service.UserService;
import com.example.system.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

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

        userMapper.selectUserRole(id).forEach(item -> roles.add(item.getRoleId()));

        UserDetailVO user = UserConvert.INSTANCE.detail(userMapper.selectById(id));
        user.setRoles(roles);

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

            if (user.getRoles() != null && user.getRoles().size() > 0) {
                //先删除所有user下的角色
                userMapper.deleteUserRole(userData.getId());
                //循环角色列表
                for (Long role : user.getRoles()) {
                    //将角色数据塞进去
                    userMapper.insertUserRole(userData.getId(), role);
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
            //返回用户信息和Token
            return userInfo(loginInfo.getLoginSystem(), Token);
        } else {
            return Result.fail("登录失败,请检查账号密码是否正确");
        }
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
        //6.获取角色
        List<RoleEntity> roles = userMapper.selectRole(userInfo.getId());
        userInfoVo.setRoles(roles);
        //7.获取角色对应菜单
        roles.forEach(role -> {
            List<MenuEntity> menuList = new ArrayList<>();

            List<MenuEntity> menus = userMapper.selectMenu(role.getId());
            
            userInfoVo.setMenuList(menus);
        });
        return Result.success(userInfoVo);
    }
}