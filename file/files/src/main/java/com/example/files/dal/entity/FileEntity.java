package com.example.files.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.framework.dal.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName(value = "file")
@ApiModel(value = "文件")
@EqualsAndHashCode(callSuper = true)
public class FileEntity extends BaseEntity {
    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id")
    private Long parentId;
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    /**
     * url地址
     */
    @ApiModelProperty(value = "url地址")
    private String url;
    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型")
    private String fileType;
    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String filePath;
    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;
}