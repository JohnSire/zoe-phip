package com.zoe.phip.web.controller;


import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangxingcai on 2016/3/22 0022.
 */
@Controller
@RequestMapping("/dictm")
public class SystemDictController {
    //系统字典列表
    @RequestMapping("/list")
    public String ToDictList(HttpServletRequest request, Model model) {
        return "/dictm/list";
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

    @RequestMapping(value="/dictItem",method = RequestMethod.GET)
    @ResponseBody
    public ServiceResultT<PageList<SystemDictItem>> getDictItem(HttpServletRequest request, Model model) {
        SystemDictItemService systemUserService = BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
        QueryPage page = new QueryPage(1, 30);
        ServiceResultT<PageList<SystemDictItem>> item = systemUserService.getList(page, SystemDictItem.class);
        return item;
    }
    @RequestMapping(value="dictCate",method = RequestMethod.GET)
    @ResponseBody
    public ServiceResultT<PageList<SystemDictCategory>> getSysDictCategoryList(HttpServletRequest request, Model model) {
        SystemDictCategoryService systemDictCategoryService = BeanFactory.getBean(Constant.SYSTEM_DICT_CATEGORY_SERVICE);
        QueryPage page = new QueryPage(1, 30);
        ServiceResultT<PageList<SystemDictCategory>> item = systemDictCategoryService.getList(page, SystemDictCategory.class);
        return item;
    }
}
