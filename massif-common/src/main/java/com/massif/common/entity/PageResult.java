package com.massif.common.entity;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable{

    /**
     * 总记录数
     */
    private Long total;


    /**
     * 每页列表内容
     */
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
