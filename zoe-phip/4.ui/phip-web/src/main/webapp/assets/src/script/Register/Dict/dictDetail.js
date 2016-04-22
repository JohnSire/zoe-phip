/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_oid_detail_dialog",//弹窗对象变量名称
                winCallback: "win_oid_detail_callback",//弹窗回调函数
                getUrl: '',//
                addUrl: '',//新增接口Url
                updateUrl: '',//修改接口Url
                loadPageEvent: function () {
                }
            })
        },
    }
    exports.init = function () {
        internal.init();
    }
});