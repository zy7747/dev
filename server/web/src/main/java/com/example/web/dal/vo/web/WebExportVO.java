package com.example.web.dal.vo.web;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.framework.dal.vo.ExcelVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "导出")
@EqualsAndHashCode(callSuper = true)
public class WebExportVO extends ExcelVO {
    /**
     * 网站名称
     */
    @ExcelProperty("网站名称")
    private String webName;
    /**
     * 网址
     */
    @ExcelProperty("网址")
    private String url;
    /**
     * 图标
     */
    @ExcelProperty("图标")
    private String icon;
    /**
     * 分类
     */
    @ExcelProperty("分类")
    private String type;
    /**
     * 状态
     */
    @ExcelProperty("状态")
    private String status;
    /**
     * 网站介绍
     */
    @ExcelProperty("网站介绍")
    private String introduction;
}