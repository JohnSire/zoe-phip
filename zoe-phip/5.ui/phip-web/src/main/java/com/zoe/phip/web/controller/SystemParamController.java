package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.sm.SystemParameter;
import com.zoe.phip.service.in.sm.SystemParameterService;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangxingcai on 2016/3/23 0023.
 */
@Controller
@RequestMapping("/param")
public class SystemParamController extends BaseController {
    //系统参数界面
    @RequestMapping("/sysparam")
    public String ToParam(HttpServletRequest request, Model model) {
        return "/param/sysparam";
    }

    @RequestMapping(value = "/getSysParamList", method = RequestMethod.GET)
    @ResponseBody
    public ServiceResultT<PageList<SystemParameter>> getSysParamList(HttpServletRequest request, Model model) {
        QueryPage page = new QueryPage(1, 30);
        ServiceResultT<PageList<SystemParameter>> param = ServiceFactory.getParameterService().getList(ComSession.getUserInfo(),page, SystemParameter.class);
        return param;
    }
}
