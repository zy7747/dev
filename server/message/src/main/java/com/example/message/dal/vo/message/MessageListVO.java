package com.example.message.dal.vo.message;

import com.example.framework.dal.vo.BaseParamsVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "列表")
@EqualsAndHashCode(callSuper = true)
public class MessageListVO extends BaseParamsVO {
    /**
     * 消息名称
     */
    @ApiModelProperty(value = "消息名称")
    private String messageName;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String messageContent;
    /**
     * 推送模块
     */
    @ApiModelProperty(value = "推送模块")
    private String module;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;
    /**
     * 是否已读
     */
    @ApiModelProperty(value = "是否已读")
    private String isRead;
    /**
     * 收件人
     */
    @ApiModelProperty(value = "收件人")
    private String receiver;

}