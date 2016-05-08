/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_dict_detail_dialog",//弹窗对象变量名称
                winCallback: "win_dict_detail_callback",//弹窗回调函数
                getUrl: 'dict/getDictCatalogInfoById',//
                addUrl: 'dict/addDictCatalogInfo',//新增接口Url
                updateUrl: 'dict/updateDictCatalogInfo',//修改接口Url
                dialogParam: {
                    otherUrlParam: ['catalogName', 'catalogId'],
                },
                loadPageEvent: function () {
                    internal.selectList.dialog('dictCatalog', {
                        target: $("#btnParentCatalog"),
                        name: 'pid',
                        parentName: 'parentName',
                        valueField: 'id',
                        displayField: 'name',
                        fkNullContent: '根级节点',
                        selectParam: {
                            storage: function () {
                                var data = [];
                                var pid = common.getParamFromUrl("catalogId");
                                var pname = common.getParamFromUrl("catalogName");
                                if (pid && pid != "null") {
                                    var info = {pid: pid, parentName: decodeURIComponent(pname)};
                                    data.push(info);
                                }
                                return data;
                            }(),
                            isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                            treeVaildMsg: '父级分类不能是其本身!',
                            multiselect: false
                        },
                        buttonsExtend: [{
                            text: '根级节点', onclick: function (item, dialog) {
                                $('input[name="pid"]').val(0);
                                $("#btnParentCatalog").find(".text-line-content").text("根级节点");
                                dialog.close();
                            }
                        }]
                    });
                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});