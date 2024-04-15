package com.example.system.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.annotation.log.Log;
import com.example.system.convert.PermissionConvert;
import com.example.system.dal.dto.permission.PermissionQueryDTO;
import com.example.system.dal.dto.permission.PermissionSaveDTO;
import com.example.system.dal.entity.PermissionEntity;
import com.example.system.dal.vo.permission.PermissionDetailVO;
import com.example.system.dal.vo.permission.PermissionExportVO;
import com.example.system.dal.vo.permission.PermissionListVO;
import com.example.system.dal.vo.permission.PermissionPageVO;
import com.example.system.enums.OperateType;
import com.example.system.mapper.PermissionMapper;
import com.example.system.service.PermissionService;
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
@Api(tags = "权限")
@CrossOrigin
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    PermissionService permissionService;
    @Resource
    PermissionMapper permissionMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<PermissionPageVO>> permissionPage(@Valid PermissionQueryDTO permission) {
        return permissionService.permissionPage(permission);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<PermissionListVO>> permissionList(@Valid PermissionQueryDTO permission) {
        return permissionService.permissionList(permission);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<PermissionDetailVO> permissionDetail(Long id) {
        return permissionService.permissionDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('permission:change')")
    @Log(title = "权限新增/修改", module = "权限模块", content = "权限新增/修改", type = OperateType.INSERT)
    public Result<PermissionEntity> permissionSave(@RequestBody @Valid PermissionSaveDTO permission) {
        return permissionService.permissionSave(permission);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('permission:change')")
    @Log(title = "权限新增/修改", module = "权限模块", content = "权限新增/修改", type = OperateType.INSERT)
    public Result<List<PermissionEntity>> permissionSaveList(@RequestBody @Valid List<PermissionSaveDTO> permissionList) {
        return permissionService.permissionSaveList(permissionList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('permission:remove')")
    @Log(title = "权限删除", module = "权限模块", content = "权限删除", type = OperateType.DELETE)
    public Result<Object> permissionDelete(@RequestBody List<Long> ids) {
        permissionMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('permission:change')")
    @Log(title = "权限导入", module = "权限模块", content = "权限导入", type = OperateType.IMPORT)
    public Result<List<PermissionEntity>> permissionImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<PermissionSaveDTO> permissionList = PermissionConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), PermissionExportVO.class));
        return permissionService.permissionSaveList(permissionList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void permissionExport(HttpServletResponse response, PermissionQueryDTO permission) throws IOException {
        permissionService.permissionExport(response, permission);
    }
}