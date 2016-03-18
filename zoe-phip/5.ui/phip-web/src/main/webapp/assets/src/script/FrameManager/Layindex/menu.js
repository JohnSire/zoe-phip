//菜单整合
define(function (require, exports, module) {
    var internal = {
        mainMenu: require("./mainMenu.js"),//主菜单
        propMenu: require("./propMenu.js"),//快捷菜单
        init: function (islink, data) {
            internal.buildMenu(islink, data);
            //主页面响应式
            internal.mainPageResize();
            //页面尺寸改变时
            var resizeTims = setTimeout(function () {
                internal.mainPageResize();//主页面响应式
                internal.popMenuTreeResize(); //快捷菜单的设置
            }, 100);
            clearTimeout(resizeTims);
        },
        //设置快捷菜单容器的大小
        popMenuTreeResize: function () {
            //计算容器的宽度
            var groupWidth = $("#popMenu .list-box .y-layout-menu").outerWidth();
            var divWidth = $("#popMenu .list-box .y-layout-menu").length * groupWidth;
            var winWidth = $(window).width();
            if (divWidth > winWidth) {
                var groupCount = Math.floor(winWidth / groupWidth);
                if (groupCount > 0) {
                    groupWidth = groupWidth * groupCount;
                }
            } else {
                groupWidth = divWidth;
            }
            $("#popMenu .pop-box").width(groupWidth);
            //只有显示的时候才能设置高度
            if ($("#popMenu").css("display") == "block") {
                internal.setPopMenuHeight();
            }
        },
        //导航菜单显示和隐藏
        mainPageResize: function () {
            //var docWidth = $(window).width();
            $("#mainNav").show();
            var height = $("body").height() - $("#header").outerHeight() - $("#footer").outerHeight() - $("#mainNav").outerHeight() - 2
            $("#main,#sidebarNav,#sidebarNav .y-layout-menu").css({
                "height": height
            })
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
        //创建菜单
        buildMenu: function (islink, data) {
            internal.loadMenuTree(islink, data);
        },
        //加载菜单
        loadMenuTree: function (_islink, data) {
            //判断是否跳转链接
            var islink = false;
            if (arguments.length == 1 && _islink) {
                islink = true;
            }
            // 获取用户菜单的权限动态展示出来
            var dataHtml = internal.GetMenuHtml(data); //返回的菜单转化成指定格式的 html
            $("#sidebarNav").html(dataHtml); //菜单
            $("#popMenu .list-box").html(dataHtml);//快捷菜单
            var jqTitle = $("#popMenu .list-box").find(".menu-title").show();
            $(jqTitle).each(function () {
                var title = $(this).attr("title");
                $(this).text(title);
            })
            //初始化导航菜单
            internal.mainMenu.init(islink);
            //初始化快捷菜单
            internal.propMenu.init();
            internal.onLink();
            var link = $.cookie('home-link');
            var navid = $.cookie('home-menu-navid');
            var mainNav = $.cookie('home-menu-mainNav');
            var hmn = $.cookie("home-menu-navid");
            if (hmn)
                $("#popMenu a[navid=" + hmn + "]").addClass("active");
            if (link) {
                $("#sidebarNav").find(".active").removeClass("active");
                $("#sidebarNav").find("[navid='" + navid + "']").addClass("active");
                if ($("#sidebarNav").find("[navid='" + navid + "']").parents("#menu-li-a").children("a").attr("href") != undefined) {
                    $("#sidebarNav").find("[navid='" + navid + "']").parents(".list").addClass("active");
                }
                $("#mainNav").find("[navid='" + mainNav + "']").addClass("active");
                $("#mainNav").find("[navid='" + mainNav + "']").click();
                if (navid) {
                    var jqNavActiveObj = $("#sidebarNav").find("[navid='" + navid + "']").closest(".y-layout-menu");
                    var index = $("#sidebarNav").find(".y-layout-menu").index(jqNavActiveObj);

                    $("#mainNav").find("li").removeClass("active").eq(index).addClass("active").click();
                }
                frames["mainframe"].location.href = link;
            } else {
                $($("#sidebarNav").find("[href]")[0]).click();
            }
        },
        onLink: function () {
            $(".y-layout-menu .link").each(function () {
                $(this).on("click", function () {
                    var href = $(this).attr("href"), navid = $(this).attr("navid");
                    if (href) {
                        internal.linkCookie(href, navid);
                    }
                    $("#popMenu .link").removeClass("active");
                    $("#popMenu .link[navid=" + $.cookie("home-menu-navid") + "]").addClass("active");
                    $("#popMenu").hide();
                    $("#btnPaograms").removeClass("active");
                });
            })
        },
        linkCookie: function (urlStr, navid) {
            frames["mainframe"].location.href = urlStr;
            $.cookie('home-link', urlStr);
            $.cookie("home-menu-navid", navid);
        },
        GetMenuHtml: function (data) {
            var dataHtml = "";
            if (data.Result && data.Result.length > 0) {
                for (var i = 0 ; i < data.Result.length; i++) {
                    dataHtml += "<div class='y-layout-menu'>";
                    dataHtml += "<h1 class='menu-title' title=" + data.Result[i].Name + "></h1>";
                    if (data.Result[i].Childrens != null && data.Result[i].Childrens.length > 0) {
                        dataHtml += "<div class='list-wrap'>";
                        for (var j = 0; j < data.Result[i].Childrens.length; j++) {
                            dataHtml += "<ul class='lists clearfix' class='lists clearfix'>";
                            dataHtml += "<li class='list' id='menu-li-a'> ";
                            dataHtml += "<a class='link' navid='" + data.Result[i].Childrens[j]["Id"] + "' href=" + webRoot + data.Result[i].Childrens[j].Address + " target='mainframe'>" + data.Result[i].Childrens[j].Name + "</a><hr class='y-line' />";
                            if (data.Result[i].Childrens[j].Childrens != null && data.Result[i].Childrens[j].Childrens.length > 0) {
                                dataHtml += "<ul class='lists clearfix'>";
                                for (var k = 0; k < data.Result[i].Childrens[j].Childrens.length; k++) {
                                    dataHtml += "<li class='list'><a class='link' navid='" + data.Result[i].Childrens[j].Childrens[k].Id + "' href=" + webRoot + data.Result[i].Childrens[j].Childrens[k].Address + " target='mainframe'>" + data.Result[i].Childrens[j].Childrens[k].Name + "</a><hr class='y-line' /></li>";
                                }
                                dataHtml += "</ul>";
                            }
                            dataHtml += "</li>";
                            dataHtml += "</ul>";
                        }
                        dataHtml += "</div>";
                    }
                    dataHtml += "</div>";
                }
            }
            return dataHtml;
        }
    };
    exports.init = function (islink, data) {
        internal.init(islink, data);
    }
});