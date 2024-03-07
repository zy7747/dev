package com.example.system.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.user.UserQueryDTO;
import com.example.system.dal.dto.user.UserSaveDTO;
import com.example.system.dal.entity.UserEntity;
import com.example.system.dal.vo.user.UserDetailVO;
import com.example.system.dal.vo.user.UserListVO;
import com.example.system.dal.vo.user.UserPageVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {
    /**
     * 获取列表分页
     *
     * @param user 分页入参
     * @return 列表分页
     */
    Result<PageList<UserPageVO>> userPageService(UserQueryDTO user);

    /**
     * 获取列表
     *
     * @param user 入参
     * @return 列表
     */
    Result<List<UserListVO>> userListService(UserQueryDTO user);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<UserDetailVO> userDetailService(Long id);

    /**
     * 新增/修改
     *
     * @param user 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<UserEntity> userSaveService(UserSaveDTO user);

    /**
     * 批量新增/修改
     *
     * @param user 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<UserEntity>> userSaveListService(List<UserSaveDTO> user);

    /**
     * 导入
     *
     * @param multipartFile 入参
     * @return 新增/修改后数据
     */
    Result<List<UserEntity>> userImportService(MultipartFile multipartFile) throws IOException;

    /**
     * 导出
     *
     * @param response,user 入参
     */
    void userExportService(UserQueryDTO user, HttpServletResponse response) throws IOException;
}