package com.zoe.phip.web.controller.Register;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.register.model.NationalStandards;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by linqinghuang on 2016/4/21.
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {
    //region oid管理模块

    @RequestMapping("/view/oidlist")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToOIDList(HttpServletRequest request, Model model) {
        return "/Register/Dict/oidList";
    }

    @RequestMapping("/view/oiddetail")
    public String ToOIDDetail(HttpServletRequest request, Model model) {
        return "/Register/Dict/oidDetail";
    }

    /**
     * 根据关键字获取OID列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getOIDList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<NationalStandards>> getOIDList(String keyWord) {
        return ServiceFactory.getNationalStandardsService().getDataListByPage(ComSession.getUserInfo(), keyWord, getQueryPage());
    }

    /**
     * 根据id获取OID信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getOIDInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<NationalStandards> getOIDInfo(String id) {
        return ServiceFactory.getNationalStandardsService().getById(ComSession.getUserInfo(), id);
    }

    /**
     * 新增OID
     *
     * @return
     */

    @RequestMapping(value = "/addOIDInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addOIDInfo(NationalStandards nsd, HttpServletRequest request) {
        return ServiceFactory.getNationalStandardsService().add(ComSession.getUserInfo(), nsd);
    }

    /**
     * 更新OID列表
     *
     * @return
     */
    @RequestMapping(value = "/updateOIDInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult updateOIDInfo(NationalStandards nsd, HttpServletRequest request) {
        return ServiceFactory.getNationalStandardsService().update(ComSession.getUserInfo(), nsd);
    }

    /**
     * 删除OID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delOIDInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delOIDInfo(HttpServletRequest request) {
        String id = request.getParameter("id");
        return ServiceFactory.getNationalStandardsService().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除OID
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delOIDList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delOIDList(HttpServletRequest request) {
        String id = request.getParameter("ids");
        return ServiceFactory.getNationalStandardsService().deleteByIds(ComSession.getUserInfo(), id);
    }

    //endregion
    //region 字典分类
    @RequestMapping("/view/dictcategorylist")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToDictCategoryList(HttpServletRequest request, Model model) {
        return "/Register/Dict/dictCategoryList";
    }

    @RequestMapping("/view/dictcategorydetail")
    public String ToDictCategoryDetail(HttpServletRequest request, Model model) {
        return "/Register/Dict/dictCategoryDetail";
    }
    //endregion

    //region 字典(字典列表）
    @RequestMapping("/view/dictlist")
    public String ToDictList(HttpServletRequest request, Model model) {
        return "/Register/Dict/dictList";
    }

    //region 字典详细
    @RequestMapping("/view/dictdetail")
    public String ToDictDetail(HttpServletRequest request, Model model) {
        return "Register/Dict/dictDetail";
    }
    //endregion
}
