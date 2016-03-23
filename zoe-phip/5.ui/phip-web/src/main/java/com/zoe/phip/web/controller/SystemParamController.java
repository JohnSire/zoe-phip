package com.zoe.phip.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangxingcai on 2016/3/23 0023.
 */
@Controller
@RequestMapping("/param")
public class SystemParamController {
    //系统参数界面
    @RequestMapping("/sysparam")
    public String ToParam(HttpServletRequest request, Model model) {
        return "/param/sysparam";
    }
}
