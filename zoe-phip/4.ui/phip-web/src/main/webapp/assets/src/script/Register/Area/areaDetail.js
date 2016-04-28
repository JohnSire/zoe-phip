/**
 * Created by linqinghuang on 2016/4/21.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_area_detail_dialog",//弹窗对象变量名称
                winCallback: "win_area_detail_callback",//弹窗回调函数
                getUrl: 'area/getAreaInfo',//
                addUrl: 'area/addAreaInfo',//新增接口Url
                updateUrl: 'area/updateAreaInfo',//修改接口Url
                loadPageEvent: function () {
                    var type = common.getParamFromUrl("state");
                    if (type == "edit") {

                    }
                    else {

                    }
                }
            })
        }
    }


    exports.init = function () {
        internal.init()
    }
})
