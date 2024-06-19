package com.example.videos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.videos.convert.VideoConvert;
import com.example.videos.dal.dto.video.VideoQueryDTO;
import com.example.videos.dal.dto.video.VideoSaveDTO;
import com.example.videos.dal.entity.VideoEntity;
import com.example.videos.dal.vo.video.VideoExportVO;
import com.example.videos.mapper.VideoMapper;
import com.example.videos.dal.vo.video.VideoDetailVO;
import com.example.videos.dal.vo.video.VideoListVO;
import com.example.videos.dal.vo.video.VideoPageVO;
import com.example.videos.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, VideoEntity> implements VideoService {
    @Resource
    VideoMapper videoMapper;

    /**
     * 获取列表分页
     *
     * @param video 分页入参
     * @return 列表分页
     */
    @Override
    public Result<PageList<VideoPageVO>> videoPage(VideoQueryDTO video) {
        return Result.success(VideoConvert.INSTANCE.page(videoMapper.queryPage(video)));
    }

    /**
     * 获取列表
     *
     * @param video 入参
     * @return 列表
     */
    @Override
    public Result<List<VideoListVO>> videoList(VideoQueryDTO video) {
        return Result.success(VideoConvert.INSTANCE.list(videoMapper.queryList(video)));
    }

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    @Override
    public Result<VideoDetailVO> videoDetail(Long id) {
        return Result.success(VideoConvert.INSTANCE.detail(videoMapper.selectById(id)));
    }

    /**
     * 新增/修改
     *
     * @param video 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<VideoEntity> videoSave(VideoSaveDTO video) {
        VideoEntity videoData = VideoConvert.INSTANCE.save(video);
        List<VideoEntity> videoList = videoMapper.queryList(new VideoQueryDTO());
        Result<VideoEntity> valid = videoMapper.onlyValid(videoData, videoList);
        if (valid.getCode() == 200) {
            this.saveOrUpdate(videoData);
            return Result.success(videoData);
        } else {
            return valid;
        }
    }

    /**
     * 批量新增/修改
     *
     * @param video 新增/修改内容
     * @return 新增/修改后数据
     */
    @Override
    public Result<List<VideoEntity>> videoSaveList(List<VideoSaveDTO> video) {
        List<VideoEntity> videoData = VideoConvert.INSTANCE.saveList(video);
        Result<List<VideoEntity>> valid = videoMapper.onlyValidList(videoData);
        if (valid.getCode() == 200) {
            this.saveOrUpdateBatch(videoData);
            return Result.success(videoData);
        } else {
            return valid;
        }
    }

    /**
     * 导出
     *
     * @param response,video 入参
     */
    @Override
    public void videoExport(HttpServletResponse response, VideoQueryDTO video) throws IOException {
        ExcelUtils.export(response, "视频.xlsx", "视频", VideoExportVO.class, VideoConvert.INSTANCE.export(videoMapper.queryList(video)));
    }
}