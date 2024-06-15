package com.example.message.contorller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.message.convert.MessageConvert;
import com.example.message.dal.dto.message.MessageQueryDTO;
import com.example.message.dal.dto.message.MessageSaveDTO;
import com.example.message.dal.entity.MessageEntity;
import com.example.message.dal.vo.message.MessageDetailVO;
import com.example.message.dal.vo.message.MessageExportVO;
import com.example.message.dal.vo.message.MessageListVO;
import com.example.message.dal.vo.message.MessagePageVO;
import com.example.message.mapper.MessageMapper;
import com.example.message.service.MessageService;
import com.example.system.annotation.log.Log;
import com.example.system.enums.OperateType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "消息通知")
@CrossOrigin
@RequestMapping("/message")
public class MessageController {
    @Resource
    MessageService messageService;
    @Resource
    MessageMapper messageMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<MessagePageVO>> messagePage(@Valid MessageQueryDTO message) {
        return messageService.messagePage(message);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<MessageListVO>> messageList(@Valid MessageQueryDTO message) {
        return messageService.messageList(message);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<MessageDetailVO> messageDetail(Long id) {
        return messageService.messageDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('message:change')")
    @Log(title = "消息通知新增/修改", module = "消息通知模块", content = "消息通知新增/修改", type = OperateType.INSERT)
    public Result<MessageEntity> messageSave(@RequestBody @Valid MessageSaveDTO message) {
        return messageService.messageSave(message);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('message:change')")
    @Log(title = "消息通知新增/修改", module = "消息通知模块", content = "消息通知新增/修改", type = OperateType.INSERT)
    public Result<List<MessageEntity>> messageSaveList(@RequestBody @Valid List<MessageSaveDTO> messageList) {
        return messageService.messageSaveList(messageList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('message:remove')")
    @Log(title = "消息通知删除", module = "消息通知模块", content = "消息通知删除", type = OperateType.DELETE)
    public Result<Object> messageDelete(@RequestBody List<Long> ids) {
        messageMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('message:change')")
    @Log(title = "消息通知导入", module = "消息通知模块", content = "消息通知导入", type = OperateType.IMPORT)
    public Result<List<MessageEntity>> messageImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<MessageSaveDTO> messageList = MessageConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), MessageExportVO.class));
        return messageService.messageSaveList(messageList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void messageExport(HttpServletResponse response, MessageQueryDTO message) throws IOException {
        messageService.messageExport(response, message);
    }
}