package com.example.system.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TableMapper {

    @DS("table_schema")
    @Select("SELECT * FROM information_schema.TABLES WHERE table_schema = 'system_server' OR table_schema = 'system_web'")
    List<Map<String, Object>> selectTableList();


    @DS("table_schema")
    @Select("SELECT COLUMN_NAME,COLUMN_COMMENT,DATA_TYPE FROM information_schema.COLUMNS WHERE (table_schema = 'system_server' OR table_schema = 'system_web') AND table_name = #{tableName}")
    List<Map<String, Object>> selectTableColumn(String tableName);
}
