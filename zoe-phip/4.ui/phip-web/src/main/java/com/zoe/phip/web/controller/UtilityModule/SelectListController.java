package com.zoe.phip.web.controller.UtilityModule;



import com.zoe.phip.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by linqinghuang on 2016/4/12.
 */
/*
* 通用选择列表界面
*/
@Controller
@RequestMapping("/select")
public class SelectListController extends BaseController {
    @RequestMapping("/view/list")
    public String ToList(HttpServletRequest request,Model model){
        return "/UtilityModule/SelectList/list";
    }
}
