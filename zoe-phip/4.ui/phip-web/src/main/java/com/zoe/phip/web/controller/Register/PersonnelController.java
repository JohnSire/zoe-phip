package com.zoe.phip.web.controller.Register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by linqinghuang on 2016/4/21.
 */
@Controller
@RequestMapping("/personnel")
public class PersonnelController {
    //region 个人信息管理
    @RequestMapping("/view/xmanbaselist")
    public String ToXmanBaseList(HttpServletRequest request, Model model) {
        return "/Register/Personnel/xmanBaseList";
    }

    @RequestMapping("/view/xmanbasedetail")
    public String ToXmanBaseDetail(HttpServletRequest request, Model model) {
        return "/Register/Personnel/xmanBaseDetail";
    }
    //endregion

    //region 医疗卫生人员
    @RequestMapping("/view/medicalstafflist")
    public String ToMedicalStaffList(HttpServletRequest request, Model model) {
        return "/Register/Personnel/medicalStaffList";
    }

    @RequestMapping("/view/medicalstaffdetail")
    public String ToMedicalStaffDetail(HttpServletRequest request, Model model) {
        return "/Register/Personnel/medicalStaffDetail";
    }
    //endregion

    // TODO: 2016/4/21
    //region 卫生管理机构人员
    @RequestMapping("/view/healthstafflist")
    public String ToHealthStaffList(HttpServletRequest request, Model model) {
        return "/Register/Personnel/healthStaffList";
    }

    @RequestMapping("/view/healthstaffdetail")
    public String ToHealthStaffDetail(HttpServletRequest request, Model model) {
        return "/Register/Personnel/healthStaffDetail";
    }
    //endregion
}
