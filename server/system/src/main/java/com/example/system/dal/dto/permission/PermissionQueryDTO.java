package com.example.system.dal.dto.permission;

import com.example.framework.dal.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "查询")
@EqualsAndHashCode(callSuper = true)
public class PermissionQueryDTO extends PageDTO {
    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;
    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String permission;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
}