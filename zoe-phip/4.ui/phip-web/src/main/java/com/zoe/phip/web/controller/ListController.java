package com.zoe.phip.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hyf.
 *
 */
@Controller
@RequestMapping("/list")
public class ListController extends BaseController {


    //用户关联管理页面
    @RequestMapping("/view/user")
    public String User(HttpServletRequest request, Model model) {
        return "/list/user";
    }

    //菜单关联管理页面
    @RequestMapping("/view/menu")
    public String menu(HttpServletRequest request, Model model) {
        return "/list/menu";
    }





}
