define(function (require, exports, module) {
    var internal = {
        delIds: '',
        del: require("../../baseGrid/req.js").req,
        init: function () {
            internal.buildGrid();
        },
        grid: null,
        catalogId: "",
        buildGrid: function () {
            var gridParam = $.extend(true, {}, internal.gridParam());
            gridParam.url = webRoot + "SystemMenu/GetMenuCfg";
            $("#catalogGrid").removeAttr("ligeruiid").empty();
            internal.grid = $("#catalogGrid").ligerGrid(gridParam);
        },
        loadData: function (catalogId) {
            if (internal.grid) {
                internal.catalogId = catalogId;
                internal.grid.setParm("catalogId", catalogId);
                internal.grid.loadData();
            }
        },
        gridParam: function () {
            var gridParam = {
                checkbox: false,
                dataAction: "server",
                delayLoad:true,
                tree: {
                    columnId: 'Id',
                    columnName: 'Name',
                    idField: 'Id',
                    parentIDField: 'FkParentMenuId'
                },
                columns: [
                { display: '菜单名称', name: 'Name', isSort: false, align: 'left' },
                //{ display: '菜单路径', name: 'Address', isSort: false, align: 'left' },
                { display: '授权时间', name: 'CreateAt', width: 120, isSort: false, align: 'left', type: 'date' },
                { display: '授权人', name: 'CreateBy', width: 150, isSort: false, align: 'left' },
                {
                    display: '状态',
                    name: 'State',
                    width: 71,
                    align: "center",
                    render: function (rowdata, index, value) {
                        if (value == 1) {
                            return "<span>启用</span>";
                            //return '<span class="btn-switch btn-switch-on"><b class="btn-switch-inner"></b></span>';
                        } else {
                            return "<span style='color:red;'>禁用</span>"
                            //return '<span class="btn-switch btn-switch-off"><b class="btn-switch-inner"></b></span>';
                        }
                    }
                },
                {
                    display: '操作',
                    name: 'KeyCode',
                    isSort: false,
                    width: 60,
                    align: 'center',
                    render: function (rowdata, rowindex, value) {
                        var h = "";
                        //if (rowdata && rowdata["children"] && rowdata["children"].length > 0)
                        //    return "";
                        h += "<a class='icon-grid icon-grid-del' title='取消关联' onclick='javascript:delMenu(\"" + rowdata.CompetenceId + "\",\"" + rowindex + "\")'></a> ";
                        return h;
                    }
                }],
                // allowAdjustColWidth:false,
                width: "100%",
                height: "49.5%",
                heightDiff: 29,
                usePager: false
            };
            return gridParam;
        },
        delMenu: function (ids, hasChildren) {
            var message = hasChildren ? "是否删除该菜单及下级菜单权限关联?" : "是否删除该菜单权限关联?";
            $.ligerDialog.confirm(message, function (yes) {
                if (yes) {
                    var req = new Request("systemMenu/DelMenuAcc");
                    req.post({
                        data: { ids: ids },
                        success: function (data) {
                            internal.loadData(internal.catalogId);
                        }
                    })
                }
            })
        },
        recursiveGetIds: function (nodeData) {
            if (internal.delIds == "") {
                internal.delIds = nodeData["CompetenceId"];
            } else {
                internal.delIds += "," + nodeData["CompetenceId"];
            }
            if (nodeData["children"]) {
                for (var i in nodeData["children"]) {
                    internal.recursiveGetIds(nodeData["children"][i], internal.delIds);
                }
            }
        }
    };
    exports.init = function () {
        internal.init();
    }
    window.delMenu = function (id, rowid) {
        var catalogGridObj = liger.get("catalogGrid");
        var rowdata = catalogGridObj.getRow(rowid);
        var hasChildren = rowdata["children"] ? true : false;
        internal.delIds = "";
        internal.recursiveGetIds(rowdata);
        var ids = internal.delIds;
        internal.delMenu(ids, hasChildren);//删除可能会照成批量删除
    }
    exports.clientEvent = function () {
        internal.clientEvent();
    }
    exports.loadData = function (catalogId) {
        internal.loadData(catalogId);
    }
});