package com.massif.system.controller;

import cn.hutool.core.util.RandomUtil;
import com.massif.common.entity.Result;
import com.massif.system.config.redis.RedisService;
import com.massif.system.entity.SysUser;
import com.massif.system.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestParam String username, @RequestParam("password") String password){


        // 将用户名和密码封装到SysUser对象中
        SysUser user = new SysUser();
        user.setUserName(username);
        user.setPassword(password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(null == authenticate){
            throw new RuntimeException("用户名或密码错误");
        }

        // 获取security封装好的用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        // 使用userId生成Token
        String userId = loginUser.getUser().getUserId().toString();
        String token = userId + RandomUtil.randomString(4).toString();    // 可用jwt等生成token

        // 存redis
        redisService.setCacheObject(token, loginUser);

        // 把token响应回去
        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        return Result.ok(map);
    }

}
