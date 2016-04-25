package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.register.model.AreaBaseInfo;

import java.util.List;

/**行政区域注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IAreaRegisterIn {

    /**
     * 	新增行政区域注册
     * @param areaBaseInfo
     * @return
     */
    ServiceResultT<AreaBaseInfo> addAreaRequest(AreaBaseInfo areaBaseInfo);


    /**
     * 	行政区域更新
     * @param areaBaseInfo
     * @return
     */
    ServiceResultT<AreaBaseInfo> updateAreaRequest(AreaBaseInfo areaBaseInfo);

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
    ServiceResultT<List<AreaBaseInfo>> areaChildrenRegistryQuery(String message);

    /**
     * 	历史行政区域信息查询
     * @param message
     * @return
     */
    ServiceResultT<AreaBaseInfo> areaHistoryRegistryQuery(String message);
}
