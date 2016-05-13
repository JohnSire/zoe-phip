/**
 * Created by chenzhisen on 2016/5/6.
 */

define(function (require, exports, module) {
    var internal = {
        init: function () {
            var fkSetId = common.getParamFromUrl("fkSetId");
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "dataSet/deleteRsSetColumn",
                    deleteList: "dataSet/deleteRsSetColumnList"
                },
                tools: {
                    btnbox: {
                        'custom': {
                            text: "返回 数据集列表", click: function () {

                                var top = common.getTopWindowDom();
                                var link = webRoot + "dataSet/view/dataSetList";
                                top.frames["mainframe"].location.href = link;
                            }
                        },
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                // reqInfoKey:'patientId',
                gridParam: {
                    url: 'dataSet/getRsSetColumn?id=' + common.getParamFromUrl("fkSetId"),
                    columns: [
                        {display: '字段名称', name: 'fieldName', width: 120, align: 'left'},
                        {display: '字段编码', name: 'fieldCode', width: 120, align: 'left'},
                        {display: '数据元名称', name: 'baseElementName', width: 120, align: 'left'},
                        {display: '数据元编码', name: 'baseElementCode', width: 120, align: 'left'},
                        {display: '字典名称', name: 'dictName', width: 120, align: 'left'},
                        {display: '字典编码', name: 'dictCode', width: 120, align: 'left'},
                        {display: '字段类型', name: 'dataType', width: 120, align: 'left'},

                        {display: '定义', name: 'define', width: 120, align: 'left'},

                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                dialogParam: {


                    winName: "win_ele_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_ele_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增字段"},
                    //编辑参数
                    edit: {title: "编辑字段"},
                    common: {
                        otherUrlParam: function () {
                            return {fkSetId: fkSetId}
                        },
                        url: 'dataSet/view/columnDetail',
                        width: 680,
                        height: 450
                    }
                }
            })
            var name = decodeURI(common.getParamFromUrl("dataSetName"));
            var html = "<p style='margin-top: 9px; '>" + "当前列表为数据集（" + name + "）下的字段</p>"
            $("#gridTools").append(html)
        }

    };
    exports.init = function () {
        internal.init();
    }
});