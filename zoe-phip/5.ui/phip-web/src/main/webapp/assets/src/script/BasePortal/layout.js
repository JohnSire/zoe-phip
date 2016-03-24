//框架布局
define(function (require, exports, module) {
    var internal = {
        top: common.getTopWindowDom(),
        unit: require("../BasePortal/unit.js").unit,
        baseModule: require("../BasePortal/module.js").module,
        gridModule: require("../BasePortal/gridModule.js").gridModule,
        chartModule: require("../BasePortal/chartModule.js").chartModule,
        drillModule: require("../BasePortal/drillModule.js").drillModule,
        originalContentWidth: 0,
        jqControlObj: null,
        options: null,
        init: function (options) {
            internal.buildHtml(options);
            internal.adaptive(options);
            internal.bindData(options);
        },
        buildHtml: function (options) {
            var jqControlObj = options["jqControlObj"];
            if (options.isShowStateBar) {
                internal.stateBar.buildHtml(options);
            }
            var jqLayoutContent = $("<div><div>").addClass("y-layout-content");
            var jqLayoutMain = $("<div></div>").addClass("y-layout-form-main");
            jqLayoutContent.append(jqLayoutMain);
            internal.toolBar.buildHtml(jqLayoutMain);
            internal.showContent.buildHtml(jqLayoutMain);
            jqControlObj.append(jqLayoutContent);
            jqControlObj.find("[ptype='content']").sortable();
        },
        layInit: function (options) {
            var jqControlObj = options["jqControlObj"];
            var height = $("body").innerHeight();
            var stateBarHeight = jqControlObj.find("[ptype='statebar']").outerHeight();
            var h = height - stateBarHeight;
            var jqToolBar = jqControlObj.find("[ptype='toolbar']").outerHeight(h);
            var w = internal.showContent.getWidth(jqControlObj);
            var jqContent = jqControlObj.find("[ptype='content']").outerHeight(h).outerWidth(w);
        },
        adaptive: function (options) {
            var jqControlObj = options["jqControlObj"];
            internal.layInit(options);
            internal.originalContentWidth = jqControlObj.find("[ptype='content']").innerWidth() - 15 - 28;//减15为内部padding;减28为预留滚动条
            //解决js window resize自动执行两次的bug
            //第一次执在界面调整前触发，第二次执行在界面调整后触发，所以取第二次执行，不能取第一次执行
            var n = 0;
            $(window).resize(function () {
                if (n % 2 == 1) {
                    internal.layInit(options);
                    var npWidth = jqControlObj.find("[ptype='content']").innerWidth() - 15 - 28;//减15为内部padding;减28为预留滚动条
                    $(jqControlObj).find("[ptype='module']").each(function () {
                        var t = this;
                        var moduleWidth = internal.module.getModuleWidth($(t).outerWidth(), internal.originalContentWidth, npWidth);
                        var moduleHeight = internal.module.getModuleHeight($(t).outerHeight(), $(t).outerWidth(), moduleWidth);
                        $(t).outerWidth(moduleWidth).outerHeight(moduleHeight);
                        var moduleContentHeight = $(t).innerHeight() - $(t).find(".y-layout-col-header").outerHeight(), moduleContentWidth = $(t).innerWidth();
                        var jqContent = $(t).find(".y-layout-col-content").width(moduleContentWidth).height(moduleContentHeight);
                        if ($(this).attr("mtype") == 'text') {
                            jqContent.css({ "overflow-y": "auto" })
                        }
                    })
                    internal.originalContentWidth = jqControlObj.find("[ptype='content']").innerWidth() - 15 - 28;
                }
                n++;
            });
        },
        //状态栏
        stateBar: {
            normalTip: '您现在所在是自行配置定义的信息页面，点击<a class="link" ptype="btnConfig">配置</a>进行自定义配置！',
            editTip: '您现在所在是自行配置定义的信息页面，您可根据所需进行配置！',
            buttons: {
                //btnParam: '<a class="btn-little btn-little-blue"><span class="text-outer">全局参数<span class="text-inner">全局参数</span></span></a>',
                btnSave: '<a class="btn-little btn-little-blue"><span class="text-outer">保存<span class="text-inner">保存</span></span></a>',
                btnCancel: '<a class="btn-little btn-little-gray"><span class="text-outer">取消<span class="text-inner">取消</span></span></a>',
                btnBack: '<a class="btn-little btn-little-gray"><span class="text-outer">返回列表<span class="text-inner">返回列表</span></span></a>'
            },
            buildHtml: function (options) {
                var jqControlObj = options["jqControlObj"];
                var jqStateBar = $("<div></div>").addClass("y-layout-header").attr({ "ptype": "statebar" });
                var jqStateTip = $("<p></p>").addClass("y-layout-tips").addClass("left").html(internal.stateBar.normalTip); //正常状态下的状态提示语
                var onClickConfig = function () {
                    jqStateTip.find("[ptype='btnConfig']").on("click", function () {
                        jqStateTip.html(internal.stateBar.editTip);
                        internal.onEdit(options);
                    });
                };
                onClickConfig();
                jqStateBar.append(jqStateTip);//追加到界面中去
                var jqBtnsPane = $("<ul></ul>").addClass("y-layout-form").addClass("right").attr({ "ptype": "stateBarBtn" }).hide();
                var jqBtnsBack = $("<ul></ul>").addClass("y-layout-form").addClass("right").attr({ "ptype": "backBarBtn" }).hide();
                //var jqBtnParam = $("<li></li>").addClass("list").html(internal.stateBar.buttons.btnParam).on("click", function () {
                //    jqStateTip.html(internal.stateBar.normalTip);
                //});
                var jqBtnSave = $("<li></li>").addClass("list").html(internal.stateBar.buttons.btnSave).on("click", function () {
                    jqStateTip.html(internal.stateBar.normalTip);
                    onClickConfig();
                    if (typeof (options["onSave"]) == "function") {
                        var data = internal.getData(jqControlObj);
                        options["onSave"](data);
                    }
                    internal.cancelEdit(options);
                });
                var jqBtnCancel = $("<li></li>").addClass("list").html(internal.stateBar.buttons.btnCancel).on("click", function () {
                    jqStateTip.html(internal.stateBar.normalTip);
                    onClickConfig();
                    internal.cancelEdit(options);
                });
                var jqBtnBack = $("<li></li>").addClass("list").html(internal.stateBar.buttons.btnBack).on("click", function () {
                    parent.frames["mainframe"].location.href = webRoot + "ThemeViewTemplate/Index";
                });
                jqBtnsBack.append(jqBtnBack);
                jqBtnsPane.append(jqBtnSave).append(jqBtnCancel);//按钮事件追加
                jqStateBar.append(jqBtnsPane).append(jqBtnsBack);
                $(jqControlObj).append(jqStateBar);
            }
        },
        //工具条
        toolBar: {
            //各个控件插件的html属性
            btnsAttr: [
                { "ptype": "text", "class": "icon-form-text", "title": "文本" },
                { "ptype": "grid", "class": "icon-form-table", "title": "表格" },
                { "ptype": "pie", "class": "icon-form-pie", "title": "饼图" },
                { "ptype": "radarMap", "class": "icon-form-radarMap", "title": "雷达图" },
                { "ptype": "bar", "class": "icon-form-bar", "title": "柱状图" },
                { "ptype": "line", "class": "icon-form-line", "title": "曲线图" },
                { "ptype": "container", "class": "icon-form-container", "title": "容器" }
            ],
            buildHtml: function (jqMainObj) {
                var jqToolBar = $("<div></div>").addClass("y-layout-form-toolbar").attr({ "ptype": 'toolbar' }).hide();
                $.each(internal.toolBar.btnsAttr, function (index, item) {
                    var jqBtn = $("<a></a>").addClass("icon-form").addClass(item["class"]).attr({ "ptype": item["ptype"], "title": item["title"] }).hide();
                    jqToolBar.append(jqBtn);
                });
                jqMainObj.append(jqToolBar);
            }
        },
        //展示内容区
        showContent: {
            buildHtml: function (jqMainObj) {
                var jqShowContent = $("<div></div>").attr({ "ptype": "content" }).addClass("y-layout-form-content");
                jqMainObj.append(jqShowContent);
            },
            getWidth: function (jqControlObj) {
                var width = $(".y-layout-form-main").innerWidth();
                var toolbarWidth = 0;
                var jqToolbar = jqControlObj.find("[ptype='toolbar']");
                if (jqToolbar.css("display") != "none") {
                    toolbarWidth = jqToolbar.outerWidth();
                }
                var w = width - toolbarWidth;
                return w;
            }
        },
        //模块
        module: {
            //获取界面改变后的模块实际宽度
            // omWidth/amWidth=opWidth/npWidth
            getModuleWidth: function (omWidth, opWidth, npWidth) {
                var amWidth = Math.round((omWidth * npWidth) / opWidth);
                return amWidth;
            },
            //获取界面改变后的模块的实际高度

            getModuleHeight: function (omHeight, omWidth, amWidth) {
                var amHeight = Math.round((amWidth / omWidth) * omHeight)
                return amHeight;
            }
        },
        //应用状态下的模块状态
        cancelEdit: function (options) {
            var jqControlObj = options["jqControlObj"];
            var jqToolBar = jqControlObj.find("[ptype='toolbar']").hide();
            var jqStateBarBtn = jqControlObj.find("[ptype='stateBarBtn']").hide();
            var jqStateBarBack = jqControlObj.find("[ptype='backBarBtn']").show();
            var jqContent = jqControlObj.find("[ptype='content']");
            var w = internal.showContent.getWidth(jqControlObj);
            var jqContent = jqControlObj.find("[ptype='content']").outerWidth(w);
            $(jqControlObj).find("[ptype='module']").removeClass("active");
            $(jqControlObj).find("[ptype='module']").find(".icon-wrapper").hide();
            $(jqControlObj).find("[ptype='module']").resizable("disable")
            $(jqControlObj).find("[ptype='content']").sortable("disable");
        },
        //编辑状态下的模块状态
        onEdit: function (options) {
            var jqControlObj = options["jqControlObj"];
            var jqToolBar = jqControlObj.find("[ptype='toolbar']").show();
            var jqStateBarBtn = jqControlObj.find("[ptype='stateBarBtn']").show();
            var jqStateBarBack = jqControlObj.find("[ptype='backBarBtn']").hide();
            var jqContent = jqControlObj.find("[ptype='content']");
            var w = internal.showContent.getWidth(jqControlObj);
            var jqContent = jqControlObj.find("[ptype='content']").outerWidth(w);
            $(jqControlObj).find("[ptype='module']").find(".icon-wrapper").show();
            $(jqControlObj).find("[ptype='module']").resizable("enable")
            $(jqControlObj).find("[ptype='content']").sortable("enable");
        },
        //获取界面的值
        getData: function (jqControlObj) {
            var data = [];
            $(jqControlObj).find("[ptype='module']").each(function () {
                var t = this, dataInfo = {};
                dataInfo["id"] = $(t).attr("id");
                if ($(t).attr("parentId")) {
                    dataInfo["parentId"] = $(t).attr("parentId");
                }
                dataInfo["targetWidth"] = $(jqControlObj).innerWidth(),
                dataInfo["targetHeight"] = $(jqControlObj).innerHeight(),
                dataInfo["width"] = $(t).innerWidth();
                dataInfo["height"] = $(t).innerHeight();
                dataInfo["mtype"] = $(t).attr("mtype");
                dataInfo = $.extend(true, {}, $(t).data("data") || {}, dataInfo);
                data.push(dataInfo);
            });
            return data;
        },
        bindData: function (options) {
            var jqControlObj = options["jqControlObj"];
            internal.jqControlObj = jqControlObj;
            internal.options = options;
            $.each(options["data"] || [], function (index, item) {
                var curWidth = item["targetWidth"] ? item["width"] / item["targetWidth"] * $(jqControlObj).innerWidth() : item["width"];
                var curHeight = item["targetWidth"] ? item["height"] / item["width"] * curWidth : item["height"];
                var jqModule = internal.baseModule.build({
                    id: item["id"],
                    parentId: item["parentId"],
                    mtype: item["mtype"],
                    width: curWidth,
                    height: item["height"],
                    onResize: function (id, moduleContentWidth, moduleContentHeight) { },
                    onSetting: function (id) {
                        var type = item.mtype;
                        if (type == "grid") {
                            type = "gridParam";
                        } else {
                            type = "chartParam";
                        }
                        internal.unit.onSetting(id, options, type);
                    },
                    onDrill: function (id) {
                        internal.drillModule.add({ parentId: id, mtype: item["mtype"], title: "钻取视图" }, options);
                    }
                });
                var jqContent = $(jqControlObj).find("[ptype='content']");
                jqContent.append(jqModule);
                if ($(jqModule).attr("parentId")) {
                    jqModule.data("data", item).hide()
                } else {
                    switch (item["mtype"]) {
                        case "grid":
                            internal.gridModule.build(jqModule, item, options);
                            break;
                        case "pie":
                        case "bar":
                        case "line":
                            internal.chartModule.build(jqModule, item, options);
                            break;

                    }

                }
            });
            internal.cancelEdit(options);
        },
        search: function () {
            var options = $.extend(true, {}, internal.options);
            internal.jqControlObj.find("[ptype='module']").each(function () {
                if (!$(this).is(":hidden")) {
                    var item = $(this).data("data");
                    switch (item["mtype"]) {
                        case "grid":
                            internal.gridModule.build($(this), item, internal.options);
                            break;
                        case "pie":
                        case "bar":
                        case "line":
                            internal.chartModule.build($(this), item, internal.options);
                            break;
                    }
                }
            })
        }
    };
    //初始化
    exports.layout = {
        init: function (options) {
            internal.init(options);
        },
        //获取值
        getData: function (jqControlObj) {
            return internal.getData(jqControlObj);
        },
        search: function () {
            internal.search();
        }
    }
});