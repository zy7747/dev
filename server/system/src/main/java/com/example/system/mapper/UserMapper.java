package com.example.system.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.system.dal.dto.user.UserQueryDTO;
import com.example.system.dal.dto.user.UserSaveDTO;
import com.example.system.dal.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

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
        /* 密码 */
        if (!StrUtil.hasBlank(user.getPassword())) {
            wrapper.eq("password", user.getPassword());
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
     * 唯一性校验
     */
    default String onlyValid(UserSaveDTO user) {
        //一次查出所以数据
        List<UserEntity> userList = queryList(new UserQueryDTO());
        for (UserEntity item : userList) {
            //修改跳过自己
            if (user.getId() != null && item.getId().equals(user.getId())) {
                continue;
            }
            if (!StrUtil.hasBlank(user.getPhone()) && Objects.equals(user.getPhone(), item.getPhone())) {
                return "电话号码已被注册";
            }
            if (!StrUtil.hasBlank(user.getEmail()) && Objects.equals(user.getEmail(), item.getEmail())) {
                return "电子邮箱已被注册";
            }
            if (!StrUtil.hasBlank(user.getAccount()) && Objects.equals(user.getAccount(), item.getAccount())) {
                return "账号已被注册";
            }
            if (!StrUtil.hasBlank(user.getUser()) && Objects.equals(user.getUser(), item.getUser())) {
                return "用户名已被注册";
            }
            if (!StrUtil.hasBlank(user.getNickname()) && Objects.equals(user.getNickname(), item.getNickname())) {
                return "昵称已被注册";
            }

        }
        return "新增成功";
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
}
