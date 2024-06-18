package com.example.system.dal.dto.role;

import com.example.framework.dal.dto.BaseParamsDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Data
@ApiModel(value = "保存")
@EqualsAndHashCode(callSuper = true)
public class RoleSaveDTO extends BaseParamsDTO {
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    @ApiModelProperty(value = "角色编码")
    private String roleCode;
    /**
     * 菜单
     */
    @ApiModelProperty(value = "菜单")
    private ArrayList<Long> menus;
}