package com.zoe.phip.web.context;

import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.web.bean.Constant;
import org.springframework.jca.cci.CciOperationNotSupportedException;

/**
 * Created by zengjiyang on 2016/3/24.
 */
public final class ComSession {

    public static SystemData getUserInfo() {
        return DataContext.Session.get(Constant.USER_SESSION);
    }

    public static void setUserInfo(SystemData userInfo) {
        DataContext.Session.put(Constant.USER_SESSION,userInfo);
    }
}
