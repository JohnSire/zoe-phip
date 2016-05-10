/**
 * Created by chenzhisen on 2016/5/6.
 */

define(function (require, exports, module) {
    var internal = {
        init: function () {
            window.toDataSetList = toDataSetList;
            window.xsl = xsl.show;
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "cda/delCdaInfo",
                    deleteList: "cda/delCdaList"
                },
                tools: {
                    btnbox: {
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                // reqInfoKey:'patientId',
                gridParam: {
                    url: 'cda/getCdaList',
                    columns: [
                        {display: '标识符', name: 'code', width: 120, align: 'left'},
                        {display: '名称', name: 'name', width: 120, align: 'left'},
                        {display: '档案类别', name: 'archiveName', width: 120, align: 'left'},
                        {display: '描述', name: 'descr', width: 120, align: 'left'},
                        {
                            display: '关联数据集', width: 120, render: function (rowdata, rowindex, value) {
                            var h = "";
                            h += "<a class='icon-grid icon-grid-setting' title='配置'"
                                + " onclick='javascript:toDataSetList(\"" + rowdata.id + "\",\"" + rowdata.name + "\")'></a>";
                            ;

                            return h;
                        }
                        },
                        {
                            display: 'XSL配置', width: 120, render: function (rowdata, rowindex, value) {
                            var h = "";
                            h += "<a class='icon-grid icon-grid-setting' title='配置'"
                                + " onclick='javascript:xsl(\"" + rowdata.id + "\",\"" + rowdata.name + "\")'></a>";
                            ;

                            return h;
                        }
                        },
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                dialogParam: {
                    winName: "win_cda_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_cda_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增Cda"},
                    //编辑参数
                    edit: {title: "编辑Cda"},
                    common: {
                        url: 'cda/view/cdaDetail',
                        width: 450,
                        height: 320
                    }
                }
            })
        }


    };

    exports.init = function () {
        internal.init();
    }


    var xsl = {
        show: function () {
            var dialogParam =
            {
                title: "xsl编辑",
                url: 'cda/view/editXsl',
                width: 680,
                height: 480,
                buttons: [
                    {
                        //verifySubmit自己的验证提交状态
                        text: "确定",
                        verifySubmit: true,
                        submitText: '提交中...',
                        onclick: function (item, dialog, submited) {
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
            var top = common.getTopWindowDom();
            top["show"] = common.dialog(dialogParam);
        }

    }

    function toDataSetList(id, name) {
        var top = common.getTopWindowDom();
        var link = webRoot + "cda/view/dataSetList?cdaName=" + name;
        top.frames["mainframe"].location.href = link;
    }
});