package com.zoe.phip.web.controller.StandardManage;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import com.zoe.phip.web.model.sdm.StElementInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
@Controller
@RequestMapping("element")
public class DataElementController extends BaseController {
    //region 数据元
    @RequestMapping("view/dataelementlist")
    public String ToDataElementList() {
        return "StandardManage/DataElement/dataElementList";
    }

    @RequestMapping("view/dataelementdetail")
    public String ToDataElementDetail() {
        return "StandardManage/DataElement/dataElementDetail";
    }

    @RequestMapping("view/import")
    public String ToImportDialog() {
        return "StandardManage/DataElement/importDialog";
    }

    /**
     * 查询数据元列表
     *
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getElementList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<StElementInfo>> getElementList(String keyWord) {
        return ServiceFactory.getStElementService().getDataPageList(ComSession.getUserInfo(), keyWord, getQueryPage());
    }

    /**
     * 数据元基本信息查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getElementInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResult getElementInfo(String id) {
        return ServiceFactory.getStElementService().getById(ComSession.getUserInfo(), id);
    }

    /**
     * 新增数据元信息
     *
     * @param stElementInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/addElementInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addElementInfo(StElementInfo stElementInfo, HttpServletRequest request) {
        return ServiceFactory.getStElementService().add(ComSession.getUserInfo(), stElementInfo);
    }

    /**
     * 更新数据元信息
     *
     * @param stElementInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateElementInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult updateElementInfo(StElementInfo stElementInfo, HttpServletRequest request) {
        return ServiceFactory.getStElementService().update(ComSession.getUserInfo(), stElementInfo);
    }

    /**
     * 删除数据元信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delElementInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delElementInfo(String id) {
        return ServiceFactory.getStElementService().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除数据元信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delElementList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delElementList(String ids) {
        return ServiceFactory.getStElementService().deleteByIds(ComSession.getUserInfo(), ids);
    }
    //endregion
}
