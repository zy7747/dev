package com.example.system.dal.dto.dict;

import com.example.framework.dal.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "查询")
@EqualsAndHashCode(callSuper = true)
public class DictQueryDTO extends PageDTO {
    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id")
    private Long parentId;
    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;
    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String dictCode;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;
    /**
     * 名
     */
    @ApiModelProperty(value = "名")
    private String label;
    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    private String value;
    /**
     * 自定义颜色
     */
    @ApiModelProperty(value = "自定义颜色")
    private String color;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 其他参数
     */
    @ApiModelProperty(value = "其他参数")
    private String params;
}