package com.massif.system.service.impl;

import com.massif.system.constant.DelConstant;
import com.massif.system.entity.SysUser;
import com.massif.system.mapper.SysUserMapper;
import com.massif.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public Optional<SysUser> getByUsername(String username) {
        return lambdaQuery()
                .eq(SysUser::getUserName, username)
                .eq(SysUser::getDelFlag, DelConstant.DEL_NO)
                .oneOpt();
    }
}
