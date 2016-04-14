package com.zoe.phip.module.service.entity;

import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNotBlank;
import com.zoe.phip.infrastructure.myvalidator.annotation.ValidateNotNull;

import java.util.Date;

/**
 * 主表
 * Created by zengjiyang on 2016/3/31.
 */
public class MasterEntity extends BaseEntity {
    /**
     * 状态
     */
    @ValidateNotNull(message = "状态不能为空")
    protected int state;

    /**
     * 修改人
     */
    @ValidateNotBlank(message = "修改人不能为空")
    private String modifyBy;

    /**
     * 实体（数据记录）修改时间
     */
    @ValidateNotNull(message = "修改时间不能为空")
    private Date modifyAt;


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     * 获取实体（数据记录）修改时间
     *
     * @return
     */
    public Date getModifyAt() {
        return modifyAt;
    }

    /**
     * 设置实体（数据记录）修改时间
     *
     * @param modifyAt
     */
    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
}
