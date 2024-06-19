package com.example.videos.dal.vo.video;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.framework.dal.vo.ExcelVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ApiModel(value = "导出")
@EqualsAndHashCode(callSuper = true)
public class VideoExportVO extends ExcelVO {
    /**
     * 父节点id
     */
    @ExcelProperty("父节点id")
    private Long parentId;
    /**
     * 视频存储节点
     */
    @ExcelProperty("视频存储节点")
    private Long savePath;
    /**
     * 视频名称
     */
    @ExcelProperty("视频名称")
    private String videoName;
    /**
     * 视频标题
     */
    @ExcelProperty("视频标题")
    private String title;
    /**
     * 视频分类(大类)
     */
    @ExcelProperty("视频分类(大类)")
    private String category;
    /**
     * 视频类型(中类)
     */
    @ExcelProperty("视频类型(中类)")
    private String type;
    /**
     * 视频地区
     */
    @ExcelProperty("视频地区")
    private String region;
    /**
     * url地址
     */
    @ExcelProperty("url地址")
    private String url;
    /**
     * 视频图片
     */
    @ExcelProperty("视频图片")
    private String picture;
    /**
     * 视频简介
     */
    @ExcelProperty("视频简介")
    private String profile;
    /**
     * 标签
     */
    @ExcelProperty("标签")
    private String label;
    /**
     * 作者
     */
    @ExcelProperty("作者")
    private String author;
    /**
     * 季
     */
    @ExcelProperty("季")
    private Integer season;
    /**
     * 集
     */
    @ExcelProperty("集")
    private Integer episode;
    /**
     * 播放量
     */
    @ExcelProperty("播放量")
    private Integer playNum;
    /**
     * 是否是集合
     */
    @ExcelProperty("是否是集合")
    private Integer isCollection;
    /**
     * 时长
     */
    @ExcelProperty("时长")
    private Date duration;
    /**
     * 创作年份
     */
    @ExcelProperty("创作年份")
    private Date createYear;
}