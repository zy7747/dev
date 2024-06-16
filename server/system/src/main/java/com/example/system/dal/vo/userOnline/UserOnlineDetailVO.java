package com.example.system.dal.vo.userOnline;

import com.example.framework.dal.vo.BaseParamsVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "详情")
@EqualsAndHashCode(callSuper = true)
public class UserOnlineDetailVO extends BaseParamsVO {
    /**
     * 用户id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)//雪花算法丢失精度问题
    @ApiModelProperty(value = "用户id")
    private Long uid;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String account;
    /**
     * 登录IP
     */
    @ApiModelProperty(value = "登录IP")
    private String loginIp;
    /**
     * 登录地
     */
    @ApiModelProperty(value = "登录地")
    private String loginPlace;
    /**
     * 登录设备
     */
    @ApiModelProperty(value = "登录设备")
    private String loginDevice;
    /**
     * 操作结果
     */
    @ApiModelProperty(value = "操作结果")
    private String operateResults;
}