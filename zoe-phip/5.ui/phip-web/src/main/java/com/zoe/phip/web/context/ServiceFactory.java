package com.zoe.phip.web.context;

import com.zoe.phip.service.in.sm.*;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;

/**
 * 服务工厂类
 * Created by zengjiyang on 2016/3/24.
 */
public final class ServiceFactory {

    public static SystemUserService getUserService() {
        return BeanFactory.getBean(Constant.SYSTEM_USER_SERVICE);
    }

    public static SystemDictItemService getDictItemService() {
        return BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
    }

    public static MenuDataService getMenuDataService() {
        return BeanFactory.getBean(Constant.MENU_DATA_SERVICE);
    }

    public static SystemDictCategoryService getDictCategoryService() {
        return BeanFactory.getBean(Constant.SYSTEM_DICT_CATEGORY_SERVICE);
    }

    public static SystemParameterService getParameterService() {
        return BeanFactory.getBean(Constant.SYSTEM_PARAMETER_SERVICE);
    }
}
