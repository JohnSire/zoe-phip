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
                $("#nav li").removeClass("hover");
                $(this).addClass("hover");
                var index = $(this).index() / 2;
            });
        }
    };
    exports.init = function () {
        internal.init();
    }
});