package com.example.system.controller;

import com.example.framework.common.Result;
import com.example.system.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Validated
@Api(tags = "数据库表")
@CrossOrigin
@RequestMapping("/table")
public class TableController {
    @Resource
    TableService tableService;

    @GetMapping("/getTableList")
    @ApiOperation(value = "获取数据库表中的列")
    public Result<List<Map<String, Object>>> getTableList() {
        return tableService.getTableList();
    }

    @GetMapping("/getTableColumn")
    @ApiOperation(value = "获取数据库表中的列")
    public Result<List<Map<String, Object>>> getTableColumn(String tableName) {
        return tableService.getTableColumn(tableName);
    }


}
