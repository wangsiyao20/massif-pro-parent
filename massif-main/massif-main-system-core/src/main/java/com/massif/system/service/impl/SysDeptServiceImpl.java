package com.massif.system.service.impl;

import com.massif.system.entity.SysDept;
import com.massif.system.mapper.SysDeptMapper;
import com.massif.system.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2023-06-09
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private SysDeptMapper deptMapper;


    /**
     * 查询部门列表
     */
    @Override
    public List<SysDept> selectDeptList(SysDept dept) {
        return lambdaQuery()
                // todo 筛选条件
                .list();
    }
}
