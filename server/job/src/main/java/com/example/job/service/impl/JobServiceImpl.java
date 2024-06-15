package com.example.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.job.convert.JobConvert;
import com.example.job.dal.dto.job.JobQueryDTO;
import com.example.job.dal.dto.job.JobSaveDTO;
import com.example.job.dal.entity.JobEntity;
import com.example.job.dal.vo.job.JobExportVO;
import com.example.job.mapper.JobMapper;
import com.example.job.dal.vo.job.JobDetailVO;
import com.example.job.dal.vo.job.JobListVO;
import com.example.job.dal.vo.job.JobPageVO;
import com.example.job.service.JobService;
import com.example.job.utils.TaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, JobEntity> implements JobService {
    @Resource
    JobMapper jobMapper;

    /**
     * 获取列表分页
     *
     * @param job 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<JobPageVO>> jobPage(JobQueryDTO job) {
        return Result.success(JobConvert.INSTANCE.page(jobMapper.queryPage(job)));
    }

    /**
     * 获取列表
     *
     * @param job 入参
     * @return 列表
     */
    @Override
    public Result<List<JobListVO>> jobList(JobQueryDTO job) {
        return Result.success(JobConvert.INSTANCE.list(jobMapper.queryList(job)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<JobDetailVO> jobDetail(Long id) {
        return Result.success(JobConvert.INSTANCE.detail(jobMapper.selectById(id)));
    }

    /**
     * 新增/修改
     *
     * @param job 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<JobEntity> jobSave(JobSaveDTO job) {
        JobEntity jobData = JobConvert.INSTANCE.save(job);
        List<JobEntity> jobList = jobMapper.queryList(new JobQueryDTO());
        Result<JobEntity> valid = jobMapper.onlyValid(jobData, jobList);
        if (valid.getCode() == 200) {
            changeJobStatusService(job);
            this.saveOrUpdate(jobData);
            return Result.success(jobData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param job 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<JobEntity>> jobSaveList(List<JobSaveDTO> job) {
        List<JobEntity> jobData = JobConvert.INSTANCE.saveList(job);
        Result<List<JobEntity>> valid = jobMapper.onlyValidList(jobData);
        if (valid.getCode() == 200) {

            this.saveOrUpdateBatch(jobData);
            return Result.success(jobData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,job 入参
     */
    @Override
    public void jobExport(HttpServletResponse response, JobQueryDTO job) throws IOException {
        ExcelUtils.export(response, "定时任务.xlsx", "定时任务", JobExportVO.class, JobConvert.INSTANCE.export(jobMapper.queryList(job)));
    }

    /**
     * 暂停或重新开启定时器
     *
     * @param job 状态
     * @return 修改后提示
     */
    @Override
    public Result<Object> changeJobStatusService(JobSaveDTO job) {
        //如果要改变的状态为运行
        if (job.getStatus().equals("run")) {
            try {
                TaskUtil.runTask(job.getJobCode());
                //重新定义开始时间
                job.setStartTime(new Date());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
            //暂停定时器
        } else if (job.getStatus().equals("paused")) {
            try {
                TaskUtil.stopTask(job.getJobCode());

            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }

        return Result.success("操作成功");
    }
}