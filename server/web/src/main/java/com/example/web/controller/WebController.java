package com.example.web.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.annotation.log.Log;
import com.example.system.enums.OperateType;
import com.example.web.convert.WebConvert;
import com.example.web.dal.dto.web.WebQueryDTO;
import com.example.web.dal.dto.web.WebSaveDTO;
import com.example.web.dal.entity.WebEntity;
import com.example.web.dal.vo.web.WebDetailVO;
import com.example.web.dal.vo.web.WebExportVO;
import com.example.web.dal.vo.web.WebListVO;
import com.example.web.dal.vo.web.WebPageVO;
import com.example.web.mapper.WebMapper;
import com.example.web.service.WebService;
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
@Api(tags = "网址收藏")
@CrossOrigin
@RequestMapping("/web")
public class WebController {
    @Resource
    WebService webService;
    @Resource
    WebMapper webMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<WebPageVO>> webPage(@Valid WebQueryDTO web) {
        return webService.webPage(web);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<WebListVO>> webList(@Valid WebQueryDTO web) {
        return webService.webList(web);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<WebDetailVO> webDetail(Long id) {
        return webService.webDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('web:change')")
    @Log(title = "网址收藏新增/修改", module = "网址收藏模块", content = "网址收藏新增/修改", type = OperateType.INSERT)
    public Result<WebEntity> webSave(@RequestBody @Valid WebSaveDTO web) {
        return webService.webSave(web);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('web:change')")
    @Log(title = "网址收藏新增/修改", module = "网址收藏模块", content = "网址收藏新增/修改", type = OperateType.INSERT)
    public Result<List<WebEntity>> webSaveList(@RequestBody @Valid List<WebSaveDTO> webList) {
        return webService.webSaveList(webList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('web:remove')")
    @Log(title = "网址收藏删除", module = "网址收藏模块", content = "网址收藏删除", type = OperateType.DELETE)
    public Result<Object> webDelete(@RequestBody List<Long> ids) {
        webMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('web:change')")
    @Log(title = "网址收藏导入", module = "网址收藏模块", content = "网址收藏导入", type = OperateType.IMPORT)
    public Result<List<WebEntity>> webImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<WebSaveDTO> webList = WebConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), WebExportVO.class));
        return webService.webSaveList(webList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void webExport(HttpServletResponse response, WebQueryDTO web) throws IOException {
        webService.webExport(response, web);
    }
}