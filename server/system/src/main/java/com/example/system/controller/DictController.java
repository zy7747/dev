package com.example.system.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.annotation.log.Log;
import com.example.system.convert.DictConvert;
import com.example.system.dal.dto.dict.DictQueryDTO;
import com.example.system.dal.dto.dict.DictSaveDTO;
import com.example.system.dal.entity.DictEntity;
import com.example.system.dal.vo.dict.DictDetailVO;
import com.example.system.dal.vo.dict.DictExportVO;
import com.example.system.dal.vo.dict.DictListVO;
import com.example.system.dal.vo.dict.DictPageVO;
import com.example.system.enums.OperateType;
import com.example.system.mapper.DictMapper;
import com.example.system.service.DictService;
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
import java.util.stream.Collectors;

@Slf4j
@RestController
@Validated
@Api(tags = "字典")
@CrossOrigin
@RequestMapping("/dict")
public class DictController {
    @Resource
    DictService dictService;
    @Resource
    DictMapper dictMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<DictPageVO>> dictPage(@Valid DictQueryDTO dict) {
        return dictService.dictPage(dict);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<DictListVO>> dictList(@Valid DictQueryDTO dict) {
        return dictService.dictList(dict);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<DictDetailVO> dictDetail(Long id) {
        return dictService.dictDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('dict:change')")
    @Log(title = "字典新增/修改", module = "字典模块", content = "字典新增/修改", type = OperateType.INSERT)
    public Result<DictEntity> dictSave(@RequestBody @Valid DictSaveDTO dict) {
        return dictService.dictSave(dict);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('dict:change')")
    @Log(title = "字典新增/修改", module = "字典模块", content = "字典新增/修改", type = OperateType.INSERT)
    public Result<List<DictEntity>> dictSaveList(@RequestBody @Valid List<DictSaveDTO> dictList) {
        return dictService.dictSaveList(dictList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('dict:remove')")
    @Log(title = "字典删除", module = "字典模块", content = "字典删除", type = OperateType.DELETE)
    public Result<Object> dictDelete(@RequestBody List<Long> ids) {
        //查找子集并删除
        ids.forEach((id) -> {
            DictQueryDTO dict = new DictQueryDTO();
            dict.setParentId(id);
            List<DictEntity> dictList = dictMapper.getSubList(dict);

            if (dictList.size() > 0) {
                List<Long> idList = dictList.stream().map(DictEntity::getId).collect(Collectors.toList());
                dictMapper.deleteBatchIds(idList);
            }
        });
        dictMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('dict:change')")
    @Log(title = "字典导入", module = "字典模块", content = "字典导入", type = OperateType.IMPORT)
    public Result<List<DictEntity>> dictImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<DictSaveDTO> dictList = DictConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), DictExportVO.class));
        return dictService.dictSaveList(dictList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void dictExport(HttpServletResponse response, DictQueryDTO dict) throws IOException {
        dictService.dictExport(response, dict);
    }

    @GetMapping("/subList")
    @ApiOperation(value = "获取子列表")
    public Result<List<DictEntity>> subList(@Valid DictQueryDTO dict) {
        return dictService.subList(dict);
    }

    @GetMapping("/dictMap")
    @ApiOperation(value = "获取所有字典集")
    public Result<Object> dictMap() {
        return dictService.dictMap();
    }
}