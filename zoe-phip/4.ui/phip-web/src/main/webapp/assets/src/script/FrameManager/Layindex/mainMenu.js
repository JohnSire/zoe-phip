//主框架菜单
define(function (require, exports, module) {
    var internal = {
        //主菜单初始化
        init: function (islink) {
            internal.buildMainMenu(islink);
        },
        //初始化导航菜单
        initMenuTree: function (islink) {
            var navObj = $("#mainNav");
            var navGroupObj = $("#sidebarNav .y-layout-menu");
            var navItemObj = $("#sidebarNav .y-layout-menu .list-wrap");
            //先清空NAV菜单内容
            navObj.html('');
            navGroupObj.each(function (i) { //循环添加菜单
                //添加菜单导航
                var navHtml = $('<li class="list" navid="menu' + i + '"><a class="link">' + $(this).children("h1").attr("title") + '<span class="text-inner">' + $(this).children("h1").attr("title") + '</span></a></li>').appendTo(navObj);
                //默认选中第一项
                if (i == 0) {
                    $(this).show();
                    navHtml.addClass("active");
                }
                //为菜单添加事件
                navHtml.click(function () {
                    navObj.children("li").removeClass("active");
                    $(this).addClass("active");
                    // 将第一级菜单存到 cookie里
                    var mainNav = $(this).attr("navid");
                    $.cookie("home-menu-mainNav", mainNav);
                    //选快捷菜单的时候，进行选中效果
                    var index = navObj.children("li").index($(this));
                    navGroupObj.hide().eq(index).show();
                    $("#mainNav").find("li").removeClass("active").eq(index).addClass("active");
                });
                //首先隐藏所有的UL
                $(this).find("ul").hide();
                //绑定树菜单事件.开始
                $(this).find("ul").each(function (j) { //遍历所有的UL
                    //遍历UL第一层LI
                    $(this).children("li").each(function () {
                        var liObj = $(this);
                        var spanObj = liObj.children("a").children("span");
                        //判断是否有子菜单和设置距左距离
                        //设置左距离
                        //如果有下级菜单
                        if (liObj.children("ul").length > 0) {
                            liObj.children("a").removeAttr("href"); //删除链接，防止跳转
                            //如果a有自定义图标则将图标插入，否则使用默认的样式
                            //隐藏下级的UL
                            liObj.children("ul").hide();
                            //绑定单击事件
                            liObj.children("a").click(function () {
                                //如果菜单已展开则闭合
                                if ($(this).hasClass("open")) {
                                    //设置自身的右图标为+号
                                    $(this).removeClass("open").addClass("close");
                                    //隐藏自身父节点的UL子菜单
                                    $(this).parent().children("ul").slideUp(300);
                                } else {
                                    //搜索所有同级LI且有子菜单的右图标为+号及隐藏子菜单
                                    $(this).parent().siblings().each(function () {
                                        if ($(this).children("ul").length > 0) {
                                            //设置自身的右图标为+号
                                            $(this).children("a").removeClass("open").addClass("close");
                                            //隐藏自身子菜单
                                            $(this).children("ul").slideUp(300);
                                        }
                                    });
                                    //设置自身的右图标为-号
                                    $(this).removeClass("close").addClass("open");
                                    //显示自身父节点的UL子菜单
                                    $(this).parent().children("ul").slideDown(300);
                                }
                            });

                        } else {
                            //如果a有自定义图标则将图标插入，否则使用默认的样式

                            if (typeof (liObj.children("a").attr("href")) != "undefined") {
                                //绑定单击事件
                                liObj.children("a").click(function () {
                                    //删除所有的选中样式
                                    navGroupObj.find("ul li a").removeClass("active");
                                    navGroupObj.find("ul li").removeClass("active");
                                    //删除所有的y-layout-menu选中样式
                                    navGroupObj.removeClass("active");
                                    //删除所有的main-nav选中样式
                                    navObj.children("a").removeClass("active");
                                    //自身添加样式
                                    $(this).addClass("active");
                                    //设置父y-layout-menu选中样式
                                    $(this).parents(".y-layout-menu").addClass("active");
                                    if ($(this).parent("#menu-li-a").children("a").attr("href") != undefined) {
                                        $(this).parents(".list").addClass("active")
                                    }
                                    //设置父main-nav选中样式
                                    navObj.children("a").eq(navGroupObj.index($(this).parents(".y-layout-menu"))).addClass("active");
                                    //隐藏所有的y-layout-menu
                                    navGroupObj.hide();
                                    //显示自己的父y-layout-menu
                                    $(this).parents(".y-layout-menu").show();
                                    //保存到cookie
                                    if (typeof ($(this).attr("navid")) != "undefined") {
                                        //addCookie("dt_manage_navigation_cookie", $(this).attr("navid"), 240);
                                    }
                                });
                            }
                        }
                    });
                    //显示第一个UL
                    if (j == 0) {
                        $(this).show();
                        //展开第一个菜单
                        if ($(this).children("li").first().children("ul").length > 0) {
                            $(this).children("li").first().children("a").removeClass("close").addClass("open");
                            $(this).children("li").first().children("ul").show();
                        }
                    }
                    $(this).show();
                });
                //绑定树菜单事件.结束
            });
            //定位或跳转到相应的菜单
            internal.linkMenuTree(islink);
        },

        //创建主菜单
        buildMainMenu: function (islink) {
            internal.initMenuTree(islink);
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
                cookieObj = navGroupObj.find('a[navid="' + $.cookie("dt_manage_navigation_cookie") + '"]');
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
                    $.cookie("dt_manage_navigation_cookie", navid);
                }
                //检查是否需要跳转链接
                if (islink == true && cookieObj.attr("href") != "" && cookieObj.attr("href") != "#") {
                    frames["mainframe"].location.href = cookieObj.attr("href");
                }
            } else if (argument == 2) {
                //删除所有的选中样式
                navGroupObj.find("ul li a").removeClass("active");
                //保存到cookie
                $.cookie("dt_manage_navigation_cookie", "");
            }

            if (navid) {
                var jqNavActiveObj = $("#sidebarNav").find("[navid='" + navid + "']").closest(".y-layout-menu");
                var index = $("#sidebarNav").find(".y-layout-menu").index(jqNavActiveObj);
                $("#mainNav").find("li").removeClass("active").eq(index).addClass("active");
            }

        }
    };
    exports.init = function (islink) {
        internal.init(islink);
    }
});