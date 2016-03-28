define(function (require, exports, module) {
    var internal = {
        req: require("./req.js").req,
        init: function () {
            var BaseAttr = require("../../baseAttr/baseAttr.js");
            var baseAttr = new BaseAttr({
                getUrl: 'menu/getMenuInfo',//获取实例的接口
                addUrl: 'menu/addMenuInfo',//新增的接口Url
                updateUrl: 'menu/updateMenuInfo',//修改的接口Url
                loadPageEvent: function () {
                    internal.parentNodes();
                },
                //绑定事件前执行||服务器相应获取数据后，绑定到界面前执行
                beforeBindEvent: function (data) {
                    if (data.isSuccess) {
                        if (data.result.state == 1) {
                            $('.btn-switch').removeClass("btn-switch-off").addClass("btn-switch-on");
                        } else {
                            $('.btn-switch').addClass("btn-switch-off").removeClass("btn-switch-on");
                        }
                        if (data.result.fkParentMenuId == "0") {
                            $("#btnFkParent").find("span.text-line-content").text("菜单根节点");
                            $("#btnFkParent").find("input.text-line").val("0");
                        } else {
                            internal.req.getInfo(data.result.fkParentMenuId, function (result) {
                                $("#btnFkParent").find("span.text-line-content").text(result.Result.name);
                                $("#btnFkParent").find("input.text-line").val(data.result.fkParentMenuId);
                            })
                        }

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
        parentNodes: function () {
            $("#btnFkParent").on("click", function () {
                var jqFkParent = this;
                var top = common.getTopWindowDom();
                top.win_menu_tree_dialog = top.$.ligerDialog.open({
                    title: '选择父级菜单节点',
                    url: webRoot + 'menu/view/tree?noDrag=1',
                    width: 500,
                    height: 580
                });
                top.win_menu_tree_select_callback = function (data) {
                    $("#btnFkParent").find("span.text-line-content").text(data.name);
                    $("#btnFkParent").find("input.text-line").val(data.id);
                    return true;
                }
            });
        }
    };
    exports.init = function () {
        internal.init();
    }
});