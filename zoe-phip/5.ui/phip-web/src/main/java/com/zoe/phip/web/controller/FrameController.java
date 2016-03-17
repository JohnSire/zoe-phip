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
@RequestMapping("/frame")
public class FrameController {
    @RequestMapping("/login")
    public String ToLogin(HttpServletRequest request, Model model){
//        DeptService deptService = BeanFactory.getBean("DeptService");
//        ServiceResult serviceResult= deptService.getById("3");
//        model.addAttribute("hello", "hello world");
//        model.addAttribute("a");
        return "/frame/login";
    }
}
