package com.zoe.phip.register.service.external;

/**医疗机构注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IOrganizationRegister {

    /**
     *	新增医疗卫生机构注册
     * @param message
     * @return
     */
    String addOrganization(String message);

    /**
     *	医疗卫生机构信息更新
     * @param message
     * @return
     */
    String updateOrganization(String message);

    /**
     * 	医疗卫生机构信息查询
     * @param message
     * @return
     */
    String organizationDetailQuery(String message);
}
