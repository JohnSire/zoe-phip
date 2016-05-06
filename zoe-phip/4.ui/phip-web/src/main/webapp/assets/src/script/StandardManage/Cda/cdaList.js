/**
 * Created by chenzhisen on 2016/5/6.
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
                        {display: '标识符', name: 'cardCode', width: 120, align: 'left'},
                        {display: '名称', name: 'name', width: 120, align: 'left'},
                        {display: '档案类别', name: 'sexCodeName', width: 120, align: 'left'},
                        {display: '描述', name: 'idNo', width: 120, align: 'left'},

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
                    add: {title: "新增Cda"},
                    //编辑参数
                    edit: {title: "编辑Cda"},
                    common: {
                        url: 'cda/view/cdaDetail',
                        width: 680,
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