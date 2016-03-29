package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.model.sm.UserCompetence;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yinzhixing on 2016/3/21.
 */
@Controller
@RequestMapping("/user")
public class SystemUserController extends BaseController {


    //region 视图

    //用户列表
    @RequestMapping("/view/list")
    public String ToList(HttpServletRequest request, Model model) {

        return "/user/list";
    }

    //用户详细信息
    @RequestMapping("/view/detail")
    public String ToDetail(HttpServletRequest request, Model model) {

        return "/user/detail";
    }

    //修改密码
    @RequestMapping("/view/pwd")
    public String ToPwd(HttpServletRequest request, Model model) {
        return "/user/pwd";
    }

    //用户选择器
    @RequestMapping("/view/selector")
    public String ToSelector(HttpServletRequest request, Model model) {
        return "/user/selector";
    }

    //endregion


    //region 方法

    /**
     * 获取用户列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResultT<PageList<SystemUser>> getUserList(HttpServletRequest request) {
        if(StringUtil.isNullOrWhiteSpace(request.getParameter("keyWord"))){
            return ServiceFactory.getUserService()
                    .getList(ComSession.getUserInfo(), getQueryPage(), SystemUser.class);
        }else {
            return ServiceFactory.getUserService()
                    .getUserList(ComSession.getUserInfo(),null,request.getParameter("keyWord"), getQueryPage());
        }
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
        ServiceResultT<SystemUser> user = ServiceFactory.getUserService().getById(ComSession.getUserInfo(), userInfo.getUserId());
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
        return ServiceFactory.getUserService().add(ComSession.getUserInfo(), userInfo);
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
        return ServiceFactory.getUserService().update(ComSession.getUserInfo(), userInfo);
    }

    /**
     * 更新用户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/update/state", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult updateState(String id,int state) {
        return ServiceFactory.getUserService().updateState(ComSession.getUserInfo(),id,state);
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResult deleteUserInfo(String id) {
        return ServiceFactory.getUserService().deleteById(ComSession.getUserInfo(), id);
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
        return ServiceFactory.getUserService().deleteByIds(ComSession.getUserInfo(), list);
    }

    /**
     * 获取用户关联菜单
     *
     * @return
     */
    @RequestMapping("/menu")
    @ResponseBody
    public ServiceResultT<List<MenuData>> getUserMenu() {
        return ServiceFactory.getMenuDataService().getCompetenceMenuByUser(ComSession.getUserInfo(), ComSession.getUserInfo().getUserId());
    }

    //endregion


    /**
     * 添加用户权限
     * @param catalogId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/addUserAcc")
    @ResponseBody
    public ServiceResult addUserAcc(@RequestParam("catalogId") String catalogId, @RequestParam("ids") String ids) {
        List<UserCompetence> models = new ArrayList<UserCompetence>();
        String [] arrayids = ids.split(",");
        for(String  id:arrayids){
            if(StringUtil.isNullOrWhiteSpace(id))continue;
            UserCompetence menu = new UserCompetence();
            menu.setFkCompetenceCategoryId(catalogId);
            menu.setFkUserId(id);
            models.add(menu);
        }
        return ServiceFactory.getUserCompetenceService().saveList(catalogId,models);
    }




}
