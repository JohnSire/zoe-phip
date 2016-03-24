package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.*;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by yinzhixing on 2016/3/21.
 */
@Controller
@RequestMapping("/user")
public class SystemUserController extends BaseController {

    private SystemUserService systemUserService;

    //region 视图

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

    //endregion




    //region 方法

    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(value = "/get/list", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResultT<PageList<SystemUser>>  GetUserList(){
        ServiceResultT<PageList<SystemUser>> result=
                getSystemUserService().getList(getQueryPage(),SystemUser.class);
        return result;
    }


    /**
     * 获取当前登录用户信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResultT<SystemUser> getUserInfo(HttpServletRequest request, Model model) {
        SystemData userInfo = ComSession.getUserInfo();
        ServiceResultT<SystemUser> user = getSystemUserService().getById(userInfo.getUserId());
        return user;
    }


    /**
     * 新增用户
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult addUserInfo(SystemUser userInfo) {
        return getSystemUserService().add(userInfo);
    }

    /**
     * 更新用户
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult updateUserInfo(SystemUser userInfo) {
        return getSystemUserService().update(userInfo);
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult deleteUserInfo(String id) {
        return getSystemUserService().deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete/all", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult deleteUserList(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        return getSystemUserService().deleteByIds(list);
    }

    //endregion

    private SystemUserService getSystemUserService() {
        if (systemUserService == null) {
            return BeanFactory.getBean(Constant.SYSTEM_USER_SERVICE);
        } else
            return systemUserService;
    }
}
