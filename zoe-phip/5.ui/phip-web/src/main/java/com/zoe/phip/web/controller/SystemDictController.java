package com.zoe.phip.web.controller;


import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.model.sm.SystemDictItem;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.in.sm.SystemDictCategoryService;
import com.zoe.phip.service.in.sm.SystemDictItemService;
import com.zoe.phip.service.in.sm.SystemUserService;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;
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

    private SystemDictCategoryService categoryService;

    private SystemDictItemService itemService;

    //系统字典列表
    @RequestMapping("/list")
    public String ToDictList(HttpServletRequest request, Model model) {
        return "/dict/list";
    }

    //字典项详情
    @RequestMapping("/item")
    public String ToDictItemDetail(HttpServletRequest request, Model model) {
        return "/dict/item";
    }

    //字典类别详情
    @RequestMapping("/category")
    public String ToDictCategoryDetail(HttpServletRequest request, Model model) {
        return "/dict/category";
    }


    /**
     * 根据关键字获取字典分类列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/getCategoryList")
    @ResponseBody
    public ServiceResultT<PageList<SystemDictCategory>> getSysDictCategoryList(HttpServletRequest request, Model model) {

        categoryService = BeanFactory.getBean(Constant.SYSTEM_DICT_CATEGORY_SERVICE);
        String key = request.getParameter("keyWord");
        return categoryService.getDictCategories(key, getQueryPage());
    }

    /**
     * 根据字典分类ID获取字典分类详情
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/getCategoryInfo")
    @ResponseBody
    public ServiceResultT<SystemDictCategory> getSysDictCategoryInfo(HttpServletRequest request, Model model) {
        categoryService = BeanFactory.getBean(Constant.SYSTEM_DICT_CATEGORY_SERVICE);

        return  categoryService.getById(request.getParameter("id"));
    }

    /**
     * 新增字典分类
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping("/addCategory")
    @ResponseBody
    public ServiceResult addCategoryInfo(SystemDictCategory info, HttpServletRequest request) {
        categoryService = BeanFactory.getBean(Constant.SYSTEM_DICT_CATEGORY_SERVICE);

        return categoryService.add(info);
    }

    /**
     * 更新字典分类
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping("/updateCategory")
    @ResponseBody
    public ServiceResult updateCategoryInfo(SystemDictCategory info, HttpServletRequest request) {
        categoryService = BeanFactory.getBean(Constant.SYSTEM_DICT_CATEGORY_SERVICE);

        return categoryService.update(info);
    }

    /**
     * 删除字典分类
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteCategory")
    @ResponseBody
    public ServiceResult deleteCategoryInfo(HttpServletRequest request) {
        categoryService = BeanFactory.getBean(Constant.SYSTEM_DICT_CATEGORY_SERVICE);

        String id = request.getParameter("id");
        return categoryService.deleteById(id);
    }

    /**
     * 批量删除字典分类
     *
     * @param request
     * @return
     */
    @RequestMapping("/deleteCategoryList")
    @ResponseBody
    public ServiceResult deleteSysDictCategoryList(HttpServletRequest request) {
        categoryService = BeanFactory.getBean(Constant.SYSTEM_DICT_CATEGORY_SERVICE);
        String id = request.getParameter("ids");
        return categoryService.deleteByIds(Arrays.asList(id.split(",")));
    }

    /**
     * 根据字典分类和关键字信息，获取系统字典项列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/getItemPageList")
    @ResponseBody
    public ServiceResultT<PageList<SystemDictItem>> getSysDictItemList(HttpServletRequest request) {

        itemService = BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
        String key = request.getParameter("keyWord");
        String categoryId = request.getParameter("categoryId");
        return itemService.getDictItems(categoryId, key, getQueryPage());
    }

    @RequestMapping("/getItemList")
    @ResponseBody
    public ServiceResultT<List<SystemDictItem>> getSysDictItemListByCode(HttpServletRequest request) {
        itemService = BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
        String catalog = request.getParameter("catalogCode");
        return itemService.getDictItemsByCategoryCode(catalog);
    }

    @RequestMapping("/getItemInfo")
    @ResponseBody
    public ServiceResultT<SystemDictItem> getSysDictItemInfo(HttpServletRequest request) {
        itemService = BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
        return itemService.getById(request.getParameter("id"));
    }


    /**
     * 添加系统字典项信息
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ServiceResult addSysDictItemInfo(SystemDictItem info, HttpServletRequest request) {
        itemService = BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
        return itemService.add(info);
    }

    /**
     * 添加系统字典项信息
     *
     * @param info
     * @param request
     * @return
     */
    @RequestMapping("/updateItem")
    @ResponseBody
    public ServiceResult updateSysDictItemInfo(SystemDictItem info, HttpServletRequest request) {
        itemService = BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
        return itemService.update(info);
    }

    /**
     * 根据字典项Id删除字典项
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteItem")
    @ResponseBody
    public ServiceResult deleteSysDictItemInfo(String id) {
        itemService = BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
        return itemService.deleteById(id);
    }

    @RequestMapping("/deleteItemList")
    @ResponseBody
    public ServiceResult deleteSysDictItemList(HttpServletRequest request) {
        itemService = BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
        return itemService.deleteByIds(Arrays.asList(request.getParameter("ids").split(",")));
    }

    @RequestMapping("/categoryExists")
    @ResponseBody
    public ServiceResult categoryExists(HttpServletRequest request) {
        itemService = BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
        return itemService.categoryExists(request.getParameter("catalogId"));
    }
}
