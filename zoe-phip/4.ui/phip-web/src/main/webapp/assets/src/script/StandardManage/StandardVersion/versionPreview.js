/**
 * Created by zhangxingcai on 2016/5/6 0006.
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
            internal.versionPreviewList();
            internal.versionPreviewTree();
        },
        versionPreviewTree: function () {
            var treeObj = new BaseTree({
                treeId: 'tree',
                btnBox: 'treeBtns',
                reqInfoKey: 'id',//根据哪个值进去获取对象
                renderData: function (data) {
                    return data.result.rows;
                },
                url: {
                    getTreeList: 'dict/dictCatalogTreeQuery'
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
            })
        },
        versionPreviewList: function () {
            internal.dictGrid = new BaseGrid({
                gridId: 'columnGrid',
                toolsBoxId: 'dictTools',
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
            })
        }
    };
    exports.init = function () {
        internal.init();
    }
});