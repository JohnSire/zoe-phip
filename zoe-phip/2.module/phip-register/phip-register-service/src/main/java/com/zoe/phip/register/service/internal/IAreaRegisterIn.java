package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.AreaBaseInfo;

/**行政区域注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IAreaRegisterIn {

    /**
     * 	新增行政区域注册
     * @param message
     * @return
     */
    ServiceResultT<AreaBaseInfo> addAreaRequest(AreaBaseInfo message);


    /**
     * 	行政区域更新
     * @param message
     * @return
     */
    ServiceResultT<AreaBaseInfo> updateAreaRequest(AreaBaseInfo message);

    /**
     * 	行政区域基本信息查询
     * @param id
     * @return
     */
    ServiceResultT<AreaBaseInfo> areaDetailQuery(String id);

    /**
     * 	所辖行政区域信息查询
     * @param message
     * @return
     */
    String areaChildrenRegistryQuery(String message);

    /**
     * 	历史行政区域信息查询
     * @param message
     * @return
     */
    String areaHistoryRegistryQuery(String message);
}
