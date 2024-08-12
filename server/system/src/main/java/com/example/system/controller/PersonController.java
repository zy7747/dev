package com.example.system.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.PersonConvert;
import com.example.system.dal.dto.person.PersonQueryDTO;
import com.example.system.dal.dto.person.PersonSaveDTO;
import com.example.system.dal.entity.PersonEntity;
import com.example.system.dal.vo.person.PersonDetailVO;
import com.example.system.dal.vo.person.PersonExportVO;
import com.example.system.dal.vo.person.PersonListVO;
import com.example.system.dal.vo.person.PersonPageVO;
import com.example.system.mapper.PersonMapper;
import com.example.system.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.system.enums.OperateType;
import com.example.system.annotation.log.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "个人信息")
@CrossOrigin
@RequestMapping("/person")
public class PersonController {
    @Resource
    PersonService personService;
    @Resource
    PersonMapper personMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<PersonPageVO>> personPage(@Valid PersonQueryDTO person) {
        return personService.personPage(person);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<PersonListVO>> personList(@Valid PersonQueryDTO person) {
        return personService.personList(person);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<PersonDetailVO> personDetail(Long id) {
        return personService.personDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('person:change')")
    @Log(title = "个人信息新增/修改", module = "个人信息模块", content = "个人信息新增/修改", type = OperateType.INSERT)
    public Result<PersonEntity> personSave(@RequestBody @Valid PersonSaveDTO person) {
        return personService.personSave(person);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('person:change')")
    @Log(title = "个人信息新增/修改", module = "个人信息模块", content = "个人信息新增/修改", type = OperateType.INSERT)
    public Result<List<PersonEntity>> personSaveList(@RequestBody @Valid List<PersonSaveDTO> personList) {
        return personService.personSaveList(personList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('person:remove')")
    @Log(title = "个人信息删除", module = "个人信息模块", content = "个人信息删除", type = OperateType.DELETE)
    public Result<Object> personDelete(@RequestBody List<Long> ids) {
        personMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('person:change')")
    @Log(title = "个人信息导入", module = "个人信息模块", content = "个人信息导入", type = OperateType.IMPORT)
    public Result<List<PersonEntity>> personImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<PersonSaveDTO> personList = PersonConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), PersonExportVO.class));
        return personService.personSaveList(personList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void personExport(HttpServletResponse response, PersonQueryDTO person) throws IOException {
        personService.personExport(response, person);
    }
}