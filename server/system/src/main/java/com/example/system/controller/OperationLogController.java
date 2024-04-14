package com.example.system.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.OperationLogConvert;
import com.example.system.dal.dto.operationLog.OperationLogQueryDTO;
import com.example.system.dal.dto.operationLog.OperationLogSaveDTO;
import com.example.system.dal.entity.OperationLogEntity;
import com.example.system.dal.vo.operationLog.OperationLogDetailVO;
import com.example.system.dal.vo.operationLog.OperationLogExportVO;
import com.example.system.dal.vo.operationLog.OperationLogListVO;
import com.example.system.dal.vo.operationLog.OperationLogPageVO;
import com.example.system.mapper.OperationLogMapper;
import com.example.system.service.OperationLogService;
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
@Api(tags = "操作日志")
@CrossOrigin
@RequestMapping("/operationLog")
public class OperationLogController {
    @Resource
    OperationLogService operationLogService;
    @Resource
    OperationLogMapper operationLogMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<OperationLogPageVO>> operationLogPage(@Valid OperationLogQueryDTO operationLog) {
        return operationLogService.operationLogPage(operationLog);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<OperationLogListVO>> operationLogList(@Valid OperationLogQueryDTO operationLog) {
        return operationLogService.operationLogList(operationLog);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<OperationLogDetailVO> operationLogDetail(Long id) {
        return operationLogService.operationLogDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('operationLog:change')")
    public Result<OperationLogEntity> operationLogSave(@RequestBody @Valid OperationLogSaveDTO operationLog) {
        return operationLogService.operationLogSave(operationLog);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('operationLog:change')")
    public Result<List<OperationLogEntity>> operationLogSaveList(@RequestBody @Valid List<OperationLogSaveDTO> operationLogList) {
        return operationLogService.operationLogSaveList(operationLogList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('operationLog:remove')")
    public Result<Object> operationLogDelete(@RequestBody List<Long> ids) {
        operationLogMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('operationLog:change')")
    public Result<List<OperationLogEntity>> operationLogImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<OperationLogSaveDTO> operationLogList = OperationLogConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), OperationLogExportVO.class));
        return operationLogService.operationLogSaveList(operationLogList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void operationLogExport(HttpServletResponse response, OperationLogQueryDTO operationLog) throws IOException {
        operationLogService.operationLogExport(response, operationLog);
    }
}