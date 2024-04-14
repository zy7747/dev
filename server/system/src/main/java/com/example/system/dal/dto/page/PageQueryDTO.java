package com.example.system.dal.dto.page;

import com.example.framework.dal.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "查询")
@EqualsAndHashCode(callSuper = true)
public class PageQueryDTO extends PageDTO {
    /**
     * 页面id
     */
    @ApiModelProperty(value = "页面id")
    private Long menuId;
    /**
     * 页面配置
     */
    @ApiModelProperty(value = "页面配置")
    private String options;
}