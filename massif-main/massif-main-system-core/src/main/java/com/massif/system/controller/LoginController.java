package com.massif.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.massif.common.entity.Result;
import com.massif.common.entity.StatusCode;
import com.massif.system.config.redis.RedisService;
import com.massif.system.entity.SysUser;
import com.massif.system.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;

    private StringBuilder failureMsg = new StringBuilder();

//    // 这个{}不能实现自定义位置拼串
//    private String initMsg = "<br/>{}、系统名称{}";

    private String initMsg = "<br/>%d、系统名称%s %s";

    private String initErrorStr = "新增任务'%s'失败，%s";


    @GetMapping("/in")
    public String home(){
        int num = 0;
        String str = "hello";
//        return failureMsg.append(initMsg + "数据重复").append(num).append(str).toString();
//        return failureMsg.append(String.format(initMsg, num, str, "你好")).toString();
        return String.format(initErrorStr, "abc", "你好'a'");
    }



    @CrossOrigin
    @PostMapping("/login")
    public Result<?> login(@RequestBody SysUser user, HttpServletResponse response){


//        // 将用户名和密码封装到SysUser对象中
//        SysUser user = new SysUser();
//        user.setUserName(username);
//        user.setPassword(password);
        Authentication authenticate = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e){
            // 密码错误
            if(e instanceof BadCredentialsException){
                // 密码登录错误, 这里使用人为构造错误码来让前端捕获
                String errorMessage = "密码输入错误，请重新输入密码。";
                Result<Object> result = new Result<>();
                result.setCode(StatusCode.LOGIN_ERROR);
                result.setMessage(errorMessage);
                // 人为在header中设置一个400错误的状态
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return result;
            } else if (e instanceof LockedException){   // 账号被锁定
                //  账号已被锁定，这里采用抛出异常让前端捕获，这个异常是500
                throw new RuntimeException("账号已被锁定");
            } else {
                // 其他登录错误问题，这里采用直接抛出让前端捕获
                throw new RuntimeException("登录失败");
            }


        }


        // 获取security封装好的用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        // 使用userId生成Token
        String userId = loginUser.getUser().getUserId().toString();
        String token = userId + RandomUtil.randomString(4).toString();    // 可用jwt等生成token

        // 把用户信息存redis，key为token
        redisService.setCacheObject(token, loginUser);

        // 把token以及用户信息响应回去
        SysUser sysUser = loginUser.getUser();
        sysUser.setPassword(null);  // 返回前端时先把密码置空
        JSONObject obj = new JSONObject();
        obj.put("userInfo", loginUser.getUser());
        obj.put("token", token);

        return Result.ok(obj);
    }
}
