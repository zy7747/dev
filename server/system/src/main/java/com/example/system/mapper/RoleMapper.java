package com.example.system.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.role.RoleQueryDTO;
import com.example.system.dal.entity.RoleEntity;
import com.example.system.dal.entity.RoleMenuEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Objects;

@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<RoleEntity> search(RoleQueryDTO role) {
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (role.getId() != null) {
            wrapper.eq("id", role.getId());
        }
        /* 角色名称 */
        if (!StrUtil.hasBlank(role.getRoleName())) {
            wrapper.eq("role_name", role.getRoleName());
        }
        /* 角色编码 */
        if (!StrUtil.hasBlank(role.getRoleCode())) {
            wrapper.eq("role_code", role.getRoleCode());
        }
        /* 备注 */
        if (!StrUtil.hasBlank(role.getRemark())) {
            wrapper.eq("remark", role.getRemark());
        }
        /* 创建人 */
        if (!StrUtil.hasBlank(role.getCreator())) {
            wrapper.eq("creator", role.getCreator());
        }
        /* 更新人 */
        if (!StrUtil.hasBlank(role.getUpdater())) {
            wrapper.eq("updater", role.getUpdater());
        }
        /* 创建时间 */
        if (role.getCreateTime() != null) {
            wrapper.like("create_time", role.getCreateTime());
        }
        /* 更新时间 */
        if (role.getUpdateTime() != null) {
            wrapper.eq("update_time", role.getUpdateTime());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<RoleEntity>> onlyValidList(List<RoleEntity> roleData) {
        List<RoleEntity> roleList = queryList(new RoleQueryDTO());
        for (RoleEntity item : roleData) {
            Result<RoleEntity> role = onlyValid(item, roleList);
            if (role.getCode() != 200) {
                return Result.fail(role.getMessage());
            }
        }
        return Result.success(roleList);
    }

    /**
     * 唯一性校验
     */
    default Result<RoleEntity> onlyValid(RoleEntity role, List<RoleEntity> roleList) {

        for (RoleEntity item : roleList) {
            //修改跳过自己
            if (role.getId() != null && item.getId().equals(role.getId())) {
                continue;
            }

            if (!StrUtil.hasBlank(role.getRoleName()) && Objects.equals(role.getRoleName(), item.getRoleName())) {
                return Result.fail("角色名称已被注册");
            }
            if (!StrUtil.hasBlank(role.getRoleCode()) && Objects.equals(role.getRoleCode(), item.getRoleCode())) {
                return Result.fail("角色编码已被注册");
            }
        }

        return Result.success(role);
    }

    /**
     * 分页查询
     */
    default PageList<RoleEntity> queryPage(RoleQueryDTO role) {
        IPage<RoleEntity> pageParams = new Page<>(role.getPage(), role.getSize());
        return PageList.setPages(selectPage(pageParams, search(role)));
    }

    /**
     * 列表查询
     */
    default List<RoleEntity> queryList(RoleQueryDTO role) {
        return selectList(search(role));
    }

    @Select("SELECT * FROM role_menu WHERE role_id = #{roleId}")
    List<RoleMenuEntity> selectRoleMenu(Long roleId);

    @Delete("DELETE FROM role_menu WHERE role_id = #{roleId}")
    void deleteRoleMenu(Long roleId);

    @Delete("DELETE FROM user_role WHERE role_id = #{roleId}")
    void deleteUserRole(Long roleId);

    @Insert("INSERT INTO role_menu(role_id, menu_id) VALUES (#{userId}, #{menuId})")
    void insertRoleMenu(Long userId, Long menuId);

    @Delete("DELETE FROM role WHERE is_deleted = 1")
    void clearRole();
}