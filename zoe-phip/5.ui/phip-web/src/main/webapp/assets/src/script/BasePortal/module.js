//控件模块
define(function (require, exports, module) {
    var internal = {
        top: common.getTopWindowDom(),
        unit: require("../BasePortal/unit.js").unit,

        //基础模块
        module: {
            id: function () { return Guid.NewGuid().ToString("D") },
            build: function (options) {
                var id = options["id"] || internal.module.id();
                var jqModule = $("<div></div>").addClass("y-layout-col").attr({ "ptype": "module" }).attr({ id: id, parentId: options["parentId"] }).css({ width: options["width"], height: options["height"] });
                var jqModuleHeader = $("<div></div>").addClass("y-layout-col-header");
                var jqModuleContent = $("<div></div>").addClass("y-layout-col-content");
                jqModule.append(jqModuleHeader);
                jqModule.append(jqModuleContent);
                var jqModuleDrillTip = internal.moduleDrillTip();
                jqModule.append(jqModuleDrillTip);
                internal.drillPath.buildHtml(jqModuleHeader);
                internal.moduleBtn.buildHtml(jqModuleHeader, function () {
                    if (options && options["onSetting"] && typeof (options["onSetting"] == "function")) { options["onSetting"](id); }
                }, function () {
                    if (options && options["onDrill"] && typeof (options["onDrill"] == "function")) { options["onDrill"](id); }
                }, function () {
                    var top = common.getTopWindowDom();
                    var jqCurModule = $("#" + id);
                    var childrenPath = internal.unit.getAfterPath(id);
                    var msg = childrenPath.length > 0 ? "是否移除该模块及关联下钻模块？" : "是否移除模块？";
                    top.$.ligerDialog.confirm(msg, function (yes) {
                        if (yes) {
                            jqCurModule.animate({
                                width: 0
                            }, 100, function () {
                                var parentId = $(jqCurModule).attr("parentId");
                                $("#" + parentId).show();
                                var mtype = $("#" + parentId).attr("mtype");
                                jqCurModule.remove();
                                $.each(childrenPath, function (index, item) {
                                    $("#" + item["id"]).remove();
                                })
                                //如果是表格，则重新设置框度
                                if (mtype == "grid") {
                                    $("#" + parentId).find(".l-grid2").width($("#" + parentId).width());
                                }

                            });
                        }
                    });
                    if (options && options["onRemove"] && typeof (options["onRemove"] == "function")) {
                        options["onRemove"](id);
                    }
                });
                ////调整模块大小事件
                jqModule.resizable({
                    autoHide: true,
                    resize: function (event, ui) {
                        var id = $(jqModule).attr("id");
                        var width = $(jqModule).width(), height = $(jqModule).height();
                        var allModuleObj = internal.unit.getAllPath(id);
                        $.each(allModuleObj, function (index, item) {
                            $("#" + item["id"]).width(width).height(height);
                            if ($("#" + item["id"]).attr("mtype") == "grid") {
                                var gridObj = liger.get("grid-" + item["id"]);
                                gridObj.setWidth(width - 5);
                                gridObj.setHeight(height - 5);
                            }
                        })
                    }
                });
                jqModule.attr({ mtype: options["mtype"] });
                var jqIcon = $('<span></span>').addClass("icon-form");
                var iconClass = "icon-form-" + options["mtype"];
                jqIcon.addClass(iconClass);
                $(jqModule).find(".y-layout-position").prepend(jqIcon);


                $(jqModule).find(".icon-grid-search,.y-layout-col-pop").hover(function () {
                    var t = this;
                    var id = $(jqModule).attr("id");
                    var jqPro = $(t).closest("[ptype='module']").find(".y-layout-col-pop");
                    var jqProList = $(jqPro).find(".lists").empty();
                    $("[ptype='module'][parentId='" + id + "']").each(function () {
                        //var childTitle = $(this).data("data")["title"], childId = $(this).data("data")["id"];
                        var childTitle = $(this).data("data")["title"], childId = $(this).attr("id");
                        var jqLi = $("<li></li>").addClass("list").attr({ "childId": childId }).on("click", function () {
                            jqModule.hide();
                            var jqChildModule = $("#" + $(this).attr("childId"))
                            internal.unit.addPath(jqChildModule, { id: childId, title: childTitle });
                            jqChildModule.show();
                        });
                        var jqA = $("<a></a>").addClass("link").text(childTitle);
                        jqLi.append(jqA);
                        jqProList.append(jqLi);
                    })
                    if (jqProList.find("li").length > 0) {
                        jqPro.show();
                    }
                }, function () {
                    $(this).closest("[ptype='module']").find(".y-layout-col-pop").hide();
                });

                return jqModule;
            }
        },
        //钻取导航面包屑'
        drillPath: {
            buildHtml: function (jqModuleHeader) {
                var jqPosition = $("<p></p>").addClass("y-layout-position");
                var jqSpan = $("<span></span>").addClass("y-layout-position-path");
                jqPosition.append(jqSpan);
                jqModuleHeader.append(jqPosition);
            }
        },
        //模块配置按钮
        moduleBtn: {
            btnsAttr: [
                { "ptype": "moduleSetting", "class": "icon-grid-setting", "title": "配置" },
                { "ptype": "moduleDrill", "class": "icon-grid-search", "title": "下钻" },
                { "ptype": "moduleDel", "class": "icon-grid-del", "title": "删除" }
            ],
            buildHtml: function (jqModuleHeader, onSetting, onDrill, onRemove) {
                var jqModuleBtn = $("<p></p>").addClass("icon-wrapper");
                $.each(internal.moduleBtn.btnsAttr, function (index, item) {
                    var jqBtn = $("<a></a>").addClass("icon-grid")
                                            .addClass(item["class"])
                                            .attr({ "ptype": item["ptype"], "title": item["title"] })
                                            .on("click", function () {
                                                var jqCurModule = $(this).closest("[ptype='module']").addClass("active");
                                                switch (item["ptype"]) {
                                                    case "moduleSetting":
                                                        jqCurModule.addClass("active");
                                                        if (typeof (onSetting) == "function") { onSetting(); }
                                                        break;
                                                    case "moduleDrill":
                                                        if (typeof (onDrill) == "function") {
                                                            onDrill();
                                                        }
                                                        break;
                                                    case "moduleDel":
                                                        if (typeof (onRemove) == "function") {
                                                            onRemove();
                                                        }
                                                        break;
                                                }
                                            });
                    jqModuleBtn.append(jqBtn);
                });
                $(jqModuleHeader).append(jqModuleBtn);

            }

        },
        moduleDrillTip: function () {
            var jqPro = $("<div></div>").addClass("y-layout-col-pop");
            var jqProBox = $("<div></div>").addClass("y-layout-col-pop-box");
            var jqProBoxList = $("<ul></ul>").addClass("lists");
            var jqProBoxLi = $("<li></li>").addClass("list");
            var jqProArrow = $("<i></i>").addClass("arrow").text("箭头");
            jqProBoxList.append(jqProBoxLi);
            jqProBox.append(jqProBoxList);
            jqPro.append(jqProBox).append(jqProArrow);
            return jqPro;
        }
    }
    exports.module = {
        build: function (options) {
            return internal.module.build(options);
        }
    }
});