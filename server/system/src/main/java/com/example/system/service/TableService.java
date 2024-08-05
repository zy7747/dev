package com.example.system.service;

import com.example.framework.common.Result;

import java.util.List;
import java.util.Map;

public interface TableService {

    /**
     * 获取表中的列
     *
     * @return 获取表中的列
     */
    Result<List<Map<String, Object>>> getTableColumn(String tableName);

    /**
     * 获取所有表
     *
     * @return 获取所有表
     */
    Result<List<Map<String, Object>>> getTableList();
}
