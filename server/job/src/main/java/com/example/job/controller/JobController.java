package com.example.job.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.job.convert.JobConvert;
import com.example.job.dal.dto.job.JobQueryDTO;
import com.example.job.dal.dto.job.JobSaveDTO;
import com.example.job.dal.entity.JobEntity;
import com.example.job.dal.vo.job.JobDetailVO;
import com.example.job.dal.vo.job.JobExportVO;
import com.example.job.dal.vo.job.JobListVO;
import com.example.job.dal.vo.job.JobPageVO;
import com.example.job.mapper.JobMapper;
import com.example.job.service.JobService;
import com.example.job.utils.TaskUtil;
import com.example.system.annotation.log.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.system.enums.OperateType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "定时任务")
@CrossOrigin
@RequestMapping("/job")
public class JobController {
    @Resource
    JobService jobService;
    @Resource
    JobMapper jobMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<JobPageVO>> jobPage(@Valid JobQueryDTO job) {
        return jobService.jobPage(job);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<JobListVO>> jobList(@Valid JobQueryDTO job) {
        return jobService.jobList(job);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<JobDetailVO> jobDetail(Long id) {
        return jobService.jobDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    @PreAuthorize("hasAuthority('job:change')")
    @Log(title = "定时任务新增/修改", module = "定时任务模块", content = "定时任务新增/修改", type = OperateType.INSERT)
    public Result<JobEntity> jobSave(@RequestBody @Valid JobSaveDTO job) {
        return jobService.jobSave(job);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    @PreAuthorize("hasAuthority('job:change')")
    @Log(title = "定时任务新增/修改", module = "定时任务模块", content = "定时任务新增/修改", type = OperateType.INSERT)
    public Result<List<JobEntity>> jobSaveList(@RequestBody @Valid List<JobSaveDTO> jobList) {
        return jobService.jobSaveList(jobList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('job:remove')")
    @Log(title = "定时任务删除", module = "定时任务模块", content = "定时任务删除", type = OperateType.DELETE)
    public Result<Object> jobDelete(@RequestBody List<Long> ids) {
        //删除定时器
        ids.forEach(id -> {
            JobEntity job = jobMapper.selectById(id);

            try {
                TaskUtil.removeTask(job.getJobCode());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        });
        jobMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    @PreAuthorize("hasAuthority('job:change')")
    @Log(title = "定时任务导入", module = "定时任务模块", content = "定时任务导入", type = OperateType.IMPORT)
    public Result<List<JobEntity>> jobImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<JobSaveDTO> jobList = JobConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), JobExportVO.class));
        return jobService.jobSaveList(jobList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void jobExport(HttpServletResponse response, JobQueryDTO job) throws IOException {
        jobService.jobExport(response, job);
    }

    @GetMapping("/changeJob")
    @ApiOperation(value = "修改定时器状态")
    public Result<Object> changeJobStatusService(JobSaveDTO job) {
        return jobService.changeJobStatusService(job);
    }
}