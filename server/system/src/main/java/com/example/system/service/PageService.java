package com.example.system.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.page.PageQueryDTO;
import com.example.system.dal.dto.page.PageSaveDTO;
import com.example.system.dal.entity.PageEntity;
import com.example.system.dal.vo.page.PageDetailVO;
import com.example.system.dal.vo.page.PageListVO;
import com.example.system.dal.vo.page.PagePageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface PageService {
    /**
     * 获取列表分页
     *
     * @param page 分页入参
     * @return 列表分页
     */
    Result<PageList<PagePageVO>> pagePage(PageQueryDTO page);

    /**
     * 获取列表
     *
     * @param page 入参
     * @return 列表
     */
    Result<List<PageListVO>> pageList(PageQueryDTO page);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<PageDetailVO> pageDetail(Long id);

    /**
     * 新增/修改
     *
     * @param page 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<PageEntity> pageSave(PageSaveDTO page);

    /**
     * 批量新增/修改
     *
     * @param page 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<PageEntity>> pageSaveList(@Valid List<PageSaveDTO> page);

    /**
     * 导出
     *
     * @param response,page 入参
     */
    void pageExport(HttpServletResponse response, PageQueryDTO page) throws IOException;
}