define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_menu_detail_dialog",//弹窗对象变量名称
                winCallback: "win_menu_detail_callback",//弹窗回调函数
                getUrl: webRoot + '/menu/getMenuInfo',//
                addUrl: webRoot + '/menu/addMenuInfo',//新增接口Url
                updateUrl: webRoot + '/menu/updateMenuInfo',//修改接口Url
                loadPageEvent: function () {
                    $(".btn-switch-outer").btnSwitch({name: 'state'});
                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});