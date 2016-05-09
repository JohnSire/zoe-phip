/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
define(function (require, exports, module) {
    var internal = {
            init: function () {
                var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
                var baseAttr = new BaseAttr({
                        winName: "win_source_detail_dialog",//弹窗对象变量名称
                        winCallback: "win_source_detail_callback",//弹窗回调函数
                        getUrl: 'source/getSourceInfo',//
                        addUrl: 'source/addSourceInfo',//新增接口Url
                        updateUrl: 'source/updateSourceInfo',//修改接口Url/修改的接口Url
                        loadPageEvent: function () {

                        }

                    }
                );
            },
        }
        ;
    exports.init = function () {
        internal.init();
    }
})
;