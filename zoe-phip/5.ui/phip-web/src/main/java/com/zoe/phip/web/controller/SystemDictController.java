package com.zoe.phip.web.controller;


import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.model.sm.SystemDictItem;
import com.zoe.phip.service.in.sm.ISystemDictCategoryService;
import com.zoe.phip.service.in.sm.ISystemDictItemService;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangxingcai on 2016/3/22 0022.
 */
@Controller
@RequestMapping("/dict")
public class SystemDictController extends BaseController {

    //系统字典列表
    @RequestMapping("/view/list")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToDictList(HttpServletRequest request, Model model) {
        return "/SystemManage/SysDict/list";
    }

    //字典项详情
    @RequestMapping("/view/item")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToDictItemDetail(HttpServletRequest request, Model model) {
        return "/SystemManage/SysDict/item";
    }

    //字典类别详情
    @RequestMapping("/view/category")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToDictCategoryDetail(HttpServletRequest request, Model model) {
        return "/SystemManage/SysDict/category";
    }


    /**
     * 根据关键字获取字典分类列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getCategoryList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<SystemDictCategory>> getSysDictCategoryList(HttpServletRequest request, Model model) {
        return ServiceFactory.getDictCategoryService().getDictCategories(ComSession.getUserInfo(), super.getKey(), getQueryPage());
    }

    /**
     * 根据字典分类ID获取字典分类详情
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getCategoryInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<SystemDictCategory> getSysDictCategoryInfo(HttpServletRequest request, Model model) {
        return ServiceFactory.getDictCategoryService().getById(ComSession.getUserInfo(), request.getParameter("id"));
    }

    /**
     * 新增字典分类
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/addCategory")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addCategoryInfo(SystemDictCategory info, HttpServletRequest request) {
        return ServiceFactory.getDictCategoryService().add(ComSession.getUserInfo(), info);
    }

    /**
     * 更新字典分类
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateCategory")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "修改")
    public ServiceResult updateCategoryInfo(SystemDictCategory info, HttpServletRequest request) {
        return ServiceFactory.getDictCategoryService().update(ComSession.getUserInfo(), info);
    }

    /**
     * 删除字典分类
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteCategory")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult deleteCategoryInfo(HttpServletRequest request) {
        String id = request.getParameter("id");
        return ServiceFactory.getDictCategoryService().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除字典分类
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteCategoryList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult deleteSysDictCategoryList(HttpServletRequest request) {
        String id = request.getParameter("ids");
        return ServiceFactory.getDictCategoryService().deleteByIds(ComSession.getUserInfo(), Arrays.asList(id.split(",")));
    }

    /**
     * 根据字典分类和关键字信息，获取系统字典项列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getItemPageList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<SystemDictItem>> getSysDictItemList(HttpServletRequest request) {
        String categoryId = request.getParameter("categoryId");
        return ServiceFactory.getDictItemService().getDictItems(ComSession.getUserInfo(), categoryId, getKey(), getQueryPage());
    }

    @RequestMapping(value = "/getItemList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<List<SystemDictItem>> getSysDictItemListByCode(HttpServletRequest request) {
        String catalog = request.getParameter("catalogCode");
        return ServiceFactory.getDictItemService().getDictItemsByCategoryCode(ComSession.getUserInfo(), catalog);
    }

    @RequestMapping(value = "/getSysDictItemInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<SystemDictItem> getSysDictItemInfo(HttpServletRequest request) {
        return ServiceFactory.getDictItemService().getById(ComSession.getUserInfo(), request.getParameter("id"));
    }


    /**
     * 添加系统字典项信息
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/addItem")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addSysDictItemInfo(SystemDictItem info, HttpServletRequest request) {
        return ServiceFactory.getDictItemService().add(ComSession.getUserInfo(), info);
    }

    /**
     * 添加系统字典项信息
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateItem")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "修改")
    public ServiceResult updateSysDictItemInfo(SystemDictItem info, HttpServletRequest request) {
        return ServiceFactory.getDictItemService().update(ComSession.getUserInfo(), info);
    }

    /**
     * 根据字典项Id删除字典项
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteItem")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult deleteSysDictItemInfo(String id) {
        return ServiceFactory.getDictItemService().deleteById(ComSession.getUserInfo(), id);
    }

    @RequestMapping(value = "/deleteItemList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult deleteSysDictItemList(HttpServletRequest request) {
        return ServiceFactory.getDictItemService().deleteByIds(ComSession.getUserInfo(), Arrays.asList(request.getParameter("ids").split(",")));
    }

    @RequestMapping(value = "/categoryExists")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResult categoryExists(HttpServletRequest request) {
        return ServiceFactory.getDictItemService().categoryExists(ComSession.getUserInfo(), request.getParameter("catalogId"));
    }
}
