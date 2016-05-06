package com.zoe.phip.web.controller.StandardManage;

import com.zoe.phip.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.Region;

/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
@Controller
@RequestMapping("source")
public class StandardSourceController extends BaseController {
    //region标准来源管理
    @RequestMapping("/view/sourcelist")
    public String ToSourceList() {
        return "StandardManage/StandardSource/sourceList";
    }

    @RequestMapping("/view/sourcedetail")
    public String ToSourceSetail() {
        return "StandardManage/StandardSource/sourceDetail";
    }

    //endregion
}
