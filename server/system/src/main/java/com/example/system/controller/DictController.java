package com.example.system.controller;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.DictConvert;
import com.example.system.dal.dto.dict.DictQueryDTO;
import com.example.system.dal.dto.dict.DictSaveDTO;
import com.example.system.dal.entity.DictEntity;
import com.example.system.dal.vo.dict.DictDetailVO;
import com.example.system.dal.vo.dict.DictExportVO;
import com.example.system.dal.vo.dict.DictListVO;
import com.example.system.dal.vo.dict.DictPageVO;
import com.example.system.mapper.DictMapper;
import com.example.system.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
    public Result<DictEntity> dictSave(@RequestBody @Valid DictSaveDTO dict) {
        return dictService.dictSave(dict);
    }
    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    public Result<List<DictEntity>> dictSaveList(@RequestBody @Valid List<DictSaveDTO> dictList) {
        return dictService.dictSaveList(dictList);
    }
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public Result<Object> dictDelete(@RequestBody List<String> ids) {
        dictMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }
    @PostMapping("/import")
    @ApiOperation(value = "导入")
    public Result<List<DictEntity>> dictImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<DictSaveDTO> dictList = DictConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), DictExportVO.class));
        return dictService.dictSaveList(dictList);
    }
    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void dictExport(HttpServletResponse response, DictQueryDTO dict) throws IOException {
        dictService.dictExport(response, dict);
    }
}