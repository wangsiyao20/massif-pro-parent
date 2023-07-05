package com.massif.common.entity;

public class StatusCode {

    /**
     * 成功
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败
     */
    public static final Integer FAIL = 500;

    /**
     * 用户名或密码错误
     */
    public static final Integer LOGIN_ERROR = 30000;

    /**
     * 权限错误
     */
    public static final Integer PERMISSION_ERROR = 40000;

    /**
     * 远程调用失败
     */
    public static final Integer REMOTE_CALL_FAIL = 50000;

    /**
     * 重复操作
     */
    public static final Integer REPEAT_OPERATE_ERROR = 60000;
}
