/**
 * Created by chenzhisen on 2016/5/10.
 */

define(function (require, exports, module) {
    var internal = {

        init: function () {
            internal.tab();
            internal.showEditor();
        },
        tab: function () {

            //预览
            $("#xslToPreview").click(function () {
                internal.xslToPreview();

            });

            //结构化
            $("#xslToStruct").click(function () {
                internal.xslToStruct();

            });

            $("#xsltab .list").click(function () {

                $("#xsltab .list").removeClass("active");
                $(this).addClass("active");
            });
        },
        xslToPreview: function () {
            var dialogParam =
            {
                title: "预览",
                url: 'cda/view/previewXsl',
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
            top["show"] = common.dialog(dialogParam);
        },
        xslToStruct: function () {
            var dialogParam =
            {
                title: "结构化视图",
                url: 'cda/view/showXSLToStruct',
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
            top["show"] = common.dialog(dialogParam);
        },
        showEditor: function () {
            KindEditor.ready(function (K) {
                internal.editor = K.create('#editor_id', {
                    items: ['undo', 'redo', '|', 'cut', 'copy'],
                    width: 666,
                    minHeight: 295,
                    resizeType: 0
                });

            });
        }

    };
    exports.init = function () {
        internal.init();
    }
});