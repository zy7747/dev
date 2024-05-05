package com.example.files.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.files.dal.dto.file.FileQueryDTO;
import com.example.files.dal.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper
public interface FileMapper extends BaseMapper<FileEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<FileEntity> search(FileQueryDTO file) {
        QueryWrapper<FileEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (file.getId() != null) {
            wrapper.eq("id", file.getId());
        }
        /* 文件名称 */
        if (!StrUtil.hasBlank(file.getFileName())) {
            wrapper.eq("file_name", file.getFileName());
        }
        /* url地址 */
        if (!StrUtil.hasBlank(file.getUrl())) {
            wrapper.eq("url", file.getUrl());
        }
        /* 文件类型 */
        if (!StrUtil.hasBlank(file.getFileType())) {
            wrapper.eq("file_type", file.getFileType());
        }
        /* 文件路径 */
        if (!StrUtil.hasBlank(file.getFilePath())) {
            wrapper.eq("file_path", file.getFilePath());
        }
        /* 状态 */
        if (!StrUtil.hasBlank(file.getStatus())) {
            wrapper.eq("status", file.getStatus());
        }
        /* 备注 */
        if (!StrUtil.hasBlank(file.getRemark())) {
            wrapper.eq("remark", file.getRemark());
        }
        /* 创建人 */
        if (!StrUtil.hasBlank(file.getCreator())) {
            wrapper.eq("creator", file.getCreator());
        }
        /* 更新人 */
        if (!StrUtil.hasBlank(file.getUpdater())) {
            wrapper.eq("updater", file.getUpdater());
        }
        /* 创建时间 */
        if (file.getCreateTime() != null) {
            wrapper.like("create_time", file.getCreateTime());
        }
        /* 更新时间 */
        if (file.getUpdateTime() != null) {
            wrapper.eq("update_time", file.getUpdateTime());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<FileEntity>> onlyValidList(List<FileEntity> fileData) {
        List<FileEntity> fileList = queryList(new FileQueryDTO());
        for (FileEntity item : fileData) {
            Result<FileEntity> file = onlyValid(item, fileList);
            if (file.getCode() != 200) {
                return Result.fail(file.getMessage());
            }
        }
        return Result.success(fileList);
    }

    /**
     * 唯一性校验
     */
    default Result<FileEntity> onlyValid(FileEntity file, List<FileEntity> fileList) {
        for (FileEntity item : fileList) {
            //修改跳过自己
            if (file.getId() != null && item.getId().equals(file.getId())) {
                continue;
            }
            if (!StrUtil.hasBlank(file.getUrl()) && Objects.equals(file.getUrl(), item.getUrl())) {
                return Result.fail("url地址已被注册");
            }
        }
        return Result.success(file);
    }

    /**
     * 分页查询
     */
    default PageList<FileEntity> queryPage(FileQueryDTO file) {
        IPage<FileEntity> pageParams = new Page<>(file.getPage(), file.getSize());
        return PageList.setPages(selectPage(pageParams, search(file)));
    }

    /**
     * 列表查询
     */
    default List<FileEntity> queryList(FileQueryDTO file) {
        return selectList(search(file));
    }
}