package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.in.sm.SystemUserService;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.DataContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinzhixing on 2016/3/21.
 */
@Controller
@RequestMapping("/user")
public class SystemUserController {
    //用户列表
    @RequestMapping("/list")
    public String ToList(HttpServletRequest request, Model model) {

        return "/user/list";
    }

    //用户详细信息
    @RequestMapping("/detail")
    public String ToDetail(HttpServletRequest request, Model model) {

        return "/user/detail";
    }

    //修改密码
    @RequestMapping("/pwd")
    public String ToPwd(HttpServletRequest request, Model model) {
        return "/user/pwd";
    }

    //用户选择器
    @RequestMapping("/selector")
    public String ToSelector(HttpServletRequest request, Model model) {
        return "/user/selector";
    }


    /**
     * 获取当前登录用户信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResultT<SystemUser> getUserInfo(HttpServletRequest request, Model model) {
        SystemData userInfo = ComSession.getUserInfo();
        SystemUserService systemUserService = BeanFactory.getBean(Constant.SYSTEM_USER_SERVICE);
        ServiceResultT<SystemUser> user = systemUserService.getById(userInfo.getUserId());
        return user;
    }
}
