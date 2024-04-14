package com.example.system.dal.vo.permission;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.framework.dal.vo.ExcelVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "导出")
@EqualsAndHashCode(callSuper = true)
public class PermissionExportVO extends ExcelVO {
    /**
     * 权限名称
     */
    @ExcelProperty("权限名称")
    private String name;
    /**
     * 权限标识
     */
    @ExcelProperty("权限标识")
    private String permission;
    /**
     * 类型
     */
    @ExcelProperty("类型")
    private String type;
    /**
     * 描述
     */
    @ExcelProperty("描述")
    private String description;
}