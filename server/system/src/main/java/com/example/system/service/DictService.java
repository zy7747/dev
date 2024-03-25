package com.example.system.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.dict.DictQueryDTO;
import com.example.system.dal.dto.dict.DictSaveDTO;
import com.example.system.dal.entity.DictEntity;
import com.example.system.dal.vo.dict.DictDetailVO;
import com.example.system.dal.vo.dict.DictListVO;
import com.example.system.dal.vo.dict.DictPageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface DictService {
    /**
     * 获取列表分页
     *
     * @param dict 分页入参
     * @return 列表分页
     */
    Result<PageList<DictPageVO>> dictPage(DictQueryDTO dict);

    /**
     * 获取列表
     *
     * @param dict 入参
     * @return 列表
     */
    Result<List<DictListVO>> dictList(DictQueryDTO dict);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<DictDetailVO> dictDetail(Long id);

    /**
     * 新增/修改
     *
     * @param dict 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<DictEntity> dictSave(DictSaveDTO dict);

    /**
     * 批量新增/修改
     *
     * @param dict 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<DictEntity>> dictSaveList(@Valid List<DictSaveDTO> dict);

    /**
     * 导出
     *
     * @param response,dict 入参
     */
    void dictExport(HttpServletResponse response, DictQueryDTO dict) throws IOException;

    /**
     * 获取子列表
     *
     * @param dict 入参
     * @return 子列表
     */
    Result<List<DictEntity>> subList(DictQueryDTO dict);

    /**
     * 获取子列表
     *
     * @return 字典Map
     */
    Result<Object> dictMap();
}