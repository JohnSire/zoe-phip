package com.zoe.phip.web.controller.Register;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.register.model.DictCatalog;
import com.zoe.phip.register.model.DictItem;
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
    public ServiceResult delOIDInfo(String id) {
        return ServiceFactory.getNationalStandardsService().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除OID
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delOIDList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delOIDList(String ids) {
        return ServiceFactory.getNationalStandardsService().deleteByIds(ComSession.getUserInfo(), ids);
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

    /**
     * 根据code获取字典分类（字典）信息
     * @param code
     * @return
     */
    @RequestMapping(value = "/getDictCatalogInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<DictCatalog> getDictCatalogInfo(String code) {
        return ServiceFactory.getDictRegisterIn().dictCatalogDetailQuery(ComSession.getUserInfo(), code);
    }

    /**
     * 根据id获取字典分类（字典）信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDictCatalogInfoById")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<DictCatalog> getDictCatalogInfoById(String id) {
        return ServiceFactory.getDictRegisterIn().dictCatalogDetailQueryById(ComSession.getUserInfo(), id);
    }

    /**
     * 新增字典分类（字典）
     * @param dc
     * @return
     */

    @RequestMapping(value = "/addDictCatalogInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addDictCatalogInfo(DictCatalog dc, HttpServletRequest request) {
        return ServiceFactory.getDictRegisterIn().addDictCatalogRequest(ComSession.getUserInfo(), dc);
    }

    /**
     * 更新字典分类（字典）
     * @param dc
     * @return
     */
    @RequestMapping(value = "/updateDictCatalogInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult updateDictCatalogInfo(DictCatalog dc, HttpServletRequest request) {
        return ServiceFactory.getDictRegisterIn().updateDictCatalogRequest(ComSession.getUserInfo(), dc);
    }

    /**
     * 删除字典分类（字典）
     * @param id
     * @return
     */
    @RequestMapping(value = "/delDictCatalogInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delDictCatalogInfo(String id) {
        ServiceResult result = ServiceFactory.getDictRegisterIn().dictCatalogDetailDelete(ComSession.getUserInfo(), id);
        return result;
    }

    /**
     * 根据关键字获取字典分类（字典）列表
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getDictCatalogList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<DictCatalog>> getDictCatalogList(String keyWord) {
        return ServiceFactory.getDictRegisterIn().dictCatalogListQuery(ComSession.getUserInfo(), getQueryPage(), keyWord);
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

    /**
     * 根据code获取字典项信息
     * @param code
     * @return
     */
    @RequestMapping(value = "/getDictItemInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<DictItem> getDictItemInfo(String code) {
        return ServiceFactory.getDictRegisterIn().dictItemDetailQuery(ComSession.getUserInfo(), code);
    }

    /**
     * 根据id获取字典项信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDictItemInfoById")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<DictItem> getDictItemInfoById(String id) {
        return ServiceFactory.getDictRegisterIn().dictItemDetailQueryById(ComSession.getUserInfo(), id);
    }

    /**
     * 新增字典项
     * @param di
     * @return
     */

    @RequestMapping(value = "/addDictItemInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addDictItemInfo(DictItem di, HttpServletRequest request) {
        return ServiceFactory.getDictRegisterIn().addDictItemRequest(ComSession.getUserInfo(), di);
    }

    /**
     * 更新字典项
     * @param di
     * @return
     */
    @RequestMapping(value = "/updateDictItemInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult updateDictItemInfo(DictItem di, HttpServletRequest request) {
        return ServiceFactory.getDictRegisterIn().updateDictItemRequest(ComSession.getUserInfo(), di);
    }

    /**
     * 删除字典项
     * @param id
     * @return
     */
    @RequestMapping(value = "/delDictItemInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delDictItemInfo(String id) {
        ServiceResult result = ServiceFactory.getDictRegisterIn().dictItemDetailDelete(ComSession.getUserInfo(), id);
        return result;
    }

    /**
     * 根据关键字获取字典分类（字典）列表
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getDictItemList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<DictItem>> getDictItemList(String keyWord, String catalogCode) {
        return ServiceFactory.getDictRegisterIn().dictItemListQuery(ComSession.getUserInfo(), catalogCode,  getQueryPage(), keyWord);
    }
    //endregion
}
