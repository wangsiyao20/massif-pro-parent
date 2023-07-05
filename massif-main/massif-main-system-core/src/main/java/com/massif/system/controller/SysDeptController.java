package com.massif.system.controller;


import com.massif.system.entity.SysDept;
import com.massif.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2023-06-09
 */
@RestController
@RequestMapping("/system/sysDept")
public class SysDeptController {

    @Autowired
    private ISysDeptService deptService;

    /**
     * 获取部门列表
     */
    @PreAuthorize("hasAuthority('"+"system:dept:list"+"')")
    @GetMapping("/list")
    public String list(SysDept dept)
    {
        List<SysDept> depts = deptService.selectDeptList(dept);

        return "abc";
    }

}

