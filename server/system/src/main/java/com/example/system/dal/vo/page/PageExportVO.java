package com.example.system.dal.vo.page;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.framework.dal.vo.ExcelVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "导出")
@EqualsAndHashCode(callSuper = true)
public class PageExportVO extends ExcelVO {
    /**
     * 页面id
     */
    @ExcelProperty("页面id")
    private Long menuId;
    /**
     * 页面编码
     */
    @ExcelProperty(value = "页面编码")
    private String pageCode;
    /**
     * 页面配置
     */
    @ExcelProperty("页面配置")
    private String options;

}