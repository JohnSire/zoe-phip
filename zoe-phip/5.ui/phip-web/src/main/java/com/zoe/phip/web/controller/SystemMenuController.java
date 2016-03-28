package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxingcai on 2016/3/22 0022.
 */
@Controller
@RequestMapping("/menu")
public class SystemMenuController extends BaseController {
    //菜单页面
    @RequestMapping("/view/list")
    public String ToMenuList(HttpServletRequest request, Model model) {
        return "/menu/list";
    }

    //修改菜单信息
    @RequestMapping("/view/detail")
    public String ToMenuDetail(HttpServletRequest request, Model model) {
        return "/menu/detail";
    }

    //菜单结构调整
    @RequestMapping("/view/tree")
    public String ToMenuTree(HttpServletRequest request, Model model) {
        return "/menu/tree";
    }

    //菜单权限
    @RequestMapping("/view/acc")
    public String ToMenuAcc(HttpServletRequest request, Model model) {
        return "/menu/acc";
    }

    /**
     * 获取用户关联菜单
     *
     * @return
     */
    @RequestMapping("/user")
    @ResponseBody
    public ServiceResultT<List<MenuData>> getMenuUser() {
        return ServiceFactory.getMenuDataService().getCompetenceMenuByUser(ComSession.getUserInfo(), ComSession.getUserInfo().getUserId());
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResultT<PageList<MenuData>> getMenuList(HttpServletRequest request, Model model) {
        ServiceResultT<PageList<MenuData>> menu = ServiceFactory.getMenuDataService().getList(ComSession.getUserInfo(), getQueryPage(), MenuData.class);
        return menu;
    }


    /**
     * 根据菜单id获取菜单信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getMenuInfo", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResultT<MenuData> getMenuInfo(HttpServletRequest request, Model model) {

        return ServiceFactory.getMenuDataService().getById(ComSession.getUserInfo(), request.getParameter("id"));
    }

    /**
     * 根据关键字获取菜单列�
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getMenuList")
    @ResponseBody
    public ServiceResultT<PageList<MenuData>> getMenuPageList(HttpServletRequest request, Model model) {
        String keyWord = request.getParameter("keyWord");
        return ServiceFactory.getMenuDataService().getMenuPages(ComSession.getUserInfo(), keyWord, getQueryPage());
    }

    /**
     * 修改菜单信息
     *
     * @param request
     * @param menuData
     * @return
     */
    @RequestMapping(value = "/updateMenuInfo", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult updateMenuInfo(HttpServletRequest request, MenuData menuData) {
        return ServiceFactory.getMenuDataService().update(ComSession.getUserInfo(), menuData);
    }


    /**
     * 添加菜单
     *
     * @param menuData
     * @return
     */
    @RequestMapping(value = "/addMenuInfo")
    @ResponseBody
    public ServiceResult addMenuInfo(MenuData menuData) {
        return ServiceFactory.getMenuDataService().add(ComSession.getUserInfo(), menuData);
    }


    @RequestMapping(value = "getMenuUser", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResultT<List<MenuData>> getMenuUser(HttpServletRequest request, String id) {
        return ServiceFactory.getMenuDataService().getCompetenceMenuByUser(ComSession.getUserInfo(), id);
    }



    @RequestMapping(value = "/updateMenuInfo",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult updateMenuList(HttpServletRequest request) {
        String strList = request.getParameter("lits");
        List<MenuData> list = new ArrayList<MenuData>();
        if(!StringUtil.isNullOrWhiteSpace(strList)){
            list = StringUtil.parseJsonArray(strList,MenuData.class);
        }
        //to do  等纪洋底层批量的方法实现�
        /*return ServiceFactory.getMenuDataService().updateList(ComSession.getUserInfo(), list);*/
        for (MenuData menuData:list) {
            ServiceFactory.getMenuDataService().update(ComSession.getUserInfo(),menuData);
        }
        ServiceResult s= new ServiceResult();
        s.setIsSuccess(true);
        return s;
    }


    @RequestMapping(value = "updateState")
    @ResponseBody
    public ServiceResult updateState(String id, int state) {
        return ServiceFactory.getMenuDataService().updateState(ComSession.getUserInfo(),id,state);
    }


}
