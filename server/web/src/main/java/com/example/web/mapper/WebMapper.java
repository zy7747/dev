package com.example.web.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.web.dal.dto.web.WebQueryDTO;
import com.example.web.dal.entity.WebEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper
public interface WebMapper extends BaseMapper<WebEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<WebEntity> search(WebQueryDTO web) {
        QueryWrapper<WebEntity> wrapper = new QueryWrapper<>();


        /* 网站名称 */
        if (!StrUtil.hasBlank(web.getWebName())) {
            wrapper.eq("web_name", web.getWebName());
        }
        /* 网址 */
        if (!StrUtil.hasBlank(web.getUrl())) {
            wrapper.eq("url", web.getUrl());
        }
        /* 图标 */
        if (!StrUtil.hasBlank(web.getIcon())) {
            wrapper.eq("icon", web.getIcon());
        }
        /* 分类 */
        if (!StrUtil.hasBlank(web.getType())) {
            wrapper.eq("type", web.getType());
        }
        /* 状态 */
        if (!StrUtil.hasBlank(web.getStatus())) {
            wrapper.eq("status", web.getStatus());
        }
        /* 网站介绍 */
        if (!StrUtil.hasBlank(web.getIntroduction())) {
            wrapper.eq("introduction", web.getIntroduction());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<WebEntity>> onlyValidList(List<WebEntity> webData) {
        List<WebEntity> webList = queryList(new WebQueryDTO());
        for (WebEntity item : webData) {
            Result<WebEntity> web = onlyValid(item, webList);
            if (web.getCode() != 200) {
                return Result.fail(web.getMessage());
            }
        }
        return Result.success(webList);
    }

    /**
     * 唯一性校验
     */
    default Result<WebEntity> onlyValid(WebEntity web, List<WebEntity> webList) {
        for (WebEntity item : webList) {
            //修改跳过自己
            if (web.getId() != null && item.getId().equals(web.getId())) {
                continue;
            }

            if (!StrUtil.hasBlank(web.getWebName()) && Objects.equals(web.getWebName(), item.getWebName())) {
                return Result.fail("网站名称已被注册");
            }
            if (!StrUtil.hasBlank(web.getUrl()) && Objects.equals(web.getUrl(), item.getUrl())) {
                return Result.fail("网址已被注册");
            }
        }
        return Result.success(web);
    }

    /**
     * 分页查询
     */
    default PageList<WebEntity> queryPage(WebQueryDTO web) {
        IPage<WebEntity> pageParams = new Page<>(web.getPage(), web.getSize());
        return PageList.setPages(selectPage(pageParams, search(web)));
    }

    /**
     * 列表查询
     */
    default List<WebEntity> queryList(WebQueryDTO web) {
        return selectList(search(web));
    }
}