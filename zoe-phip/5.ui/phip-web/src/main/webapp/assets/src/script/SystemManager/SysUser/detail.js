define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_user_detail_dialog",//弹窗对象变量名称
                winCallback: "win_user_detail_callback",//弹窗回调函数
                getUrl: webRoot + '/user/getUserInfo',//
                addUrl: webRoot + '/user/addUserInfo',//新增接口Url
                updateUrl: webRoot + '/user/updateUserInfo',//修改接口Url
                loadPageEvent: function () {
                    var type = common.getParamFromUrl("state");
                    if (type == "edit") {
                        var id = common.getParamFromUrl("id")
                        internal.onResetPwd(id);
                        $("#liPwd").remove();
                        $("#liResetPwd").show();
                    }
                    else {
                        $("#liPwd").show();
                        $("#liResetPwd").remove();
                    }
                    $(".btn-switch-outer").btnSwitch({name: 'state'});
                }
            })
        },
        onResetPwd: function (id) {
            $("#resetuserpwd").click(function () {
                if ($(".Validform_error").length == 0) {
                    $.ligerDialog.confirm('是否重置密码？', function (yes) {
                        if (yes) {
                            var req = new Request("SystemUser/ResetUserPwd");
                            var data={};
                            data["password"] = "123456";
                            data["id"] = id;
                            req.post({
                                async: false,
                                isTip: false,
                                data: data,
                                success: function (data) {
                                    if (data.isSuccess) {
                                        common.jsmsgSuccess('密码重置成功!');
                                    } else {
                                        common.jsmsgError(data.Message[0].Content);
                                    }
                                }
                            })
                        }
                    })
                }
                else {
                    common.jsmsgError("信息不能为空！");
                }
            });
        }
    }
    exports.init = function () {
        internal.init();
    }
});