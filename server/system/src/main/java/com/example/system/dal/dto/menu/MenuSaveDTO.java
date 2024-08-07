package com.example.system.dal.dto.menu;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.framework.dal.dto.BaseParamsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "保存")
@EqualsAndHashCode(callSuper = true)
public class MenuSaveDTO extends BaseParamsDTO {
    @ApiModelProperty(value = "父节点id")
    private Long parentId;
    /**
     * 菜单名
     */
    @NotBlank(message = "菜单名称不能为空")
    @ApiModelProperty(value = "菜单名")
    private String title;
    /**
     * 组件名
     */
    @ApiModelProperty(value = "组件名")
    private String name;
    /**
     * 路由路径
     */
    @ApiModelProperty(value = "路由路径")
    private String path;
    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空")
    @ApiModelProperty(value = "类型")
    private String type;
    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    private String component;
    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String permission;
    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    private Integer level;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 是否可见
     */
    @ApiModelProperty(value = "是否可见")
    private Integer visible;
    /**
     * 是否缓存
     */
    @ApiModelProperty(value = "是否缓存")
    private Integer keepAlive;
    /**
     * 是否总是显示
     */
    @ApiModelProperty(value = "是否总是显示")
    private Integer alwaysShow;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 其他信息
     */
    @ApiModelProperty(value = "其他信息")
    private String meta;
}