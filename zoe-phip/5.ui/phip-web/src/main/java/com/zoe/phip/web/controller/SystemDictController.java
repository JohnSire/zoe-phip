package com.zoe.phip.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
