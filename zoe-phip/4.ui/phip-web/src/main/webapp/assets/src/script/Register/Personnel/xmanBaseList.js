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
                    deleteInfo: "",
                    deleteList: ""
                },
                tools: {
                    btnbox: {
                        'custom': {
                            text: "合并", click: function () {
                                internal.menuTree();
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
                    url: '',
                    columns: [
                        {display: '卡号', name: 'id', width: 120, align: 'left'},
                        {display: '姓名', name: 'name', width: 120, align: 'left'},
                        {display: '性别', name: 'sex', width: 120, align: 'left'},
                        {display: '身份证号', name: 'idcard', width: 120, align: 'left'},
                        {display: '健康档案编号', name: 'telecom', width: 120, align: 'left'},
                        {
                            display: '出生日期', name: 'birthday', width: 120, align: 'left', type: 'date',
                            format: 'yyyy-mm-dd'
                        },
                        {display: '联系电话', name: 'telecom', width: 120, align: 'left'},
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
        }

    };
    exports.init = function () {
        internal.init();
    }
});