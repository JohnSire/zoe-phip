/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                deleteUrl: {
                    deleteInfo: "element/delElementInfo",
                    deleteList: "element/delElementList"
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
                    url: "personnel/getXmanList",//element/getElementList
                    columns: [
                        {display: '编码', name: 'cardCode', align: 'left'},//code
                        {display: '名称', name: 'name', align: 'left'},//name
                        {display: '标准来源', name: 'sexCodeName', align: 'left'},//sourceName
                        {display: '分类', name: 'idNo', align: 'left'},//typeName
                        {display: '值域', name: 'healthRecordNo', align: 'left'},//dictName
                        {display: '定义', name: 'detelNofine'},//define
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