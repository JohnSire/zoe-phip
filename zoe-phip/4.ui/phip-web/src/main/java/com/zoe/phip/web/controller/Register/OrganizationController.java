package com.zoe.phip.web.controller.Register;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.DictItem;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by linqinghuang on 2016/4/21.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {
    //region 医疗机构(科室）
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
    public ServiceResult addMedicalOrgInfo(OrgDeptInfo orgDeptInfo) {
       return ServiceFactory.getOrganizationRegisterIn().addOrganization(ComSession.getUserInfo(),orgDeptInfo);
    }

    /**
     * @param orgDeptInfo
     * @return
     */
    public ServiceResult updateMedicalOrgInfo(OrgDeptInfo orgDeptInfo) {
        return ServiceFactory.getOrganizationRegisterIn().updateOrg(ComSession.getUserInfo(),orgDeptInfo);
    }

    /**
     * 删除机构（科室）信息
     *
     * @param id
     * @return
     */
    public ServiceResult delMedicalOrgInfo(String id) {

        return ServiceFactory.getOrganizationRegisterIn().deleteById(ComSession.getUserInfo(),id);
    }

    /**
     * 批量删除机构（科室）信息
     *
     * @param ids
     * @return
     */
    public ServiceResult delMedicalOrgList(String ids) {
        return ServiceFactory.getOrganizationRegisterIn().deleteByIds(ComSession.getUserInfo(),ids);
    }

    /**
     * 获取机构（科室）详情
     *
     * @param id
     * @return
     */
    public ServiceResult getMedicalOrgInfo(String id) {
        return ServiceFactory.getOrganizationRegisterIn().getById(ComSession.getUserInfo(),id);
    }

    /**
     * 根据机构（科室）编码获取机构列表
     *
     * @param keyWord
     * @param deptTypeCode
     * @return
     */
    @RequestMapping("/getMedicalOrgList")
    @ResponseBody
    public ServiceResult getMedicalOrgList( String deptTypeCode,String keyWord) {

        return ServiceFactory.getOrganizationRegisterIn().organizationListQuery(ComSession.getUserInfo(),deptTypeCode,keyWord,getQueryPage());
    }

    /**
     * 获取机构（科室）分类列表
     *
     * @return
     */

    @RequestMapping("/getMedicalOrgCategoryTree")
    @ResponseBody
    public ServiceResultT<List<DictItem>> getMedicalOrgCategoryList(String category) {
        //category为空时，查询机构分类，不为空时，为具体的某个机构分类如：医疗机构分类
        return ServiceFactory.getOrganizationRegisterIn().dictItemListQuery(ComSession.getUserInfo(), category);
    }



}
