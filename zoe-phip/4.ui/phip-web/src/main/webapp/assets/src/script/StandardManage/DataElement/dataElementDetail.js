/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
define(function (require, exports, module) {
    var internal = {
            selectList: require("{dir}/UtilityModule/SelectList/list"),
            init: function () {
                var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
                var oidCodeConfig = require("{dir}/JsConfig/oidCodeConfig").oidCodeConfig;
                var baseAttr = new BaseAttr({
                        winName: "win_dataElement_detail_dialog",//弹窗对象变量名称
                        winCallback: "win_dataElement_detail_callback",//弹窗回调函数
                        getUrl: 'element/getElementList',//
                        addUrl: 'element/addElementList',//新增接口Url
                        updateUrl: 'element/updateElementList',//修改接口Url/修改的接口Url
                        loadPageEvent: function () {
                            //值域选择
                            $("#chooseDict").click(function () {
                                internal.selectList.dialog('domain', {
                                    target: $("#chooseDict"),
                                    name: 'dictCode',//绑定value值
                                    parentName: 'dictName',//绑定name值
                                    displayField: 'name',
                                    valueField: 'code',
                                    selectParam: {
                                        multiselect: false
                            }
                                });
                            });
                            $("#fkSourceId").select({
                                name: 'code',
                                display: 'name',
                                ajaxParam: {
                                    url: 'source/getSourceList',//url 请求的地址
                                },
                                value: 'code',//值
                                text: 'name'//展示的内容
                            });
                            //婚姻状况
                            $("#fkTypeId").select({
                                name: 'code',
                                display: 'name',
                                ajaxParam: {
                                    url: 'dict/dictCatalogListQueryPage',//url 请求的地址
                                },
                                value: 'code',//值
                                text: 'name'//展示的内容

                            });
                        }

                    }
                );
            },
        }
        ;
    exports.init = function () {
        internal.init();
    }
})
;