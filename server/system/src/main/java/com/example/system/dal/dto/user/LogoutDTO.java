package com.example.system.dal.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "用户登出")
public class LogoutDTO {

    @ApiModelProperty(value = "当次登录的id")
    private Long id;

}
