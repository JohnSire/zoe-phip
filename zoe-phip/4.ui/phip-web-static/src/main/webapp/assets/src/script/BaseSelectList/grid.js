/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var internal = {
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
            $("#grid").ligerGrid(gridParam);
            //如果是非多选的，则移除全选框
            if (!internal.multiselect) {
                var chkObj = $("#grid .l-grid-hd-cell-checkbox");
                chkObj.empty().removeClass("l-grid-hd-cell-checkbox");
            }
            internal.itemsbox.addItemList(internal.storage,internal.displayField,internal.valueField,function(data){
               internal.unChecked(data);
            });

        },
        //是否选中
        isChecked: function (rowdata) {
            for (var i = 0; i < internal.storage.length; i++) {
                var index=internal["valueField"];
                if (rowdata[index] == internal.storage[i][index]) {
                    return true;
                }
            }
            return false;
        },
        onChecked: function (checked, rowdata, rowindex) {
            if (checked) {
                if (!internal.multiselect) {
                    for (var rowid in this.records) {
                        this.unselect(rowid);
                        internal.itemsbox.removeItem(this.records[rowid],internal.valueField);
                    }
                }
                this.select(rowindex);
                internal.itemsbox.addItem(rowdata, internal.displayField, internal.valueField,function(data){
                   internal.unChecked(data);
                });
            } else {
                this.unselect(rowindex);
                internal.itemsbox.removeItem(rowdata,internal.valueField);
            }
        },
        onCheckAllRow: function (checked) {
            if (checked) {
                var gridObj=common.getGrid("grid");
                var rowsdata=gridObj.getSelectedRows();
                internal.itemsbox.addItemList(rowsdata,internal.displayField,internal.valueField,
                   function(data){
                       internal.unChecked(data);
                   });

            } else {
                var gridObj=common.getGrid("grid");
                var rowsdata=gridObj.getData();
                internal.itemsbox.removeItemList(rowsdata,internal.valueField);
            }
        },
        unChecked:function(data){
            var gridObj=common.getGrid("grid");
            var rows=gridObj.getSelectedRows();
            for (var i = 0; i < rows.length; i++) {
                var curObj = rows[i];
                if (curObj[internal.valueField] == data[internal.valueField]) {
                    gridObj.unselect(curObj);
                    break;
                }
            }
        },
        //递归选中节点（在树形列表下用到）
        recursiveChecked: function () {

        },
        //递归移除选中节点（在树形选择列表下用到）
        removeRecursiveChecked: function () {

        }
    };
    exports.grid = {
        build: internal.buildGrid
    }
});