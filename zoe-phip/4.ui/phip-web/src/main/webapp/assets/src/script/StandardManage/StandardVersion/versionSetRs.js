/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var BaseTree = require("{staticDir}/BaseTree/baseTree");

    var internal = {
        init: function () {
            internal.tab();
            internal.dictList();
            internal.dictTree();
        },
        tab: function () {
            $('.y-dialog-menu .lists .list').click(function () {
                $(this).addClass('active').siblings().removeClass('active');
                $('.y-dialog-menu-wrapper>div:eq(' + $(this).index() + ')').show().siblings().hide();
            })
        },
        dictTree: function () {
            var treeObj = new BaseTree({
                treeId: 'dataSet',
                reqInfoKey: 'id',//根据哪个值进去获取对象
                url: {
                    getTreeList: 'dict/dictCatalogTreeQuery'
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
            })
        },
        dictList: function () {
            internal.dictGrid = new BaseGrid({
                gridId: 'dataSetGrid',
                toolsBoxId: 'dictTools',
                extendParam: function () {
                    return {catalogId: internal.catalogId};
                },
                gridParam: {
                    dataAction: "local",
                    url: 'dict/getDictItemListByCatalogId',
                    columns: [
                        {display: '编码', name: 'code', width: 165, align: 'left'},
                        {display: '名称', name: 'name', width: 160, align: 'left'}
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