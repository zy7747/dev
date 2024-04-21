package com.example.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.PageConvert;
import com.example.system.dal.dto.page.PageQueryDTO;
import com.example.system.dal.dto.page.PageSaveDTO;
import com.example.system.dal.entity.PageEntity;
import com.example.system.dal.vo.page.PageExportVO;
import com.example.system.mapper.PageMapper;
import com.example.system.dal.vo.page.PageDetailVO;
import com.example.system.dal.vo.page.PageListVO;
import com.example.system.dal.vo.page.PagePageVO;
import com.example.system.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, PageEntity> implements PageService {
    @Resource
    PageMapper pageMapper;

    /**
     * 获取列表分页
     *
     * @param page 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<PagePageVO>> pagePage(PageQueryDTO page) {
        return Result.success(PageConvert.INSTANCE.page(pageMapper.queryPage(page)));
    }

    /**
     * 获取列表
     *
     * @param page 入参
     * @return 列表
     */
    @Override
    public Result<List<PageListVO>> pageList(PageQueryDTO page) {
        return Result.success(PageConvert.INSTANCE.list(pageMapper.queryList(page)));
    }

    /**
     * 获取详情
     *
     * @param pageDetail 入参
     * @return 详情
     */
    @Override
    public Result<PageDetailVO> pageDetail(PageDetailVO pageDetail) {
        if (pageDetail.getId() != null) {
            return Result.success(PageConvert.INSTANCE.detail(pageMapper.selectById(pageDetail.getId())));
        } else {
            QueryWrapper<PageEntity> wrapper = new QueryWrapper<>();

            /* 页面编码 */
            if (!StrUtil.hasBlank(pageDetail.getPageCode())) {
                wrapper.eq("page_code", pageDetail.getPageCode());
            }

            return Result.success(PageConvert.INSTANCE.detail(pageMapper.selectOne(wrapper)));
        }

    }

    /**
     * 新增/修改
     *
     * @param page 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<PageEntity> pageSave(PageSaveDTO page) {
        PageEntity pageData = PageConvert.INSTANCE.save(page);
        List<PageEntity> pageList = pageMapper.queryList(new PageQueryDTO());
        Result<PageEntity> valid = pageMapper.onlyValid(pageData, pageList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(pageData);
            return Result.success(pageData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param page 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<PageEntity>> pageSaveList(List<PageSaveDTO> page) {
        List<PageEntity> pageData = PageConvert.INSTANCE.saveList(page);
        Result<List<PageEntity>> valid = pageMapper.onlyValidList(pageData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(pageData);
            return Result.success(pageData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,page 入参
     */
    @Override
    public void pageExport(HttpServletResponse response, PageQueryDTO page) throws IOException {
        ExcelUtils.export(response, "配置页面.xlsx", "配置页面", PageExportVO.class, PageConvert.INSTANCE.export(pageMapper.queryList(page)));
    }
}