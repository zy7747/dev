package com.example.job.job;

import com.example.framework.utils.SpringUtils;
import com.example.system.mapper.DictMapper;
import com.example.system.mapper.MenuMapper;
import com.example.system.mapper.RoleMapper;
import com.example.system.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClearDataJob {


    MenuMapper menuMapper = SpringUtils.getBean(MenuMapper.class);
    RoleMapper roleMapper = SpringUtils.getBean(RoleMapper.class);
    UserMapper userMapper = SpringUtils.getBean(UserMapper.class);
    DictMapper dictMapper = SpringUtils.getBean(DictMapper.class);

    public void clearRole() {
        roleMapper.clearRole();
    }

    public void clearMenu() {
        menuMapper.clearMenu();
    }

    public void clearUser() {
        userMapper.clearUser();
    }

    public void clearDict() {
        dictMapper.clearDict();
    }


}
