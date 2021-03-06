package com.zoe.phip.web.controller.SystemManager;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.annotation.AuthController;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.MenuCode;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import com.zoe.phip.web.model.sm.SystemParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxingcai on 2016/3/23 0023.
 */
@Controller
@RequestMapping("/param")
@AuthController(code = MenuCode.SystemParameter)
public class SystemParamController extends BaseController {
    //系统参数界面
    @RequestMapping("/detail")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToParam(HttpServletRequest request, Model model) {
        return "SystemManage/SysParam/detail";
    }


    @RequestMapping(value = "/getSysParamList", method = RequestMethod.GET)
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<SystemParameter>> getSysParamList(HttpServletRequest request, Model model) {
        QueryPage page = new QueryPage(1, 30);
        ServiceResultT<PageList<SystemParameter>> param = ServiceFactory.getParameterService().getList(ComSession.getUserInfo(), page, SystemParameter.class);
        return param;
    }

    @RequestMapping(value = "/updateSysParamList", method = RequestMethod.POST)
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "修改")
    public ServiceResult updateSysParamList(HttpServletRequest request, Model model) {
        String strList = request.getParameter("list");
        List<SystemParameter> list = new ArrayList<>();
        if (!StringUtil.isNullOrWhiteSpace(strList)) {
            list = StringUtil.parseJsonArray(strList, SystemParameter.class);
        }
      /*  list.forEach(v->{
            ServiceFactory.getParameterService().update(ComSession.getUserInfo(),v);
        });*/
        return ServiceFactory.getParameterService().updateList(ComSession.getUserInfo(), list);
    }
}
