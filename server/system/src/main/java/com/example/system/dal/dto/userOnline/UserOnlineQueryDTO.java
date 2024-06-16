package com.example.system.dal.dto.userOnline;

import com.example.framework.dal.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "查询")
@EqualsAndHashCode(callSuper = true)
public class UserOnlineQueryDTO extends PageDTO {
    /**
     * 用户id
     */
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