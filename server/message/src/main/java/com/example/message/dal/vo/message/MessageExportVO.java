package com.example.message.dal.vo.message;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.framework.dal.vo.ExcelVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "导出")
@EqualsAndHashCode(callSuper = true)
public class MessageExportVO extends ExcelVO {
    /**
     * 消息名称
     */
    @ExcelProperty("消息名称")
    private String messageName;
    /**
     * 消息内容
     */
    @ExcelProperty("消息内容")
    private String messageContent;
    /**
     * 推送模块
     */
    @ExcelProperty("推送模块")
    private String module;
    /**
     * 类型
     */
    @ExcelProperty("类型")
    private String type;
    /**
     * 是否已读
     */
    @ExcelProperty("是否已读")
    private String isRead;
    /**
     * 收件人
     */
    @ExcelProperty("收件人")
    private String receiver;
}