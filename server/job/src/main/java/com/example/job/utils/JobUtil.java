package com.example.job.utils;

import com.example.job.job.ClearDataJob;
import com.example.job.job.PrintJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

@Slf4j
public class JobUtil implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        String jobName = context.getJobDetail().getKey().getName();
        //清除没有用的数据
        if (jobName.equals("ClearDataJob")) {
            ClearDataJob clearDataJo = new ClearDataJob();
            clearDataJo.clearRole();
            clearDataJo.clearMenu();
            clearDataJo.clearUser();
            clearDataJo.clearDict();
            new PrintJob().printTest("定时清理任务执行时间：");
        }
    }
}