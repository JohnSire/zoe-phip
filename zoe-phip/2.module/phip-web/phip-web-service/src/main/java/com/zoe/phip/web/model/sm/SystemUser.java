/*
 * Powered By zoe
 * Since 2008 - 2016
 */
package com.zoe.phip.web.model.sm;

import com.zoe.phip.module.service.entity.First;
import com.zoe.phip.module.service.entity.MasterEntity;
import com.zoe.phip.web.myvalidator.MyPassword;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * @author
 * @version 1.0
 * @date 2016-03-18
 */
@Table(name = "SYS_SYSTEM_USER")
public class  SystemUser extends MasterEntity implements First {

    /**
     * 名称
     */
    @Column(name = "NAME")
//    @JSONField(name = "Name")
    @NotEmpty(message = "{不能为空}")
    @Length(min = 5, max = 20, message = "{名称最小长度为5，最大为20！}")
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "{名称只能是英文字母！}")
    private String name;

    /**
     * 登陆名
     */
    @Column(name = "LOGIN_NAME")
    @NotEmpty(message = "{不能为空}")
//    @JSONField(name = "LoginName")
    private String loginName;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
//    @JSONField(name = "Password")
    @MyPassword
    private String password;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(String competenceId) {
        this.competenceId = competenceId;
    }

    /// <summary>
    /// 对应的权限标识
    /// </summary>
    @Transient
    @Column(name = "COMPETENCE_ID")
    public String competenceId;


    public static void main(String[] args) {
       SystemUser systemUser;
        systemUser = new SystemUser();
        systemUser.setName("2");
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<SystemUser>> set = validator.validate(systemUser);
        for (ConstraintViolation<SystemUser> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }
    }

}
