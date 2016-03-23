//钻取模块
define(function (require, exports, module) {
    var internal = {
        top: common.getTopWindowDom(),
        module: require("../BasePortal/module.js").module,
        unit: require("../BasePortal/unit.js").unit,
        add: function (moduleOptions, options) {
            var jqControlObj = options["jqControlObj"];
            var mtype = moduleOptions["mtype"];
            var type = "chartParam";
            switch (mtype) {
                case "grid":
                    type = "gridParam";
                    break;
                default:
                    type = "chartParam";
                    break;
            }

            var jqModule = internal.module.build({
                parentId: moduleOptions["parentId"],
                width: $("#" + moduleOptions["parentId"]).width(),
                height: $("#" + moduleOptions["parentId"]).height(),
                onResize: function () {

                },
                onSetting: function (id) {
                    internal.unit.onSetting(id, options, type);
                },
                onDrill: function (id) {
                    internal.add({ parentId: id, mtype: moduleOptions["mtype"], title: "钻取视图" }, options);
                },
                mtype: moduleOptions["mtype"]
            });
            var parentId = moduleOptions["parentId"];
            var id = $(jqModule).attr("id");
            $(jqModule).insertAfter($("#" + parentId));
            var pid = $(jqModule).attr("parentId");
            $("#" + pid).hide();
            internal.unit.addPath(jqModule, { id: id, title: moduleOptions["title"] });
            jqModule.data("data", { id: id, title: moduleOptions["title"] });
        }

    };
    exports.drillModule = {
        add: function (moduleOptions, options) {
            internal.add(moduleOptions, options);
        }
    }
});