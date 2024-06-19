package com.example.videos.controller;

import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.framework.utils.ExcelUtils;
import com.example.videos.convert.VideoConvert;
import com.example.videos.dal.dto.video.VideoQueryDTO;
import com.example.videos.dal.dto.video.VideoSaveDTO;
import com.example.videos.dal.entity.VideoEntity;
import com.example.videos.dal.vo.video.VideoDetailVO;
import com.example.videos.dal.vo.video.VideoExportVO;
import com.example.videos.dal.vo.video.VideoListVO;
import com.example.videos.dal.vo.video.VideoPageVO;
import com.example.videos.mapper.VideoMapper;
import com.example.videos.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "视频")
@CrossOrigin
@RequestMapping("/video")
public class VideoController {
    @Resource
    VideoService videoService;
    @Resource
    VideoMapper videoMapper;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public Result<PageList<VideoPageVO>> videoPage(@Valid VideoQueryDTO video) {
        return videoService.videoPage(video);
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public Result<List<VideoListVO>> videoList(@Valid VideoQueryDTO video) {
        return videoService.videoList(video);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情")
    public Result<VideoDetailVO> videoDetail(Long id) {
        return videoService.videoDetail(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增/修改")
    public Result<VideoEntity> videoSave(@RequestBody @Valid VideoSaveDTO video) {
        return videoService.videoSave(video);
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量新增/修改")
    public Result<List<VideoEntity>> videoSaveList(@RequestBody @Valid List<VideoSaveDTO> videoList) {
        return videoService.videoSaveList(videoList);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public Result<Object> videoDelete(@RequestBody List<Long> ids) {
        videoMapper.deleteBatchIds(ids);
        return Result.success("删除成功");
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入")
    public Result<List<VideoEntity>> videoImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<VideoSaveDTO> videoList = VideoConvert.INSTANCE.imports(ExcelUtils.imports(multipartFile.getInputStream(), VideoExportVO.class));
        return videoService.videoSaveList(videoList);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void videoExport(HttpServletResponse response, VideoQueryDTO video) throws IOException {
        videoService.videoExport(response, video);
    }
}