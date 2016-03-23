//表格模块
define(function (require, exports, module) {
    var internal = {
        options: null,
        unit: require("../BasePortal/unit.js").unit,
        module: require("../BasePortal/module.js").module,
        drillModule: require("../BasePortal/drillModule.js").drillModule,
        add: function (options) {
            internal.options = options;
            var jqControlObj = options["jqControlObj"];
            var jqModule = internal.module.build({
                mtype: "grid",
                onResize: function (id, moduleContentWidth, moduleContentHeight) { },
                onSetting: function (id) {
                    internal.unit.onSetting(id, options, "gridParam");
                },
                onDrill: function (id) {
                    var jqParentModule = $("#" + id);
                    jqParentModule.hide();
                    var mtype = jqParentModule.attr("mtype");
                    internal.drillModule.add({ parentId: id, mtype: mtype, title: '钻取视图' }, options);
                }
            });
            var jqContent = $(jqControlObj).find("[ptype='content']");
            jqContent.append(jqModule);

        },
        buildGrid: function (jqModule, gridProps, options, drillData) {

            internal.options = internal.options || options || {};
            drillData = drillData || {};
            $(jqModule).data("data", gridProps);
            var id = $(jqModule).attr("id");
            var moduleContentHeight = $(jqModule).innerHeight() - $(jqModule).find(".y-layout-col-header").outerHeight(), moduleContentWidth = $(jqModule).innerWidth();
            var jqGrid = $(jqModule).find(".y-layout-col-content").attr({ "id": "grid-" + id }).width(moduleContentWidth).height(moduleContentHeight).removeAttr("ligeruiid").empty();
            var width = $(jqGrid).innerWidth() - 2, height = $(jqGrid).innerHeight() - 2;
            var title = gridProps["title"];
            internal.unit.addPath(jqModule, { id: id, title: title });
            var columns = [];
            if (!gridProps["columns"])
                return;
            $.each(gridProps["columns"], function (index, item) {
                item["hide"] = (item["state"] == "show" ? false : true);
                item["render"] = function (rowdata, rowIndex, value) {
                    var columnName = item["name"];
                    var h = "<a  class='drillName-" + columnName + "' onclick='javascript:baseProtalDrillGridRow(\"" + id + "\",\"" + rowIndex + "\",\"" + columnName + "\")'>" + value + "</a>";
                    return h;
                }
                columns.push(item);
            });

            var paramListData = internal.unit.getParamList(jqModule, internal.options);
            //#region 获取界面具体值
            var pageSearchData = {};
            $.each(paramListData["pageSearchParam"], function (index, item) {
                pageSearchData[item["name"]] = item["value"];
            });
            //#endregion
            var sql = internal.unit.convertSQL(gridProps["SQL"], pageSearchData, drillData);
            var data = { SQL: sql };
            $(jqGrid).ligerGrid({
                columns: columns,
                dataAction: "server",
                parms: data,
                pageSize: gridProps["pageSize"],
                width: width,
                height: gridProps["isUsePager"] ? height : height + 26,
                heightDiff: -2,
                usePager: gridProps["isUsePager"],
                url: webRoot + 'BasePortalMain/GetGridData',
                onAfterShowData: function () {
                    var jqNextChildrenObj = $("[parentId='" + id + "']");
                    var isExist = false;
                    $(jqNextChildrenObj).each(function (idx, itm) {
                        var columnName = $(itm).data("data")["drillForm"];
                        $("#" + id).find(".drillName-" + columnName).css({ "text-decoration": "underline", "color": "blue" });
                    });
                }
            });
        }
    };
    exports.gridModule = {
        add: function (charttype, options) {
            internal.add(charttype, options);
        },
        build: function (jqModule, gridProps, options, drillData) {
            internal.buildGrid(jqModule, gridProps, options, drillData);
        }
    };
    window.baseProtalDrillGridRow = function (id, rowindex, columnName) {
        var drillFact = columnName;
        var jqModule = $("#" + id);
        var gridObj = liger.get("grid-" + id);
        var drillData = gridObj.getRow(rowindex);
        var isExistDrill = false;
        $("[parentId=" + id + "]").each(function (index, item) {
            var parentProps = $(item).data("data");
            if (parentProps["drillForm"] == drillFact) {
                $(item).show();
                internal.buildGrid($(item), $(item).data("data"), null, drillData);
                isExistDrill = true;
            }
        });
        if (isExistDrill) {
            $(jqModule).hide();
        }

    }
});