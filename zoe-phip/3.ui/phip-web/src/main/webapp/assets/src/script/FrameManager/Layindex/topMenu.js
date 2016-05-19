/**
 * Created by zhangxingcai on 2016/5/18 0018.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            internal.topMenu();
        },
        topMenu: function () {
            $("#nav li").click(function () {
                $("#nav li").removeClass("active");
                $(this).addClass("active");
                var index = $(this).index() / 2;
            });
        }
    };
    exports.init = function () {
        internal.init();
    }
});