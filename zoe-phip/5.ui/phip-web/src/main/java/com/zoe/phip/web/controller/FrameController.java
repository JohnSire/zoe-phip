package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.service.in.sm.SystemUserService;
import com.zoe.phip.web.bean.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
    //平台首页
    @RequestMapping("/index")
    public String ToIndex(HttpServletRequest request, Model model){
        return "/frame/index";
    }
    //管理中心
    @RequestMapping("/center")
    public String ToCenter(HttpServletRequest request, Model model){
        return "/frame/center";
    }
    //登录界面
    @RequestMapping("/login")
    public String ToLogin(HttpServletRequest request, Model model){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"application-context-consumer.xml"});
        context.start();
        SystemUserService deptService = BeanFactory.getBean("SystemUserService");
        ServiceResult serviceResult= deptService.login("zjy","zjy",1000*10);
        model.addAttribute("hello", "hello world");
        model.addAttribute("a");
        return "/frame/login";
    }
    //未登录界面或着登录超时跳转中间界面
    @RequestMapping("/skip")
    public String ToSkip(HttpServletRequest request, Model model){
        return "/frame/skip";
    }
    // 注销用户登录
    @RequestMapping("/loginOut")
    public String ToLoginOut(HttpServletRequest request, Model model){
        return "/frame/loginOut";
    }
    //404界面
    @RequestMapping("/error")
    public String ToError(HttpServletRequest request, Model model){
        return "/frame/error";
    }
    //无权限访问提醒界面
    @RequestMapping("/noPower")
    public String ToNoPower(HttpServletRequest request, Model model){
        return "/frame/noPower";
    }
}
