package com.massif.base.service;

import com.massif.base.entity.Label;
import com.massif.common.entity.PageResult;

import java.util.List;

public interface ILabelService {


    /**
     * 标签列表
     */
    PageResult<Label> getLabelList(Label label, Integer pageNum, Integer pageSize);
}
