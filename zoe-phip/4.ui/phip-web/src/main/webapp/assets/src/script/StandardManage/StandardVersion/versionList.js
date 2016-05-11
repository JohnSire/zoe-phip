/**
 * Created by chenzhisen on 2016/5/6.
 */
/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            window.xsl = xsl.show;
            window.versionPreview = versionPreview.Preview;
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                deleteUrl: {
                    deleteInfo: "version/delVersionInfo",
                    deleteList: "version/delVersionList"
                },
                tools: {
                    btnbox: {
                        'custom': {
                            text: "本级平台标准预览", click: function () {
                            }
                        },
                        'add': true,
                        'del': true

                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                gridParam: {
                    url: "version/getVersionList",
                    columns: [
                        {display: '版本号', name: 'version', align: 'left', width: "10%"},
                        {display: '名称', name: 'name', align: 'left'},
                        {display: '编码', name: 'code', align: 'left', width: "20%"},

                        {display: '生成时间', name: 'ceateAt', align: 'center', type: "date", width: "10%",},
                        {
                            display: '版本预览', width: 120, render: function (rowdata, rowindex, value) {
                            var h = "";
                            h += "<a class='icon-grid icon-grid-search' title='版本预览'"
                                + " onclick='javascript:versionPreview(\"" + rowdata.id + "\")'></a>";
                            ;

                            return h;
                        }
                        },

                        {
                            display: '关联设置', width: 120, render: function (rowdata, rowindex, value) {
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
                //弹出参数配置
                dialogParam: {
                    winName: "win_version_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_version_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增标准版本"},
                    //编辑参数
                    edit: {title: "编辑标准版本"},
                    common: {
                        url: 'version/view/versiondetail',
                        width: 450,
                        height: 350
                    }
                }
            });

        }
    };
    var versionPreview = {
        Preview: function () {
            var dialogParam =
            {
                title: "标准版本预览",
                url: 'version/view/versionPreview',
                width: 680,
                height: 480,
                buttons: [

                    {
                        text: "关闭",
                        onclick: function (item, dialog) {
                            dialog.close();
                        }
                    }
                ]
            }
            var top = common.getTopWindowDom();
            top["Preview"] = common.dialog(dialogParam);
        },
    }
    var xsl = {
        show: function () {
            var dialogParam =
            {
                title: "平台标准版本",
                url: 'version/view/versionSetRs',
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

    exports.init = function () {
        internal.init();
    }
});