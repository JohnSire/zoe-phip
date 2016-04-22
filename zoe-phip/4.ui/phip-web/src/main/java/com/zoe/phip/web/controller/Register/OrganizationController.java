package com.zoe.phip.web.controller.Register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by linqinghuang on 2016/4/21.
 */
@Controller
@RequestMapping("organization")
public class OrganizationController {
    //region 医疗机构
    @RequestMapping("/view/medicalorglist")
    public String ToMedicalOrgList() {
        return "Register/Organization/medicalOrgList";
    }

    @RequestMapping("/view/medicalOrgDetail")
    public String ToMedicalOrgDetail() {
        return "Register/Organization/medicalOrgDetail";
    }
    //endregion

    // TODO: 2016/4/21

    //region 卫生管理机构
    @RequestMapping("/view/healthOrgList")
    public String ToHealthOrgList() {
        return "Register/Organization/healthOrgList";
    }

    @RequestMapping("/view/healthOrgDetail")
    public String ToHealthOrgDetail() {
        return "Register/Organization/healthOrgDetail";
    }
    //endregion

    // TODO: 2016/4/21
    //region 第三方机构管理
    public String ToThirdOrgList() {
        return null;
    }

    public String ToThirdOrgDetail() {
        return null;
    }
    //endregion
}
