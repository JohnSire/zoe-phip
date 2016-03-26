define(function (require, exports, module) {
    var internal = {
        param: null,
        req: require("./req.js").req,
        init: function (param) {
            internal.param = param;
            var gridParam = param.gridParam;
            return internal.buildGrid(gridParam);
        },
        columns: [
            {
                display: '操作',
                isSort: false,
                width: 120,
                render: function (rowdata, rowindex, value) {
                    var h = "";

                    if (internal.param.gridParam.editBtn)
                        h += "<a class='icon-grid icon-grid-edit' title='编辑' onclick='javascript:editLigerGridRow(\"" + rowdata.id + "\")'></a> ";
                    if (internal.param.deleteUrl)
                        h += "<a class='icon-grid icon-grid-del' title='删除' onclick='javascript:deleteLigerGridRow(\"" + rowdata.id + "\")'></a> ";
                    return h;
                }
            }
        ],
        dealColumns: function (gridParam) {
            var columns = [];
            var columnsPart1 = gridParam["columns"];
            var columnsPart2 = [];

            columnsPart2 = internal["columns"];

            columns = $.merge(columnsPart1, columnsPart2);
            gridParam["columns"] = columns;
            return gridParam;
        },
        buildGrid: function (gridParam) {
            var param = internal.dealColumns(gridParam);
            $("#grid").removeAttr("ligeruiid").empty();
            return $("#grid").ligerGrid(param);
        }
    };
    window.deleteLigerGridRow = function (id) {
        //是否删除询问
        internal.req.deleteInfo({ url: internal.param.deleteUrl.deleteInfo, "id": id }, function (data) {
            var gridObj = liger.get("grid");
           
            if (gridObj.options.page == 1) {
                gridObj.reload();
            }
            else {
                gridObj.changePage("first");
            }
        });
    };
    window.editLigerGridRow = function (id) {
        var dialogParam = $.extend(true, {}, internal.param.dialogParam.common, internal.param.dialogParam.edit);
        dialogParam.url = dialogParam.url + "?id=" + id + "&type=edit";
        var top = common.getTopWindowDom();
        top.win_base_attr_dialog = $.ligerDialog.open(dialogParam);

    }
    exports.init = function (param) {
        return internal.init(param);
    }
});