package com.massif.system.service;

import com.massif.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
