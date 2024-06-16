package com.example.system.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.user.LoginDTO;
import com.example.system.dal.dto.user.LogoutDTO;
import com.example.system.dal.dto.user.UserQueryDTO;
import com.example.system.dal.dto.user.UserSaveDTO;
import com.example.system.dal.entity.UserEntity;
import com.example.system.dal.vo.user.UserDetailVO;
import com.example.system.dal.vo.user.UserInfoVO;
import com.example.system.dal.vo.user.UserListVO;
import com.example.system.dal.vo.user.UserPageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface UserService {
    /**
     * 获取列表分页
     *
     * @param user 分页入参
     * @return 列表分页
     */
    Result<PageList<UserPageVO>> userPage(UserQueryDTO user);

    /**
     * 获取列表
     *
     * @param user 入参
     * @return 列表
     */
    Result<List<UserListVO>> userList(UserQueryDTO user);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<UserDetailVO> userDetail(Long id);

    /**
     * 新增/修改
     *
     * @param user 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<UserEntity> userSave(UserSaveDTO user);

    /**
     * 批量新增/修改
     *
     * @param user 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<UserEntity>> userSaveList(@Valid List<UserSaveDTO> user);


    /**
     * 导出
     *
     * @param response,user 入参
     */
    void userExport(HttpServletResponse response, UserQueryDTO user) throws IOException;

    /**
     * 登录接口
     *
     * @param loginInfo 登录用户信息
     * @return 用户信息
     */
    Result<UserInfoVO> login(LoginDTO loginInfo);

    /**
     * 登出接口
     *
     * @param logoutInfo 登出信息
     * @return 登出
     */
    Result<Object> logout(@Valid LogoutDTO logoutInfo);

    /**
     * 通过token获取用户信息
     *
     * @param loginSystem,token 获取用户信息
     * @return 用户信息
     */
    Result<UserInfoVO> userInfo(String loginSystem, String token);
}