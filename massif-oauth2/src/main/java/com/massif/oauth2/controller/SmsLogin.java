package com.massif.oauth2.controller;

import com.alibaba.fastjson.JSONObject;
import com.massif.common.entity.Result;
import com.massif.system.model.SmsLoginModel;
import com.massif.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 拦截器实现的短信登录（未完成），
 * 没有用到oauth2
 */

@RestController
@RequestMapping("/sms")
public class SmsLogin {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 验证手机号并发送短信验证码接口
     */
    @PostMapping("sendCode")
    public Result<?> sendCode(@RequestParam String phoneNum,    // todo 这个参数应该改为XxxModel类型
                              HttpServletRequest request){
        sysUserService.sendCode(phoneNum, request);
        return Result.ok();
    }

    /**
     * 短信验证码登录
     */
    @PostMapping("smsLogin")
    public Result<JSONObject> smsLogin(@RequestBody SmsLoginModel smsLogin,
                                       HttpSession session){
        return Result.ok(sysUserService.smsLogin(smsLogin, session));
    }

}
