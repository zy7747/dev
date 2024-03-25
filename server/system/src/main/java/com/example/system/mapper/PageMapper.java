package com.example.system.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.page.PageQueryDTO;
import com.example.system.dal.entity.PageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PageMapper extends BaseMapper<PageEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<PageEntity> search(PageQueryDTO page) {
        QueryWrapper<PageEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (page.getId() != null) {
            wrapper.eq("id", page.getId());
        }
        /* 页面配置 */
        if (!StrUtil.hasBlank(page.getOptions())) {
            wrapper.eq("options", page.getOptions());
        }
        /* 状态 */
        if (!StrUtil.hasBlank(page.getStatus())) {
            wrapper.eq("status", page.getStatus());
        }
        /* 备注 */
        if (!StrUtil.hasBlank(page.getRemark())) {
            wrapper.eq("remark", page.getRemark());
        }
        /* 创建人 */
        if (!StrUtil.hasBlank(page.getCreator())) {
            wrapper.eq("creator", page.getCreator());
        }
        /* 更新人 */
        if (!StrUtil.hasBlank(page.getUpdater())) {
            wrapper.eq("updater", page.getUpdater());
        }

        /* 创建时间 */
        if (page.getCreateTime() != null) {
            wrapper.like("create_time", page.getCreateTime());
        }
        /* 更新时间 */
        if (page.getUpdateTime() != null) {
            wrapper.eq("update_time", page.getUpdateTime());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<PageEntity>> onlyValidList(List<PageEntity> pageData) {
        List<PageEntity> pageList = queryList(new PageQueryDTO());
        for (PageEntity item : pageData) {
            Result<PageEntity> page = onlyValid(item, pageList);
            if (page.getCode() != 200) {
                return Result.fail(page.getMessage());
            }
        }
        return Result.success(pageList);
    }

    /**
     * 唯一性校验
     */
    default Result<PageEntity> onlyValid(PageEntity page, List<PageEntity> pageList) {
        for (PageEntity item : pageList) {
            //修改跳过自己
            if (page.getId() != null && item.getId().equals(page.getId())) {
                continue;
            }
        }
        return Result.success(page);
    }

    /**
     * 分页查询
     */
    default PageList<PageEntity> queryPage(PageQueryDTO page) {
        IPage<PageEntity> pageParams = new Page<>(page.getPage(), page.getSize());
        return PageList.setPages(selectPage(pageParams, search(page)));
    }

    /**
     * 列表查询
     */
    default List<PageEntity> queryList(PageQueryDTO page) {
        return selectList(search(page));
    }
}