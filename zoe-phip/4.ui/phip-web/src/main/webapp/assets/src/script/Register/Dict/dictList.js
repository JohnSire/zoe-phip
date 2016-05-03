/**
 * Created by zhangxingcai on 2016/4/25 0025.
 */
define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var BaseTree = require("{staticDir}/BaseTree/baseTree");

    var internal = {
        dictGrid: null,
        catalogId: null,
        init: function () {
            internal.dictList();
            internal.dictTree();
        },
        dictTree: function () {
            var treeObj = new BaseTree({
                treeId: 'tree',
                btnBox: 'treeBtns',
                reqInfoKey: 'id',//根据哪个值进去获取对象
                tools: {
                    btns: {'add': false, 'edit': false, 'del': false}
                },
                url: {
                    getTreeList: 'dict/dictCatalogTreeQuery',
                    delTreeInfo: 'dict/delDictCatalogInfo',
                },
                treeParam: {
                    idFieldName: 'id',
                    parentIDFieldName: 'pid',
                    textFieldName: 'name',
                    checkbox: false,
                    //选择
                    onSelect: function (data) {
                        internal.catalogId = data["data"]["id"];
                        var itemGrid = common.getGrid("dictGrid");
                        if (itemGrid.get("dataAction") == "local") {
                            internal.dictGrid.setServer();
                        } else {
                            internal.dictGrid.reload();
                        }
                    },
                    //取消选择
                    onCancelselect: function (data) {
                        var dictGrid = common.getGrid("dictGrid");
                        dictGrid.loadData({rows: [], total: 0});
                    }
                },
                validate: {
                    //点击新增按钮验证
                    add: {
                        isValidate: false,
                        fn: function () {

                        }
                    },
                    //点击编辑按钮验证
                    edit: {
                        isValidate: true,
                        fn: function (data) {
                            if (data["type"] == 0) {
                                common.jsmsgError("分类不能做编辑,选择字典进行编辑！");
                                return false;
                            }
                            return true;
                        }
                    },
                    //点击删除按钮验证
                    del: {
                        isValidate: true,
                        fn: function (data) {
                            if (data["type"] == 0) {
                                common.jsmsgError("分类不能做删除,选择字典进行删除！");
                                return false;
                            }
                            return true;
                        }
                    }
                },
                dialogParam: {
                    winName: "win_dict_detail_dialog",
                    winCallback: "win_dict_detail_callback",
                    titleKey: null,//弹窗标题索引 �编辑用户--张三"其中张三是通过�userName'获取
                    //新增参数
                    add: {
                        title: "新增信息"
                    },
                    //编辑参数
                    edit: {
                        title: "编辑信息"
                    },
                    common: {
                        url: 'dict/view/dictdetail',
                        width: 360,
                        height: 260
                    }
                }
            })
        },
        dictList: function () {
            internal.dictGrid = new BaseGrid({
                gridId: 'dictGrid',
                toolsBoxId: 'dictTools',
                deleteUrl: {
                    deleteInfo: "dict/delDictItemInfo",
                    deleteList: "dict/delDictItemList",
                },
                tools: {
                    btnbox: {
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ],
                    validate: {
                        add: {
                            isValidate: false,
                            fn: function () {
                                alert(1);
                            }
                        },
                    }
                },
                extendParam: function () {
                    return {catalogId: internal.catalogId};
                },
                gridParam: {
                    dataAction: "local",
                    url: 'dict/getDictItemListByCatalogId',
                    columns: [
                        {display: '编码', name: 'code', width: 400, align: 'left'},
                        {display: '名称', name: 'name', width: 400, align: 'left'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    frozen: false,
                    usePage: true,
                    width: "100%",
                    height: "99%"
                },
                dialogParam: {
                    winName: "win_dict_item_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_dict_item_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增字典项信息"},
                    //编辑参数
                    edit: {title: "编辑字典项信息"},
                    common: {
                        url: 'dict/view/dictItemDetail',
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