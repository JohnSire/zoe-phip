package com.zoe.phip.web.controller.StandardManage;

import com.zoe.phip.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
@Controller
@RequestMapping("element")
public class DataElementController extends BaseController {
    //region 数据元
    @RequestMapping("view/dataelementlist")
    public String ToDataElementList() {
        return "StandardManage/DataElement/dataElementList";
    }

    @RequestMapping("view/dataelementdetail")
    public String ToDataElementDetail() {
        return "StandardManage/DataElement/dataElementDetail";
    }

    @RequestMapping("view/import")
    public String ToImportDialog() {
        return "StandardManage/DataElement/importDialog";
    }
    //endregion
}
