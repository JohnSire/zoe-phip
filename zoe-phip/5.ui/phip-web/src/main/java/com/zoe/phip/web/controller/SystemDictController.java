package com.zoe.phip.web.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangxingcai on 2016/3/22 0022.
 */
@Controller
@RequestMapping("/SysManage")
public class SystemDictController {
    //系统字典列表
    @RequestMapping("/sysDictList")
    public String ToDictList(HttpRequest request, Model model) {
        return "/SysManage/sysDictList";
    }

    //字典项详情
    @RequestMapping("/sysDictItemDetail")
    public String ToDictItemDetail(HttpRequest request, Model model) {
        return "/SysDict/sysDictItemDetail";
    }

    //字典类别详情
    public String ToDictCategoryDetail(HttpRequest request, Model model) {
        return "/SysDict/sysDictCategoryDetail";
    }
}
