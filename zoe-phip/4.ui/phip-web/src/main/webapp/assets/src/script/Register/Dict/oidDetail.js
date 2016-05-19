/**
 * Created by zhangxingcai on 2016/4/21 0021.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_oid_detail_dialog",//弹窗对象变量名称
                winCallback: "win_oid_detail_callback",//弹窗回调函数
                getUrl: 'dict/getOIDInfo',//
                addUrl: 'dict/addOIDInfo',//新增接口Url
                updateUrl: 'dict/updateOIDInfo',//修改接口Url
                loadPageEvent: function () {
                }
            })
        },
    }
    exports.init = function () {
        internal.init();
    }
});