package com.zoe.phip.model.sm;

import com.zoe.phip.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;


/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
@Table(name = "SYS_USER_COMPETENCE")
public class MenuCompetence extends BaseEntity {
    /**
     * 权限分类设计
     */
    @Column(name = "FK_COMPETENCE_CATEGORY_ID")
    private String fkCompetenceCategoryId;
    /**
     * 用户主键
     */
    @Column(name = "FK_USER_ID")
    private String fkUserId;

    public String getFkCompetenceCategoryId()

    {
        return this.fkCompetenceCategoryId;
    }

    public void setFkCompetenceCategoryId(String fkCompetenceCategoryId) {
        this.fkCompetenceCategoryId = fkCompetenceCategoryId;
    }

    public String getFkUserId()

    {
        return this.fkUserId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }
}
