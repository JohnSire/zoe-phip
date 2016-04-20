/**
 * Created by linqinghuang on 2016/3/31.
 */


define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var internal = {
        categoryList: null,
        itemList: null,
        fkSystemDictCategoryId: null,
        categoryId: null,
        init: function () {
            internal.itemList();
            internal.categoryList();
        },
        categoryList: function () {
            var categoryGrid = new BaseGrid({
                gridId: 'dictCategoryGrid',
                toolsBoxId: 'categoryTools',
                tools: {
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                gridParam: {
                    url: 'dict/getCategoryList',
                    columns: [
                        {display: '字典编码', name: 'code', isSort: false, width: 160, align: 'left'},
                        {display: '字典名称', name: 'name', isSort: false, width: 200, align: 'left'},
                        {display: '描述', name: 'descr', isSort: false, width: 220, align: 'left'}
                    ],
                    isSelected: function (rowdata) {
                        if (rowdata["__id"] == 'r1001') {
                            return true;
                        }
                    },
                    onSelectRow: function (rowdata, rowid, rowobj) {
                        internal.fkSystemDictCategoryId = rowdata.id;
                        internal.categoryId = rowdata.id;
                        var itemGrid = liger.get("dictItemGrid");
                        if (itemGrid.get("dataAction") == "local") {
                            internal.itemGrid.setServer();
                        } else {
                            internal.itemGrid.reload();
                        }
                    },
                    checkbox: false,
                    width: "100%",
                    height: "99%"//500
                }
            });
        },
        itemList: function () {
            internal.itemGrid = new BaseGrid({
                gridId: 'dictItemGrid',
                toolsBoxId: 'dictItemTools',
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
    }
    exports.init = function () {
        internal.init();
    }
});