package com.zoe.phip.web.context;

import com.zoe.phip.register.service.internal.*;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;
import com.zoe.phip.web.service.sm.*;
import com.zoe.phip.web.service.sdm.*;

/**
 * 服务工厂类
 * Created by zengjiyang on 2016/3/24.
 */
public final class ServiceFactory {

    /*web服务*/
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

    /*注册服务*/
    public static INationalStandardsService getNationalStandardsService() {
        return BeanFactory.getBean(Constant.NATIONAL_STANDARDS_SERVICE);
    }

    public static IAreaRegisterIn getAreaRegisterIn() {
        return BeanFactory.getBean(Constant.AREA_REGISTER_IN);
    }

    public static IDictRegisterIn getDictRegisterIn() {
        return BeanFactory.getBean(Constant.DICT_REGISTER_IN);
    }

    public static IMedicalStaffRegisterIn getMedicalStaffRegisterIn() {
        return BeanFactory.getBean(Constant.MEDICAL_STAFF_REGISTER_IN);
    }

    public static IOrganizationRegisterIn getOrganizationRegisterIn() {
        return BeanFactory.getBean(Constant.ORGANIZATION_REGISTER_IN);
    }

    public static IPatientRegisterIn getPatientRegisterIn() {
        return BeanFactory.getBean(Constant.PATIENT_REGISTER_IN);
    }

    /*标准管理*/
    public static IStNormSourceInfoService getStNormSourceService() {
        return BeanFactory.getBean(Constant.NORM_SOURCE_SERVICE);
    }

    public static IStElementInfoService getStElementService() {
        return BeanFactory.getBean(Constant.ST_ELEMENT_SERVICE);
    }

    public static IStandardVersionService getStandardVersionService() {
        return BeanFactory.getBean(Constant.STANDARD_VERSION_SERVICE);
    }

    public static IStSetInfoService getStSetInfoService() {
        return BeanFactory.getBean(Constant.STANDARD_DATASET_SERVICE);
    }

    public static IStCdaInfoService getStCdaInfoService() {
        return BeanFactory.getBean(Constant.STANDARD_CDA_SERVICE);
    }

    public static IStRsSetElementInfoService getStRsSetElementInfoService() {
        return BeanFactory.getBean(Constant.STANDARD_RsSetElement_SERVICE);
    }

    public static IStRsCdaSetInfoService getStRsCdaSetInfoService() {
        return BeanFactory.getBean(Constant.STANDARD_RsCdaSetInfo_SERVICE);
    }

    public static IStandardVerRsSetService getStandardVerRsSetService() {
        return BeanFactory.getBean(Constant.STANDARD_VER_RSSET_SERVICE);
    }

    public static IStandardVerRsCdaService getStandardVerRsCdaService() {
        return BeanFactory.getBean(Constant.STANDARD_VERRSCDA_SERVICE);
    }
}
