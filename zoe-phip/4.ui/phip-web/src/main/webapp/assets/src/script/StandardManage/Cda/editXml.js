/**
 * Created by chenzhisen on 2016/5/12.
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
        saveXml: function (xml,callback) {
            var req = new Request("cda/saveXml");
            req.post({
                isTip: true,//是否有请求结果消息提示（成功||失败）
                data:{"id":id,"xml":xml},
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

        init: function () {

            ajaxStore.getCDA(id, function (data) {
                var xml=data.result.sampleXml;
                $.cookie("xml",xml);
            })
            internal.event();
            internal.showEditor();
        },
        event: function () {

            top.saveXMl=internal.saveXMl;
            //上传
            $("#uploadXml").click(function () {
                var path=$("#clientPath").val()
                if(!path||path=="请选择样例Xml文件"){
                    common.jsmsgError("请选择样例Xml文件");
                    return;
                }
                var options = {
                    dataType:'json',
                    success: function (data) {
                        if (data.isSuccess) {
                            var fileContent=data.result.fileContent;
                            fileContent = fileContent.replace(/</g, '&lt;');
                            fileContent = fileContent.replace(/>/g, '&gt;');
                            internal.editor.html(fileContent);
                        }
                    }
                };
                $("#fm_main").ajaxSubmit(options);

            });

            //结构化
            $("#xslToStruct").click(function () {
                var xml=internal.editor.html();
                $.cookie("xml",xml);
                internal.xslToStruct();

            });
            $("#choosefile").change(function (e) {
                var src = e.target || window.event.srcElement; //获取事件源，兼容chrome/IE
                var arr = $(this).val().split('\\');
                var fileName = arr[arr.length - 1];
                $("#clientPath").val(fileName);
            });
        },
        saveXMl:function(submited){
            var xml=internal.editor.html();
            $.cookie("xml",xml);
            ajaxStore.saveXml(xml,function(data){
                submited();
            })

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