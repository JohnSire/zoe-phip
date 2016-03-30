package com.zoe.phip.service.impl.bootstrapper;

import com.zoe.phip.infrastructure.bean.BeanFactory;
import com.zoe.phip.infrastructure.entity.Menu;
import com.zoe.phip.infrastructure.security.MenuCode;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.service.impl.in.sm.MenuDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengjiyang on 2016/3/29.
 */
public class MenuInit {

    public static final int State=1;

    public static final MenuData SYSTEM_MANAGER=new MenuData("系统管理","#", MenuCode.SystemManager.getCode(),State,1);
    public static final MenuData SYSTEM_USER=new MenuData("用户管理","/user/view/list", MenuCode.SystemUser.getCode(),State,2);
    public static final MenuData SYSTEM_MENU=new MenuData("菜单管理","/menu/view/list",MenuCode.SystemMenu.getCode(),State,3);
    public static final MenuData SYSTEM_PARAMETER=new MenuData("参数管理","/param/view/index",MenuCode.SystemParameter.getCode(),State,4);
    public static final MenuData SYSTEM_DICT=new MenuData("字典管理","/dict/view/list",MenuCode.SystemDict.getCode(),State,5);


    public static void toDatabase(){
        //如果数据库中该表数据不为0，则返回
        if(getMenuDataService().selectCountByExample(null)>0){
            return;
        }
        List<MenuData> initData=new ArrayList<MenuData>();
        //系统管理
        SYSTEM_MANAGER.children.add(SYSTEM_USER);
        SYSTEM_MANAGER.children.add(SYSTEM_MENU);
        SYSTEM_MANAGER.children.add(SYSTEM_PARAMETER);
        SYSTEM_MANAGER.children.add(SYSTEM_DICT);
        initData.add(SYSTEM_MANAGER);

        getMenuDataService().insertMenuData(initData);
    }

    public static MenuDataServiceImpl getMenuDataService(){
        return BeanFactory.getBean("MenuDataService");
    }
}
