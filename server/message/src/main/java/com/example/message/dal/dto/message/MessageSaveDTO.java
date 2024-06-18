package com.example.message.dal.dto.message;

import com.example.framework.dal.dto.BaseParamsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel(value = "保存")
@EqualsAndHashCode(callSuper = true)
public class MessageSaveDTO extends BaseParamsDTO {
    /**
     * 消息名称
     */
    @NotBlank(message = "消息名称不能为空")
    @ApiModelProperty(value = "消息名称")
    private String messageName;
    /**
     * 消息内容
     */
    @NotBlank(message = "消息内容不能为空")
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
    @NotBlank(message = "是否已读不能为空")
    @ApiModelProperty(value = "是否已读")
    private String isRead;
    /**
     * 收件人
     */
    @ApiModelProperty(value = "收件人")
    private String receiver;
    /**
     * 收件人List
     */
    @ApiModelProperty(value = "收件人")
    private List<String> receiverList;

}