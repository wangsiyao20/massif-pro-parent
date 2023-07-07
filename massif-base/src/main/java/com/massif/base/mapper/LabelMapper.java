package com.massif.base.mapper;

import com.massif.base.entity.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LabelMapper {

    /**
     * 获取标签列表
     */
    List<Label> getLabelList(Label label);
}
