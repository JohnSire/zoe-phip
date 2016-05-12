/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
        req: require("./req").req,
        top: common.getTopWindowDom(),
        init: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "personnel/delXmanInfo",
                    deleteList: "personnel/delXmanList"
                },
                tools: {
                    btnbox: {
                        'custom': {
                            text: "合并", click: function () {
                                internal.merge();
                            }
                        },
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                gridParam: {
                    url: 'personnel/getXmanList',
                    columns: [
                        {display: '个人编码', name: 'patientId', width: 120, align: 'left'},
                        {display: '卡号', name: 'cardCode', width: 120, align: 'left'},
                        {display: '姓名', name: 'name', width: 120, align: 'left'},
                        {display: '性别', name: 'sexCodeName', width: 120, align: 'left'},
                        {display: '身份证号', name: 'idNo', width: 120, align: 'left'},
                        {display: '健康档案编号', name: 'healthRecordNo', width: 120, align: 'left'},
                        {
                            display: '出生日期', name: 'birthDate', width: 120, align: 'left', type: 'date',
                            format: 'yyyy-MM-dd'
                        },
                        {display: '联系电话', name: 'telNo', width: 120, align: 'left'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                dialogParam: {
                    winName: "win_xmanbase_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_xmanbase_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增个人信息"},
                    //编辑参数
                    edit: {title: "编辑个人信息"},
                    common: {
                        url: 'personnel/view/xmanbasedetail',
                        width: 1000,
                        height: 580
                    }
                }
            })
        },
        merge: function () {
            var gridObj = common.getGrid("grid");
            var selectRows = gridObj.getSelectedRows();
            if (selectRows.length == 2) {
                internal.top.win_xmanbase_merge_selectrows = selectRows;
                common.dialog({
                    title: '选择合并后保留记录',
                    url: 'personnel/view/xmanbasemerge',
                    width: 500,
                    height: 260,
                    buttons: [
                        {
                            text: '合并',
                            onclick: function (item, dialog) {
                                var idArray = internal.top.win_xmanbase_merge_callback();
                                if (idArray) {
                                    var newId = idArray[0], oldId = idArray[1];
                                    internal.req.xmanBaseMerge(newId, oldId, function (data) {
                                        if (data.isSuccess) {
                                            var gridObj = common.getGrid("grid");
                                            gridObj.reload();
                                            dialog.close();
                                        }

                                    })
                                }
                            }
                        },
                        {
                            text: '取消',
                            onclick: function (item, dialog) {
                                dialog.close();
                            }
                        }]
                })
            } else if (selectRows.length < 2) {
                common.jsmsgError("请选择两条记录进行合并!");
            } else {
                common.jsmsgError("只能选择两条记录进行合并!");
            }

        }

    };
    exports.init = function () {
        internal.init();
    }
});