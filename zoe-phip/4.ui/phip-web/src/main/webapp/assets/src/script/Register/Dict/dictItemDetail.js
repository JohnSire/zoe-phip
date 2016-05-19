/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_dict_item_detail_dialog",//弹窗对象变量名称
                winCallback: "win_dict_item_detail_callback",//弹窗回调函数
                getUrl: 'dict/getDictItemInfoById',//
                addUrl: 'dict/addDictItemInfo',//新增接口Url
                updateUrl: 'dict/updateDictItemInfo',//修改接口Url
                dialogParam: {
                    otherUrlParam: ['fkCatalogName', 'fkCatalogId', 'fkCatalogType'],
                },
                loadPageEvent: function () {
                    internal.selectList.dialog('dict', {
                        target: $("#btnParentCatalog"),
                        name: 'fkCatalogId',
                        parentName: 'fkCatalogName',
                        valueField: 'id',
                        displayField: 'name',
                        selectParam: {
                            multiselect: false,
                            storage: function () {
                                var data = [];
                                var fkCatalogId = common.getParamFromUrl("fkCatalogId");
                                var fkCatalogName = common.getParamFromUrl("fkCatalogName");
                                var fkCatalogType = common.getParamFromUrl("fkCatalogType");
                                if (fkCatalogType == 1) {
                                    var info = {
                                        fkCatalogId: fkCatalogId,
                                        fkCatalogName: decodeURIComponent(fkCatalogName)
                                    };
                                    data.push(info);
                                }
                                return data;
                            }(),
                        }
                    });
                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});