package com.zoe.phip.register.service;

/**行政区域注册服务
 * Created by zengjiyang on 2016/4/11.
 */
public interface IAreaRegister {

    /**
     * 	新增行政区域注册
     * @param message
     * @return
     */
    String addAreaRequest (String message);


    /**
     * 	行政区域更新
     * @param message
     * @return
     */
    String updateAreaRequest (String message);

    /**
     * 	行政区域基本信息查询
     * @param message
     * @return
     */
    String areaDetailQuery(String message);

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
