/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseAttr = new BaseAttr({
                getUrl: '',//获取实例的接口
                loadPageEvent: function () {
                    $("#openFile").click(function () {
                        $("#choosefile").click();
                    });
                    $("#choosefile").change(function (e) {
                        var src = e.target || window.event.srcElement; //获取事件源，兼容chrome/IE
                        var arr = $(this).val().split('\\');
                        var fileName = arr[arr.length - 1];
                        $("#clientPath").val(fileName);
                    });
                },
            });
        }
    };
    exports.init = function () {
        internal.init();
    }
});