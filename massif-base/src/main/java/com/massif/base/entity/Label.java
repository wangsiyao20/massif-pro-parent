package com.massif.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("标签")
public class Label implements Serializable{

    @ApiModelProperty("标签ID")
    private String id;

    // 数据表中中为labelname
    @ApiModelProperty("标签名")
    private String labelName;

    @ApiModelProperty("状态")
    private String state;

    // 如果实体类属性类型是Integer， 就把example转为Long类型， 而example默认为""
    // 所以要给example一个数值型
    @ApiModelProperty(value = "数量", example = "0")
    private Integer count;

    @ApiModelProperty("是否推荐")
    private String recommend;

    @ApiModelProperty(value = "粉丝数", example = "0")
    private Integer fans;
}
