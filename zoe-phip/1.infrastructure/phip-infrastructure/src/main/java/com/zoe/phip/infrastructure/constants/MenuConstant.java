package com.zoe.phip.infrastructure.constants;

import com.zoe.phip.infrastructure.annotation.Menu;
import com.zoe.phip.infrastructure.security.MenuCode;
import com.zoe.phip.infrastructure.security.Permission;

/**
 * Created by zengjiyang on 2016/3/29.
 */
public class MenuConstant {
    @Menu(uri = "/user/view/list",code = "",order = 1)
    public static final String SYSTEM_USER="用户管理";

    @Menu(uri = "/menu/view/list",code = "",order = 1)
    public static final String SYSTEM_MENU="菜单管理";

    @Menu(uri = "/param/view/index",code = "",order = 1)
    public static final String SYSTEM_PARAM="参数管理";

    @Menu(uri = "/dict/view/list",code = "",order = 1)
    public static final String SYSTEM_DICT="字典管理";

    @Menu(uri = "/user/view/list",code = "",order = 1)
    public static final String SYSTEM_ACC="权限管理";


}
