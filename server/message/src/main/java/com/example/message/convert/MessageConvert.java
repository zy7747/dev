package com.example.message.convert;

import com.example.framework.common.PageList;
import com.example.message.dal.dto.message.MessageSaveDTO;
import com.example.message.dal.entity.MessageEntity;
import com.example.message.dal.vo.message.MessageDetailVO;
import com.example.message.dal.vo.message.MessageExportVO;
import com.example.message.dal.vo.message.MessageListVO;
import com.example.message.dal.vo.message.MessagePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MessageConvert {
    MessageConvert INSTANCE = Mappers.getMapper(MessageConvert.class);

    PageList<MessagePageVO> page(PageList<MessageEntity> message);

    List<MessageListVO> list(List<MessageEntity> message);

    MessageDetailVO detail(MessageEntity message);

    MessageEntity save(MessageSaveDTO message);

    List<MessageEntity> saveList(List<MessageSaveDTO> message);

    List<MessageExportVO> export(List<MessageEntity> message);

    List<MessageSaveDTO> imports(List<MessageExportVO> message);
}