/**
 * Created by chenzhisen on 2016/5/6.
 */

define(function (require, exports, module) {
    var top = common.getTopWindowDom();
    var internal = {
        init: function () {
            window.toColumnList = toColumnList;
            window.toChildSet = toChildSet;

            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "dataSet/deleteSetInfo",
                    deleteList: "dataSet/deleteSetList"
                },
                tools: {
                    btnbox: {
                        'add': true,
                        'del': true,
                        'import': true,
                        'export': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                // reqInfoKey:'patientId',
                gridParam: {
                    url: 'dataSet/getSetList',
                    columns: [
                        {display: '编码', name: 'code', width: 250, align: 'left'},
                        {display: '名称', name: 'name', width: 250, align: 'left'},
                        //{display: '标准来源名称', name: 'sexCodeName', width: 120, align: 'left'},
                        {display: '描述', name: 'descr', width: 250, align: 'left'},
                        {
                            display: '关联字段', width: 120, render: function (rowdata, rowindex, value) {
                            var h = "";
                            h += "<a class='icon-grid icon-grid-setting' title='配置'"
                                + " onclick='javascript:toColumnList(\"" + rowdata.id + "\",\"" + rowdata.name + "\")'></a>";
                            ;

                            return h;
                        }
                        },
                        {
                            display: '子级数据集', width: 120, render: function (rowdata, rowindex, value) {
                            var h = "";
                            h += "<a class='icon-grid icon-grid-search' title='查看'"
                                + " onclick='javascript:toChildSet(\"" + rowdata.id + "\",\"" + rowdata.name + "\")'></a>";
                            ;

                            return h;
                        }
                        },
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    frozen: false,
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                dialogParam: {
                    winName: "win_set_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_set_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增数据集"},
                    //编辑参数
                    edit: {title: "编辑数据集"},
                    common: {
                        url: 'dataSet/view/dataSetDetail',
                        width: 680,
                        height: 300
                    }
                }
            });
            internal.event();

        },
        event:function(){
            $(".btn-import").click(function () {
                var dialogParam =
                {
                    title: "数据集导入",
                    url: 'dataSet/view/import',
                    width: 400,
                    height: 280,
                    buttons: [
                        {
                            //verifySubmit自己的验证提交状态
                            text: "确定",
                            verifySubmit: true,
                            submitText: '提交中...',
                            onclick: function (item, dialog, submited) {
                                top.upload(submited);
                            }

                        },
                        {
                            text: "取消",
                            onclick: function (item, dialog) {
                                dialog.close();
                            }
                        }
                    ]
                }

                top["show"] = common.dialog(dialogParam);
            });
        }
    };

    function toColumnList(id, name) {
        var top = common.getTopWindowDom();
        var link = webRoot + "dataSet/view/columnList?fkSetId=" + id + "&dataSetName=" + name;
        top.frames["mainframe"].location.href = link;

    }

    function toChildSet(id, name) {
        var top = common.getTopWindowDom();
        var link = webRoot + "dataSet/view/childSet?fkSetId=" + id + "&dataSetName=" + name;
        top.frames["mainframe"].location.href = link;

    }

    exports.init = function () {
        internal.init();
    }
});