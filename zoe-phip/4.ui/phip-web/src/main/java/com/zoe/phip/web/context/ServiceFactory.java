package com.zoe.phip.web.context;

import com.zoe.phip.register.service.external.IOrganizationRegister;
import com.zoe.phip.register.service.internal.IOrganizationRegisterIn;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;
import com.zoe.phip.web.service.sm.*;

/**
 * 服务工厂类
 * Created by zengjiyang on 2016/3/24.
 */
public final class ServiceFactory {

    public static ISystemUserService getUserService() {
        return BeanFactory.getBean(Constant.SYSTEM_USER_SERVICE);
    }

    public static ISystemDictItemService getDictItemService() {
        return BeanFactory.getBean(Constant.SYSTEM_DICT_ITEM_SERVICE);
    }

    public static IMenuDataService getMenuDataService() {
        return BeanFactory.getBean(Constant.MENU_DATA_SERVICE);
    }

    public static ISystemDictCategoryService getDictCategoryService() {
        return BeanFactory.getBean(Constant.SYSTEM_DICT_CATEGORY_SERVICE);
    }

    public static ISystemParameterService getParameterService() {
        return BeanFactory.getBean(Constant.SYSTEM_PARAMETER_SERVICE);
    }

    public static IUserCompetenceService getUserCompetenceService() {
        return BeanFactory.getBean(Constant.USER_COMPETENCE_SERVICE);
    }

    public static IMenuCompetenceService getMenuCompetenceService() {
        return BeanFactory.getBean(Constant.MENU_COMPETENCE_SERVICE);
    }


    public static IOrganizationRegisterIn getOrganizationRegister() {
        return BeanFactory.getBean(Constant.ORGANIZATION_REGISTER_SERVICE);
    }
}
