package com.example.system.dal.dto.page;

import com.example.framework.dal.dto.BaseParamsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "保存")
@EqualsAndHashCode(callSuper = true)
public class PageSaveDTO extends BaseParamsDTO {
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