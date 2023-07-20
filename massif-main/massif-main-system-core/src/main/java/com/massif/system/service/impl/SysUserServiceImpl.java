package com.massif.system.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.massif.system.constant.DelConstant;
import com.massif.system.entity.SysUser;
import com.massif.system.mapper.SysUserMapper;
import com.massif.system.model.SmsLoginModel;
import com.massif.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2023-06-09
 */

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    /**
     * Logger available to subclasses.
     */
    protected static final Log logger = LogFactory.getLog(SysUserServiceImpl.class);

    @Override
    public Optional<SysUser> getByUsername(String username) {
        return lambdaQuery()
                .eq(SysUser::getUserName, username)
                .eq(SysUser::getDelFlag, DelConstant.DEL_NO)
                .oneOpt();
    }

    @Override
    public void sendCode(String phoneNum, HttpServletRequest request) {

        // todo 验证手机号格式是否正确

        // 生成验证码
        String verifyCode = RandomUtil.randomNumbers(6);

        // 保存验证码到session  todo 用token代替session
        request.getSession().setAttribute("verifyCode", verifyCode);    // key应为常量，反正以后不用session，无所谓了
        logger.info("验证码为：" + verifyCode);

        // todo 买短信，调用第三方发送短信验证码
        // todo 指定时间内防重验证，有能力也验证ip，不然无限刷了

    }

    /**
     * 通过短信验证码登录
     */
    @Override
    public JSONObject smsLogin(SmsLoginModel smsLogin, HttpSession session) {

        // todo 验证手机号格式是否正确

        // 验证短信验证码
        String verifyCode = (String) session.getAttribute("verifyCode");    // key应为常量，反正以后不用session，无所谓了
        if (!StringUtils.equals(smsLogin.getVerifyCode(), verifyCode)) {
            throw new RuntimeException("验证码错误");
        }

        // 判断数据表里是否有该手机号，有则用户信息存session，没有则注册并存session
        Optional<SysUser> sysUser = lambdaQuery()
                .eq(SysUser::getPhoneNumber, smsLogin.getPhoneNumber())
                .eq(SysUser::getDelFlag, "0")   // todo 常量
                .oneOpt();

        sysUser.ifPresent(user -> session.setAttribute("user", user));  // todo  常量

        if (!sysUser.isPresent()) {
            // 用户不存在则创建用户
            SysUser user = createUser(smsLogin.getPhoneNumber());
            session.setAttribute("user", user);
        }


        // todo 需要到token时再处理这个return，暂时存的session
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", session.getAttribute("user"));
        return jsonObject;
    }

    /**
     * 根据手机号创建用户
     */
    private SysUser createUser(String phoneNumber) {

        // todo 用于拼接的用户名前缀，应该定义成常量
        String prefixName = "普通用户";

        // 随机生成一个用户名后缀并拼接
        String nickName = prefixName + RandomUtil.randomString(6);

        SysUser sysUser = new SysUser();
        sysUser.setNickName(nickName);
        sysUser.setPhoneNumber(phoneNumber);

        baseMapper.insert(sysUser);

        return sysUser;
    }
}
