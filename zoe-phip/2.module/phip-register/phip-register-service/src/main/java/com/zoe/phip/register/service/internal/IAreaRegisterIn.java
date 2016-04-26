package com.zoe.phip.register.service.internal;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.register.model.AreaBaseInfo;

import java.util.List;

/**行政区域注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IAreaRegisterIn extends IBaseInService<AreaBaseInfo> {


    ServiceResultT<PageList<AreaBaseInfo>> getDataList(String key, QueryPage queryPage);

    /**
     * 	行政区域基本信息查询
     * @param id
     * @return
     */
    ServiceResultT<AreaBaseInfo> getAreaBaseInfo(String id);

    /**
     * 	所辖行政区域信息查询
     * @param id
     * @return
     */
    ServiceResultT<List<AreaBaseInfo>> getAreaChildrenRegistry(String id);

    /**
     * 	历史行政区域信息查询
     * @param message
     * @return
     */
    ServiceResultT<AreaBaseInfo> areaHistoryRegistryQuery(String message);
}
