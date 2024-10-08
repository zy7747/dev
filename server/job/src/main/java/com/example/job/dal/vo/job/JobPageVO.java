package com.example.job.dal.vo.job;

import com.example.framework.dal.vo.BaseParamsVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ApiModel(value = "分页查询结果")
@EqualsAndHashCode(callSuper = true)
public class JobPageVO extends BaseParamsVO {
    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String jobName;
    /**
     * 任务编码
     */
    @ApiModelProperty(value = "任务编码")
    private String jobCode;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    /**
     * 执行次数
     */
    @ApiModelProperty(value = "执行次数")
    private Integer executeCount;
    /**
     * 执行间隔
     */
    @ApiModelProperty(value = "执行间隔")
    private Long executeInterval;
    /**
     * cron表达式
     */
    @ApiModelProperty(value = "cron表达式")
    private String cron;
}