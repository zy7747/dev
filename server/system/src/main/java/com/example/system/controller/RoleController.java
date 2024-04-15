package com.example.system.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.annotation.log.Log;
import com.example.system.convert.RoleConvert;
import com.example.system.dal.dto.role.RoleQueryDTO;
import com.example.system.dal.dto.role.RoleSaveDTO;
import com.example.system.dal.entity.RoleEntity;
import com.example.system.dal.vo.role.RoleDetailVO;
import com.example.system.dal.vo.role.RoleExportVO;
import com.example.system.dal.vo.role.RoleListVO;
import com.example.system.dal.vo.role.RolePageVO;
import com.example.system.enums.OperateType;
import com.example.system.mapper.RoleMapper;
import com.example.system.service.RoleService;
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
@Api(tags = "角色")
@CrossOrigin
@RequestMapping("/role")
public class RoleController {
    @Resource
    RoleService roleService;
    @Resource
    RoleMapper roleMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<RolePageVO>> rolePage(@Valid RoleQueryDTO role) {
        return roleService.rolePage(role);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<RoleListVO>> roleList(@Valid RoleQueryDTO role) {
        return roleService.roleList(role);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<RoleDetailVO> roleDetail(Long id) {
        return roleService.roleDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('role:change')")
    @Log(title = "角色新增/修改", module = "角色模块", content = "角色新增/修改", type = OperateType.INSERT)
    public Result<RoleEntity> roleSave(@RequestBody @Valid RoleSaveDTO role) {
        return roleService.roleSave(role);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('role:change')")
    @Log(title = "角色新增/修改", module = "角色模块", content = "角色新增/修改", type = OperateType.INSERT)
    public Result<List<RoleEntity>> roleSaveList(@RequestBody @Valid List<RoleSaveDTO> roleList) {
        return roleService.roleSaveList(roleList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('role:remove')")
    @Log(title = "角色删除", module = "角色模块", content = "角色删除", type = OperateType.DELETE)
    public Result<Object> roleDelete(@RequestBody List<Long> ids) {
        roleMapper.deleteBatchIds(ids);
        ids.forEach(id -> {
            roleMapper.deleteRoleMenu(id);
            roleMapper.deleteUserRole(id);
        });
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('role:change')")
    @Log(title = "角色导入", module = "角色模块", content = "角色导入", type = OperateType.IMPORT)
    public Result<List<RoleEntity>> roleImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<RoleSaveDTO> roleList = RoleConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), RoleExportVO.class));
        return roleService.roleSaveList(roleList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void roleExport(HttpServletResponse response, RoleQueryDTO role) throws IOException {
        roleService.roleExport(response, role);
    }
}