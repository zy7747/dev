package com.example.system.dal.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "用户登录")
public class LoginDTO {
    @ApiModelProperty(value = "登录方式")
    private String loginType;

    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "用户名")
    private String user;

    @ApiModelProperty(value = "短信验证码")
    private String sms;

    @NotBlank(message = "密码不能为空")
    @Length(message = "密码不能超过个 {max} 字符", max = 16)
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "登录IP")
    private String loginIp;

    @ApiModelProperty(value = "登录设备")
    private String loginDevice;

    @ApiModelProperty(value = "登录系统")
    private String loginSystem;
}
