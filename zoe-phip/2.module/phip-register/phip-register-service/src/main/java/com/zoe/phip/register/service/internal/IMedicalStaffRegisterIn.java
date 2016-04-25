package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.MedicalStaffInfo;

import java.util.List;
import java.util.Map;

/**医护人员注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IMedicalStaffRegisterIn {
    /**
     * 医护人员注册
     * @param medicalStaffInfo
     * @return
     */
    ServiceResultT<MedicalStaffInfo> addProvider(MedicalStaffInfo medicalStaffInfo);

    /**
     * 	医护人员信息更新
     * @param medicalStaffInfo
     * @return
     */
    ServiceResultT<MedicalStaffInfo> updateProvider(MedicalStaffInfo medicalStaffInfo);

    /**
     * 医护人员查询
     * @param map
     * @return
     */
    ServiceResultT<MedicalStaffInfo> providerDetailsQuery(Map<String, Object> map);

    /**
     * 医护人员删除
     * @param staffInfo
     * @return
     */
    ServiceResult providerDelete(MedicalStaffInfo staffInfo);

    /**
     * 查询医疗卫生人员列表
     * @param map
     * @return
     */
    ServiceResultT<List<MedicalStaffInfo>> providerListQuery(Map<String, Object> map);
}
