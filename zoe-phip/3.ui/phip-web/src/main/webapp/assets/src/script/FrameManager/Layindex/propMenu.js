//属性框快捷菜单
define(function (require, exports, module) {
    var internal = {
        //捷菜单初始化
        init: function () {
            internal.buildProMenu();
        },
        //初始化快捷导航菜单
        initPopMenuTree: function () {
            //遍历及加载事件
            $("#popMenu .pop-box .list-box li").each(function () {
                var linkObj = $(this).children("a");
                //linkObj.removeAttr("href");
                if ($(this).children("ul").length > 0) { //如果无下级菜单
                    linkObj.addClass("nolink");
                } else {
                    linkObj.addClass("link");
                    linkObj.click(function () {
                        internal.linkMenuTree(true, linkObj.attr("navid")); //加载函数
                    });
                }
            });
            //设置快捷菜单容器的大小
            internal.popMenuTreeResize();
        },
        //定位或跳转到相应的菜单
        linkMenuTree: function (islink, navid) {
            var navObj = $("#mainNav");
            var navGroupObj = $("#sidebarNav .y-layout-menu");
            var navItemObj = $("#sidebarNav .y-layout-menu .list-wrap");
            //读取Cookie,如果存在该ID则定位到对应的导航
            var cookieObj;
            var argument = arguments.length;
            if (argument == 2) {
                cookieObj = navGroupObj.find('a[navid="' + navid + '"]');

            } else {
                cookieObj = navGroupObj.find('a[navid="' + getCookie("dt_manage_navigation_cookie") + '"]');
            }
            if (cookieObj.length > 0) {
                //显示所在的导航和组
                //删除所有的选中样式
                navGroupObj.find("ul li a").removeClass("active");
                navGroupObj.find("ul li").removeClass("active");
                //删除所有的y-layout-menu选中样式
                navGroupObj.removeClass("active");
                //删除所有的main-nav选中样式
                navObj.children("a").removeClass("active");
                //自身添加样式
                cookieObj.addClass("active");
                //设置父y-layout-menu选中样式
                cookieObj.parents(".y-layout-menu").addClass("active");
                cookieObj.parent(".list").addClass("active");
                //设置父main-nav选中样式
                navObj.children("a").eq(navGroupObj.index(cookieObj.parents(".y-layout-menu"))).addClass("active");
                //隐藏所有的y-layout-menu
                navGroupObj.hide();
                //显示自己的父y-layout-menu
                cookieObj.parents(".y-layout-menu").show();
                //遍历所有的LI父节点
                cookieObj.parents("li").each(function () {
                    //搜索所有同级LI且有子菜单的右图标为+号及隐藏子菜单
                    $(this).siblings().each(function () {
                        if ($(this).children("ul").length > 0) {
                            //设置自身的右图标为+号
                            $(this).children("a").removeClass("open").addClass("close");
                            //隐藏自身子菜单
                            $(this).children("ul").hide();
                        }
                    });
                    //设置自身的右图标为-号
                    if ($(this).children("ul").length > 0) {
                        $(this).children("a").removeClass("close").addClass("open");
                    }
                    //显示自身的UL
                    $(this).children("ul").show();
                });
                //检查是否需要保存到cookie
                if (argument == 2) {
                    $.cookie("dt_manage_navigation_cookie", navid, 240);
                }
                //检查是否需要跳转链接
                if (islink == true && cookieObj.attr("href") != "" && cookieObj.attr("href") != "#") {
                    frames["mainframe"].location.href = cookieObj.attr("href");
                }
            } else if (argument == 2) {
                //删除所有的选中样式
                navGroupObj.find("ul li a").removeClass("active");
                //保存到cookie
                $.cookie("dt_manage_navigation_cookie", "", 240);
            }

            if (navid) {
                var jqNavActiveObj = $("#sidebarNav").find("[navid='" + navid + "']").closest(".y-layout-menu");
                var index = $("#sidebarNav").find(".y-layout-menu").index(jqNavActiveObj);
                $("#mainNav").find("li").removeClass("active").eq(index).addClass("active");
            }

        },
        //设置快捷菜单容器的大小
        popMenuTreeResize:function() {
            //计算容器的宽度
            var groupWidth = $("#popMenu .list-box .y-layout-menu").outerWidth();
            var divWidth = $("#popMenu .list-box .y-layout-menu").length * groupWidth;
            var winWidth = $(window).width();
            if (divWidth > winWidth - 25) {
                var groupCount = Math.floor(winWidth / groupWidth);
                if (groupCount > 0) {
                    groupWidth = groupWidth * groupCount + 10;
                }
            } else {
                groupWidth = divWidth + 10;//10为滚动条预留宽度
            }
            $("#popMenu .pop-box").width(groupWidth);
            //只有显示的时候才能设置高度
            if ($("#popMenu").css("display") == "block") {
                internal.setPopMenuHeight();
            }
        },
        //设置快捷菜单的高度
        setPopMenuHeight: function () {
            //计算容器的高度
            var divHeight = $(window).height() * 0.8;
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
        //创建快捷菜单
        buildProMenu: function () {
            internal.initPopMenuTree();
        }
    };
    exports.init = function () {
        internal.init();
    }
});