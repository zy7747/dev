package com.example.system.dal.vo.userOnline;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.framework.dal.vo.ExcelVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "导出")
@EqualsAndHashCode(callSuper = true)
public class UserOnlineExportVO extends ExcelVO {
    /**
     * 用户id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)//雪花算法丢失精度问题
    @ExcelProperty("用户id")
    private Long uid;
    /**
     * 账号
     */
    @ExcelProperty("账号")
    private String account;
    /**
     * 登录IP
     */
    @ExcelProperty("登录IP")
    private String loginIp;
    /**
     * 登录地
     */
    @ExcelProperty("登录地")
    private String loginPlace;
    /**
     * 登录设备
     */
    @ExcelProperty("登录设备")
    private String loginDevice;
    /**
     * 操作结果
     */
    @ExcelProperty("操作结果")
    private String operateResults;
}