package com.example.count.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.count.dal.dto.CountQueryDTO;
import com.example.count.dal.vo.CountQueryVO;
import com.example.count.service.CountService;
import com.example.framework.common.Result;
import com.example.system.dal.entity.UserEntity;
import com.example.system.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class CountServiceImpl implements CountService {
    @Resource
    UserMapper userMapper;


    //统计用户
    public int getUserCount() {
        QueryWrapper<UserEntity> userWrapper = new QueryWrapper<>();

        List<UserEntity> userList = userMapper.selectList(userWrapper);

        return userList.size();
    }


    /**
     * 获取统计后数据
     *
     * @param
     * @return 获取统计后数据
     */
    @Override
    public Result<CountQueryVO> getCount(CountQueryDTO count) {
        //所有统计数据
        CountQueryVO statistics = new CountQueryVO();

        statistics.setUserTotal(getUserCount());

        return Result.success(statistics);
    }
}
