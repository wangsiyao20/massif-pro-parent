package com.massif.system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SmsLoginModel implements Serializable {

    @JsonProperty("phoneNumber")    // 这个注解是指定json中的字段名
    private String phoneNumber;

    private String verifyCode;

}
