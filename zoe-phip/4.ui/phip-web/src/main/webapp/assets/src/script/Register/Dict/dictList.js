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
                    icons: ['add', 'edit', 'del'],
                    btnbox: {
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },

                dialogParam: {
                    winName: "win_oid_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_oid_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增字典"},
                    //编辑参数
                    edit: {title: "编辑字典"},
                    common: {
                        url: 'dict/view/dictdetail',
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