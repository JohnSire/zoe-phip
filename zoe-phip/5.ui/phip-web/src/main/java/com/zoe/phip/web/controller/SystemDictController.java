package com.zoe.phip.web.controller;


import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.in.sm.SystemUserService;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangxingcai on 2016/3/22 0022.
 */
@Controller
@RequestMapping("/dictm")
public class SystemDictController {
    //系统字典列表
    @RequestMapping("/list")
    public String ToDictList(HttpServletRequest request, Model model) {
        return "/dictm/list";
    }

    //字典项详情
    @RequestMapping("/item")
    public String ToDictItemDetail(HttpServletRequest request, Model model) {
        return "/dict/item";
    }

    //字典类别详情
    @RequestMapping("/category")
    public String ToDictCategoryDetail(HttpServletRequest request, Model model) {
        return "/dict/category";
    }

    @RequestMapping("/test")
    @ResponseBody
    public ServiceResultT<PageList<SystemUser>> getDict(HttpServletRequest request, Model model) {
        SystemUserService systemUserService = BeanFactory.getBean(Constant.SYSTEM_USER_SERVICE);
        QueryPage page = new QueryPage(1, 30);
        ServiceResultT<PageList<SystemUser>> resultT = systemUserService.getList(page, SystemUser.class);
        return resultT;
    }
}
