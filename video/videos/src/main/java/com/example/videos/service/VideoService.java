package com.example.videos.service;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.videos.dal.dto.video.VideoQueryDTO;
import com.example.videos.dal.dto.video.VideoSaveDTO;
import com.example.videos.dal.entity.VideoEntity;
import com.example.videos.dal.vo.video.VideoDetailVO;
import com.example.videos.dal.vo.video.VideoListVO;
import com.example.videos.dal.vo.video.VideoPageVO;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
public interface VideoService {
    /**
     * 获取列表分页
     *
     * @param video 分页入参
     * @return 列表分页
     */
    Result<PageList<VideoPageVO>> videoPage(VideoQueryDTO video);

    /**
     * 获取列表
     *
     * @param video 入参
     * @return 列表
     */
    Result<List<VideoListVO>> videoList(VideoQueryDTO video);

    /**
     * 获取详情
     *
     * @param id 入参
     * @return 详情
     */
    Result<VideoDetailVO> videoDetail(Long id);

    /**
     * 新增/修改
     *
     * @param video 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<VideoEntity> videoSave(VideoSaveDTO video);

    /**
     * 批量新增/修改
     *
     * @param video 新增/修改内容
     * @return 新增/修改后数据
     */
    Result<List<VideoEntity>> videoSaveList(@Valid List<VideoSaveDTO> video);

    /**
     * 导出
     *
     * @param response,video 入参
     */
    void videoExport(HttpServletResponse response, VideoQueryDTO video) throws IOException;
}