package com.example.system.dal.vo.dict;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.example.framework.dal.vo.ExcelVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "导出")
@EqualsAndHashCode(callSuper = true)
public class DictExportVO extends ExcelVO {
    /**
     * 父节点id
     */
    @ExcelProperty("父节点id")
    private Long parentId;
    /**
     * 字典名称
     */
    @ColumnWidth(30)
    @ExcelProperty("字典名称")
    private String dictName;
    /**
     * 字典编码
     */
    @ColumnWidth(30)
    @ExcelProperty("字典编码")
    private String dictCode;
    /**
     * 字典类型
     */
    @ExcelProperty("字典类型")
    private String dictType;
    /**
     * 名
     */
    @ExcelProperty("名")
    private String label;
    /**
     * 值
     */
    @ExcelProperty("值")
    private String value;
    /**
     * 自定义颜色
     */
    @ExcelProperty("自定义颜色")
    private String color;
    /**
     * 排序
     */
    @ExcelProperty("排序")
    private Integer sort;
    /**
     * 其他参数
     */
    @ExcelProperty("其他参数")
    private String params;
}