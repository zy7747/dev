package com.example.files.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.files.convert.FileConvert;
import com.example.files.dal.dto.file.FileQueryDTO;
import com.example.files.dal.dto.file.FileSaveDTO;
import com.example.files.dal.entity.FileEntity;
import com.example.files.dal.vo.file.FileDetailVO;
import com.example.files.dal.vo.file.FileExportVO;
import com.example.files.dal.vo.file.FileListVO;
import com.example.files.dal.vo.file.FilePageVO;
import com.example.files.mapper.FileMapper;
import com.example.files.service.FileService;
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
@Api(tags = "文件")
@CrossOrigin
@RequestMapping("/file")
public class FileController {
    @Resource
    FileService fileService;
    @Resource
    FileMapper fileMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<FilePageVO>> filePage(@Valid FileQueryDTO file) {
        return fileService.filePage(file);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<FileListVO>> fileList(@Valid FileQueryDTO file) {
        return fileService.fileList(file);
    }

    @GetMapping("/fileDetailList")
    @ApiOperation(value = "列表")
    public Result<List<FileListVO>> fileDetailList(@Valid FileQueryDTO file) {
        return fileService.fileDetailList(file);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<FileDetailVO> fileDetail(Long id) {
        return fileService.fileDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    public Result<FileEntity> fileSave(@RequestBody @Valid FileSaveDTO file) {
        return fileService.fileSave(file);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    public Result<List<FileEntity>> fileSaveList(@RequestBody @Valid List<FileSaveDTO> fileList) {
        return fileService.fileSaveList(fileList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public Result<Object> fileDelete(@RequestBody List<Long> ids) {
        fileMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    public Result<List<FileEntity>> fileImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<FileSaveDTO> fileList = FileConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), FileExportVO.class));
        return fileService.fileSaveList(fileList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void fileExport(HttpServletResponse response, FileQueryDTO file) throws IOException {
        fileService.fileExport(response, file);
    }
}