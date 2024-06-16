package com.example.system.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.system.dal.dto.userOnline.UserOnlineQueryDTO;
import com.example.system.dal.dto.userOnline.UserOnlineSaveDTO;
import com.example.system.dal.entity.UserOnlineEntity;
import com.example.system.dal.vo.userOnline.UserOnlineDetailVO;
import com.example.system.dal.vo.userOnline.UserOnlineListVO;
import com.example.system.dal.vo.userOnline.UserOnlinePageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface UserOnlineService {
    /**
     * 获取列表分页
     *
     * @param userOnline 分页入参
     * @return 列表分页
     */
    Result<PageList<UserOnlinePageVO>> userOnlinePage(UserOnlineQueryDTO userOnline);

    /**
     * 获取列表
     *
     * @param userOnline 入参
     * @return 列表
     */
    Result<List<UserOnlineListVO>> userOnlineList(UserOnlineQueryDTO userOnline);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<UserOnlineDetailVO> userOnlineDetail(Long id);

    /**
     * 新增/修改
     *
     * @param userOnline 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<UserOnlineEntity> userOnlineSave(UserOnlineSaveDTO userOnline);

    /**
     * 批量新增/修改
     *
     * @param userOnline 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<UserOnlineEntity>> userOnlineSaveList(@Valid List<UserOnlineSaveDTO> userOnline);

    /**
     * 导出
     *
     * @param response,userOnline 入参
     */
    void userOnlineExport(HttpServletResponse response, UserOnlineQueryDTO userOnline) throws IOException;
}