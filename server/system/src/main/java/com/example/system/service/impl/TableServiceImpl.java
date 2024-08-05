package com.example.system.service.impl;

import com.example.framework.common.Result;
import com.example.system.mapper.TableMapper;
import com.example.system.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TableServiceImpl implements TableService {

    @Resource
    TableMapper tableMapper;

    /**
     * 获取表格中的列
     *
     * @return 获取表格中的列
     */
    @Override
    public Result<List<Map<String, Object>>> getTableColumn(String tableName) {
        return Result.success(tableMapper.selectTableColumn(tableName));
    }

    /**
     * 获取所有表
     *
     * @return 获取所有表
     */
    @Override
    public Result<List<Map<String, Object>>> getTableList() {
        List<Map<String, Object>> tableList = tableMapper.selectTableList();

        return Result.success(tableList);
    }

}
