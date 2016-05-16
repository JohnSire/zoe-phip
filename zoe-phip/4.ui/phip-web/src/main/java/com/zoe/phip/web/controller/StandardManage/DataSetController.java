package com.zoe.phip.web.controller.StandardManage;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import com.zoe.phip.web.model.sdm.StRsSetElementInfo;
import com.zoe.phip.web.model.sdm.StSetInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenzhisen on 2016/5/6.
 */
@Controller
@RequestMapping("/dataSet")
public class DataSetController extends BaseController {
    //region 数据集 View

    @RequestMapping("/view/dataSetList")
    @AuthAction(permission = {Permission.View}, name = "查看数据集")
    public String ToDataSetList(HttpServletRequest request, Model model) {
        return "/StandardManage/DataSet/dataSetList";
    }

    @RequestMapping("/view/dataSetDetail")
    @AuthAction(permission = {Permission.View}, name = "查看数据集详情")
    public String ToDataSetDetail(HttpServletRequest request, Model model) {
        return "/StandardManage/DataSet/dataSetDetail";
    }

    @RequestMapping("/view/childSet")
    @AuthAction(permission = {Permission.View}, name = "查看子级数据集")
    public String ToChildSet(HttpServletRequest request, Model model) {
        return "/StandardManage/DataSet/childSet";
    }


    @RequestMapping("/view/columnList")
    @AuthAction(permission = {Permission.View}, name = "查看数据集字段")
    public String ToColumnList(HttpServletRequest request, Model model) {
        return "/StandardManage/DataSet/columnList";
    }

    @RequestMapping("/view/columnDetail")
    @AuthAction(permission = {Permission.View}, name = "查看数据集字段详情")
    public String ToColumnDetail(HttpServletRequest request, Model model) {
        return "/StandardManage/DataSet/columnDetail";
    }
    //endregion

    //region 数据集 Json
    @RequestMapping(value = "/getSetList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询数据集列表")
    public ServiceResultT<PageList<StSetInfo>> getSetList(String keyWord) {
        return ServiceFactory.getStSetInfoService().getDataPageList(ComSession.getUserInfo(), keyWord, getQueryPage());
    }

    @RequestMapping(value = "/getSetInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询数据集")
    public ServiceResult getSetInfo(String id, HttpServletRequest request) {
        return ServiceFactory.getStSetInfoService().getById(ComSession.getUserInfo(), id);
    }

    /**
     * @param StSetInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/addSetInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增数据集")
    public ServiceResult addSetInfo(StSetInfo StSetInfo, HttpServletRequest request) {
        return ServiceFactory.getStSetInfoService().add(ComSession.getUserInfo(), StSetInfo);
    }

    /**
     * @param StSetInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateSetInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新数据集")
    public ServiceResult updateSetInfo(StSetInfo StSetInfo, HttpServletRequest request) {
        return ServiceFactory.getStSetInfoService().update(ComSession.getUserInfo(), StSetInfo);
    }

    @RequestMapping(value = "/getByChildSet")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询数据集子级")
    public ServiceResultT<PageList<StSetInfo>> getByChildSet(String pid) {
        return ServiceFactory.getStSetInfoService().getByPid(ComSession.getUserInfo(), pid,getQueryPage());
    }

    /**
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteSetList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "批量删除数据集")
    public ServiceResult deleteSetList(String ids) {
        return ServiceFactory.getStSetInfoService().deleteByIds(ComSession.getUserInfo(), ids);
    }

    @RequestMapping(value = "/deleteSetInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除数据集")
    public ServiceResult deleteSetInfo(String id) {
        return ServiceFactory.getStSetInfoService().deleteById(ComSession.getUserInfo(), id);
    }
    //endregion

    //region 数据集字段 Json
    @RequestMapping(value = "/getRsSetColumn")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询数据集字段列表")
    public ServiceResult getRsSetColumn(String id, String keyWord) {
        return ServiceFactory.getStRsSetElementInfoService().getDataPageList(ComSession.getUserInfo(), id, keyWord, getQueryPage());
    }

    @RequestMapping(value = "/getRsSetColumnById")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询数据集字段")
    public ServiceResult getRsSetColumnById(String id) {
        return ServiceFactory.getStRsSetElementInfoService().getById(ComSession.getUserInfo(), id);
    }

    @RequestMapping(value = "/addRsSetColumn")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "添加数据集字段")
    public ServiceResult addRsSetColumn(StRsSetElementInfo model, String keyWord) {
        return ServiceFactory.getStRsSetElementInfoService().add(ComSession.getUserInfo(), model);
    }

    @RequestMapping(value = "/updateRsSetColumn")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新数据集字段")
    public ServiceResult updateRsSetColumn(StRsSetElementInfo model, String keyWord) {
        return ServiceFactory.getStRsSetElementInfoService().update(ComSession.getUserInfo(), model);
    }

    @RequestMapping(value = "/deleteRsSetColumn")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除数据集字段")
    public ServiceResult deleteRsSetColumn(String id, String keyWord) {
        return ServiceFactory.getStRsSetElementInfoService().deleteById(ComSession.getUserInfo(), id);
    }

    @RequestMapping(value = "/deleteRsSetColumnList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "批量删除数据集字段")
    public ServiceResult deleteRsSetColumnList(String ids, String keyWord) {
        return ServiceFactory.getStRsSetElementInfoService().deleteByIds(ComSession.getUserInfo(), ids);
    }
    //endregion
}
