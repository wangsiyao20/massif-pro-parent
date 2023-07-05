package com.massif.common.entity;

import java.io.Serializable;

/**
 * 返回信息实体类
 */
public class Result<T> implements Serializable{

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 成功标志
     */
    private Boolean success;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;


    public Result() {
    }

    public Result(Boolean flag,Integer code,String message) {
        this.code = code;
        this.success = flag;
        this.message = message;
    }

    public Result (Boolean flag,Integer code,T data) {
        this.code = code;
        this.success = flag;
        this.data = data;
    }

    public Result(Boolean flag,Integer code, String message, T data) {
        this.code = code;
        this.success = flag;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
