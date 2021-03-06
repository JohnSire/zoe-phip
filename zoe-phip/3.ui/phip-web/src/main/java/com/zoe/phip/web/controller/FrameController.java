package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.module.service.entity.LoginCredentials;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.context.UrlExpand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zengjiyang on 2016/3/11.
 */
@Controller
@RequestMapping("/frame")
public class FrameController extends BaseController {


    //平台首页
    @RequestMapping("/index")
    public ModelAndView ToIndex(HttpServletRequest request, Model model) {

        SystemData systemData = ComSession.getUserInfo();

        ModelAndView view = new ModelAndView("/frame/index");
        view.addObject("userInfo", systemData);
        return view;

//        return "/frame/index";
    }

    //管理中心
    @RequestMapping("/center")
    public String ToCenter(HttpServletRequest request, Model model) {
        return "/frame/center";
    }

    //登录界面
    @RequestMapping("/login")
    public String ToLogin(HttpServletRequest request, Model model) {

        return "/frame/login";
    }

    @RequestMapping(value = "/login/auth", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult loginAuth(HttpServletRequest request, Model model) {
        ServiceResult result = new ServiceResult();
        if (request.getParameter("userCode") != null && request.getParameter("userPwd") != null) {
            ServiceResultT<LoginCredentials> serviceResult = ServiceFactory.getUserService().login(request.getParameter("userCode"),
                    request.getParameter("userPwd"), 1000 * 60 * 100);//session默认为10分钟
            result.setIsSuccess(serviceResult.getIsSuccess());
            result.setMessages(serviceResult.getMessages());
            //存储用户session
            if (serviceResult.getIsSuccess()) {
                SystemData systemData = new SystemData();
                systemData.setUserId(serviceResult.getResult().getUserId());
                systemData.setUserName(serviceResult.getResult().getUserName());
                systemData.setCredential(serviceResult.getResult().getCredential());
                //设置session
                ComSession.setUserInfo(systemData);
            }
        }
        return result;
    }


    //未登录界面或着登录超时跳转中间界面
    @RequestMapping("/skip")
    public String ToSkip(HttpServletRequest request, Model model) {
        return "/frame/skip";
    }

    // 注销用户登录
    @RequestMapping("/loginout")
    public String ToLoginOut(HttpServletRequest request, Model model) {
        return "/frame/loginOut";
    }

    //404界面
    @RequestMapping("/error")
    public String ToError(HttpServletRequest request, Model model) {
        return "/frame/error";
    }

    //无权限访问提醒界
    @RequestMapping("/noPower")
    public String ToNoPower(HttpServletRequest request, Model model) {
        return "/frame/noPower";
    }
}
