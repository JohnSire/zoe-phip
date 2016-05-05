/**
 * Created by linqinghuang on 2016/4/21.
 */
define(function (require, exports, module) {
    var internal = {
        path: require("./path").path,
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            internal.path.init();
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "area/delAreaInfo",
                    deleteList: "area/delAreaList"
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
                extendParam: function () {
                    return {id: "86"};
                },
                gridParam: {
                    url: 'area/getAreaList',
                    columns: [
                        {display: '代码', name: 'code', width: 160, align: 'left'},
                        {display: '名称', name: 'name', width: 180, align: 'left'},
                        {
                            display: '成立时间',
                            name: 'buildTime',
                            width: 120,
                            align: 'left',
                            type: 'date',
                            format: 'yyyy-MM-dd'
                        },
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
                    /*width: $("body").innerWidth() - 2,
                     height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500*/
                },
                dialogParam: {
                    winName: "win_area_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_area_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增行政区划"},
                    //编辑参数
                    edit: {title: "编辑行政区划"},
                    common: {
                        url: 'area/view/areadetail',
                        width: 680,
                        height: 450
                    }
                }
            })
        }


    };
    exports.init = function () {
        internal.init();
    }
});