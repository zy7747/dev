package com.example.job.dal.vo.job;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.framework.dal.vo.ExcelVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ApiModel(value = "导出")
@EqualsAndHashCode(callSuper = true)
public class JobExportVO extends ExcelVO {
    /**
     * 任务名称
     */
    @ExcelProperty("任务名称")
    private String jobName;
    /**
     * 任务编码
     */
    @ExcelProperty("任务编码")
    private String jobCode;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ExcelProperty("开始时间")
    private Date startTime;
    /**
     * 执行次数
     */
    @ExcelProperty("执行次数")
    private Integer executeCount;
    /**
     * 执行间隔
     */
    @ExcelProperty("执行间隔")
    private Long executeInterval;
    /**
     * cron表达式
     */
    @ExcelProperty("cron表达式")
    private String cron;
}