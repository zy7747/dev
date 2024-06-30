package com.example.count.controller;


import com.example.count.dal.dto.CountQueryDTO;
import com.example.count.dal.vo.CountQueryVO;
import com.example.count.service.CountService;
import com.example.framework.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@CrossOrigin
@Validated
@Api(tags = "数据统计接口")
@RequestMapping("/count")
public class CountController {
    @Resource
    CountService countService;

    @GetMapping("/get")
    @ApiOperation(value = "统计数据")
    public Result<CountQueryVO> getCount(CountQueryDTO count) {
        return countService.getCount(count);
    }
}
