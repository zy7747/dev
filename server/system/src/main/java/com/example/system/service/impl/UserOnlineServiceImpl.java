package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.system.convert.UserOnlineConvert;
import com.example.system.dal.dto.userOnline.UserOnlineQueryDTO;
import com.example.system.dal.dto.userOnline.UserOnlineSaveDTO;
import com.example.system.dal.entity.UserOnlineEntity;
import com.example.system.dal.vo.userOnline.UserOnlineExportVO;
import com.example.system.mapper.UserOnlineMapper;
import com.example.system.dal.vo.userOnline.UserOnlineDetailVO;
import com.example.system.dal.vo.userOnline.UserOnlineListVO;
import com.example.system.dal.vo.userOnline.UserOnlinePageVO;
import com.example.system.service.UserOnlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class UserOnlineServiceImpl extends ServiceImpl<UserOnlineMapper, UserOnlineEntity> implements UserOnlineService {
    @Resource
    UserOnlineMapper userOnlineMapper;

    /**
     * 获取列表分页
     *
     * @param userOnline 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<UserOnlinePageVO>> userOnlinePage(UserOnlineQueryDTO userOnline) {
        return Result.success(UserOnlineConvert.INSTANCE.page(userOnlineMapper.queryPage(userOnline)));
    }

    /**
     * 获取列表
     *
     * @param userOnline 入参
     * @return 列表
     */
    @Override
    public Result<List<UserOnlineListVO>> userOnlineList(UserOnlineQueryDTO userOnline) {
        return Result.success(UserOnlineConvert.INSTANCE.list(userOnlineMapper.queryList(userOnline)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<UserOnlineDetailVO> userOnlineDetail(Long id) {
        return Result.success(UserOnlineConvert.INSTANCE.detail(userOnlineMapper.selectById(id)));
    }

    /**
     * 新增/修改
     *
     * @param userOnline 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<UserOnlineEntity> userOnlineSave(UserOnlineSaveDTO userOnline) {
        UserOnlineEntity userOnlineData = UserOnlineConvert.INSTANCE.save(userOnline);
        List<UserOnlineEntity> userOnlineList = userOnlineMapper.queryList(new UserOnlineQueryDTO());
        Result<UserOnlineEntity> valid = userOnlineMapper.onlyValid(userOnlineData, userOnlineList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(userOnlineData);
            return Result.success(userOnlineData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param userOnline 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<UserOnlineEntity>> userOnlineSaveList(List<UserOnlineSaveDTO> userOnline) {
        List<UserOnlineEntity> userOnlineData = UserOnlineConvert.INSTANCE.saveList(userOnline);
        Result<List<UserOnlineEntity>> valid = userOnlineMapper.onlyValidList(userOnlineData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(userOnlineData);
            return Result.success(userOnlineData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,userOnline 入参
     */
    @Override
    public void userOnlineExport(HttpServletResponse response, UserOnlineQueryDTO userOnline) throws IOException {
        ExcelUtils.export(response, "在线用户.xlsx", "在线用户", UserOnlineExportVO.class, UserOnlineConvert.INSTANCE.export(userOnlineMapper.queryList(userOnline)));
    }
}