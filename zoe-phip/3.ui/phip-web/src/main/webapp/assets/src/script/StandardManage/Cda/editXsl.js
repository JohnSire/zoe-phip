/**
 * Created by chenzhisen on 2016/5/10.
 */

define(function (require, exports, module) {
    var ajaxStore = {
        getCDA: function (id,callback) {
            var req = new Request("cda/getCdaInfo");
            req.get({
                isTip: false,//是否有请求结果消息提示（成功||失败）
                data:{"id":id},
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        },
        saveXsl: function (xsl,callback) {
            var req = new Request("cda/saveXsl");
            req.post({
                isTip: true,//是否有请求结果消息提示（成功||失败）
                data:{"id":id,"xsl":xsl,"type":internal.xslType},
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        }
    }
    var top=common.getTopWindowDom();
    var id=common.getParamFromUrl("id");

    var internal = {
        xslType:"ToHtml",
        init: function () {
            internal.event();
            internal.showEditor();


        },
        event: function () {
            top.saveXsl=internal.saveXsl;
            //上传
            $("#uploadXsl").click(function () {
                var path = $("#clientPath").val()
                if (!path || path == "请选择Xsl文件") {
                    common.jsmsgError("请选择Xsl文件");
                    return;
                }
                var options = {
                    success: function (data) {
                        if (data.isSuccess) {
                            var fileContent = data.result.fileContent;
                            fileContent = fileContent.replace(/</g, '&lt;');
                            fileContent = fileContent.replace(/>/g, '&gt;');
                            internal.editor.html(fileContent);
                        }
                    }
                };
                $("#fm_main").ajaxSubmit(options);

            });

            //预览
            $("#xslToPreview").click(function () {
                var xsl=internal.editor.html();
                $.cookie(internal.xslType,xsl);
                internal.xslToPreview();

            });

            //结构化
            $("#xslToStruct").click(function () {
                var xsl=internal.editor.html();
                $.cookie(internal.xslType,xsl);
                internal.xslToStruct();

            });
            // xsl Tab
            $("#xsltab .list").click(function () {
                var oldxsl=internal.editor.html();
                $.cookie(internal.xslType,oldxsl);

                internal.xslType =$(this).attr("type");
                $("#xsltab .list").removeClass("active");
                $(this).addClass("active");
                var xsl=$.cookie(internal.xslType);
                internal.editor.html(xsl);
            });

            //选择文件
            $("#choosefile").change(function (e) {
                var src = e.target || window.event.srcElement; //获取事件源，兼容chrome/IE
                var arr = $(this).val().split('\\');
                var fileName = arr[arr.length - 1];
                $("#clientPath").val(fileName);
            });
        },
        saveXsl:function(submited){
            var xsl=internal.editor.html();
            $.cookie(internal.xslType,xsl);
            ajaxStore.saveXsl(xsl,function(data){

                submited();
            })

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
                ajaxStore.getCDA(id, function (data) {
                    var ToHtml=data.result.toHtmlXsl;
                    var ToSummary=data.result.toSummaryXsl;
                    var ToSet=data.result.toSetXsl;

                    $.cookie("ToHtml",ToHtml);
                    $.cookie("ToSummary",ToSummary);
                    $.cookie("ToSet",ToSet);

                    internal.editor.html(ToHtml);
                })
            });
        }

    };
    exports.init = function () {
        internal.init();
    }
});