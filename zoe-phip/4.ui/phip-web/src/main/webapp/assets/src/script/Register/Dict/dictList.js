/**
 * Created by zhangxingcai on 2016/4/25 0025.
 */
define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var BaseTree = require("{staticDir}/BaseTree/baseTree");

    var internal = {
        dictGrid: null,
        catalogId: null,
        catalogCode: null,
        catalogName: null,
        catalogType: null,
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
                    btns: {'add': true, 'edit': true, 'del': true}
                },
                url: {
                    getTreeList: 'dict/dictCatalogTreeQuery',
                    delTreeInfo: 'dict/delDictCatalogInfo',
                },
                renderData: function (data) {
                    return data.result.rows;
                },
                treeParam: {
                    idFieldName: 'id',
                    parentIDFieldName: 'pid',
                    textFieldName: 'name',
                    checkbox: false,
                    nodeWidth: 200,
                    //选择
                    onSelect: function (data) {
                        internal.catalogId = data["data"]["id"];
                        internal.catalogName = data["data"]["name"];
                        internal.CatalogType = data["data"]["type"];
                        var itemGrid = common.getGrid("dictGrid");
                        if (itemGrid.get("dataAction") == "local") {
                            internal.dictGrid.setServer();
                        } else {
                            internal.dictGrid.reload();
                        }
                    }
                },
                validate: {
                    //字典分能作为父节点
                    add: {
                        isValidate: true,
                        fn: function (data) {
                            if (data["type"] == 1) {
                                common.jsmsgError("父节点不能是字典，请选择分类节点！");
                                return false;
                            }
                            return true;
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
                        otherUrlParam: function () {
                            return {
                                catalogId: internal.catalogId,
                                catalogName: internal.catalogName
                            }
                        },
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
                    deleteList: "dict/delDictItemList"
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
                    return {catalogId: internal.catalogId};
                },
                gridParam: {
                    dataAction: "local",
                    url: 'dict/getDictItemListByCatalogId',
                    columns: [
                        {display: '编码', name: 'code', width: 220, align: 'left'},
                        {display: '名称', name: 'name', width: 380, align: 'left'},
                        {display: '操作', isSort: false, width: 100, icons: ['edit', 'del']}
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
                        otherUrlParam: function () {
                            return {
                                fkCatalogId: internal.catalogId,
                                fkCatalogName: internal.catalogName,
                                fkCatalogType: internal.CatalogType,
                            }
                        },
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