package com.example.system.dal.vo.page;

import com.example.framework.dal.vo.BaseParamsVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "详情")
@EqualsAndHashCode(callSuper = true)
public class PageDetailVO extends BaseParamsVO {
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