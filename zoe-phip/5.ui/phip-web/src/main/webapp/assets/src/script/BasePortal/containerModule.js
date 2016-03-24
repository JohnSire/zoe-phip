//文本模块
define(function (require, exports, module) {
    var internal = {

        baseModule: require("../BasePortal/module.js").baseModule,
        add: function (jqControlObj) {
            var jqBaseModule = internal.baseModule.buildHtml(jqControlObj);
            jqBaseModule.attr({ mtype: "container" });
            var jqIcon = $('<span></span>').addClass("icon-form").addClass("icon-form-container").attr({ title: "文本" });
            $(jqBaseModule).find(".y-layout-position").prepend(jqIcon);

        }
    }
    exports.containerModule = {
        add: function (jqControlObj) {
            internal.add(jqControlObj);
        }
    }
});