package com.zoe.phip.web.controller.Register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by linqinghuang on 2016/4/21.
 */
@Controller
@RequestMapping("area")
public class AreaController {
    @RequestMapping("/view/arealist")
    //region 行政区划
    public String ToAreaList() {
        return "Register/Area/areaList";
    }

    @RequestMapping("/view/areadetail")
    public String ToAreaDetail() {
        return "Register/Area/areaDetail";
    }
    //endregion

    // TODO: 2016/4/21

    //region 历史行政区划
    public String ToAreaHistoryList() {
        return null;
    }

    public String ToAreaHistoryDetail() {
        return null;
    }

}
