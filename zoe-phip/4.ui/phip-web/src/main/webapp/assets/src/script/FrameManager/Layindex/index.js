//主框架
define(function (require, exports, module) {
    var internal = {
        top: common.getTopWindowDom(),
        req: require("./req.js").req,
        menu: require("./menu.js"),
        updatePwd: require("./userUpdatePwd.js"),
        dataMenu: null,
        init: function () {
            internal.getMenuList();
            internal.buildMenu(true, internal.dataMenu);
            internal.SafeExit();
            internal.togglePopMenu();
            internal.UpdateUserPwd();
            internal.top.msgTip = function (msgContext, msgCss) {
                common.jsmsg(msgContext, msgCss);
            };

            $(".img-logo").on("click", function () {
                $("body").lockScreen({});
            });

        },
        //获取菜单数据
        getMenuList: function () {
            internal.req.getMenuList(function (data) {
                internal.dataMenu = data;
            })
        },
        //创建菜单
        buildMenu: function (islink, data) {
            //加载管理首页左边导航菜单
            if (data != null) {
                internal.menu.init(islink, data);
            }
        },
        //设置快捷菜单的高度
        setPopMenuHeight: function () {
            //计算容器的高度
            var divHeight = $(window).height() * 0.6;
            var groupHeight = 0;
            $("#popMenu .list-box .y-layout-menu").each(function () {
                if ($(this).height() > groupHeight) {
                    groupHeight = $(this).height();
                }
            });
            if (divHeight > groupHeight) {
                divHeight = groupHeight;
            }
            $("#popMenu .list-box .y-layout-menu").height(groupHeight);
            $("#popMenu .pop-box").height(divHeight);
        },
        //快捷菜单的显示与隐藏
        togglePopMenu: function () {
            var $popMenu = $("#popMenu");
            var $btnPaograms = $("#btnPaograms");
            var $btnPopClose = $("#btnPopClose");
            //鼠标点击触发
            $btnPaograms.click(function (e) {
                if ($btnPaograms.hasClass("active")) {
                    $popMenu.hide();
                    $btnPaograms.removeClass("active");
                }
                else {
                    $popMenu.show();
                    $("#popMenu hr.y-line").hide();
                    $btnPaograms.addClass("active");
                    internal.setPopMenuHeight();//只有显示的时候才能设置高度
                    $popMenu.find(".link").click(function () {
                        $popMenu.hide();//点击链接关闭
                        $btnPaograms.removeClass("active");
                    });
                }
                e.stopPropagation();
            });
            $(document).click(function (e) {
                if (!$(e.target).hasClass("pop-menu")) {
                    $popMenu.hide();
                    $btnPaograms.removeClass("active");
                }
            });
            $btnPopClose.click(function () {
                $popMenu.hide();//点击关闭按钮关闭
                $btnPaograms.removeClass("active");
            });
        },
        //安全退出
        SafeExit: function () {
            //安全退出
            $("#exit").click(function () {
                $.ligerDialog.confirm('是否安全退出？', function (yes) {
                    if (yes) {
                        window.location.href = webRoot + 'frame/loginout';
                        //退出登录时清空cookie
                        $.cookie('home-link', "");
                        $.cookie("home-menu-navid", '');
                    }
                })
            });
        },

        //修改用户密码
        UpdateUserPwd: function () {
            $("#LoginName").on("click", function () {

                common.dialog({
                    title: "用户密码修改",
                    url: "SystemUser/UserPwd",
                    width: 400,
                    height: 300,
                    buttons: [
                        {
                            text: "确认",
                            onclick: function () {
                                internal.top.win_update_user_password();
                            }
                        },
                        {
                            text: '取消',
                            onclick: function (item, dialog) {
                                dialog.close();
                            }
                        }
                    ]
                })
            });
        }
    };
    window.togglePopMenu = function () {
        internal.togglePopMenu();
    }
    exports.init = function () {
        internal.init();
    }
});