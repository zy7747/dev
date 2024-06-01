package com.example.system.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.menu.MenuQueryDTO;
import com.example.system.dal.entity.MenuEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<MenuEntity> search(MenuQueryDTO menu) {
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (menu.getId() != null) {
            wrapper.eq("id", menu.getId());
        }
        /* 菜单名 */
        if (!StrUtil.hasBlank(menu.getTitle())) {
            wrapper.eq("title", menu.getTitle());
        }
        /* 组件名 */
        if (!StrUtil.hasBlank(menu.getName())) {
            wrapper.eq("name", menu.getName());
        }
        /* 路由路径 */
        if (!StrUtil.hasBlank(menu.getPath())) {
            wrapper.eq("path", menu.getPath());
        }
        /* 类型 */
        if (!StrUtil.hasBlank(menu.getType())) {
            wrapper.eq("type", menu.getType());
        }
        /* 组件 */
        if (!StrUtil.hasBlank(menu.getComponent())) {
            wrapper.eq("component", menu.getComponent());
        }
        /* 权限标识 */
        if (!StrUtil.hasBlank(menu.getPermission())) {
            wrapper.eq("permission", menu.getPermission());
        }
        /* 图标 */
        if (!StrUtil.hasBlank(menu.getIcon())) {
            wrapper.eq("icon", menu.getIcon());
        }
        /* 描述 */
        if (!StrUtil.hasBlank(menu.getDescription())) {
            wrapper.eq("description", menu.getDescription());
        }
        /* 其他信息 */
        if (!StrUtil.hasBlank(menu.getMeta())) {
            wrapper.eq("meta", menu.getMeta());
        }
        /* 状态 */
        if (!StrUtil.hasBlank(menu.getStatus())) {
            wrapper.eq("status", menu.getStatus());
        }
        /* 备注 */
        if (!StrUtil.hasBlank(menu.getRemark())) {
            wrapper.eq("remark", menu.getRemark());
        }
        /* 创建人 */
        if (!StrUtil.hasBlank(menu.getCreator())) {
            wrapper.eq("creator", menu.getCreator());
        }
        /* 更新人 */
        if (!StrUtil.hasBlank(menu.getUpdater())) {
            wrapper.eq("updater", menu.getUpdater());
        }
        /* 创建时间 */
        if (menu.getCreateTime() != null) {
            wrapper.like("create_time", menu.getCreateTime());
        }
        /* 更新时间 */
        if (menu.getUpdateTime() != null) {
            wrapper.eq("update_time", menu.getUpdateTime());
        }
        wrapper.orderByAsc("sort");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<MenuEntity>> onlyValidList(List<MenuEntity> menuData) {
        List<MenuEntity> menuList = queryList(new MenuQueryDTO());
        for (MenuEntity item : menuData) {
            Result<MenuEntity> menu = onlyValid(item, menuList);
            if (menu.getCode() != 200) {
                return Result.fail(menu.getMessage());
            }
        }
        return Result.success(menuList);
    }

    /**
     * 唯一性校验
     */
    default Result<MenuEntity> onlyValid(MenuEntity menu, List<MenuEntity> menuList) {

        return Result.success(menu);
    }

    /**
     * 分页查询
     */
    default PageList<MenuEntity> queryPage(MenuQueryDTO menu) {
        IPage<MenuEntity> pageParams = new Page<>(menu.getPage(), menu.getSize());
        return PageList.setPages(selectPage(pageParams, search(menu)));
    }

    /**
     * 列表查询
     */
    default List<MenuEntity> queryList(MenuQueryDTO menu) {
        return selectList(search(menu));
    }

    @Delete("DELETE FROM role_menu WHERE role_id = #{roleId}")
    void deleteRoleMenu(Long roleId);
}