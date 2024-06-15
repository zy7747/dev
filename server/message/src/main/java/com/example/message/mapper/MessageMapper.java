package com.example.message.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.message.dal.dto.message.MessageQueryDTO;
import com.example.message.dal.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper
public interface MessageMapper extends BaseMapper<MessageEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<MessageEntity> search(MessageQueryDTO message) {
        QueryWrapper<MessageEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (message.getId() != null) {
            wrapper.eq("id", message.getId());
        }

        /* 消息名称 */
        if (!StrUtil.hasBlank(message.getMessageName())) {
            wrapper.eq("message_name", message.getMessageName());
        }
        /* 消息内容 */
        if (!StrUtil.hasBlank(message.getMessageContent())) {
            wrapper.eq("message_content", message.getMessageContent());
        }
        /* 推送模块 */
        if (!StrUtil.hasBlank(message.getModule())) {
            wrapper.eq("module", message.getModule());
        }
        /* 类型 */
        if (!StrUtil.hasBlank(message.getType())) {
            wrapper.eq("type", message.getType());
        }
        /* 是否已读 */
        if (!StrUtil.hasBlank(message.getIsRead())) {
            wrapper.eq("is_read", message.getIsRead());
        }
        /* 收件人 */
        if (!StrUtil.hasBlank(message.getReceiver())) {
            wrapper.eq("receiver", message.getReceiver());
        }
        /* 状态 */
        if (!StrUtil.hasBlank(message.getStatus())) {
            wrapper.eq("status", message.getStatus());
        }
        /* 备注 */
        if (!StrUtil.hasBlank(message.getRemark())) {
            wrapper.eq("remark", message.getRemark());
        }
        /* 发送人 */
        if (!StrUtil.hasBlank(message.getCreator())) {
            wrapper.eq("creator", message.getCreator());
        }

        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<MessageEntity>> onlyValidList(List<MessageEntity> messageData) {
        List<MessageEntity> messageList = queryList(new MessageQueryDTO());
        for (MessageEntity item : messageData) {
            Result<MessageEntity> message = onlyValid(item, messageList);
            if (message.getCode() != 200) {
                return Result.fail(message.getMessage());
            }
        }
        return Result.success(messageList);
    }

    /**
     * 唯一性校验
     */
    default Result<MessageEntity> onlyValid(MessageEntity message, List<MessageEntity> messageList) {
        for (MessageEntity item : messageList) {
            //修改跳过自己
            if (message.getId() != null && item.getId().equals(message.getId())) {
                continue;
            }
        }
        return Result.success(message);
    }

    /**
     * 分页查询
     */
    default PageList<MessageEntity> queryPage(MessageQueryDTO message) {
        IPage<MessageEntity> pageParams = new Page<>(message.getPage(), message.getSize());
        return PageList.setPages(selectPage(pageParams, search(message)));
    }

    /**
     * 列表查询
     */
    default List<MessageEntity> queryList(MessageQueryDTO message) {
        return selectList(search(message));
    }
}