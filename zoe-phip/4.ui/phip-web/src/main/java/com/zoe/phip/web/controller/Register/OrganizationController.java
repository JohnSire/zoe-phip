package com.zoe.phip.web.controller.Register;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.DictItem;
import com.zoe.phip.register.model.OrgDeptInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by linqinghuang on 2016/4/21.
 */
@Controller
@RequestMapping("organization")
public class OrganizationController {
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
        return null;
    }

    /**
     * @param orgDeptInfo
     * @return
     */
    public ServiceResult updateMedicalOrgInfo(OrgDeptInfo orgDeptInfo) {
        return null;
    }

    /**
     * 删除机构（科室）信息
     *
     * @param id
     * @return
     */
    public ServiceResult delMedicalOrgInfo(String id) {
        return null;
    }

    /**
     * 批量删除机构（科室）信息
     *
     * @param ids
     * @return
     */
    public ServiceResult delMedicalOrgList(String ids) {
        return null;
    }

    /**
     * 获取机构（科室）详情
     *
     * @param id
     * @return
     */
    public ServiceResult getMedicalOrgInfo(String id) {
        return null;
    }

    /**
     * 根据机构（科室）编码获取机构列表
     *
     * @param keyWord
     * @param deptTypeCode
     * @return
     */
    public ServiceResult getMedicalOrgList( String deptTypeCode,String keyWord) {
        return null;
    }

    /**
     * 获取机构（科室）分类列表
     *
     * @return
     */

    @RequestMapping("/getMedicalOrgCategoryTree")
    @ResponseBody
    public ServiceResultT<List<DictItem>> getMedicalOrgCategoryList(String category) {
        return null;
//                ServiceFactory.getOrganizationRegisterIn().dictItemListQuery(ComSession.getUserInfo(), category);
    }


    //endregion

    // TODO: 2016/4/21

    //region 卫生管理机构
    @RequestMapping("/view/healthOrgList")
    public String ToHealthOrgList() {
        return "Register/Organization/healthOrgList";
    }

    @RequestMapping("/view/healthOrgDetail")
    public String ToHealthOrgDetail() {
        return "Register/Organization/healthOrgDetail";
    }
    //endregion

    // TODO: 2016/4/21
    //region 第三方机构管理
    public String ToThirdOrgList() {
        return null;
    }

    public String ToThirdOrgDetail() {
        return null;
    }
    //endregion
}
