package com.example.system.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.annotation.Log;
import com.example.system.convert.PageConvert;
import com.example.system.dal.dto.page.PageQueryDTO;
import com.example.system.dal.dto.page.PageSaveDTO;
import com.example.system.dal.entity.PageEntity;
import com.example.system.dal.vo.page.PageDetailVO;
import com.example.system.dal.vo.page.PageExportVO;
import com.example.system.dal.vo.page.PageListVO;
import com.example.system.dal.vo.page.PagePageVO;
import com.example.system.enums.OperateType;
import com.example.system.mapper.PageMapper;
import com.example.system.service.PageService;
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
@Api(tags = "配置页面")
@CrossOrigin
@RequestMapping("/page")
public class PageController {
    @Resource
    PageService pageService;
    @Resource
    PageMapper pageMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<PagePageVO>> pagePage(@Valid PageQueryDTO page) {
        return pageService.pagePage(page);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<PageListVO>> pageList(@Valid PageQueryDTO page) {
        return pageService.pageList(page);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<PageDetailVO> pageDetail(Long id) {
        return pageService.pageDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('page:change')")
    @Log(title = "配置页面新增/修改", module = "配置页面模块", content = "配置页面新增/修改", type = OperateType.INSERT)
    public Result<PageEntity> pageSave(@RequestBody @Valid PageSaveDTO page) {
        return pageService.pageSave(page);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('page:change')")
    @Log(title = "配置页面新增/修改", module = "配置页面模块", content = "配置页面新增/修改", type = OperateType.INSERT)
    public Result<List<PageEntity>> pageSaveList(@RequestBody @Valid List<PageSaveDTO> pageList) {
        return pageService.pageSaveList(pageList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('page:remove')")
    @Log(title = "配置页面删除", module = "配置页面模块", content = "配置页面删除", type = OperateType.DELETE)
    public Result<Object> pageDelete(@RequestBody List<String> ids) {
        pageMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('page:change')")
    @Log(title = "配置页面导入", module = "配置页面模块", content = "配置页面导入", type = OperateType.IMPORT)
    public Result<List<PageEntity>> pageImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<PageSaveDTO> pageList = PageConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), PageExportVO.class));
        return pageService.pageSaveList(pageList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void pageExport(HttpServletResponse response, PageQueryDTO page) throws IOException {
        pageService.pageExport(response, page);
    }
}