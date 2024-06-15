package com.example.message.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.message.dal.dto.message.MessageQueryDTO;
import com.example.message.dal.dto.message.MessageSaveDTO;
import com.example.message.dal.entity.MessageEntity;
import com.example.message.dal.vo.message.MessageDetailVO;
import com.example.message.dal.vo.message.MessageListVO;
import com.example.message.dal.vo.message.MessagePageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface MessageService {
    /**
     * 获取列表分页
     *
     * @param message 分页入参
     * @return 列表分页
     */
    Result<PageList<MessagePageVO>> messagePage(MessageQueryDTO message);

    /**
     * 获取列表
     *
     * @param message 入参
     * @return 列表
     */
    Result<List<MessageListVO>> messageList(MessageQueryDTO message);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<MessageDetailVO> messageDetail(Long id);

    /**
     * 新增/修改
     *
     * @param message 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<MessageEntity> messageSave(MessageSaveDTO message);

    /**
     * 批量新增/修改
     *
     * @param message 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<MessageEntity>> messageSaveList(@Valid List<MessageSaveDTO> message);

    /**
     * 导出
     *
     * @param response,message 入参
     */
    void messageExport(HttpServletResponse response, MessageQueryDTO message) throws IOException;
}