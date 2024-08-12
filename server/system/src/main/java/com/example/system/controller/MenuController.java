package com.example.system.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.annotation.dict.DictUtil;
import com.example.system.annotation.log.Log;
import com.example.system.convert.MenuConvert;
import com.example.system.dal.dto.menu.MenuQueryDTO;
import com.example.system.dal.dto.menu.MenuSaveDTO;
import com.example.system.dal.entity.MenuEntity;
import com.example.system.dal.vo.menu.MenuDetailVO;
import com.example.system.dal.vo.menu.MenuExportVO;
import com.example.system.dal.vo.menu.MenuListVO;
import com.example.system.dal.vo.menu.MenuPageVO;
import com.example.system.enums.OperateType;
import com.example.system.mapper.MenuMapper;
import com.example.system.service.MenuService;
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
@Api(tags = "菜单")
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {
    @Resource
    MenuService menuService;
    @Resource
    MenuMapper menuMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<MenuPageVO>> menuPage(@Valid MenuQueryDTO menu) {
        return menuService.menuPage(menu);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    @DictUtil(type = "list")
    public Result<List<MenuListVO>> menuList(@Valid MenuQueryDTO menu) {
        return menuService.menuList(menu);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<MenuDetailVO> menuDetail(Long id) {
        return menuService.menuDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('menu:change')")
    @Log(title = "菜单新增/修改", module = "菜单模块", content = "菜单新增/修改", type = OperateType.INSERT)
    public Result<MenuEntity> menuSave(@RequestBody @Valid MenuSaveDTO menu) {
        return menuService.menuSave(menu);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('menu:change')")
    @Log(title = "菜单新增/修改", module = "菜单模块", content = "菜单新增/修改", type = OperateType.INSERT)
    public Result<List<MenuEntity>> menuSaveList(@RequestBody @Valid List<MenuSaveDTO> menuList) {
        return menuService.menuSaveList(menuList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('menu:remove')")
    @Log(title = "菜单新增/修改", module = "菜单模块", content = "菜单新增/修改", type = OperateType.INSERT)
    public Result<Object> menuDelete(@RequestBody List<Long> ids) {
        menuMapper.deleteBatchIds(ids);
        ids.forEach(id -> menuMapper.deleteRoleMenu(id));
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('menu:change')")
    @Log(title = "菜单导入", module = "菜单模块", content = "菜单导入", type = OperateType.IMPORT)
    public Result<List<MenuEntity>> menuImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<MenuSaveDTO> menuList = MenuConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), MenuExportVO.class));
        return menuService.menuSaveList(menuList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void menuExport(HttpServletResponse response, MenuQueryDTO menu) throws IOException {
        menuService.menuExport(response, menu);
    }
}