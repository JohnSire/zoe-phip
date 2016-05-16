/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var sysDictConfig = require("{dir}/JsConfig/sysDictConfig").sysDictConfig;
            var baseAttr = new BaseAttr({
                    winName: "win_dataElement_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_dataElement_detail_callback",//弹窗回调函数
                    getUrl: 'element/getElementInfo',//
                    addUrl: 'element/addElementInfo',//新增接口Url
                    updateUrl: 'element/updateElementInfo',//修改接口Url
                    loadPageEvent: function () {
                        //值域选择
                        internal.selectList.dialog('domain', {
                            target: $("#fkdictName"),
                            name: 'dictCode',//绑定value值
                            parentName: 'dictName',//绑定name值
                            displayField: 'name',
                            valueField: 'code',
                            selectParam: {
                                multiselect: false
                            }
                        });
                        //标准来源
                        $("#fkSourceId").select({
                            name: 'code',
                            display: 'name',
                            ajaxParam: {
                                url: 'source/getSourceList',//url 请求的地址
                            },
                            value: 'code',//值
                            text: 'name'//展示的内容
                        });
                        //分类
                        $("#fkTypeId").select({
                            name: 'code',
                            display: 'name',
                            ajaxParam: {
                                url: 'dict/getItemList ',//url 请求的地址
                                data: {catalogCode: sysDictConfig.elementType}
                            },
                            renderData: function (data) {
                                return data.result;
                            },
                        internal.selectList.dialog('sysDict', {
                            target: $("#fkTypeId"),
                            name: 'dictCode',//绑定value值
                            parentName: 'dictName',//绑定name值
                            displayField: 'name',
                            valueField: 'code',
                            selectParam: {
                                multiselect: false,
                                param: {"categoryId": "4D01A80C08CB4ACA88679457A3358A94"}
                            }
                        });
                    }
                }
            );
        },
    };
    exports.init = function () {
        internal.init();
    }
})
;