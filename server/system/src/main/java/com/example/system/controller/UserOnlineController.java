package com.example.system.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.annotation.log.Log;
import com.example.system.convert.UserOnlineConvert;
import com.example.system.dal.dto.userOnline.UserOnlineQueryDTO;
import com.example.system.dal.dto.userOnline.UserOnlineSaveDTO;
import com.example.system.dal.entity.UserOnlineEntity;
import com.example.system.dal.vo.userOnline.UserOnlineDetailVO;
import com.example.system.dal.vo.userOnline.UserOnlineExportVO;
import com.example.system.dal.vo.userOnline.UserOnlineListVO;
import com.example.system.dal.vo.userOnline.UserOnlinePageVO;
import com.example.system.mapper.UserOnlineMapper;
import com.example.system.service.UserOnlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.system.enums.OperateType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "在线用户")
@CrossOrigin
@RequestMapping("/userOnline")
public class UserOnlineController {
    @Resource
    UserOnlineService userOnlineService;
    @Resource
    UserOnlineMapper userOnlineMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<UserOnlinePageVO>> userOnlinePage(@Valid UserOnlineQueryDTO userOnline) {
        return userOnlineService.userOnlinePage(userOnline);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<UserOnlineListVO>> userOnlineList(@Valid UserOnlineQueryDTO userOnline) {
        return userOnlineService.userOnlineList(userOnline);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<UserOnlineDetailVO> userOnlineDetail(Long id) {
        return userOnlineService.userOnlineDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('userOnline:change')")
    @Log(title = "在线用户新增/修改", module = "在线用户模块", content = "在线用户新增/修改", type = OperateType.INSERT)
    public Result<UserOnlineEntity> userOnlineSave(@RequestBody @Valid UserOnlineSaveDTO userOnline) {
        return userOnlineService.userOnlineSave(userOnline);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('userOnline:change')")
    @Log(title = "在线用户新增/修改", module = "在线用户模块", content = "在线用户新增/修改", type = OperateType.INSERT)
    public Result<List<UserOnlineEntity>> userOnlineSaveList(@RequestBody @Valid List<UserOnlineSaveDTO> userOnlineList) {
        return userOnlineService.userOnlineSaveList(userOnlineList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('userOnline:remove')")
    @Log(title = "在线用户删除", module = "在线用户模块", content = "在线用户删除", type = OperateType.DELETE)
    public Result<Object> userOnlineDelete(@RequestBody List<Long> ids) {
        userOnlineMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('userOnline:change')")
    @Log(title = "在线用户导入", module = "在线用户模块", content = "在线用户导入", type = OperateType.IMPORT)
    public Result<List<UserOnlineEntity>> userOnlineImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<UserOnlineSaveDTO> userOnlineList = UserOnlineConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), UserOnlineExportVO.class));
        return userOnlineService.userOnlineSaveList(userOnlineList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void userOnlineExport(HttpServletResponse response, UserOnlineQueryDTO userOnline) throws IOException {
        userOnlineService.userOnlineExport(response, userOnline);
    }
}