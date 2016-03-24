define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseAttr = require("../../baseAttr/baseAttr.js");
            var baseAttr = new BaseAttr({
                getUrl: 'SystemUser/GetUserInfo',//获取实例的接口
                addUrl: 'SystemUser/AddUserInfo',//新增的接口Url
                updateUrl: 'SystemUser/UpdateUserInfo',//修改的接口Url
                loadPageEvent: function () {
                    var type = common.getParamFormUrl("type");
                    var id = common.getParamFormUrl("id")
                    internal.onResetPwd(id);
                    if (type != "edit") {
                        $("#userpwd-box").show();
                    }
                    else {
                        $("#reset-userpwd").show();
                    }
                },
                beforeBindEvent: function (data) {
                    if (data.Result.State == 1) {
                        $('.btn-switch').removeClass("btn-switch-off").addClass("btn-switch-on");
                    } else {
                        $('.btn-switch').addClass("btn-switch-off").removeClass("btn-switch-on");
                    }
                },
                beforeSaveEvent: function () {
                    var extendState = { State: 0 };
                    if ($('.btn-switch').hasClass("btn-switch-off")) {
                        extendState = { State: 0 };
                    } else {
                        extendState = { State: 1 };
                    }
                    return extendState;
                }
            });
            internal.btnSwitch();
        },
        btnSwitch: function () {
            $('.btn-switch').click(function (e) {
                var toggle = $(this);
                if (toggle.hasClass("btn-switch-on")) {
                    toggle.animate({
                        "margin-left": -35
                    }, 500, function () {
                        toggle.removeClass("btn-switch-on").addClass("btn-switch-off");
                    });
                }
                else if (toggle.hasClass("btn-switch-off")) {
                    toggle.animate({
                        "margin-left": 0
                    }, 500, function () {
                        toggle.removeClass("btn-switch-off").addClass("btn-switch-on");
                    });
                }
            });
        },
        //重置密码
        onResetPwd: function (id) {
            $("#resetuserpwd").click(function () {
                if ($(".Validform_error").length == 0) {
                    $.ligerDialog.confirm('是否重置密码？', function (yes) {
                        if (yes) {
                            var req = new Request("SystemUser/ResetUserPwd");
                            var data = $("#baseAttrForm").form2json();
                            data["Password"] = "123456";
                            data["Id"] = id;
                            req.post({
                                async: false,
                                isTip: false,
                                data: data,
                                success: function (data) {
                                    if (data.IsSuccess) {
                                        common.jsmsgSuccess('密码重置成功!');
                                        setTimeout(function () { top.win_base_attr_dialog.close() }, 1000);
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
    };
    exports.init = function () {
        internal.init();
    }
});