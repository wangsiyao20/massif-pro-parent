package com.massif.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.massif.base.entity.Label;
import com.massif.base.mapper.LabelMapper;
import com.massif.base.service.ILabelService;
import com.massif.common.entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements ILabelService {

    @Autowired
    private LabelMapper labelMapper;

    /**
     * 标签列表
     */
    public PageResult<Label> getLabelList(Label label, Integer pageNum, Integer pageSize) {
        // 设置分页查询参数，在数据库查询之前设置上
        PageHelper.startPage(pageNum, pageSize);
        List<Label> labelList = labelMapper.getLabelList(label);
        // 将查出的数据放到PageInfo中，之后可用PageInfo中的方法获取到这页的信息
        PageInfo<Label> labelPageInfo = new PageInfo<Label>(labelList);
        /*
        PageInfo对象可以提供详细的分页信息和查询结果，它包含以下属性：
        pageNum：当前页码;   pageSize：每页数据条数;   size：当前页实际的数据条数;   startRow：当前页的起始行号;   endRow：当前页的结束行号;
        pages：总页数;   total：总记录数;   list：当前页的数据列表;   prePage：上一页的页码（如果有）;  nextPage：下一页的页码（如果有）
        isFirstPage：是否为第一页;   isLastPage：是否为最后一页;   hasPreviousPage：是否有上一页;   hasNextPage：是否有下一页
        以上属性都可以get方法获取到
        */
        // PageInfo 可以使用 getList() 方法获取【当前页】的列表数据，【它存的就是分页后的列表数据】
        return new PageResult<Label>(labelPageInfo.getTotal(), labelPageInfo.getList());
    }
}
