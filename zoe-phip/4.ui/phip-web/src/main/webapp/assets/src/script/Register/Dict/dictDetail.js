/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_dict_detail_dialog",//弹窗对象变量名称
                winCallback: "win_dict_detail_callback",//弹窗回调函数
                getUrl: 'dict/getDictCatalogInfoById',//
                addUrl: 'dict/addDictCatalogInfo',//新增接口Url
                updateUrl: 'dict/updateDictCatalogInfo',//修改接口Url
                loadPageEvent: function () {
                }
            })
        },
    }
    exports.init = function () {
        internal.init();
    }
});