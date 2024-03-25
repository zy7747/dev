package com.example.system.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.dict.DictQueryDTO;
import com.example.system.dal.entity.DictEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface DictMapper extends BaseMapper<DictEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<DictEntity> search(DictQueryDTO dict) {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (dict.getId() != null) {
            wrapper.eq("id", dict.getId());
        }
        /* 字典名称 */
        if (!StrUtil.hasBlank(dict.getDictName())) {
            wrapper.like("dict_name", dict.getDictName());
        }
        /* 字典编码 */
        if (!StrUtil.hasBlank(dict.getDictCode())) {
            wrapper.eq("dict_code", dict.getDictCode());
        }
        /* 字典类型 */
        if (!StrUtil.hasBlank(dict.getDictType())) {
            wrapper.eq("dict_type", dict.getDictType());
        }
        /* 名 */
        if (!StrUtil.hasBlank(dict.getLabel())) {
            wrapper.eq("label", dict.getLabel());
        }
        /* 值 */
        if (!StrUtil.hasBlank(dict.getValue())) {
            wrapper.eq("value", dict.getValue());
        }
        /* 自定义颜色 */
        if (!StrUtil.hasBlank(dict.getColor())) {
            wrapper.eq("color", dict.getColor());
        }
        /* 其他参数 */
        if (!StrUtil.hasBlank(dict.getParams())) {
            wrapper.eq("params", dict.getParams());
        }
        /* 状态 */
        if (!StrUtil.hasBlank(dict.getStatus())) {
            wrapper.eq("status", dict.getStatus());
        }
        /* 备注 */
        if (!StrUtil.hasBlank(dict.getRemark())) {
            wrapper.eq("remark", dict.getRemark());
        }
        /* 创建人 */
        if (!StrUtil.hasBlank(dict.getCreator())) {
            wrapper.eq("creator", dict.getCreator());
        }
        /* 更新人 */
        if (!StrUtil.hasBlank(dict.getUpdater())) {
            wrapper.eq("updater", dict.getUpdater());
        }
        /* 创建时间 */
        if (dict.getCreateTime() != null) {
            wrapper.like("create_time", dict.getCreateTime());
        }
        /* 更新时间 */
        if (dict.getUpdateTime() != null) {
            wrapper.eq("update_time", dict.getUpdateTime());
        }
        wrapper.orderByDesc("create_time");

        wrapper.isNull("parent_id");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<DictEntity>> onlyValidList(List<DictEntity> dictData) {
        List<DictEntity> dictList = queryList(new DictQueryDTO());
        for (DictEntity item : dictData) {
            Result<DictEntity> dict = onlyValid(item, dictList);
            if (dict.getCode() != 200) {
                return Result.fail(dict.getMessage());
            }
        }
        return Result.success(dictList);
    }


    /**
     * 唯一性校验
     */
    default Result<DictEntity> onlyValid(DictEntity dict, List<DictEntity> dictList) {
        for (DictEntity item : dictList) {
            //修改跳过自己
            if (dict.getId() != null && item.getId().equals(dict.getId())) {
                continue;
            }
            if (!StrUtil.hasBlank(dict.getDictName()) && Objects.equals(dict.getDictName(), item.getDictName())) {
                return Result.fail("字典名称已被注册");
            }
            if (!StrUtil.hasBlank(dict.getDictCode()) && dict.getParentId() != null && Objects.equals(dict.getDictCode(), item.getDictCode())) {
                return Result.fail("字典编码已被注册");
            }
            if (!StrUtil.hasBlank(dict.getLabel()) && Objects.equals(dict.getDictCode(), item.getDictCode()) && Objects.equals(dict.getLabel(), item.getLabel())) {
                return Result.fail("名已被注册");
            }
            if (!StrUtil.hasBlank(dict.getValue()) && Objects.equals(dict.getDictCode(), item.getDictCode()) && Objects.equals(dict.getValue(), item.getValue())) {
                return Result.fail("值已被注册");
            }
        }
        return Result.success(dict);
    }

    /**
     * 分页查询
     */
    default PageList<DictEntity> queryPage(DictQueryDTO dict) {
        IPage<DictEntity> pageParams = new Page<>(dict.getPage(), dict.getSize());
        return PageList.setPages(selectPage(pageParams, search(dict)));
    }

    /**
     * 列表查询
     */
    default List<DictEntity> queryList(DictQueryDTO dict) {
        return selectList(search(dict));
    }

    /**
     * 子列表查询
     */
    default List<DictEntity> getSubList(DictQueryDTO dict) {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();
        //1.直接通过PID去找
        if (dict.getParentId() != null) {
            wrapper.eq("parent_id", dict.getParentId());
        }
        //2.根据CODE去找
        if (dict.getDictCode() != null) {
            wrapper.eq("dict_code", dict.getDictCode());
        }

        wrapper.orderByAsc("sort");

        return selectList(wrapper);
    }

    default Object getDictMap() {
        QueryWrapper<DictEntity> wrapper = new QueryWrapper<>();

        wrapper.orderByAsc("sort");

        //新建一个要返回的集合
        Map<String, Object> dictMap = new HashMap<>();

        //1.拿出所有集合
        List<DictEntity> dictList = selectList(wrapper);

        //便利所有集合
        dictList.forEach(item -> {

            if (item.getParentId() == null) {
                List<DictEntity> subset = new ArrayList<>();

                dictList.forEach(dictItem -> {
                    if (item.getId().equals(dictItem.getParentId())) {
                        dictItem.setDictType(item.getDictType());
                        subset.add(dictItem);
                    }
                });

                dictMap.put(item.getDictCode(), subset);
            }
        });

        return dictMap;
    }
}