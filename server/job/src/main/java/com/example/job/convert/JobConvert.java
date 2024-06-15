package com.example.job.convert;

import com.example.framework.common.PageList;
import com.example.job.dal.dto.job.JobSaveDTO;
import com.example.job.dal.entity.JobEntity;
import com.example.job.dal.vo.job.JobDetailVO;
import com.example.job.dal.vo.job.JobExportVO;
import com.example.job.dal.vo.job.JobListVO;
import com.example.job.dal.vo.job.JobPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JobConvert {
    JobConvert INSTANCE = Mappers.getMapper(JobConvert.class);

    PageList<JobPageVO> page(PageList<JobEntity> job);

    List<JobListVO> list(List<JobEntity> job);

    JobDetailVO detail(JobEntity job);

    JobEntity save(JobSaveDTO job);

    List<JobEntity> saveList(List<JobSaveDTO> job);

    List<JobExportVO> export(List<JobEntity> job);

    List<JobSaveDTO> imports(List<JobExportVO> job);
}