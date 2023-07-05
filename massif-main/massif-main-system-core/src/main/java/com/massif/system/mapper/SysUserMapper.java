package com.massif.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.massif.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author xxx
 * @since 2023-06-09
 */

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    // XxxMapper.xml文件要放在resource目录的文件夹下，否则需要在配置文件中指定路径
//    @Select("SELECT role_id FROM sys_user_role WHERE user_id = #{userId}")
    List<Long> getRoleIdsByUserId(@Param("userId") Long userId);

    /**
     * 通过角色ID集合获取权限信息
     */
    Set<String> getPermsByRoles(List<Long> roleIds);
}
