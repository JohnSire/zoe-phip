package com.zoe.phip.web.model.sm;

import com.zoe.phip.module.infrastructure.entity.MasterEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
@Table(name = "SYS_USER_COMPETENCE")
public class UserCompetence extends MasterEntity {
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

    @Transient
    @Column(name = "NAME")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    @Column(name = "LOGIN_NAME")
    public String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

}
