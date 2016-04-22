package com.zoe.phip.web.controller.Register;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by linqinghuang on 2016/4/21.
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {
    //region oid管理模块

    @RequestMapping("/view/oidlist")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToOIDList(HttpServletRequest request, Model model) {
        return "/Register/Dict/oidList";
    }

    @RequestMapping("/view/oiddetail")
    public String ToOIDDetail(HttpServletRequest request, Model model) {
        return "/Register/Dict/oidDetail";
    }

    public String getOIDList(HttpServletRequest request, Model model) {
        return null;
    }

    public String AddOIDInfo() {
        return null;
    }

    public String UpdateOIDInfo() {
        return null;
    }

    public String DelOIDInfo(String id) {
        return null;
    }

    public String DelOIDList(String ids) {
        return null;
    }

    //endregion
    //region 字典分类
    @RequestMapping("/view/dictcategorylist")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToDictCategoryList(HttpServletRequest request, Model model) {
        return "/Register/Dict/dictCategoryList";
    }

    @RequestMapping("/view/dictcategorydetail")
    public String ToDictCategoryDetail(HttpServletRequest request, Model model) {
        return "/Register/Dict/dictCategoryDetail";
    }
    //endregion

    //region 字典(字典列表）
    @RequestMapping("/view/dictlist")
    public String ToDictList(HttpServletRequest request, Model model) {
        return "/Register/Dict/dictList";
    }

    //region 字典详细
    @RequestMapping("/view/dictdetail")
    public String ToDictDetail(HttpServletRequest request, Model model) {
        return "Register/Dict/dictDetail";
    }
    //endregion
}
