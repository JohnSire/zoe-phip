package com.zoe.phip.web.controller;

import com.zoe.phip.model.base.ServiceResult;
import com.zoe.phip.service.in.demo.DeptService;
import com.zoe.phip.web.bean.BeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zengjiyang on 2016/3/11.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping("/test")
    public String toIndex(HttpServletRequest request, Model model){
//        DeptService deptService = BeanFactory.getBean("DeptService");
//        ServiceResult serviceResult= deptService.getById("3");
        return "test";
    }
    @RequestMapping("/home")
    public String toUserIndex(HttpServletRequest request, Model model){
        model.addAttribute("hello", "hello world");
        model.addAttribute("a");
        return "/index";
    }
}
