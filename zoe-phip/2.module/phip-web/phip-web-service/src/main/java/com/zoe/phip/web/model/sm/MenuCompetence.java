/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.model.sm;


import com.zoe.phip.module.infrastructure.entity.MasterEntity;

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

    public void setFkCompetenceCategoryId(String fkCompetenceCategoryId) {
        this.fkCompetenceCategoryId = fkCompetenceCategoryId;
    }

    public String getFkMenuId() {
        return this.fkMenuId;
    }

    public void setFkMenuId(String fkMenuId) {
        this.fkMenuId = fkMenuId;
    }
}
