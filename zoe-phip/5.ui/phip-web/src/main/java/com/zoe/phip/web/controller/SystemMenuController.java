package com.zoe.phip.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangxingcai on 2016/3/22 0022.
 */
@Controller
@RequestMapping("/menu")
public class SystemMenuController {
    //菜单页面
    @RequestMapping("/list")
    public String  ToMenuList(HttpServletRequest request, Model model)
    {
        return "/menu/list";
    }
    //修改菜单信息
    @RequestMapping("/detail")
    public String ToMenuDetail(HttpServletRequest request,Model model){
        return "/menu/detail";
    }
    //菜单结构调整
    @RequestMapping("/tree")
    public String ToMenuTree(HttpServletRequest request ,Model model){
        return "/menu/tree";
    }
    //菜单权限
    @RequestMapping("/acc")
    public String ToMenuAcc(HttpServletRequest request,Model model){
        return "/menu/acc";
    }
}
