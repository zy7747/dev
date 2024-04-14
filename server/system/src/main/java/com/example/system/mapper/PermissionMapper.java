package com.example.system.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.permission.PermissionQueryDTO;
import com.example.system.dal.entity.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper
public interface PermissionMapper extends BaseMapper<PermissionEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<PermissionEntity> search(PermissionQueryDTO permission) {
        QueryWrapper<PermissionEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (permission.getId() != null) {
            wrapper.eq("id", permission.getId());
        }
        /* 权限名称 */
        if (!StrUtil.hasBlank(permission.getName())) {
            wrapper.eq("name", permission.getName());
        }
        /* 权限标识 */
        if (!StrUtil.hasBlank(permission.getPermission())) {
            wrapper.eq("permission", permission.getPermission());
        }
        /* 类型 */
        if (!StrUtil.hasBlank(permission.getType())) {
            wrapper.eq("type", permission.getType());
        }
        /* 描述 */
        if (!StrUtil.hasBlank(permission.getDescription())) {
            wrapper.eq("description", permission.getDescription());
        }
        /* 创建时间 */
        if (permission.getCreateTime() != null) {
            wrapper.like("create_time", permission.getCreateTime());
        }
        /* 更新时间 */
        if (permission.getUpdateTime() != null) {
            wrapper.eq("update_time", permission.getUpdateTime());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<PermissionEntity>> onlyValidList(List<PermissionEntity> permissionData) {
        List<PermissionEntity> permissionList = queryList(new PermissionQueryDTO());
        for (PermissionEntity item : permissionData) {
            Result<PermissionEntity> permission = onlyValid(item, permissionList);
            if (permission.getCode() != 200) {
                return Result.fail(permission.getMessage());
            }
        }
        return Result.success(permissionList);
    }

    /**
     * 唯一性校验
     */
    default Result<PermissionEntity> onlyValid(PermissionEntity permission, List<PermissionEntity> permissionList) {
        for (PermissionEntity item : permissionList) {
            //修改跳过自己
            if (permission.getId() != null && item.getId().equals(permission.getId())) {
                continue;
            }

            if (!StrUtil.hasBlank(permission.getName()) && Objects.equals(permission.getName(), item.getName())) {
                return Result.fail("权限名称已被注册");
            }
            if (!StrUtil.hasBlank(permission.getPermission()) && Objects.equals(permission.getPermission(), item.getPermission())) {
                return Result.fail("权限标识已被注册");
            }
        }
        return Result.success(permission);
    }

    /**
     * 分页查询
     */
    default PageList<PermissionEntity> queryPage(PermissionQueryDTO permission) {
        IPage<PermissionEntity> pageParams = new Page<>(permission.getPage(), permission.getSize());
        return PageList.setPages(selectPage(pageParams, search(permission)));
    }

    /**
     * 列表查询
     */
    default List<PermissionEntity> queryList(PermissionQueryDTO permission) {
        return selectList(search(permission));
    }
}