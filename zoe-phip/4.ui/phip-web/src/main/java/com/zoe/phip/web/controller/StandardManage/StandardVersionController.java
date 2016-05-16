package com.zoe.phip.web.controller.StandardManage;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import com.zoe.phip.web.model.sdm.StCdaInfo;
import com.zoe.phip.web.model.sdm.StSetInfo;
import com.zoe.phip.web.model.sdm.StandardVersion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
@Controller
@RequestMapping("version")
public class StandardVersionController extends BaseController {
    //region 标准版本管理
    @RequestMapping("view/versionlist")
    public String ToVersionList() {
        return "StandardManage/StandardVersion/versionList";
    }

    @RequestMapping("view/versiondetail")
    public String ToVersionDetail() {
        return "StandardManage/StandardVersion/versionDetail";
    }

    @RequestMapping("view/versionSetRs")
    public String ToVersionSetRS() {
        return "StandardManage/StandardVersion/versionSetRs";
    }

    @RequestMapping("view/versionPreview")
    public String ToVersionPreview() {
        return "StandardManage/StandardVersion/versionPreview";
    }

    /**
     * 标准版本管理列表查询
     *
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getVersionList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<StandardVersion>> getVersionList(String keyWord) {
        return ServiceFactory.getStandardVersionService().getDataPageList(ComSession.getUserInfo(), keyWord, getQueryPage());
    }

    /**
     * 标准版本管理信息查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getVersionInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResult getVersionInfo(String id) {
        return ServiceFactory.getStandardVersionService().getById(ComSession.getUserInfo(), id);
    }

    /**
     * 新增标准版本管理
     *
     * @param standardVersion
     * @param request
     * @return
     */
    @RequestMapping(value = "/addVersionInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addVersionInfo(StandardVersion standardVersion, HttpServletRequest request) {
        return ServiceFactory.getStandardVersionService().add(ComSession.getUserInfo(), standardVersion);
    }

    /**
     * 更新标准版本管理
     *
     * @param standardVersion
     * @param request
     * @return
     */
    @RequestMapping(value = "updateVersionInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult updateVersionInfo(StandardVersion standardVersion, HttpServletRequest request) {
        return ServiceFactory.getStandardVersionService().update(ComSession.getUserInfo(), standardVersion);
    }

    /**
     * 删除标准版本管理信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delVersionInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delVersionInfo(String id) {
        return ServiceFactory.getStandardVersionService().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除标准管理信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delVersionList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delVersionList(String ids) {
        return ServiceFactory.getStandardVersionService().deleteByIds(ComSession.getUserInfo(), ids);
    }

    //endregion
    //region 标准版本

    /**
     * 通过标准版本ID和CDA的ID获取数据集
     *
     * @param fkVersionId
     * @param fkCdaId
     * @return
     */
    @RequestMapping(value = "/getVerRsSetInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "通过标准版本ID和CDA的ID获取数据集")
    public ServiceResultT<List<StSetInfo>> getVerRsSetInfo(String fkVersionId, String fkCdaId) {
        return ServiceFactory.getStandardVerRsSetService().getVerRsSetInfo(ComSession.getUserInfo(), fkVersionId, fkCdaId);
    }

    /**
     * 通过标准版本ID获取CDA
     *
     * @param fkVersionId
     * @return
     */
    @RequestMapping(value = "/getVerRsCdaInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "通过标准版本ID获取CDA")
    public ServiceResultT<List<StCdaInfo>> getVerRsCdaInfo(String fkVersionId) {
        return ServiceFactory.getStandardVerRsCdaService().getVerRsCdaInfo(ComSession.getUserInfo(), fkVersionId);
    }

}
