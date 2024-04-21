package com.example.system.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.framework.dal.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName(value = "page")
@ApiModel(value = "配置页面")
@EqualsAndHashCode(callSuper = true)
public class PageEntity extends BaseEntity {
    /**
     * 页面id
     */
    @ApiModelProperty(value = "页面id")
    private Long menuId;
    /**
     * 页面编码
     */
    @ApiModelProperty(value = "页面编码")
    private String pageCode;
    /**
     * 页面配置
     */
    @ApiModelProperty(value = "页面配置")
    private String options;
}