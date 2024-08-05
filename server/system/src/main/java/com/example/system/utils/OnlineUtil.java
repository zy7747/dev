package com.example.system.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.framework.utils.SpringUtils;
import com.example.system.dal.dto.user.LoginDTO;
import com.example.system.dal.dto.user.LogoutDTO;
import com.example.system.dal.dto.userOnline.UserOnlineSaveDTO;
import com.example.system.dal.entity.UserEntity;
import com.example.system.dal.entity.UserOnlineEntity;
import com.example.system.mapper.UserOnlineMapper;
import com.example.system.service.UserOnlineService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class OnlineUtil {

    UserOnlineService userOnlineService = SpringUtils.getBean(UserOnlineService.class);
    UserOnlineMapper userOnlineMapper = SpringUtils.getBean(UserOnlineMapper.class);

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    public UserOnlineEntity setUserOnlineInfoSuccess(UserEntity userInfo, LoginDTO loginInfo) {

        //先查一次是否在数据库中
        QueryWrapper<UserOnlineEntity> userOnlineWrapper = new QueryWrapper<>();
        userOnlineWrapper.eq("uid", userInfo.getId());

        List<UserOnlineEntity> userOnlineList = userOnlineMapper.selectList(userOnlineWrapper);

        UserOnlineSaveDTO userOnlineInfo = new UserOnlineSaveDTO();

        if (userOnlineList.size() > 0) {
            BeanUtils.copyProperties(userOnlineList.get(0), userOnlineInfo);
        }

        //IP
        HttpServletRequest request = getRequest();
        String ip = IPUtil.getIp(request);

        userOnlineInfo.setUid(userInfo.getId());
        userOnlineInfo.setStatus("Online");
        userOnlineInfo.setAccount(userInfo.getAccount());
        userOnlineInfo.setOperateResults("success");
        userOnlineInfo.setLoginIp(ip);

        //物理地址
        String cityInfo = null;
        try {
            cityInfo = IPUtil.getAdd(ip);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        userOnlineInfo.setLoginPlace(cityInfo);
        userOnlineInfo.setLoginDevice(loginInfo.getLoginDevice());


        return userOnlineService.userOnlineSave(userOnlineInfo).getData();


    }


    public void Logout(LogoutDTO logoutInfo) {
        UserOnlineEntity userOnline = userOnlineMapper.selectById(logoutInfo.getId());

        userOnline.setStatus("Offline");

        userOnlineMapper.updateById(userOnline);
    }

}
