/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var internal = {
        searchbox: require("./searchbox").searchbox,
        itemsbox: require("./itemsbox").itemsbox,
        stroage: [],
        multiselect: false,
        displayField: "",
        valueField: "",
        //生成表格
        buildGrid: function (options) {
            var gridParam = options["gridParam"];
            internal.multiselect = options["multiselect"];//是否多选
            internal.displayField = options["displayField"];
            internal.valueField = options["valueField"];
            internal.storage = options["stroage"];
            gridParam["onCheckRow"] = internal.onChecked;
            gridParam["onCheckAllRow"] = internal.onCheckAllRow;
            gridParam["isChecked"] = internal.isChecked;
            var gridObj = $("#grid").ligerGrid(gridParam);
            //如果是非多选的，则移除全选框
            if (!internal.multiselect) {
                var chkObj = $("#grid .l-grid-hd-cell-checkbox");
                chkObj.empty().removeClass("l-grid-hd-cell-checkbox");
            }
            //初始化时追加选中的值
            internal.itemsbox.addItemList(internal.storage, internal.displayField, internal.valueField, function (data) {
                internal.unChecked(data);
            });
            internal.searchbox.search(options, function (data) {
                var gridObj = common.getGrid("grid");
                if (gridObj) {
                    $.each(data, function (index, item) {
                        gridObj.setParm(index, item);
                    })
                    gridObj.reload();
                }

            })
        },
        //是否选中
        isChecked: function (rowdata) {
            var stroage = internal.itemsbox.getItemsData();
            for (var i = 0; i < stroage.length; i++) {
                var index = internal["valueField"];
                if (rowdata[index] == stroage[i][index]) {
                    return true;
                }
            }
            return false;
        },
        //点击选中
        onChecked: function (checked, rowdata, rowindex) {
            if (checked) {
                if (!internal.multiselect) {
                    for (var rowid in this.records) {
                        this.unselect(rowid);
                        internal.itemsbox.removeItem(this.records[rowid], internal.valueField);
                    }
                }
                internal.recursiveChecked(rowdata);
                this.select(rowindex);
            } else {
                this.unselect(rowindex);
                internal.removeRecursiveChecked(rowdata);
            }
        },
        onCheckAllRow: function (checked) {
            if (checked) {
                var gridObj = common.getGrid("grid");
                var rowsdata = gridObj.getSelectedRows();
                internal.itemsbox.addItemList(rowsdata, internal.displayField, internal.valueField,
                    function (data) {
                        internal.unChecked(data);
                    });

            } else {
                var gridObj = common.getGrid("grid");
                var rowsdata = gridObj.getData();
                internal.itemsbox.removeItemList(rowsdata, internal.valueField);
            }
        },
        unChecked: function (data) {
            var gridObj = common.getGrid("grid");
            var rows = gridObj.getSelectedRows();

            for (var i = 0; i < rows.length; i++) {
                var curObj = rows[i];
                if (curObj[internal.valueField] == data[internal.valueField]) {
                    gridObj.unselect(curObj);
                    break;
                }
            }
        },
        //递归选中节点（在树形列表下用到）
        recursiveChecked: function (nodeData) {
            internal.itemsbox.addItem(nodeData, internal.displayField, internal.valueField, function (data) {
                //点击移除按钮，取消选中

                internal.unChecked(data);
            });
            if (nodeData["children"]) {
                for (var i in nodeData["children"]) {
                    internal.recursiveChecked(nodeData["children"][i]);
                }
            }
        },
        //递归移除选中节点（在树形选择列表下用到）
        removeRecursiveChecked: function (nodeData) {
            internal.itemsbox.removeItem(nodeData, internal.valueField);
            if (nodeData["children"]) {
                for (var i in nodeData["children"]) {
                    internal.removeRecursiveChecked(nodeData["children"][i]);
                }
            }
        }
    };
    exports.grid = {
        build: internal.buildGrid
    }
});