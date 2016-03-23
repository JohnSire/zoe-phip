define(function (require, exports, module) {
    var internal = {
        //#region  获取路径
        beforePathArray: [],
        afterPathArray: [],
        firstParentId: "",
        allPathArray: [],
        //私有方法（获取该id之前的路径，不包含其本身，仅它所在的那条路径）
        priGetBeforePath: function (id) {
            var parentId = $("#" + id).attr("parentId");
            if (parentId) {
                var title = "";
                if ($("#" + parentId).data("data")) {
                    title = $("#" + parentId).data("data")["title"] || "";
                }

                var pathObj = { id: parentId, title: title };
                internal.beforePathArray.push(pathObj);
                internal.priGetBeforePath(parentId);
            }
        },
        //私有方法（获取该id之后的路径，不包含其本身）
        priGetAfterPath: function (id) {
            var jqChild = $("[parentId='" + id + "']");
            $.each(jqChild, function (index, item) {
                var childId = $(item).attr("id");
                var title = "";
                if ($("#" + childId).data("data")) {
                    title = $("#" + childId).data("data")["title"] || "";
                }
                var pathObj = { id: childId, title: title };
                internal.afterPathArray.push(pathObj);
                internal.priGetAfterPath(childId);
            })
        },
        //找到第一个父节点
        priGetFirstParentId: function (id) {
            internal.firstParentId = $("#" + id).attr("parentId") || id;
            var parentId = $("#" + id).attr("parentId");
            if (parentId) {
                internal.priGetFirstParentId(parentId);
            };
        },
        //获取所有路径节点
        getAllPath: function (id) {
            internal.firstParentId = "";
            internal.priGetFirstParentId(id);
            internal.getAfterPath(internal.firstParentId);
            var title = "";

            if ($("#" + internal.firstParentId).data("data")) {
                title = $("#" + internal.firstParentId).data("data")["title"] || "";
            }

            var firstPath = [{ id: internal.firstParentId, title: title }];
            var data = $.merge(firstPath, internal.afterPathArray);
            return data;
        },

        //对外方法（获取该id之前的路径，不包含其本身[单条路径]）
        getBeforePath: function (id) {
            internal.beforePathArray = [];
            internal.priGetBeforePath(id);
            internal.beforePathArray.reverse();//顺序处理
            return internal.beforePathArray;

        },
        //对外方法（获取该id之后的方法，不包含其本身）
        getAfterPath: function (id) {
            internal.afterPathArray = [];
            internal.priGetAfterPath(id);
            return internal.afterPathArray;
        },
        //#endregion

        //#region 添加路径
        //私有方法
        priAddPath: function (jqModule, pathOptions) {
            var pathId = pathOptions["id"], pathTitle = pathOptions["title"] || "";
            var jqSelf = $(jqModule).find("[pathId='" + pathId + "']");//本身
            if (jqSelf.length > 0) {
                $("[pathId='" + pathId + "']").text(pathTitle);//如果下钻路径存在，进行修改名称操作
            } else {
                var jqPathArray = $(jqModule).find(".y-layout-position-path").find("a");
                pathOptions["isFirst"] = jqPathArray.length > 0 ? false : true;
                var jqText = $("<a></a>")
                                .addClass("link")
                                .text(pathTitle)
                                .attr({ "pathId": pathId })
                                .on("click", function () {
                                    var id = $(this).attr("pathId");
                                    $("#" + id).show();//本身显示
                                    var moduleContentWidth = $(jqModule).innerWidth() - 19;
                                    var moduleContentHeight = $(jqModule).innerHeight() - 35;
                                    $("#" + id).find(".l-grid2").width(moduleContentWidth);
                                    $("#grid-" + id).innerHeight(moduleContentHeight);
                                    var jqNextChildrenObj = $("[parentId='" + id + "']");
                                    var isExist = false;
                                    $(jqNextChildrenObj).each(function (idx, itm) {
                                        var columnName = $(itm).data("data")["drillForm"];
                                        $("#" + id).find(".drillName-" + columnName).css({ "text-decoration": "underline", "color": "blue" });
                                    });

                                    var beforePathArray = internal.getBeforePath(id);
                                    var afterPathArray = internal.getAfterPath(id);
                                    $.each(beforePathArray, function (index, item) { $("#" + item["id"]).hide(); })
                                    $.each(afterPathArray, function (index, item) { $("#" + item["id"]).hide(); })

                                });
                var jqArrow = $("<span></span>").addClass("arrow").html("&gt;");
                if (pathOptions && pathOptions["isFirst"]) {
                    jqModule.find(".y-layout-position-path").html(jqText);
                } else {
                    jqModule.find(".y-layout-position-path").append(jqArrow).append(jqText);
                }
            }
        },

        addPath: function (jqModule, pathOptions) {
            var id = $(jqModule).attr("id");
            var beforePathArray = internal.getBeforePath(id);
            $.each(beforePathArray, function (index, item) {
                internal.priAddPath(jqModule, { id: item["id"], title: item["title"] });
            });
            internal.priAddPath(jqModule, { id: pathOptions["id"], title: pathOptions["title"] });
        },
        //#endregion

        //#region 点击配置按钮
        onSetting: function (id, options, mtype) {
            //if (mtype == "grid") {

            //}
            var jqModule = $("#" + id);
            var props = jqModule.data("data");
            var pageSearchParam = options["usePageSearchParam"] ? options["pageSearchParam"]() : [];//页面查询参数
            var drillParam = [];
            //如果这个节点存在父亲节点
            if ($(jqModule).attr("parentId")) {
                var parentId = $(jqModule).attr("parentId");
                var parentParam = $("#" + parentId).data("data");//找到父级节点参数
                drillParam = parentParam["columns"] || [];
            }
            var paramListData = { pageSearchParam: pageSearchParam, drillParam: drillParam };
            options[mtype]["onSetting"](jqModule, props, paramListData);
        },
        //#endregion

        //#region
        converSQL: function (sql, pageSearchData, drillData) {
            if (!sql)
                return "";
            var sql = sql.replace(/\{#(\w+)#\}/g, function (m, i) {
                return drillData[i];
            });
            var sql = sql.replace(/\[#(\w+)#\]/g, function (m, i) {
                return pageSearchData[i];
            });
            return sql;
        },
        //#endregion
        getParamList: function (jqModule, options) {
            var props = $(jqModule).data("data");
            var pageSearchParam = options["pageSearchParam"] ? options["pageSearchParam"]() : [];//页面查询参数
            var drillParam = [];
            //如果这个节点存在父亲节点
            if ($(jqModule).attr("parentId")) {
                var parentId = $(jqModule).attr("parentId");
                var parentParam = $("#" + parentId).data("data");//找到父级节点参数
                drillParam = parentParam["columns"] || [];
            }
            var paramListData = { pageSearchParam: pageSearchParam, drillParam: drillParam };
            return paramListData;
        }
    };
    exports.unit = {
        getBeforePath: function (id) {
            return internal.getBeforePath(id);
        },
        getAfterPath: function (id) {
            return internal.getAfterPath(id);
        },
        getAllPath: function (id) {
            return internal.getAllPath(id);
        },
        addPath: function (jqModule, pathOptions) {
            internal.addPath(jqModule, pathOptions);
        },
        onSetting: function (id, options, mtype) {
            internal.onSetting(id, options, mtype)
        },
        getParamList: function (jqModule, options) {
            return internal.getParamList(jqModule, options);
        },
        convertSQL: function (sql, pageSearchData, drillData) {
            return internal.converSQL(sql, pageSearchData, drillData);
        }

    }
})