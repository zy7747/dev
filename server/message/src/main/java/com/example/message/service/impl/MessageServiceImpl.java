package com.example.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.dal.dto.SendMessageDTO;
import com.example.framework.service.WebSocketService;
import com.example.framework.utils.ExcelUtils;
import com.example.message.convert.MessageConvert;
import com.example.message.dal.dto.message.MessageQueryDTO;
import com.example.message.dal.dto.message.MessageSaveDTO;
import com.example.message.dal.entity.MessageEntity;
import com.example.message.dal.vo.message.MessageExportVO;
import com.example.message.mapper.MessageMapper;
import com.example.message.dal.vo.message.MessageDetailVO;
import com.example.message.dal.vo.message.MessageListVO;
import com.example.message.dal.vo.message.MessagePageVO;
import com.example.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageEntity> implements MessageService {
    @Resource
    MessageMapper messageMapper;

    @Resource
    WebSocketService webSocketService;

    /**
     * 获取列表分页
     *
     * @param message 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<MessagePageVO>> messagePage(MessageQueryDTO message) {
        return Result.success(MessageConvert.INSTANCE.page(messageMapper.queryPage(message)));
    }

    /**
     * 获取列表
     *
     * @param message 入参
     * @return 列表
     */
    @Override
    public Result<List<MessageListVO>> messageList(MessageQueryDTO message) {
        return Result.success(MessageConvert.INSTANCE.list(messageMapper.queryList(message)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<MessageDetailVO> messageDetail(Long id) {
        return Result.success(MessageConvert.INSTANCE.detail(messageMapper.selectById(id)));
    }

    /**
     * 新增/修改
     *
     * @param message 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<MessageEntity> messageSave(MessageSaveDTO message) {
        MessageEntity messageData = MessageConvert.INSTANCE.save(message);
        List<MessageEntity> messageList = messageMapper.queryList(new MessageQueryDTO());
        Result<MessageEntity> valid = messageMapper.onlyValid(messageData, messageList);
        if (valid.getCode() == 200) {
            if (message.getId() == null && message.getReceiverList().size() > 0) {
                List<MessageEntity> messageArr = new ArrayList<>();
                SendMessageDTO messageMsg = new SendMessageDTO();
                messageMsg.setMessage("推送消息");

                message.getReceiverList().forEach(item -> {
                    MessageEntity newData = new MessageEntity();

                    BeanUtils.copyProperties(messageData, newData);
                    newData.setReceiver(item);
                    messageArr.add(newData);
                });

                this.saveOrUpdateBatch(messageArr);

                message.getReceiverList().forEach(item -> {
                    messageMsg.setUserId(item);
                    webSocketService.sendOneMessage(messageMsg);
                });

            } else {
                this.saveOrUpdate(messageData);
            }

            return Result.success(messageData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param message 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<MessageEntity>> messageSaveList(List<MessageSaveDTO> message) {
        List<MessageEntity> messageData = MessageConvert.INSTANCE.saveList(message);
        Result<List<MessageEntity>> valid = messageMapper.onlyValidList(messageData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(messageData);
            return Result.success(messageData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,message 入参
     */
    @Override
    public void messageExport(HttpServletResponse response, MessageQueryDTO message) throws IOException {
        ExcelUtils.export(response, "消息通知.xlsx", "消息通知", MessageExportVO.class, MessageConvert.INSTANCE.export(messageMapper.queryList(message)));
    }
}