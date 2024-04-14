package com.example.system.dal.vo.user;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.framework.dal.vo.ExcelVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "导出")
@EqualsAndHashCode(callSuper = true)
public class UserExportVO extends ExcelVO {
    /**
     * 用户id
     */
    @ExcelProperty("用户id")
    private Long uid;
    /**
     * 电话号码
     */
    @ExcelProperty("电话号码")
    private String phone;
    /**
     * 电子邮箱
     */
    @ExcelProperty("电子邮箱")
    private String email;
    /**
     * 账号
     */
    @ExcelProperty("账号")
    private String account;
    /**
     * 用户名
     */
    @ExcelProperty("用户名")
    private String user;
    /**
     * 密码
     */
    @ExcelProperty("密码")
    private String password;
    /**
     * 昵称
     */
    @ExcelProperty("昵称")
    private String nickname;
    /**
     * 头像
     */
    @ExcelProperty("头像")
    private String avatar;
    /**
     * 类型
     */
    @ExcelProperty("类型")
    private String userType;
}