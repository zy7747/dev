package com.example.system.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.userOnline.UserOnlineQueryDTO;
import com.example.system.dal.dto.userOnline.UserOnlineSaveDTO;
import com.example.system.dal.entity.UserOnlineEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper
public interface UserOnlineMapper extends BaseMapper<UserOnlineEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<UserOnlineEntity> search(UserOnlineQueryDTO userOnline) {
        QueryWrapper<UserOnlineEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (userOnline.getId() != null) {
            wrapper.eq("id", userOnline.getId());
        }

        /* 用户id */
        if (userOnline.getUid() != null) {
            wrapper.eq("uid", userOnline.getUid());
        }
        /* 账号 */
        if (!StrUtil.hasBlank(userOnline.getAccount())) {
            wrapper.eq("account", userOnline.getAccount());
        }
        /* 登录IP */
        if (!StrUtil.hasBlank(userOnline.getLoginIp())) {
            wrapper.eq("login_ip", userOnline.getLoginIp());
        }
        /* 登录IP */
        if (!StrUtil.hasBlank(userOnline.getStatus())) {
            wrapper.eq("status", userOnline.getStatus());
        }
        /* 登录地 */
        if (!StrUtil.hasBlank(userOnline.getLoginPlace())) {
            wrapper.eq("login_place", userOnline.getLoginPlace());
        }
        /* 登录设备 */
        if (!StrUtil.hasBlank(userOnline.getLoginDevice())) {
            wrapper.eq("login_device", userOnline.getLoginDevice());
        }
        /* 操作结果 */
        if (!StrUtil.hasBlank(userOnline.getOperateResults())) {
            wrapper.eq("operate_results", userOnline.getOperateResults());
        }
        /* 创建时间 */
        if (userOnline.getCreateTime() != null) {
            wrapper.like("create_time", userOnline.getCreateTime());
        }
        /* 更新时间 */
        if (userOnline.getUpdateTime() != null) {
            wrapper.eq("update_time", userOnline.getUpdateTime());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<UserOnlineEntity>> onlyValidList(List<UserOnlineEntity> userOnlineData) {
        List<UserOnlineEntity> userOnlineList = queryList(new UserOnlineQueryDTO());
        for (UserOnlineEntity item : userOnlineData) {
            Result<UserOnlineEntity> userOnline = onlyValid(item, userOnlineList);
            if (userOnline.getCode() != 200) {
                return Result.fail(userOnline.getMessage());
            }
        }
        return Result.success(userOnlineList);
    }

    /**
     * 唯一性校验
     */
    default Result<UserOnlineEntity> onlyValid(UserOnlineEntity userOnline, List<UserOnlineEntity> userOnlineList) {

        return Result.success(userOnline);
    }

    /**
     * 分页查询
     */
    default PageList<UserOnlineEntity> queryPage(UserOnlineQueryDTO userOnline) {
        IPage<UserOnlineEntity> pageParams = new Page<>(userOnline.getPage(), userOnline.getSize());
        return PageList.setPages(selectPage(pageParams, search(userOnline)));
    }

    /**
     * 列表查询
     */
    default List<UserOnlineEntity> queryList(UserOnlineQueryDTO userOnline) {
        return selectList(search(userOnline));
    }

}