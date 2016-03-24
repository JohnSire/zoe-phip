define(function (require, exports, module) {
    var internal = {
        req: require("./req.js").req,
        init: function () {
            internal.buildSysDictCategoryGrid();
        },
        buildSysDictCategoryGrid: function () {
            if (internal.__gridDictCategory) {
                internal.__gridDictCategory.setParm('keyWord', $("#txtDictCategoryKeyWod").val() || "");
                internal.__gridDictCategory.loadData();
                return;
            }

            var gridParam = $.extend(true, {}, internal.gridParam());
            gridParam.checkbox = false;
            gridParam.url = "getCategoryList";
            gridParam.parms = {keyWord: $("#txtDictCategoryKeyWod").val() || ""};
            gridParam.onUnSelectRow = function (rowdata, rowid, rowobj) {
                internal.buildSysDictItemGrid('', "");
            };
            gridParam.onSelectRow = function (data, rowid, rowdata) {
                var gridObj = liger.get("dictItemGrid");
                if (gridObj) {
                    gridObj.set("page", 1);
                    gridObj.set("newPage", 1);
                }
                internal.buildSysDictItemGrid(data.Id, "");
            };
            gridParam.columns = [
                {display: '字典编码', name: 'Code', isSort: false, width: 180, align: 'left'},
                {display: '字典名称', name: 'Name', isSort: false, width: 220, align: 'left'},
                {display: '描述', name: 'Descr', isSort: false, align: 'left'}
            ];
            gridParam.isSelected = function (rowdata) {
                if (rowdata["__id"] == 'r1001')
                    return true;
            }

            internal.__gridDictCategory = $("#dictCategoryGrid").ligerGrid(gridParam);
        },
        buildSysDictItemGrid: function (categoryId, keyWord) {
            if (internal.__gridDictItem) {
                internal.__gridDictItem.setParm('categoryId', categoryId);
                internal.__gridDictItem.setParm('keyWord', keyWord);
                internal.__gridDictItem.loadData();
                return;
            }
            var gridParam = $.extend(true, {}, internal.gridParam());
            gridParam.url = webRoot + "SystemDict/GetSysDictItemList";
            gridParam.parms = {categoryId: categoryId, keyWord: keyWord};
            gridParam.columns = [
                {display: '项编码', name: 'code', width: 150, align: 'left'},
                {display: '项名称', name: 'name', width: 400, align: 'left'},
                {
                    display: '操作', isSort: false, width: 120, render: function (rowdata, rowindex, value) {
                    var h = "";
                    h += "<a class='icon-grid icon-grid-edit' title='编辑'  onclick='javascript:editLigerGridRow(\"" + rowdata.Id + "\")'></a> ";
                    h += "<a class='icon-grid icon-grid-del'  title='删除' onclick='javascript:deleteLigerGridRow(\"" + rowdata.Id + "\")'></a> ";
                    return h;
                }
                }
            ];


            internal.__gridDictItem = $("#dictItemGrid").ligerGrid(gridParam);
        },
        gridParam: function (param) {
            var gridParam = {
                checkbox: true,
                dataAction: "server",
                columns: [],
                pageSize: 30,
                width: '100%',
                height: '99%',
                usePage: true
            };
            return gridParam;
        },
        dictCategoryReload: function () {
            //$("#dictCategoryGrid").removeAttr("ligeruiid").empty();
            internal.buildSysDictCategoryGrid();
        },
        dictItemReload: function (categoryId, keyWord) {
            //$("#dictItemGrid").removeAttr("ligeruiid").empty();
            internal.buildSysDictItemGrid(categoryId, keyWord);
        }
    };
    window.deleteLigerGridRow = function (id) {
        $.ligerDialog.confirm('是否删除该记录？', function (yes) {
            if (yes) {
                internal.req.dictItem.deleteInfo(id, function (data) {
                    var gridObj = liger.get("dictItemGrid");
                    gridObj.reload();
                })
            }
        })
    };
    window.editLigerGridRow = function (id) {
        var gridObj = liger.get("dictCategoryGrid");
        var rowdata = gridObj.getSelected();
        var top = common.getTopWindowDom();
        top.win_dict_item_dialog = $.ligerDialog.open({
            title: '新增字典项',
            url: webRoot + 'SystemDict/SysDictItemDetail?type=edit&&categoryId=' + rowdata["Id"] + '&&id=' + id,
            width: 590,
            height: 200,
            buttons: [
                {
                    text: '确定', onclick: function (item, dialog) {
                    var top = common.getTopWindowDom();
                    if (typeof (top.win_dict_item_callback) == "function") {
                        var reloadGrid = function () {
                            var gridObj = liger.get("dictItemGrid");
                            gridObj.reload();
                        };
                        top.win_dict_item_callback(reloadGrid);
                    }
                }
                },
                {
                    text: '取消', onclick: function (item, dialog) {
                    dialog.close();
                }
                }
            ]
        })
    }
    exports.init = function () {
        internal.init();
    }
    exports.dictCategoryReload = function () {
        internal.dictCategoryReload();
    };
    exports.dictItemReload = function (categoryId, keyWord) {
        internal.dictItemReload(categoryId, keyWord);
    }
});