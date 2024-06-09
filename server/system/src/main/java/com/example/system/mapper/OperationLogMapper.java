package com.example.system.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.operationLog.OperationLogQueryDTO;
import com.example.system.dal.entity.OperationLogEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLogEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<OperationLogEntity> search(OperationLogQueryDTO operationLog) {
        QueryWrapper<OperationLogEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (operationLog.getId() != null) {
            wrapper.eq("id", operationLog.getId());
        }

        /* 标题 */
        if (!StrUtil.hasBlank(operationLog.getTitle())) {
            wrapper.eq("title", operationLog.getTitle());
        }
        /* 操作模块 */
        if (!StrUtil.hasBlank(operationLog.getModule())) {
            wrapper.eq("module", operationLog.getModule());
        }
        /* 操作内容 */
        if (!StrUtil.hasBlank(operationLog.getContent())) {
            wrapper.eq("content", operationLog.getContent());
        }
        /* 操作类型 */
        if (!StrUtil.hasBlank(operationLog.getType())) {
            wrapper.eq("type", operationLog.getType());
        }
        /* 操作IP */
        if (!StrUtil.hasBlank(operationLog.getOperateIp())) {
            wrapper.eq("operate_ip", operationLog.getOperateIp());
        }
        /* 操作地 */
        if (!StrUtil.hasBlank(operationLog.getOperatePlace())) {
            wrapper.eq("operate_place", operationLog.getOperatePlace());
        }
        /* 错误信息 */
        if (!StrUtil.hasBlank(operationLog.getErrorMsg())) {
            wrapper.eq("error_msg", operationLog.getErrorMsg());
        }
        /* 创建时间 */
        if (operationLog.getCreateTime() != null) {
            wrapper.like("create_time", operationLog.getCreateTime());
        }
        /* 更新时间 */
        if (operationLog.getUpdateTime() != null) {
            wrapper.eq("update_time", operationLog.getUpdateTime());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<OperationLogEntity>> onlyValidList(List<OperationLogEntity> operationLogData) {
        List<OperationLogEntity> operationLogList = queryList(new OperationLogQueryDTO());
        for (OperationLogEntity item : operationLogData) {
            Result<OperationLogEntity> operationLog = onlyValid(item, operationLogList);
            if (operationLog.getCode() != 200) {
                return Result.fail(operationLog.getMessage());
            }
        }
        return Result.success(operationLogList);
    }

    /**
     * 唯一性校验
     */
    default Result<OperationLogEntity> onlyValid(OperationLogEntity operationLog, List<OperationLogEntity> operationLogList) {

        for (OperationLogEntity item : operationLogList) {
            //修改跳过自己
            if (operationLog.getId() != null && item.getId().equals(operationLog.getId())) {
                continue;
            }
        }

        return Result.success(operationLog);
    }

    /**
     * 分页查询
     */
    default PageList<OperationLogEntity> queryPage(OperationLogQueryDTO operationLog) {
        IPage<OperationLogEntity> pageParams = new Page<>(operationLog.getPage(), operationLog.getSize());
        return PageList.setPages(selectPage(pageParams, search(operationLog)));
    }

    /**
     * 列表查询
     */
    default List<OperationLogEntity> queryList(OperationLogQueryDTO operationLog) {
        return selectList(search(operationLog));
    }
}