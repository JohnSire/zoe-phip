package com.zoe.phip.web.controller;

import com.zoe.phip.model.base.ServiceResult;
import com.zoe.phip.service.in.sm.SystemUserService;
import com.zoe.phip.web.bean.BeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by yinzhixing on 2016/3/21.
 */
@Controller
@RequestMapping("/SysUser")
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
}
