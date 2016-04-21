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
@RequestMapping("/oid")
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


}
