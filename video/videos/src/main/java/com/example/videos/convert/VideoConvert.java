package com.example.videos.convert;

import com.example.framework.common.PageList;
import com.example.videos.dal.dto.video.VideoSaveDTO;
import com.example.videos.dal.entity.VideoEntity;
import com.example.videos.dal.vo.video.VideoDetailVO;
import com.example.videos.dal.vo.video.VideoExportVO;
import com.example.videos.dal.vo.video.VideoListVO;
import com.example.videos.dal.vo.video.VideoPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VideoConvert {
    VideoConvert INSTANCE = Mappers.getMapper(VideoConvert.class);

    PageList<VideoPageVO> page(PageList<VideoEntity> video);

    List<VideoListVO> list(List<VideoEntity> video);

    VideoDetailVO detail(VideoEntity video);

    VideoEntity save(VideoSaveDTO video);

    List<VideoEntity> saveList(List<VideoSaveDTO> video);

    List<VideoExportVO> export(List<VideoEntity> video);

    List<VideoSaveDTO> imports(List<VideoExportVO> video);
}