package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.in.sm.SystemUserService;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;
import com.zoe.phip.web.context.DataContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by yinzhixing on 2016/3/21.
 */
@Controller
@RequestMapping("/SystemUser")
public class SystemUserController {
    //用户列表
    @RequestMapping("/userList")
    public String ToUserList(HttpServletRequest request, Model model){

        return "/SysUser/userList";
    }
    //用户详细信息
    @RequestMapping("/userDetail")
    public String ToUserDetail(HttpServletRequest request, Model model){

        return "/SysUser/userDetail";
    }
    //修改密码
    @RequestMapping("/userPwd")
    public String ToUserPwd(HttpServletRequest request, Model model){
        return "/SysUser/userPwd";
    }
    //用户选择器
    @RequestMapping("/userSelector")
    public String ToUserSelector(HttpServletRequest request, Model model){
        return "/SysUser/userSelector";
    }

    @RequestMapping(value = "/GetLoginUserInfo",method = RequestMethod.POST)
    public ServiceResultT<SystemUser> getUserInfo(HttpServletRequest request, Model model){
        SystemData userInfo= DataContext.Session.get(Constant.USER_INFO);
        SystemUserService systemUserService = BeanFactory.getBean(Constant.SYSTEM_USER_SERVICE);
        ServiceResultT<SystemUser> user= systemUserService.getById(userInfo.getUserId());
        return user;
    }
}
