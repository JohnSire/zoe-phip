/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
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


                            }
                        },
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                // reqInfoKey:'patientId',
                gridParam: {
                    url: 'personnel/getXmanList',
                    columns: [
                        {display: '卡号', name: 'cardCode', width: 120, align: 'left'},
                        {display: '姓名', name: 'name', width: 120, align: 'left'},
                        {display: '性别', name: 'sexCodeName', width: 120, align: 'left'},
                        {display: '身份证号', name: 'idNo', width: 120, align: 'left'},
                        {display: '健康档案编号', name: 'healthRecordNo', width: 120, align: 'left'},
                        {
                            display: '出生日期', name: 'birthDate', width: 120, align: 'left', type: 'date',
                            format: 'yyyy-mm-dd'
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
        }

    };
    exports.init = function () {
        internal.init();
    }
});