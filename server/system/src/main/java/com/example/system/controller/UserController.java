package com.example.system.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.annotation.dict.DictUtil;
import com.example.system.annotation.log.Log;
import com.example.system.convert.UserConvert;
import com.example.system.dal.dto.user.LoginDTO;
import com.example.system.dal.dto.user.LogoutDTO;
import com.example.system.dal.dto.user.UserQueryDTO;
import com.example.system.dal.dto.user.UserSaveDTO;
import com.example.system.dal.entity.UserEntity;
import com.example.system.dal.vo.user.*;
import com.example.system.enums.OperateType;
import com.example.system.mapper.UserMapper;
import com.example.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "用户")
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    UserMapper userMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    @DictUtil(type = "page")
    public Result<PageList<UserPageVO>> userPage(@Valid UserQueryDTO user) {
        return userService.userPage(user);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<UserListVO>> userList(@Valid UserQueryDTO user) {
        return userService.userList(user);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<UserDetailVO> userDetail(Long id) {
        return userService.userDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('user:change')")
    @Log(title = "用户新增/修改", module = "用户模块", content = "用户新增/修改", type = OperateType.INSERT)
    public Result<UserEntity> userSave(@RequestBody @Valid UserSaveDTO user) {
        return userService.userSave(user);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('user:change')")
    @Log(title = "用户新增/修改", module = "用户模块", content = "用户新增/修改", type = OperateType.INSERT)
    public Result<List<UserEntity>> userSaveList(@RequestBody @Valid List<UserSaveDTO> userList) {
        return userService.userSaveList(userList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('user:remove')")
    @Log(title = "用户删除", module = "用户模块", content = "用户删除", type = OperateType.DELETE)
    public Result<Object> userDelete(@RequestBody List<Long> ids) {
        userMapper.deleteBatchIds(ids);

        ids.forEach(id -> userMapper.deleteUserRole(id));
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('user:change')")
    @Log(title = "用户导入", module = "用户模块", content = "用户导入", type = OperateType.IMPORT)
    public Result<List<UserEntity>> userImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<UserSaveDTO> userList = UserConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), UserExportVO.class));
        return userService.userSaveList(userList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void userExport(HttpServletResponse response, UserQueryDTO user) throws IOException {
        userService.userExport(response, user);
    }

    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public Result<UserInfoVO> login(@Valid LoginDTO loginInfo) {
        return userService.login(loginInfo);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "登出")
    public Result<Object> logout(@Valid LogoutDTO logoutInfo) {
        return userService.logout(logoutInfo);
    }


    @GetMapping("/userInfo")
    @ApiOperation(value = "获取用户信息")
    public Result<UserInfoVO> userInfo(@RequestHeader("Authorization") String token, String loginSystem) {
        return userService.userInfo(loginSystem, token);
    }
}