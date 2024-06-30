package com.example.count.service;

import com.example.count.dal.dto.CountQueryDTO;
import com.example.count.dal.vo.CountQueryVO;
import com.example.framework.common.Result;

public interface CountService {
    /**
     * 获取统计后数据
     *
     * @param
     * @return 获取统计后数据
     */
    Result<CountQueryVO> getCount(CountQueryDTO count);
}
