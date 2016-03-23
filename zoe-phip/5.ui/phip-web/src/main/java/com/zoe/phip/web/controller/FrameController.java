package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.model.sm.LoginCredentials;
import com.zoe.phip.service.in.sm.SystemUserService;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;
import com.zoe.phip.web.context.DataContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

        return "/frame/login";
    }

    @RequestMapping(value = "/login/auth",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult loginAuth(HttpServletRequest request, Model model){
        ServiceResult result=new ServiceResult();
        if(request.getParameter("userCode")!=null&&request.getParameter("userPwd")!=null){
            SystemUserService systemUserService = BeanFactory.getBean(Constant.SYSTEM_USER_SERVICE);
            ServiceResultT<LoginCredentials> serviceResult= systemUserService.login(request.getParameter("userCode"),
                    request.getParameter("userPwd"),1000*10);
            result.setIsSuccess(serviceResult.getIsSuccess());
            result.setMessages(serviceResult.getMessages());
            //存储用户session
            if(serviceResult.getIsSuccess())
            {
                SystemData systemData=new SystemData();
                systemData.setUserId(serviceResult.getResult().getUserId());
                systemData.setCredential(serviceResult.getResult().getCredential());
                DataContext.Session.put(Constant.USER_SESSION,systemData);
            }

        }
        return result;
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
    //无权限访问提醒界
    @RequestMapping("/noPower")
    public String ToNoPower(HttpServletRequest request, Model model){
        return "/frame/noPower";
    }
}
