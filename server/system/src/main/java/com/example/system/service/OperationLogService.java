package com.example.system.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.operationLog.OperationLogQueryDTO;
import com.example.system.dal.dto.operationLog.OperationLogSaveDTO;
import com.example.system.dal.entity.OperationLogEntity;
import com.example.system.dal.vo.operationLog.OperationLogDetailVO;
import com.example.system.dal.vo.operationLog.OperationLogListVO;
import com.example.system.dal.vo.operationLog.OperationLogPageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface OperationLogService {
    /**
     * 获取列表分页
     *
     * @param operationLog 分页入参
     * @return 列表分页
     */
    Result<PageList<OperationLogPageVO>> operationLogPage(OperationLogQueryDTO operationLog);

    /**
     * 获取列表
     *
     * @param operationLog 入参
     * @return 列表
     */
    Result<List<OperationLogListVO>> operationLogList(OperationLogQueryDTO operationLog);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<OperationLogDetailVO> operationLogDetail(Long id);

    /**
     * 新增/修改
     *
     * @param operationLog 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<OperationLogEntity> operationLogSave(OperationLogSaveDTO operationLog);

    /**
     * 批量新增/修改
     *
     * @param operationLog 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<OperationLogEntity>> operationLogSaveList(@Valid List<OperationLogSaveDTO> operationLog);

    /**
     * 导出
     *
     * @param response,operationLog 入参
     */
    void operationLogExport(HttpServletResponse response, OperationLogQueryDTO operationLog) throws IOException;
}