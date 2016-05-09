/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
define(function (require, exports, module) {
    var internal = {
            selectList: require("{dir}/UtilityModule/SelectList/list"),
            init: function () {
                var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
                var baseAttr = new BaseAttr({
                        winName: "win_dataElement_detail_dialog",//弹窗对象变量名称
                        winCallback: "win_dataElement_detail_callback",//弹窗回调函数
                        getUrl: 'element/getElementList',//
                        addUrl: 'element/addElementList',//新增接口Url
                        updateUrl: 'element/updateElementList',//修改接口Url/修改的接口Url
                        loadPageEvent: function () {
                            //值域选择
                            $("#chooseDict").click(function () {
                                internal.selectList.dialog('dictItem', {
                                    target: $("#chooseDict"),
                                    name: 'nationalityCode',//绑定value值
                                    parentName: 'nationalityName',//绑定name值
                                    displayField: 'name',
                                    valueField: 'code',
                                    selectParam: {
                                        multiselect: false
                                    }
                                });

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