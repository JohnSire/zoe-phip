package com.zoe.phip.web.controller;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.in.sm.MenuDataService;
import com.zoe.phip.service.in.sm.SystemUserService;
import com.zoe.phip.web.bean.BeanFactory;
import com.zoe.phip.web.bean.Constant;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.DataContext;
import com.zoe.phip.web.context.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yinzhixing on 2016/3/25.
 */
@Controller
@RequestMapping("/list")
public class ListController {
    //region 视图

    //用户关联管理
    @RequestMapping("/user")
    public String ToList(HttpServletRequest request, Model model) {

        return "/list/user";
    }

    //菜单关联管理
    @RequestMapping("/menu")
    public String ToDetail(HttpServletRequest request, Model model) {

        return "/list/menu";
    }
    //endregion
}
