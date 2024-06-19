package com.example.videos.dal.dto.video;

import com.example.framework.dal.dto.BaseParamsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ApiModel(value = "保存")
@EqualsAndHashCode(callSuper = true)
public class VideoSaveDTO extends BaseParamsDTO {
    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id")
    private Long parentId;
    /**
     * 视频存储节点
     */
    @ApiModelProperty(value = "视频存储节点")
    private Long savePath;
    /**
     * 视频名称
     */
    @ApiModelProperty(value = "视频名称")
    private String videoName;
    /**
     * 视频标题
     */
    @ApiModelProperty(value = "视频标题")
    private String title;
    /**
     * 视频分类(大类)
     */
    @ApiModelProperty(value = "视频分类(大类)")
    private String category;
    /**
     * 视频类型(中类)
     */
    @ApiModelProperty(value = "视频类型(中类)")
    private String type;
    /**
     * 视频地区
     */
    @ApiModelProperty(value = "视频地区")
    private String region;
    /**
     * url地址
     */
    @ApiModelProperty(value = "url地址")
    private String url;
    /**
     * 视频图片
     */
    @ApiModelProperty(value = "视频图片")
    private String picture;
    /**
     * 视频简介
     */
    @ApiModelProperty(value = "视频简介")
    private String profile;
    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String label;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String author;
    /**
     * 季
     */
    @ApiModelProperty(value = "季")
    private Integer season;
    /**
     * 集
     */
    @ApiModelProperty(value = "集")
    private Integer episode;
    /**
     * 播放量
     */
    @ApiModelProperty(value = "播放量")
    private Integer playNum;
    /**
     * 是否是集合
     */
    @ApiModelProperty(value = "是否是集合")
    private Integer isCollection;
    /**
     * 时长
     */
    @ApiModelProperty(value = "时长")
    private Date duration;
    /**
     * 创作年份
     */
    @ApiModelProperty(value = "创作年份")
    private Date createYear;
}