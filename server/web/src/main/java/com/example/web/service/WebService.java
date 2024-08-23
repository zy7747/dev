package com.example.web.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.web.dal.dto.web.WebQueryDTO;
import com.example.web.dal.dto.web.WebSaveDTO;
import com.example.web.dal.entity.WebEntity;
import com.example.web.dal.vo.web.WebDetailVO;
import com.example.web.dal.vo.web.WebListVO;
import com.example.web.dal.vo.web.WebPageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface WebService {
    /**
     * 获取列表分页
     *
     * @param web 分页入参
     * @return 列表分页
     */
    Result<PageList<WebPageVO>> webPage(WebQueryDTO web);

    /**
     * 获取列表
     *
     * @param web 入参
     * @return 列表
     */
    Result<List<WebListVO>> webList(WebQueryDTO web);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<WebDetailVO> webDetail(Long id);

    /**
     * 新增/修改
     *
     * @param web 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<WebEntity> webSave(WebSaveDTO web);

    /**
     * 批量新增/修改
     *
     * @param web 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<WebEntity>> webSaveList(@Valid List<WebSaveDTO> web);

    /**
     * 导出
     *
     * @param response,web 入参
     */
    void webExport(HttpServletResponse response, WebQueryDTO web) throws IOException;
}