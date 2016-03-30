define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_user_detail_dialog",//弹窗对象变量名称
                winCallback: "win_user_detail_callback",//弹窗回调函数
                getUrl: webRoot + '/user/info',//
                addUrl: webRoot + '/user/add',//新增接口Url
                updateUrl: webRoot + '/user/update',//修改接口Url
                loadPageEvent: function () {
                    var type = common.getParamFromUrl("state");
                    if (type == "edit") {
                        $("#liPwd").remove();
                        $("#liResetPwd").show();
                    }
                    else {
                        $("#liPwd").show();
                        $("#liResetPwd").remove();
                    }

                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});