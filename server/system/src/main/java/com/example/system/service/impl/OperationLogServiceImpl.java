package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.OperationLogConvert;
import com.example.system.dal.dto.operationLog.OperationLogQueryDTO;
import com.example.system.dal.dto.operationLog.OperationLogSaveDTO;
import com.example.system.dal.entity.OperationLogEntity;
import com.example.system.dal.vo.operationLog.OperationLogExportVO;
import com.example.system.mapper.OperationLogMapper;
import com.example.system.dal.vo.operationLog.OperationLogDetailVO;
import com.example.system.dal.vo.operationLog.OperationLogListVO;
import com.example.system.dal.vo.operationLog.OperationLogPageVO;
import com.example.system.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLogEntity> implements OperationLogService {
    @Resource
    OperationLogMapper operationLogMapper;

    /**
     * 获取列表分页
     *
     * @param operationLog 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<OperationLogPageVO>> operationLogPage(OperationLogQueryDTO operationLog) {
        return Result.success(OperationLogConvert.INSTANCE.page(operationLogMapper.queryPage(operationLog)));
    }

    /**
     * 获取列表
     *
     * @param operationLog 入参
     * @return 列表
     */
    @Override
    public Result<List<OperationLogListVO>> operationLogList(OperationLogQueryDTO operationLog) {
        return Result.success(OperationLogConvert.INSTANCE.list(operationLogMapper.queryList(operationLog)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<OperationLogDetailVO> operationLogDetail(Long id) {
        return Result.success(OperationLogConvert.INSTANCE.detail(operationLogMapper.selectById(id)));
    }

    /**
     * 新增/修改
     *
     * @param operationLog 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<OperationLogEntity> operationLogSave(OperationLogSaveDTO operationLog) {
        OperationLogEntity operationLogData = OperationLogConvert.INSTANCE.save(operationLog);
        List<OperationLogEntity> operationLogList = operationLogMapper.queryList(new OperationLogQueryDTO());
        Result<OperationLogEntity> valid = operationLogMapper.onlyValid(operationLogData, operationLogList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(operationLogData);
            return Result.success(operationLogData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param operationLog 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<OperationLogEntity>> operationLogSaveList(List<OperationLogSaveDTO> operationLog) {
        List<OperationLogEntity> operationLogData = OperationLogConvert.INSTANCE.saveList(operationLog);
        Result<List<OperationLogEntity>> valid = operationLogMapper.onlyValidList(operationLogData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(operationLogData);
            return Result.success(operationLogData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,operationLog 入参
     */
    @Override
    public void operationLogExport(HttpServletResponse response, OperationLogQueryDTO operationLog) throws IOException {
        ExcelUtils.export(response, "操作日志.xlsx", "操作日志", OperationLogExportVO.class, OperationLogConvert.INSTANCE.export(operationLogMapper.queryList(operationLog)));
    }
}