package com.massif.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.massif.system.entity.SysUser;
import com.massif.system.model.SmsLoginModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author xxx
 * @since 2023-06-09
 */
public interface ISysUserService extends IService<SysUser> {

    Optional<SysUser> getByUsername(String username);

    /**
     * 验证手机号并发送验证码
     */
    void sendCode(String phoneNum, HttpServletRequest request);

    /**
     * 短信验证码验证
     */
    JSONObject smsLogin(SmsLoginModel smsLogin, HttpSession session);
}
