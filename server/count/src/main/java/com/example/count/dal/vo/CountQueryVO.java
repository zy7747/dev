package com.example.count.dal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数据统计")
public class CountQueryVO {
    /**
     * 用户总数
     */
    @ApiModelProperty(value = "用户总数")
    private Integer userTotal;

    /**
     * 在线用户总数
     */
    @ApiModelProperty(value = "在线用户总数")
    private Integer userOnlineTotal;
}
