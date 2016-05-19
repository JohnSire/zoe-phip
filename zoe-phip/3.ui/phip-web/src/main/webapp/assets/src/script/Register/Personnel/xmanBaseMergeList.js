/**
 * Created by linqinghuang on 2016/5/10.
 */
/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
        top: common.getTopWindowDom(),
        init: function () {
            internal.buildGrid();
        },
        buildGrid: function () {
            var data = internal.top.win_xmanbase_merge_selectrows;
            common.grid($("#grid"), {
                columns: [
                    {display: '健康档案编号', name: 'healthRecordNo', width: 250, align: 'left'},
                    {display: '姓名', name: 'name', width: 160, align: 'left'}
                ],
                checkbox: true,
                usePager: false,
                width: $("body").innerWidth() - 2,
                height: '100%',
                data: {rows: data},
                onCheckRow: function (checked, rowdata, rowindex) {
                    for (var rowid in this.records)
                        this.unselect(rowid);
                    this.select(rowindex);
                }
            });
            var chkObj = $("#grid .l-grid-hd-cell-checkbox");
            chkObj.empty().removeClass("l-grid-hd-cell-checkbox");
            internal.top.win_xmanbase_merge_callback = function () {
                var idArray = [];
                var gridObj = common.getGrid("grid");
                var selectRow = gridObj.getSelectedRows();
                if (selectRow.length == 0) {
                    common.jsmsgError("请选择请选择合并后最终保留记录!");
                    return false;
                } else {
                    var newId = selectRow[0]["patientId"];
                    var oldId = "";
                    var data = gridObj.getData();
                    $.each(data, function (index, item) {
                        var patientId = item["patientId"];
                        if (patientId != newId) {
                            oldId = patientId;
                        }
                    });
                    idArray.push(newId)
                    idArray.push(oldId);
                    return idArray;
                }
            }

        }
    };
    exports.init = function () {
        internal.init();
    }
});