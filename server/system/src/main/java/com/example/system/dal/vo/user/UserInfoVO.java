package com.example.system.dal.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
@ApiModel(value = "登录信息")
public class UserInfoVO {
    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "用户信息")
    private UserBaseInfoVO userInfo;
}
