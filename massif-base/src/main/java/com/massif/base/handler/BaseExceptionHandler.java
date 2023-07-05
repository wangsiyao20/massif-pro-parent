package com.massif.base.handler;

import com.massif.common.entity.Result;
import com.massif.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   //捕获Controller中的异常，Service可以往上抛到Controller来通过这里捕获
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)      // value中指定异常类型类的.class
    public Result exception(Exception e){
        e.printStackTrace();    // todo 换成记录日志
        return new Result(false, StatusCode.FAIL, e.getMessage());
    }

}
