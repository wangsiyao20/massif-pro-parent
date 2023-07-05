package com.massif.system.service;

import com.massif.system.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author xxx
 * @since 2023-06-09
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 查询部门列表
     */
    List<SysDept> selectDeptList(SysDept dept);

}
