package com.zoe.phip.web.controller.Register;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.DataContext;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by linqinghuang on 2016/4/21.
 */
@Controller
@RequestMapping("area")
public class AreaController extends BaseController {
    @RequestMapping("/view/arealist")
    //region 行政区划
    public String ToAreaList() {
        return "Register/Area/areaList";
    }

    @RequestMapping("/view/areadetail")
    public String ToAreaDetail() {
        return "Register/Area/areaDetail";
    }

    /**
     * 行政区域基本信息列表
     *
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getAreaList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<AreaBaseInfo>> getAreaList(String keyWord) {
        return ServiceFactory.getAreaRegisterIn().getDataList(ComSession.getUserInfo(), keyWord, getQueryPage());
    }

    /**
     * 行政区域基本信息查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAreaInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<AreaBaseInfo> getAreaInfo(String id) {
        return ServiceFactory.getAreaRegisterIn().getAreaBaseInfo(ComSession.getUserInfo(), id);
    }

    /**
     * 新增行政区域基本信息
     *
     * @param areaBaseInfo
     * @return
     */
    @RequestMapping(value = "/addAreaInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addAreaInfo(AreaBaseInfo areaBaseInfo, HttpServletRequest request) {
        return ServiceFactory.getAreaRegisterIn().add(ComSession.getUserInfo(), areaBaseInfo);
    }

    /**
     * 更新行政区域信息
     *
     * @param areaBaseInfo
     * @return
     */
    @RequestMapping(value = "/updateAreaInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult updateAreaInfo(AreaBaseInfo areaBaseInfo, HttpServletRequest request) {
        return ServiceFactory.getAreaRegisterIn().update(ComSession.getUserInfo(), areaBaseInfo);
    }

    /**
     * 删除行政区域信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delAreaInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delAreaInfo(String id) {
        return ServiceFactory.getAreaRegisterIn().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除行政区域信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delAreaList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delAreaList(String ids) {
        return ServiceFactory.getAreaRegisterIn().deleteByIds(ComSession.getUserInfo(), ids);
    }

    /**
     * 所辖行政区域信息查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAreaChildrenRegistry")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<AreaBaseInfo>> getAreaChildrenRegistry(String id) {
        return ServiceFactory.getAreaRegisterIn().getAreaChildrenRegistry(ComSession.getUserInfo(), id, getQueryPage());
    }

    //endregion

    // TODO: 2016/4/21

    //region 历史行政区划
    public String ToAreaHistoryList() {
        return null;
    }

    public String ToAreaHistoryDetail() {
        return null;
    }

}
