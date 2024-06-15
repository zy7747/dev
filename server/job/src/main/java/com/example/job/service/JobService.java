package com.example.job.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.job.dal.dto.job.JobQueryDTO;
import com.example.job.dal.dto.job.JobSaveDTO;
import com.example.job.dal.entity.JobEntity;
import com.example.job.dal.vo.job.JobDetailVO;
import com.example.job.dal.vo.job.JobListVO;
import com.example.job.dal.vo.job.JobPageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface JobService {
    /**
     * 获取列表分页
     *
     * @param job 分页入参
     * @return 列表分页
     */
    Result<PageList<JobPageVO>> jobPage(JobQueryDTO job);

    /**
     * 获取列表
     *
     * @param job 入参
     * @return 列表
     */
    Result<List<JobListVO>> jobList(JobQueryDTO job);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<JobDetailVO> jobDetail(Long id);

    /**
     * 新增/修改
     *
     * @param job 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<JobEntity> jobSave(JobSaveDTO job);

    /**
     * 批量新增/修改
     *
     * @param job 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<JobEntity>> jobSaveList(@Valid List<JobSaveDTO> job);

    /**
     * 导出
     *
     * @param response,job 入参
     */
    void jobExport(HttpServletResponse response, JobQueryDTO job) throws IOException;

    /**
     * 暂停或重新开启定时器
     *
     * @param job 状态
     * @return 修改后提示
     */
    Result<Object> changeJobStatusService(JobSaveDTO job);
}