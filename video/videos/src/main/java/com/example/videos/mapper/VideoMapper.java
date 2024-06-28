package com.example.videos.mapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.framework.common.PageList;
import com.example.framework.common.Result;
import com.example.videos.dal.dto.video.VideoQueryDTO;
import com.example.videos.dal.entity.VideoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper extends BaseMapper<VideoEntity> {
    /**
     * 查询条件
     */
    default QueryWrapper<VideoEntity> search(VideoQueryDTO video) {
        QueryWrapper<VideoEntity> wrapper = new QueryWrapper<>();
        /* id */
        if (video.getId() != null) {
            wrapper.eq("id", video.getId());
        }
        /* 视频存储节点 */
        if (video.getSavePath() != null) {
            wrapper.eq("save_path", video.getSavePath());
        }
        /* 视频名称 */
        if (!StrUtil.hasBlank(video.getVideoName())) {
            wrapper.like("video_name", video.getVideoName());
        }
        /* 视频标题 */
        if (!StrUtil.hasBlank(video.getTitle())) {
            wrapper.eq("title", video.getTitle());
        }
        /* 视频分类(大类) */
        if (!StrUtil.hasBlank(video.getCategory())) {
            wrapper.eq("category", video.getCategory());
        }
        /* 视频类型(中类) */
        if (!StrUtil.hasBlank(video.getType())) {
            wrapper.eq("type", video.getType());
        }
        /* 视频地区 */
        if (!StrUtil.hasBlank(video.getRegion())) {
            wrapper.eq("region", video.getRegion());
        }
        /* url地址 */
        if (!StrUtil.hasBlank(video.getUrl())) {
            wrapper.eq("url", video.getUrl());
        }
        /* 视频图片 */
        if (!StrUtil.hasBlank(video.getPicture())) {
            wrapper.eq("picture", video.getPicture());
        }
        /* 视频简介 */
        if (!StrUtil.hasBlank(video.getProfile())) {
            wrapper.eq("profile", video.getProfile());
        }
        /* 标签 */
        if (!StrUtil.hasBlank(video.getLabel())) {
            wrapper.eq("label", video.getLabel());
        }
        /* 作者 */
        if (!StrUtil.hasBlank(video.getAuthor())) {
            wrapper.eq("author", video.getAuthor());
        }
        /* 季 */
        if (video.getSeason() != null) {
            wrapper.eq("season", video.getSeason());
        }
        /* 集 */
        if (video.getEpisode() != null) {
            wrapper.eq("episode", video.getEpisode());
        }
        /* 播放量 */
        if (video.getPlayNum() != null) {
            wrapper.eq("play_num", video.getPlayNum());
        }
        /* 是否是集合 */
        if (video.getIsCollection() != null) {
            wrapper.eq("is_collection", video.getIsCollection());
        }
        /* 时长 */
        if (video.getDuration() != null) {
            wrapper.eq("duration", video.getDuration());
        }
        /* 创作年份 */
        if (video.getCreateYear() != null) {
            wrapper.eq("create_year", video.getCreateYear());
        }
        /* 创建时间 */
        if (video.getCreateTime() != null) {
            wrapper.like("create_time", video.getCreateTime());
        }
        /* 更新时间 */
        if (video.getUpdateTime() != null) {
            wrapper.eq("update_time", video.getUpdateTime());
        }

        /* 按月份查询 */
        if (video.getMonth() != null) {
            wrapper.apply("DATE_FORMAT(create_time, '%Y-%m') = {0}", video.getMonth());
        }

        //排序方式
        if (!StrUtil.hasBlank(video.getSortWay())) {
            if ("playNum".equals(video.getSortWay())) {
                wrapper.orderByDesc("play_num");
            } else {
                wrapper.orderByDesc("create_time");
            }
        } else {
            wrapper.orderByDesc("create_time");
        }

        wrapper.isNull("parent_id");
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    /**
     * 多列参数唯一性校验
     */
    default Result<List<VideoEntity>> onlyValidList(List<VideoEntity> videoData) {
        List<VideoEntity> videoList = queryList(new VideoQueryDTO());
        for (VideoEntity item : videoData) {
            Result<VideoEntity> video = onlyValid(item, videoList);
            if (video.getCode() != 200) {
                return Result.fail(video.getMessage());
            }
        }
        return Result.success(videoList);
    }

    /**
     * 唯一性校验
     */
    default Result<VideoEntity> onlyValid(VideoEntity video, List<VideoEntity> videoList) {
        for (VideoEntity item : videoList) {
            //修改跳过自己
            if (video.getId() != null && item.getId().equals(video.getId())) {
                continue;
            }
        }
        return Result.success(video);
    }

    /**
     * 分页查询
     */
    default PageList<VideoEntity> queryPage(VideoQueryDTO video) {
        IPage<VideoEntity> pageParams = new Page<>(video.getPage(), video.getSize());
        return PageList.setPages(selectPage(pageParams, search(video)));
    }

    /**
     * 列表查询
     */
    default List<VideoEntity> queryList(VideoQueryDTO video) {
        return selectList(search(video));
    }

    /**
     * 集合查询
     */
    default List<VideoEntity> queryDetailList(Long id, int isCollection) {
        QueryWrapper<VideoEntity> wrapper = new QueryWrapper<>();
        //  如果不是集合查自己
        if (isCollection == 0) {
            wrapper.eq("id", id);
        } else {
            wrapper.eq("parent_id", id);
        }


        return selectList(wrapper);
    }
}