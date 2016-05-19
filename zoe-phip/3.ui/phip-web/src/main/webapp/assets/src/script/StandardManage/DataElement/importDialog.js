/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
define(function (require, exports, module) {
    var top = common.getTopWindowDom();
    var internal = {
            init: function () {
                var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
                var baseAttr = new BaseAttr({
                    getUrl: '',//获取实例的接口
                    loadPageEvent: function () {

                        $("#choosefile").change(function (e) {
                            var src = e.target || window.event.srcElement; //获取事件源，兼容chrome/IE
                            var arr = $(this).val().split('\\');
                            var fileName = arr[arr.length - 1];
                            $("#clientPath").val(fileName);
                        });
                    },
                });
                internal.event();
            },
            event: function () {
                top.upload = internal.upload;
            },
            upload: function (submited) {
                var path = $("#clientPath").val()
                if (!path || path == "请选择文件") {
                    common.jsmsgError("请选择文件");
                    submited();
                    return;
                }
                var options = {
                    dataType: 'text',
                    success: function (data) {
                        submited();
                        common.jsmsgSuccess(data);
                        if (data.isSuccess) {

                        }
                    }
                };
                $("#baseAttrForm").ajaxSubmit(options);

            }

        }
        ;
    exports.init = function () {
        internal.init();
    }
});