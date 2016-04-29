package com.zoe.phip.web.controller.Register;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by linqinghuang on 2016/4/21.
 */
@Controller
@RequestMapping("/personnel")
public class PersonnelController extends BaseController {
    //region 个人信息管理
    @RequestMapping("/view/xmanbaselist")
    public String ToXmanBaseList(HttpServletRequest request, Model model) {
        return "/Register/Personnel/xmanBaseList";
    }

    @RequestMapping("/view/xmanbasedetail")
    public String ToXmanBaseDetail(HttpServletRequest request, Model model) {
        return "/Register/Personnel/xmanBaseDetail";
    }

    /**
     * 根据条件查询病人列表
     *
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getXmanList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<XmanBaseInfo>> getXmanList(String keyWord) {
        return ServiceFactory.getPatientRegisterIn().patientRegistryListQuery(ComSession.getUserInfo(), keyWord, getQueryPage());
    }

    /**
     * 个人基本信息查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getXmanInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResult getXmanInfo(String id) {
        return ServiceFactory.getPatientRegisterIn().getById(ComSession.getUserInfo(), id);
    }

    /**
     * 个人信息新增
     *
     * @param xmanBaseInfo
     * @return
     */
    @RequestMapping(value = "/addXmanInfo", method = RequestMethod.POST)
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "更新")
    public ServiceResult addXmanInfo(XmanBaseInfo xmanBaseInfo, HttpServletRequest request) {
        return ServiceFactory.getPatientRegisterIn().addPatientRegistry(ComSession.getUserInfo(), xmanBaseInfo);
    }

    /**
     * 个人信息更新
     *
     * @param xmanBaseInfo
     * @return
     */
    @RequestMapping(value = "/updateXmanInfo", method = RequestMethod.POST)
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult updateXmanInfo(XmanBaseInfo xmanBaseInfo, HttpServletRequest request) {
        return ServiceFactory.getPatientRegisterIn().updatePatientRegistry(ComSession.getUserInfo(), xmanBaseInfo);
    }

    /**
     * 个人身份合并
     *
     * @param newPatientId
     * @param oldPatientId
     * @return
     */
    @RequestMapping(value = "/mergeXmanInfo")
    @ResponseBody
    public ServiceResultT<XmanBaseInfo> mergeXManInfo(String newPatientId, String oldPatientId) {
        return ServiceFactory.getPatientRegisterIn().mergePatientRegistry(ComSession.getUserInfo(), newPatientId, oldPatientId);
    }

    /**
     * 批量删除个人信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delXmanList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delXmanList(String ids) {
        return ServiceFactory.getPatientRegisterIn().deleteByIds(ComSession.getUserInfo(), ids);
    }

    /**
     * 删除个人信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delXmanInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delManInfo(String id) {
        return ServiceFactory.getPatientRegisterIn().deleteById(ComSession.getUserInfo(), id);
    }
    //endregion

    //region 医疗卫生人员
    @RequestMapping("/view/medicalstafflist")
    public String ToMedicalStaffList(HttpServletRequest request, Model model) {
        return "/Register/Personnel/medicalStaffList";
    }

    @RequestMapping("/view/medicalstaffdetail")
    public String ToMedicalStaffDetail(HttpServletRequest request, Model model) {
        return "/Register/Personnel/medicalStaffDetail";
    }

    /**
     * 查询医疗卫生人员科室
     *
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getMedDeptList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<OrgDeptInfo>> getMedDept(String keyWord) {
        if(StringUtil.isNullOrWhiteSpace(keyWord)){
            keyWord="0101";
        }
        return ServiceFactory.getOrganizationRegisterIn().orgListQuery(ComSession.getUserInfo());
    }


    /**
     * 查询医疗卫生人员列表
     *
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getMedStfList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<MedicalStaffInfo>> getMedStfList(String keyWord) {
        return ServiceFactory.getMedicalStaffRegisterIn().providerListQuery(ComSession.getUserInfo(), keyWord,"", getQueryPage());
    }

    /**
     * 查询卫生医疗人员信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getMedStfInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<MedicalStaffInfo> getMedStfInfo(String id) {
        return ServiceFactory.getMedicalStaffRegisterIn().providerDetailsQuery(ComSession.getUserInfo(), id);
    }

    /**
     * 新增医护人员
     *
     * @param medicalStaffInfo
     * @return
     */
    @RequestMapping(value = "/addMedStaffInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResultT<MedicalStaffInfo> addMedStfInfo(MedicalStaffInfo medicalStaffInfo) {
        return ServiceFactory.getMedicalStaffRegisterIn().addProvider(ComSession.getUserInfo(), medicalStaffInfo);
    }

    /**
     * 医护人员信息更新
     *
     * @param medicalStaffInfo
     * @return
     */
    @RequestMapping(value = "/updateMedStfInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResultT<MedicalStaffInfo> updateMedStfInfo(MedicalStaffInfo medicalStaffInfo) {
        return ServiceFactory.getMedicalStaffRegisterIn().updateProvider(ComSession.getUserInfo(), medicalStaffInfo);
    }

    /**
     * 删除医护人员信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delMedStfInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delMedStfInfo(String id) {
        return ServiceFactory.getMedicalStaffRegisterIn().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除医护人员信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delMedStfList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delMedStfList(String ids) {
        return ServiceFactory.getMedicalStaffRegisterIn().deleteByIds(ComSession.getUserInfo(), ids);
    }
    //endregion

    // TODO: 2016/4/21
    //region 卫生管理机构人员
    @RequestMapping("/view/healthstafflist")
    public String ToHealthStaffList(HttpServletRequest request, Model model) {
        return "/Register/Personnel/healthStaffList";
    }

    @RequestMapping("/view/healthstaffdetail")
    public String ToHealthStaffDetail(HttpServletRequest request, Model model) {
        return "/Register/Personnel/healthStaffDetail";
    }
    //endregion
}
