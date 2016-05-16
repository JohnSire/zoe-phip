/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                deleteUrl: {
                    deleteInfo: "source/delSourceInfo",
                    deleteList: "source/delSourceList"
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
                    url: "source/getSourceList",
                    columns: [
                        {display: '标准来源标识', name: 'code', align: 'left', width: 150},
                        {display: '标准来源名称', name: 'name', align: 'left', width: 380},
                        {
                            display: '来源类型', name: 'standardType', width: 150, align: 'left',
                            render: function (rowdata, rowindex, value) {
                                switch (rowdata.standardType) {
                                    case -1:
                                        return "";
                                    case 1:
                                        return "数据元来源";
                                    case 0:
                                        return "数据集来源";
                                    case 2:
                                        return "CDA来源";
                                }
                            }
                        },
                        {display: '描述', name: 'descr', width: 380, align: 'left'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                //弹出参数配置
                dialogParam: {
                    winName: "win_source_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_source_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增标准来源"},
                    //编辑参数
                    edit: {title: "编辑标准来源"},
                    common: {
                        url: 'source/view/sourcedetail',
                        width: 560,
                        height: 360
                    }
                }
            });

        }
    };
    exports.init = function () {
        internal.init();
    }
});