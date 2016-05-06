/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                deleteUrl: {
                    deleteInfo: "",
                    deleteList: ""
                },
                tools: {
                    btnbox: {
                        'add': true,
                        'del': true,
                        'import': true,
                        'export': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                gridParam: {
                    url: "",
                    columns: [
                        {display: '编码', name: 'Code', align: 'left'},
                        {display: '名称', name: 'Name', align: 'left'},
                        {display: '标准来源', name: 'SourceName', align: 'left'},
                        {display: '分类', name: 'TypeName', align: 'left'},
                        {display: '值域', name: 'DictName', align: 'left'},
                        {display: '定义', name: 'Define'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                //弹出参数配置
                dialogParam: {
                    winName: "win_dataElement_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_dataElement_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增数据元"},
                    //编辑参数
                    edit: {title: "编辑数据元"},
                    common: {
                        url: 'element/view/dataelementdetail',
                        width: 550,
                        height: 420
                    }
                }
            });

        }
    };
    exports.init = function () {
        internal.init();
    }
});