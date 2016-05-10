/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                    winName: "win_cda_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_cda_detail_callback",//弹窗回调函数
                    getUrl: 'cda/getCdaInfo',//
                    addUrl: 'cda/addCdaInfo',//新增接口Url
                    updateUrl: 'cda/updateCdaInfo',//修改接口Url
                    loadPageEvent: function () {
                    }
                }
            );
        },
    };
    exports.init = function () {
        internal.init();
    }
})
;