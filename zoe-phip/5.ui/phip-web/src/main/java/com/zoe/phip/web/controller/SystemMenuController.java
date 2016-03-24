package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.service.in.sm.MenuDataService;
import com.zoe.phip.service.in.sm.SystemDictCategoryService;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;
import com.zoe.phip.web.context.ComSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangxingcai on 2016/3/22 0022.
 */
@Controller
@RequestMapping("/menu")
public class SystemMenuController {
    //菜单页面
    @RequestMapping("/list")
    public String ToMenuList(HttpServletRequest request, Model model) {
        return "/menu/list";
    }

    //修改菜单信息
    @RequestMapping("/detail")
    public String ToMenuDetail(HttpServletRequest request, Model model) {
        return "/menu/detail";
    }

    //菜单结构调整
    @RequestMapping("/tree")
    public String ToMenuTree(HttpServletRequest request, Model model) {
        return "/menu/tree";
    }

    //菜单权限
    @RequestMapping("/acc")
    public String ToMenuAcc(HttpServletRequest request, Model model) {
        return "/menu/acc";
    }

    /**
     * 获取用户关联菜单
     * @return
     */
    @RequestMapping("/user")
    @ResponseBody
    public ServiceResultT<List<MenuData>> getMenuUser(){
        MenuDataService menuDataService = BeanFactory.getBean(Constant.MENU_DATA_SERVICE);
        return menuDataService.getCompetenceMenuByUser(null,ComSession.getUserInfo().getUserId());
    }
    @RequestMapping(value = "/getMenuList",method = RequestMethod.GET)
    @ResponseBody
    public ServiceResultT<PageList<MenuData>> getMenuList(HttpServletRequest request, Model model) {
        MenuDataService menuDataService = BeanFactory.getBean(Constant.MENU_DATA_SERVICE);
        QueryPage page = new QueryPage(1, 30);
        ServiceResultT<PageList<MenuData>> menu = menuDataService.getList(page, MenuData.class);
        return menu;
    }
}
