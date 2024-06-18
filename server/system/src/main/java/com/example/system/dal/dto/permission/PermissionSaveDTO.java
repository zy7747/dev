package com.example.system.dal.dto.permission;

import com.example.framework.dal.dto.BaseParamsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "保存")
@EqualsAndHashCode(callSuper = true)
public class PermissionSaveDTO extends BaseParamsDTO {
    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空")
    @ApiModelProperty(value = "权限名称")
    private String name;
    /**
     * 权限标识
     */
    @NotBlank(message = "权限标识不能为空")
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