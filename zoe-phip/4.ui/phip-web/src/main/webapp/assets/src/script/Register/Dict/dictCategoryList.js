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
                    deleteInfo: "dict/delDictCatalogInfo"
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
                    url: 'dict/dictCatalogListQueryPage',
                    columns: [
                        {display: '名称', name: 'name', width: 120, align: 'left'},
                        {display: '编码', name: 'code', width: 120, align: 'left'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePager: false,
                    height: "100%",
                    heightDiff: 29,
                    tree: {
                        columnId: 'id',
                        columnName: 'name',
                        idField: 'id',
                        parentIDField: 'pid'
                    }
                    //width: $("body").innerWidth() - 2,
                    //height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                dialogParam: {
                    winName: "win_dictCategory_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_dictCategory_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增字典分类"},
                    //编辑参数
                    edit: {title: "编辑字典分类"},
                    common: {
                        url: 'dict/view/dictcategorydetail',
                        width: 360,
                        height: 260
                    }
                }
            })
        }

    };
    exports.init = function () {
        internal.init();
    }
});