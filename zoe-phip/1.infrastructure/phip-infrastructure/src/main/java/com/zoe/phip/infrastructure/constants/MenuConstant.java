package com.zoe.phip.infrastructure.constants;

import com.zoe.phip.infrastructure.entity.Menu;
import com.zoe.phip.infrastructure.security.MenuCode;

/**
 * Created by zengjiyang on 2016/3/29.
 */
public class MenuConstant {
    public static final Menu SYSTEM_MANAGER=new Menu("系统管理","#", MenuCode.SystemManager,1,null);

    public static final Menu SYSTEM_USER=new Menu("用户管理","/user/view/list", MenuCode.SystemUser,1,SYSTEM_MANAGER);

    public static final Menu SYSTEM_MENU=new Menu("菜单管理","/menu/view/list",MenuCode.SystemMenu,1,SYSTEM_MANAGER);

    public static final Menu SYSTEM_PARAMETER=new Menu("参数管理","/param/view/index",MenuCode.SystemParameter,1,SYSTEM_MANAGER);

    public static final Menu SYSTEM_DICT=new Menu("字典管理","/dict/view/list",MenuCode.SystemDict,1,SYSTEM_MANAGER);

}
