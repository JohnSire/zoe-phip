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
                            target: $("#fkDictId"),
                            name: 'fkDictId',//绑定value值
                            parentName: 'dictName',//绑定name值
                            displayField: 'name',
                            valueField: 'id',
                         
                            selectParam: {
                                //stroage: [{'name': '111', id: '2222'}, {name: '2222', id: '3333'}],
                                multiselect: false
                            }
                        });
                        //数据来源
                        internal.selectList.dialog('source', {

                            target: $("#fkSourceId"),
                            name: 'fkSourceId',
                            parentName: 'sourceName',
                            isTextbox: false,
                            displayField: 'name',
                            valueField: 'id',
                            fkNullContent: '',
                            selectParam: {
                                isTreeVaild: false,//如果是树节点，父节点不能是其本身验证
                                multiselect: true,
                                param: {"type": 1}
                            }
                        });
                        //16个分类
                        internal.selectList.dialog('sysDict', {
                            stroage: {"name": "czs"},
                            target: $("#fkTypeId"),
                            name: 'fkTypeId',//绑定value值
                            parentName: 'typeName',//绑定name值
                            displayField: 'name',
                            valueField: 'Id',
                            selectParam: {
                                multiselect: false,
                                param: {"catalogCode": sysDictConfig.elementType}
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