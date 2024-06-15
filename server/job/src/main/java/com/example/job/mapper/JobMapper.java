package com.example.job.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.job.dal.dto.job.JobQueryDTO;
import com.example.job.dal.entity.JobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

@Mapper
public interface JobMapper extends BaseMapper<JobEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<JobEntity> search(JobQueryDTO job) {
        QueryWrapper<JobEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (job.getId() != null) {
            wrapper.eq("id", job.getId());
        }

        /* 任务名称 */
        if (!StrUtil.hasBlank(job.getJobName())) {
            wrapper.eq("job_name", job.getJobName());
        }
        /* 任务编码 */
        if (!StrUtil.hasBlank(job.getJobCode())) {
            wrapper.eq("job_code", job.getJobCode());
        }
        /* cron表达式 */
        if (!StrUtil.hasBlank(job.getCron())) {
            wrapper.eq("cron", job.getCron());
        }
        /* 创建时间 */
        if (job.getCreateTime() != null) {
            wrapper.like("create_time", job.getCreateTime());
        }
        /* 更新时间 */
        if (job.getUpdateTime() != null) {
            wrapper.eq("update_time", job.getUpdateTime());
        }
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<JobEntity>> onlyValidList(List<JobEntity> jobData) {
        List<JobEntity> jobList = queryList(new JobQueryDTO());
        for (JobEntity item : jobData) {
            Result<JobEntity> job = onlyValid(item, jobList);
            if (job.getCode() != 200) {
                return Result.fail(job.getMessage());
            }
        }
        return Result.success(jobList);
    }

    /**
     * 唯一性校验
     */
    default Result<JobEntity> onlyValid(JobEntity job, List<JobEntity> jobList) {
        for (JobEntity item : jobList) {
            //修改跳过自己
            if (job.getId() != null && item.getId().equals(job.getId())) {
                continue;
            }

            if (!StrUtil.hasBlank(job.getJobName()) && Objects.equals(job.getJobName(), item.getJobName())) {
                return Result.fail("任务名称已被注册");
            }
            if (!StrUtil.hasBlank(job.getJobCode()) && Objects.equals(job.getJobCode(), item.getJobCode())) {
                return Result.fail("任务编码已被注册");
            }
        }
        return Result.success(job);
    }

    /**
     * 分页查询
     */
    default PageList<JobEntity> queryPage(JobQueryDTO job) {
        IPage<JobEntity> pageParams = new Page<>(job.getPage(), job.getSize());
        return PageList.setPages(selectPage(pageParams, search(job)));
    }

    /**
     * 列表查询
     */
    default List<JobEntity> queryList(JobQueryDTO job) {
        return selectList(search(job));
    }
}