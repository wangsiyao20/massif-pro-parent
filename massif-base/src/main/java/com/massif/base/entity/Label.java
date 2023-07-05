package com.massif.base.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Label implements Serializable{

    private String id;
    //需要要给labelName一个别名，在使用sql时
    private String labelName;
    private String state;
    private Integer count;
    private String recommend;
    private Integer fans;
}
