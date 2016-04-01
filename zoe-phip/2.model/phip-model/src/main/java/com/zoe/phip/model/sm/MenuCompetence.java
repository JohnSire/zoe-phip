/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.model.sm;

import com.zoe.phip.model.base.BaseEntity;
import com.zoe.phip.model.base.MasterEntity;

import javax.persistence.Column;
import javax.persistence.Table;


/**
 * @author
 * @version 1.0
 * @date 2016-03-29
 */
@Table(name = "SYS_MENU_COMPETENCE")
public class MenuCompetence extends MasterEntity {
    /**
     * 权限分类设计
     */
    @Column(name = "FK_COMPETENCE_CATEGORY_ID")
    private String fkCompetenceCategoryId;
    /**
     * 菜单主键
     */
    @Column(name = "FK_MENU_ID")
    private String fkMenuId;

    public String getFkCompetenceCategoryId() {
        return this.fkCompetenceCategoryId;
    }

    public void setFkCompetenceCategoryId(java.lang.String fkCompetenceCategoryId) {
        this.fkCompetenceCategoryId = fkCompetenceCategoryId;
    }

    public String getFkMenuId() {
        return this.fkMenuId;
    }

    public void setFkMenuId(java.lang.String fkMenuId) {
        this.fkMenuId = fkMenuId;
    }
}
