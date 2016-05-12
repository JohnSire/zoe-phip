package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.register.model.MedicalStaffInfo;

import java.util.List;
import java.util.Map;

/**
 * 医护人员注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IMedicalStaffRegisterIn extends IBaseInService<MedicalStaffInfo> {
    /**
     * 医护人员注册
     *
     * @param medicalStaffInfo
     * @return
     */
    ServiceResultT<MedicalStaffInfo> addProvider(SystemData systemData, MedicalStaffInfo medicalStaffInfo);

    /**
     * 医护人员信息更新
     *
     * @param medicalStaffInfo
     * @return
     */
    ServiceResultT<MedicalStaffInfo> updateProvider(SystemData systemData, MedicalStaffInfo medicalStaffInfo);

    /**
     * 医护人员查询
     *
     * @param id
     * @return
     */
    ServiceResultT<MedicalStaffInfo> providerDetailsQuery(SystemData systemData, String id);

    /**
     * 医护人员删除
     *
     * @param staffInfo
     * @return
     */
    ServiceResult providerDelete(SystemData systemData, MedicalStaffInfo staffInfo);

    /**
     * 查询医疗卫生人员列表
     *
     * @param systemData 权限验证
     * @param type       1:是指点击科室分类，全部数据，2:是指二级机构分类数据，其他是指单个数据
     * @param keyWord    查询关键字
     * @param  orgTypeCode 所在机构类别
     * @param deptCode   所在科室deptcode
     * @param page       分页信息
     * @return
     */
    ServiceResultT<PageList<MedicalStaffInfo>> providerListQuery(SystemData systemData, String type, String keyWord, String orgTypeCode, String deptCode, QueryPage page);
}
