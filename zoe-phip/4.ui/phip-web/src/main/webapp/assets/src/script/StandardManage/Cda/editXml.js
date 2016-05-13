/**
 * Created by chenzhisen on 2016/5/12.
 */

define(function (require, exports, module) {
    var internal = {

        init: function () {
            internal.event();
            internal.showEditor();
        },
        event: function () {
            //上传
            $("#uploadXml").click(function () {
                var options = {
                    success: function (data) {

                    }
                };
                $("#fm_main").ajaxSubmit(options);

            });

            //结构化
            $("#xslToStruct").click(function () {
                internal.xslToStruct();

            });
            $("#choosefile").change(function (e) {
                var src = e.target || window.event.srcElement; //获取事件源，兼容chrome/IE
                var arr = $(this).val().split('\\');
                var fileName = arr[arr.length - 1];
                $("#clientPath").val(fileName);
            });
        },

        xslToStruct: function () {
            var dialogParam =
            {
                title: "结构化视图",
                url: 'cda/view/showXMLToStruct',
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