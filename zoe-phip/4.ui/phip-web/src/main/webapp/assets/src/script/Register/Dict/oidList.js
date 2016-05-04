/**
 * Created by zhangxingcai on 2016/4/21 0021.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "dict/delOIDInfo",
                    deleteList: "dict/delOIDList"
                },
                tools: {
                    btnbox: {
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                gridParam: {
                    url: 'dict/getOIDList',
                    columns: [
                        {display: '编码', name: 'code', width: 100, align: 'left'},
                        {display: '代码', name: 'codeSystem', width: 200, align: 'left'},
                        {display: '名称', name: 'name', width: 200, align: 'left'},
                        {display: '标准', name: 'dictCode', width: 120, align: 'left'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                dialogParam: {
                    winName: "win_oid_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_oid_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增OID"},
                    //编辑参数
                    edit: {title: "编辑OID"},
                    common: {
                        url: 'dict/view/oiddetail',
                        width: 680,
                        height: 300
                    }
                }
            })
        }

    };
    exports.init = function () {
        internal.init();
    }
});