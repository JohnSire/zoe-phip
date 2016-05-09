/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_dictCategory_detail_dialog",//弹窗对象变量名称
                winCallback: "win_dictCategory_detail_callback",//弹窗回调函数
                getUrl: 'dict/getDictCatalogInfoById',//
                addUrl: 'dict/addDictCatalogInfo',//新增接口Url
                updateUrl: 'dict/updateDictCatalogInfo',//修改接口Url
                loadPageEvent: function () {

                    $("#isPk").btnSwitch({name: 'isPk'});
                    $("#isNull").btnSwitch({name: 'isNull'});
                    internal.selectList.dialog('dataElement', {
                        target: $("#btnDataElement"),
                        name: 'pid',
                        parentName: 'parentName',
                        valueField: 'id',
                        displayField: 'name',
                        fkNullContent: '根级节点',
                        selectParam: {
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
                    internal.selectList.dialog('dict', {
                        target: $("#btnDict"),
                        name: 'pid',
                        parentName: 'parentName',
                            valueField: 'id',
                            displayField: 'name',
                            fkNullContent: '根级节点',
                            selectParam: {
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