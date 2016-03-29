package com.zoe.phip.model.sm;

import javax.persistence.Table;

import javax.persistence.*;

import com.zoe.phip.model.base.BaseEntity;


/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
@Table(name = "SYS_USER_COMPETENCE")
public class UserCompetence extends BaseEntity {
    /**
     * 权限分类设计
     */
    @Column(name = "FK_COMPETENCE_CATEGORY_ID")
    private java.lang.String fkCompetenceCategoryId;
    /**
     * 用户主键
     */
    @Column(name = "FK_USER_ID")
    private java.lang.String fkUserId;

    public java.lang.String getFkCompetenceCategoryId()

    {
        return this.fkCompetenceCategoryId;
    }

    public void setFkCompetenceCategoryId(java.lang.String fkCompetenceCategoryId) {
        this.fkCompetenceCategoryId = fkCompetenceCategoryId;
    }

    public java.lang.String getFkUserId()

    {
        return this.fkUserId;
    }

    public void setFkUserId(java.lang.String fkUserId) {
        this.fkUserId = fkUserId;
    }
}
