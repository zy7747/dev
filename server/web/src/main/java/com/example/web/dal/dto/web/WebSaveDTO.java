package com.example.web.dal.dto.web;

import com.example.framework.dal.dto.BaseParamsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "保存")
@EqualsAndHashCode(callSuper = true)
public class WebSaveDTO extends BaseParamsDTO {
    /**
     * 网站名称
     */
    @ApiModelProperty(value = "网站名称")
    private String webName;
    /**
     * 网址
     */
    @ApiModelProperty(value = "网址")
    private String url;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /**
     * 分类
     */
    @ApiModelProperty(value = "分类")
    private String type;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;
    /**
     * 网站介绍
     */
    @ApiModelProperty(value = "网站介绍")
    private String introduction;
}