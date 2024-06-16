package com.example.system.dal.vo.user;

import com.example.system.dal.entity.MenuEntity;
import com.example.system.dal.entity.RoleEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Data
@Service
@ApiModel(value = "登录信息")
public class UserInfoVO {
    @ApiModelProperty(value = "token")
    private String token;

    @JsonFormat(shape = JsonFormat.Shape.STRING)//雪花算法丢失精度问题
    @ApiModelProperty(value = "当次登录ID")
    private Long loginId;

    @ApiModelProperty(value = "用户信息")
    private UserBaseInfoVO userInfo;

    @ApiModelProperty(value = "角色列表")
    private List<RoleEntity> roles;

    @ApiModelProperty(value = "菜单列表")
    private List<MenuEntity> menuList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "当次登录时间")
    private Date loginTime;


}
