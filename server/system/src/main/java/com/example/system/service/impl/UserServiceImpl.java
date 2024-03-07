package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.UserConvert;
import com.example.system.dal.dto.user.UserQueryDTO;
import com.example.system.dal.dto.user.UserSaveDTO;
import com.example.system.dal.entity.UserEntity;
import com.example.system.dal.vo.user.UserExportVO;
import com.example.system.mapper.UserMapper;
import com.example.system.dal.vo.user.UserDetailVO;
import com.example.system.dal.vo.user.UserListVO;
import com.example.system.dal.vo.user.UserPageVO;
import com.example.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Resource
    UserMapper userMapper;

    /**
     * 获取列表分页
     *
     * @param user 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<UserPageVO>> userPageService(UserQueryDTO user) {
        return Result.success(UserConvert.INSTANCE.page(userMapper.queryPage(user)));
    }

    /**
     * 获取列表
     *
     * @param user 入参
     * @return 列表
     */
    @Override
    public Result<List<UserListVO>> userListService(UserQueryDTO user) {
        return Result.success(UserConvert.INSTANCE.list(userMapper.queryList(user)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<UserDetailVO> userDetailService(Long id) {
        return Result.success(UserConvert.INSTANCE.detail(userMapper.selectById(id)));
    }

    /**
     * 新增/修改
     *
     * @param user 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<UserEntity> userSaveService(UserSaveDTO user) {
        UserEntity userList = UserConvert.INSTANCE.save(user);
        this.saveOrUpdate(userList);
        return Result.success(userList);
    }

    /**
     * 批量新增/修改
     *
     * @param user 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<UserEntity>> userSaveListService(List<UserSaveDTO> user) {
        List<UserEntity> userList = UserConvert.INSTANCE.saveList(user);
        this.saveOrUpdateBatch(userList);
        return Result.success(userList);
    }

    /**
     * 导入
     *
     * @param multipartFile 入参
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<UserEntity>> userImportService(MultipartFile multipartFile) throws IOException {
        List<UserEntity> userList = UserConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), UserExportVO.class));
        this.saveOrUpdateBatch(userList);
        return Result.success(userList);
    }

    /**
     * 导出
     *
     * @param response,user 入参
     */
    @Override
    public void userExportService(UserQueryDTO user, HttpServletResponse response) throws IOException {
        ExcelUtils.export(response, "用户.xlsx", "用户", UserExportVO.class, UserConvert.INSTANCE.export(userMapper.queryList(user)));
    }
}