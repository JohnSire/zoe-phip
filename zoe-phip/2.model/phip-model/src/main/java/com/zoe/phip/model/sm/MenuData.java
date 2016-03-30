/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.model.sm;

import com.zoe.phip.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-03-21
 */
@Table(name = "SYS_MENU_DATA")
public class MenuData extends BaseEntity {


    /**
     * 名称
     */
    @Column(name = "NAME")
//    @JSONField(name = "Name")
    private String name;


    /**
     * 编码
     */
    @Column(name = "CODE")
    private String code;
    /**
     * 排序
     */
    @Column(name = "SORT")
    private int sort;

    /**
     * 地址
     */
    @Column(name = "ADDRESS")
//    @JSONField(name = "Address")
    private String address;

    /**
     * 上级菜单
     */
    @Column(name = "FK_PARENT_MENU_ID")
//    @JSONField(name = "FkParentMenuId")
    private String fkParentMenuId;

    private MenuData parentMenu;




    /**
     * 状�
     */
    @Column(name = "STATE")
//    @JSONField(name = "State")
    private int state;


    public MenuData(){

    }

    public MenuData(String name, String uri, String code,int state, int sort){
        this.name=name;
        this.address=uri;
        this.code=code;
        this.sort=sort;
        this.state=state;
        this.children=new ArrayList<>();
    }

    /**
     * 子节�
     */
    @Transient
//    @JSONField(name = "Childrens")
    public List<MenuData> children;

    public List<MenuData> getChildren() {
        return children;
    }

    public void setChildren(List<MenuData> children) {
        this.children = children;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSort(){
        return this.sort;
    }

    public void setSort(int sort){
        this.sort=sort;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFkParentMenuId() {
        return this.fkParentMenuId;
    }

    public void setFkParentMenuId(String fkParentMenuId) {
        this.fkParentMenuId = fkParentMenuId;
    }

    public MenuData getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(MenuData parentMenu) {
        this.parentMenu = parentMenu;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "MenuData{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", address='" + address + '\'' +
                ", fkParentMenuId='" + fkParentMenuId + '\'' +
                ", state=" + state +
                '}';
    }

    public String getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(String competenceId) {
        this.competenceId = competenceId;
    }

    /// <summary>
    /// 对应的权限标�
    /// </summary>
    @Transient
    @Column(name = "COMPETENCE_ID")
    public String competenceId;


    public String getNamePath() {
        return namePath;
    }

    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    /// <summary>
    /// 菜单路径
    /// </summary>
    @Transient
    public String namePath;
}
