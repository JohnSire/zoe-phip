package com.zoe.phip.web.controller.StandardManage;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import com.zoe.phip.web.model.sdm.StNormSourceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
@Controller
@RequestMapping("source")
public class StandardSourceController extends BaseController {
    //region标准来源管理
    @RequestMapping("/view/sourcelist")
    public String ToSourceList() {
        return "StandardManage/StandardSource/sourceList";
    }

    @RequestMapping("/view/sourcedetail")
    public String ToSourceSetail() {
        return "StandardManage/StandardSource/sourceDetail";
    }

    /**
     * 数据来源列表查询
     *
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getSourceList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<StNormSourceInfo>> getSourceList(String keyWord) {
        return ServiceFactory.getStNormSourceService().getDataPageList(ComSession.getUserInfo(), keyWord, getQueryPage());
    }

    /**
     * 查询数据来源基本信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getSourceInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResult getSourceInfo(String id) {
        return ServiceFactory.getStNormSourceService().getById(ComSession.getUserInfo(), id);
    }

    /**
     * 新增数据来源信息
     *
     * @param stNormSourceInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/addSourceInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addSourceInfo(StNormSourceInfo stNormSourceInfo, HttpServletRequest request) {
        return ServiceFactory.getStNormSourceService().add(ComSession.getUserInfo(), stNormSourceInfo);
    }

    /**
     * 更新数据来源信息
     *
     * @param stNormSourceInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateSourceInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult updateSourceInfo(StNormSourceInfo stNormSourceInfo, HttpServletRequest request) {
        return ServiceFactory.getStNormSourceService().update(ComSession.getUserInfo(), stNormSourceInfo);
    }

    /**
     * 删除数据来源信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delSourceInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delSourceInfo(String id) {
        return ServiceFactory.getStNormSourceService().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除数据来源信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delSourceList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delSourceList(String ids) {
        return ServiceFactory.getStNormSourceService().deleteByIds(ComSession.getUserInfo(), ids);
    }
    //endregion
}
