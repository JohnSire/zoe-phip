package com.zoe.phip.infrastructure.bean;

/**
 * Created by zengjiyang on 2016/4/25.
 */
public interface IBootstrapper {

    /**
     * 启动服务
     */
    void startService();

    /**
     * 执行顺序
     * @return
     */
    int getExecutionOrder();
}
