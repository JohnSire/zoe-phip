package com.zoe.phip.infrastructure.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linqinghuang on 2016/1/26.
 */
public class PageList<T> implements Serializable {
    /**
     * 分页列表条数
     */
    @JSONField(name = "Total")
    private int total;
    /**
     * 行内容对象
     */
    @JSONField(name = "Rows")
    private List<T> rows;

    /**
     * 获取分页总条数
     *
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     * 设置分页总条数
     *
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 获取行内容对象
     *
     * @return
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * 设置行内容对象
     *
     * @param rows
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
