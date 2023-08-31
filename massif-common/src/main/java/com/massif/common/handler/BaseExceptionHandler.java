package com.massif.common.handler;


import com.massif.common.entity.Result;
import com.massif.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 【全局异常捕获类】
 * 自定义的公共异常处理器
 *      1、声明异常处理器
 *      2、对异常统一处理
 */

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<?> error(HttpServletRequest request, HttpServletResponse response, Exception e){
        return new Result<>(false, StatusCode.FAIL, "系统繁忙，请稍后再试...", null);
    }


}
