/**
 * Created by linqinghuang on 2016/4/22.
 */
define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var internal = {
        medicalOrgGrid: null,
        init: function () {
            internal.medicalOrgList();
        },
        medicalOrgList: function () {
            internal.medicalOrgGrid = new BaseGrid({
                gridId: 'medicalOrgGrid',
                toolsBoxId: 'medicalOrgTools',
                deleteUrl: {
                    deleteInfo: "dict/deleteItem",
                    deleteList: "dict/deleteItemList"
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
                    return {categoryId: internal.categoryId};
                },
                gridParam: {
                    dataAction: "local",
                    url: 'dict/getItemPageList',
                    columns: [
                        {display: '项编码', name: 'code', width: 150, align: 'left'},
                        {display: '项名称', name: 'name', width: 400, align: 'left'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    frozen: false,
                    usePage: true,
                    width: "100%",
                    height: "99%"//$("body").innerHeight() - $("#dictItemTools").outerHeight() - 76//500
                },
                dialogParam: {
                    winName: "win_dict_item_dialog",//弹窗对象变量名称
                    winCallback: "win_dict_item_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增字典项信息"},
                    //编辑参数
                    edit: {title: "编辑字典项信息"},
                    common: {
                        otherUrlParam: function () {
                            return {fkSystemDictCategoryId: internal.fkSystemDictCategoryId}
                        },
                        url: 'dict/view/item',
                        width: 590,
                        height: 200
                    }
                }
            })
        }
    };
    exports.init = function () {
        internal.init();
    }
});