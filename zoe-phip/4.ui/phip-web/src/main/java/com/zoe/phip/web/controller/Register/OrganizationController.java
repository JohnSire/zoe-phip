package com.zoe.phip.web.controller.Register;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.register.model.NationalStandards;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.web.context.ComSession;
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
@RequestMapping("/organization")
public class OrganizationController extends BaseController {
    //region 医疗机构(科室)
    @RequestMapping("/view/medicalorglist")
    public String ToMedicalOrgList() {
        return "Register/Organization/medicalOrgList";
    }

    @RequestMapping("/view/medicalOrgDetail")
    public String ToMedicalOrgDetail() {
        return "Register/Organization/medicalOrgDetail";
    }

    /**
     * 新增医疗机构（科室）信息
     *
     * @param orgDeptInfo 机构科室信息
     * @return
     */
    @RequestMapping(value = "/addMedicalOrgInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addMedicalOrgInfo(OrgDeptInfo orgDeptInfo, HttpServletRequest request) {
        return ServiceFactory.getOrganizationRegisterIn().addOrganization(ComSession.getUserInfo(), orgDeptInfo);
    }

    /**
     *更新医疗机构（科室）信息
     * @param orgDeptInfo
     * @return
     */
    @RequestMapping(value = "/updateMedicalOrgInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult updateMedicalOrgInfo(OrgDeptInfo orgDeptInfo, HttpServletRequest request) {
        return ServiceFactory.getOrganizationRegisterIn().updateOrg(ComSession.getUserInfo(), orgDeptInfo);
    }

    /**
     * 删除机构（科室）信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delMedicalOrgInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delMedicalOrgInfo(String id) {

        return ServiceFactory.getOrganizationRegisterIn().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除机构（科室）信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delMedicalOrgList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delMedicalOrgList(String ids) {
        return ServiceFactory.getOrganizationRegisterIn().deleteByIds(ComSession.getUserInfo(), ids);
    }

    /**
     * 获取机构（科室）详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getMedicalOrgInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResult getMedicalOrgInfo(String id) {
        return ServiceFactory.getOrganizationRegisterIn().getById(ComSession.getUserInfo(), id);
    }

    /**
     * 根据机构（科室）编码获取机构列表
     *
     * @param keyWord
     * @param deptTypeCode
     * @return
     */
    @RequestMapping(value = "/getMedicalOrgList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResult getMedicalOrgList(String type, String deptTypeCode, String keyWord) {

        return ServiceFactory.getOrganizationRegisterIn().organizationListQuery(ComSession.getUserInfo(), type, deptTypeCode, keyWord, getQueryPage());

    }

    /**
     * 获取机构（科室）分类列表 分类树
     *
     * @return
     */

    @RequestMapping("/getMedicalOrgCategoryTree")
    @ResponseBody
    public ServiceResultT<NationalStandards> getMedicalOrgCategoryList(String codeSystem) {
        codeSystem = "2.16.156.10011.2.3.4.1";
        return ServiceFactory.getOrganizationRegisterIn().dictItemListQueryByCodeSystem(ComSession.getUserInfo(), codeSystem);

    }


}
