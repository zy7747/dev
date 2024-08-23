package com.example.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.web.convert.WebConvert;
import com.example.web.dal.dto.web.WebQueryDTO;
import com.example.web.dal.dto.web.WebSaveDTO;
import com.example.web.dal.entity.WebEntity;
import com.example.web.dal.vo.web.WebExportVO;
import com.example.web.mapper.WebMapper;
import com.example.web.dal.vo.web.WebDetailVO;
import com.example.web.dal.vo.web.WebListVO;
import com.example.web.dal.vo.web.WebPageVO;
import com.example.web.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class WebServiceImpl extends ServiceImpl<WebMapper, WebEntity> implements WebService {
    @Resource
    WebMapper webMapper;

    /**
     * 获取列表分页
     *
     * @param web 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<WebPageVO>> webPage(WebQueryDTO web) {
        return Result.success(WebConvert.INSTANCE.page(webMapper.queryPage(web)));
    }

    /**
     * 获取列表
     *
     * @param web 入参
     * @return 列表
     */
    @Override
    public Result<List<WebListVO>> webList(WebQueryDTO web) {
        return Result.success(WebConvert.INSTANCE.list(webMapper.queryList(web)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<WebDetailVO> webDetail(Long id) {
        return Result.success(WebConvert.INSTANCE.detail(webMapper.selectById(id)));
    }

    /**
     * 新增/修改
     *
     * @param web 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<WebEntity> webSave(WebSaveDTO web) {
        WebEntity webData = WebConvert.INSTANCE.save(web);
        List<WebEntity> webList = webMapper.queryList(new WebQueryDTO());
        Result<WebEntity> valid = webMapper.onlyValid(webData, webList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(webData);
            return Result.success(webData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param web 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<WebEntity>> webSaveList(List<WebSaveDTO> web) {
        List<WebEntity> webData = WebConvert.INSTANCE.saveList(web);
        Result<List<WebEntity>> valid = webMapper.onlyValidList(webData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(webData);
            return Result.success(webData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,web 入参
     */
    @Override
    public void webExport(HttpServletResponse response, WebQueryDTO web) throws IOException {
        ExcelUtils.export(response, "网址收藏.xlsx", "网址收藏", WebExportVO.class, WebConvert.INSTANCE.export(webMapper.queryList(web)));
    }
}