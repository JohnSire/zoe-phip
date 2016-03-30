package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.MenuCompetence;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.model.sm.SystemUser;
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
 * Created by zhangxingcai on 2016/3/22 0022.
 * updated by hyf
 */
@Controller
@RequestMapping("/menu")
public class SystemMenuController extends BaseController {
    //region 菜单管理视图
    //菜单列表
    @RequestMapping("/list")
    public String ToMenuList(HttpServletRequest request, Model model) {
        return "SystemManage/SysMenu/list";
    }
    //菜单详情
    @RequestMapping("/detail")
    public String ToMenuDetail(HttpServletRequest request, Model model) {
        return "SystemManage/SysMenu/detail";
    }
    //菜单树
    @RequestMapping("/menutree")
    public String ToMenuTree(HttpServletRequest request, Model model) {
        return "SystemManage/SysMenu/tree";
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
        if(null==keyWord)keyWord="";
        return ServiceFactory.getMenuDataService().getMenuList(ComSession.getUserInfo(), keyWord, getQueryPage());
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
        menuData.setModifyBy(ComSession.getUserInfo().getUserId());
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
        menuData.setCreateBy(ComSession.getUserInfo().getUserId());
        return ServiceFactory.getMenuDataService().add(ComSession.getUserInfo(), menuData);
    }


    @RequestMapping(value = "getMenuUser", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResultT<List<MenuData>> getMenuUser(HttpServletRequest request, String id) {
        return ServiceFactory.getMenuDataService().getCompetenceMenuByUser(ComSession.getUserInfo(), id);
    }



    @RequestMapping(value = "/updateMenuList",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult updateMenuList(HttpServletRequest request) {
        String strList = request.getParameter("list");
        List<MenuData> list = new ArrayList<MenuData>();
        if(!StringUtil.isNullOrWhiteSpace(strList)){
            list = StringUtil.parseJsonArray(strList,MenuData.class);
        }

        ServiceResult result =ServiceFactory.getMenuDataService().updateList(ComSession.getUserInfo(), list);
        return result;
    }


    @RequestMapping(value = "updateState")
    @ResponseBody
    public ServiceResult updateState(String id, int state) {

        return ServiceFactory.getMenuDataService().updateState(ComSession.getUserInfo(),id,state);
    }


    /**
     * 用户配置
     * @param catalogId
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getUserCfg")
    @ResponseBody
    public ServiceResultT<PageList<SystemUser>> getUserCfg(@RequestParam("catalogId") String catalogId, @RequestParam("keyWord") String keyWord) {

        return ServiceFactory.getUserCompetenceService().getUserListByCompetenceCategory(ComSession.getUserInfo(),catalogId,keyWord, getQueryPage());
    }


    /**
     *添加菜单权限
     * @param catalogId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/addMenuAcc")
    @ResponseBody
    public ServiceResult addMenuAcc(@RequestParam("catalogId") String catalogId, @RequestParam("ids") String ids) {
        List<MenuCompetence> models = new ArrayList<MenuCompetence>();
        String [] arrayids = ids.split(",");
        for(String  id:arrayids){
            if(StringUtil.isNullOrWhiteSpace(id))continue;
            MenuCompetence menu = new MenuCompetence();
            menu.setFkCompetenceCategoryId(catalogId);
            menu.setFkMenuId(id);
            models.add(menu);
        }
        return ServiceFactory.getMenuCompetenceService().saveList(ComSession.getUserInfo(),catalogId,models);
    }



    /**
     * 菜单配置
     * @param catalogId
     * @return
     */
    @RequestMapping(value = "/getMenuCfg")
    @ResponseBody
    public ServiceResultT<PageList<MenuData>> getMenuCfg(@RequestParam("catalogId") String catalogId) {
        return ServiceFactory.getMenuCompetenceService().getMenuListByCompetenceCategory( ComSession.getUserInfo(),catalogId, "", getQueryPage());
    }


    /**
     * 删除菜单权限
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delMenuAcc")
    @ResponseBody
    public ServiceResult delMenuAcc( @RequestParam("ids") String ids) {
      List<String> idList = Arrays.asList( ids.split(","));
        return ServiceFactory.getMenuCompetenceService().deleteByIds(ComSession.getUserInfo(),idList);
    }

}
