package com.zoe.phip.web.controller.StandardManage;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenzhisen on 2016/5/6.
 */
@Controller
@RequestMapping("/dataSet")
public class DataSetController extends BaseController {

    @RequestMapping("/view/dataSetList")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToDataSetList(HttpServletRequest request, Model model) {
        return "/StandardManage/DataSet/dataSetList";
    }

    @RequestMapping("/view/dataSetDetail")
    public String ToDataSetDetail(HttpServletRequest request, Model model) {
        return "/StandardManage/DataSet/dataSetDetail";
    }
}
