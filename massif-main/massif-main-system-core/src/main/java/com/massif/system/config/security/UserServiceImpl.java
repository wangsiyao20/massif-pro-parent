package com.massif.system.config.security;

import com.massif.system.entity.SysUser;
import com.massif.system.mapper.SysUserMapper;
import com.massif.system.model.LoginUser;
import com.massif.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //`loadUserByUsername()` 和 `authenticate()` 都是 Spring Security 框架中用于实现身份验证的方法，其中 `loadUserByUsername()` 是 `UserDetailsService` 接口中的方法，而 `authenticate()` 则是 `AuthenticationManager` 接口中的方法。
        //`loadUserByUsername()` 方法的主要作用是从系统中获取一个用户的详细信息并构建一个 `UserDetails` 对象，以用于后续的身份验证操作。在通常情况下，该方法会根据给定的用户名从数据库或其他数据源中查询用户的详细信息，例如密码、角色等，并将其封装为一个 `UserDetails` 对象或其子类的实例。`UserDetails` 对象通常包含用户名、密码、权限、是否启用等信息，并已经准备好用于验证用户的身份。
        //`authenticate()` 方法则是通过提交的凭据来验证用户身份，并返回一个代表该用户身份的 `Authentication` 对象。在典型的应用程序中，`authenticate()` 方法通常在用户身份验证时用于处理输入的用户名和密码，经过身份验证之后，生成带有身份信息的 `Authentication` 对象，并交由 Spring Security 安全框架处理后续身份验证和授权操作。
        // 因此，这两个方法的主要区别在于它们的主要工作范围。`loadUserByUsername()` 方法旨在从数据源中加载用户详细信息并创建一个 `UserDetails`对象，用于验证用户身份；而 `authenticate()` 方法则是验证用户提交的凭据（通常是用户名和密码）并生成一个表示用户身份的 `Authentication` 对象。虽然 `loadUserByUsername()` 方法通常会在处理身份验证前调用，但是，实际上它并不直接处理身份验证，而是返回一个准备好进行身份验证的 `UserDetails` 对象。
        // 请注意，实现 `UserDetailsService` 接口并重写 `loadUserByUsername()` 方法，是实现身份验证的一部分，而不是全部。`loadUserByUsername()` 方法的作用是从数据源中加载 `UserDetails` 对象，因此它既可以用于基于表单的登录身份验证，也可以用于许多其他形式的身份验证，例如 OAuth2、SAML 等。而 `authenticate()` 方法是使用凭据进行身份验证的主要方法，在大多数情况下，你将需要实现此方法，以便在身份验证对话期间处理和验证用户凭据。
        Optional<SysUser> user = sysUserService.getByUsername(username);
        user.orElseThrow(() -> new RuntimeException("未检测到该用户"));
        user.get().setPassword(passwordEncoder.encode(user.get().getPassword()));   // 密码加密
        LoginUser loginUser = new LoginUser(user.get());
        // 查询该用户下的角色，通过用户角色关联表查
        List<Long> roleIds = getRoleIdsByUserId(user.get().getUserId());
        // 查询角色下所属权限
        Set<String> perms = getPermsByRoles(roleIds);

        // 将权限赋过去
        List<SimpleGrantedAuthority> authorities = perms.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        loginUser.setGrantedAuthorities(authorities);
        return loginUser;
    }

    /**
     * 通过角色ID的集合获取权限信息
     */
    private Set<String> getPermsByRoles(List<Long> roleIds) {
        return sysUserMapper.getPermsByRoles(roleIds);
    }

    /**
     * 通过用户ID获取角色ID
     */
    private List<Long> getRoleIdsByUserId(Long userId) {
        return sysUserMapper.getRoleIdsByUserId(userId);
    }
}
