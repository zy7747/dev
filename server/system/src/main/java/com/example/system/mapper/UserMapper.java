package com.example.system.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.user.UserQueryDTO;
import com.example.system.dal.entity.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Objects;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<UserEntity> search(UserQueryDTO user) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();

        /* id */
        if (user.getId() != null) {
            wrapper.eq("id", user.getId());
        }
        /* 用户id */
        if (user.getUid() != null) {
            wrapper.eq("uid", user.getUid());
        }
        /* 电话号码 */
        if (!StrUtil.hasBlank(user.getPhone())) {
            wrapper.eq("phone", user.getPhone());
        }
        /* 电子邮箱 */
        if (!StrUtil.hasBlank(user.getEmail())) {
            wrapper.eq("email", user.getEmail());
        }
        /* 账号 */
        if (!StrUtil.hasBlank(user.getAccount())) {
            wrapper.eq("account", user.getAccount());
        }
        /* 用户名 */
        if (!StrUtil.hasBlank(user.getUser())) {
            wrapper.eq("user", user.getUser());
        }
        /* 昵称 */
        if (!StrUtil.hasBlank(user.getNickname())) {
            wrapper.eq("nickname", user.getNickname());
        }
        /* 头像 */
        if (!StrUtil.hasBlank(user.getAvatar())) {
            wrapper.eq("avatar", user.getAvatar());
        }
        /* 类型 */
        if (!StrUtil.hasBlank(user.getUserType())) {
            wrapper.eq("user_type", user.getUserType());
        }
        /* 状态 */
        if (!StrUtil.hasBlank(user.getStatus())) {
            wrapper.eq("status", user.getStatus());
        }
        /* 备注 */
        if (!StrUtil.hasBlank(user.getRemark())) {
            wrapper.eq("remark", user.getRemark());
        }
        /* 创建人 */
        if (!StrUtil.hasBlank(user.getCreator())) {
            wrapper.eq("creator", user.getCreator());
        }
        /* 更新人 */
        if (!StrUtil.hasBlank(user.getUpdater())) {
            wrapper.eq("updater", user.getUpdater());
        }
        /* 创建时间 */
        if (user.getCreateTime() != null) {
            wrapper.like("create_time", user.getCreateTime());
        }
        /* 更新时间 */
        if (user.getUpdateTime() != null) {
            wrapper.eq("update_time", user.getUpdateTime());
        }

        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<UserEntity>> onlyValidList(List<UserEntity> userData) {
        List<UserEntity> userList = queryList(new UserQueryDTO());

        for (UserEntity item : userData) {
            Result<UserEntity> user = onlyValid(item, userList);
            if (user.getCode() != 200) {
                return Result.fail(user.getMessage());
            }
        }
        return Result.success(userList);
    }

    /**
     * 唯一性校验
     */
    default Result<UserEntity> onlyValid(UserEntity user, List<UserEntity> userList) {

        for (UserEntity item : userList) {
            //修改跳过自己
            if (user.getId() != null && item.getId().equals(user.getId())) {
                continue;
            }
            if (!StrUtil.hasBlank(user.getPhone()) && Objects.equals(user.getPhone(), item.getPhone())) {
                return Result.fail("电话号码已被注册");
            }
            if (!StrUtil.hasBlank(user.getEmail()) && Objects.equals(user.getEmail(), item.getEmail())) {
                return Result.fail("电子邮箱已被注册");
            }
            if (!StrUtil.hasBlank(user.getAccount()) && Objects.equals(user.getAccount(), item.getAccount())) {
                return Result.fail("账号已被注册");
            }
            if (!StrUtil.hasBlank(user.getUser()) && Objects.equals(user.getUser(), item.getUser())) {
                return Result.fail("用户名已被注册");
            }
            if (!StrUtil.hasBlank(user.getNickname()) && Objects.equals(user.getNickname(), item.getNickname())) {
                return Result.fail("昵称已被注册");
            }
        }

        return Result.success(user);
    }

    /**
     * 分页查询
     */
    default PageList<UserEntity> queryPage(UserQueryDTO user) {
        IPage<UserEntity> pageParams = new Page<>(user.getPage(), user.getSize());

        return PageList.setPages(selectPage(pageParams, search(user)));
    }

    /**
     * 列表查询
     */
    default List<UserEntity> queryList(UserQueryDTO user) {
        return selectList(search(user));
    }


    @Select("SELECT * FROM user_role WHERE user_id = #{userId}")
    List<UserRoleEntity> selectUserRole(Long userId);

    @Select("SELECT * FROM user_permission WHERE user_id = #{userId}")
    List<UserPermissionEntity> selectUserPermission(Long userId);

    @Select("SELECT * FROM role JOIN user_role ur on role.id = ur.role_id WHERE user_id = #{userId} AND is_deleted = 0")
    List<RoleEntity> selectRole(Long userId);

    @Select("SELECT * FROM permission JOIN user_permission ur on permission.id = ur.permission_id WHERE user_id = #{userId} AND is_deleted = 0")
    List<PermissionEntity> selectPermission(Long userId);

    @Select("SELECT * FROM menu JOIN role_menu rm on menu.id = rm.menu_id WHERE role_id = #{roleId} AND is_deleted = 0")
    List<MenuEntity> selectMenu(Long roleId);

    @Delete("DELETE FROM user_role WHERE user_id = #{userId}")
    void deleteUserRole(Long userId);

    @Delete("DELETE FROM user_permission WHERE user_id = #{userId}")
    void deleteUserPermission(Long userId);

    @Insert("INSERT INTO user_role(user_id, role_id) VALUES (#{userId}, #{roleId})")
    void insertUserRole(Long userId, Long roleId);

    @Insert("INSERT INTO user_permission(user_id, permission_id) VALUES (#{userId}, #{permissionId})")
    void insertUserPermission(Long userId, Long permissionId);

    @Delete("DELETE FROM user WHERE is_deleted = 1")
    void clearUser();
}
