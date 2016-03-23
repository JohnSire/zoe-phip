//模板配置的工具条
define(function (require, exports, module) {
    var internal = {
        textModule: require("../BasePortal/textModule.js").textModule,
        gridModule: require("../BasePortal/gridModule.js").gridModule,
        chartModule: require("../BasePortal/chartModule.js").chartModule,
        init: function (options) {
            internal.show(options);
            internal.drag(options);
        },
        drag: function (options) {
            var jqControlObj = options["jqControlObj"];
            $(jqControlObj).find(".icon-form").draggable({
                helper: "clone",
                containment: "[ptype='content']"
            });
            $(jqControlObj).find("[ptype='content']").droppable({
                accept: ".icon-form",
                drop: function (event, ui) {
                    var btnType = ui.helper.attr("ptype");
                    switch (btnType) {
                        case "grid":
                            internal.gridModule.add(options);
                            break;
                        case "pie":
                        case "line":
                        case "bar":
                        case "radarMap":
                            internal.chartModule.add(btnType, options);
                            break;
                        case "text":
                            internal.textModule.add(options);

                            break;
                    }

                }
            });
        },
        show: function (options) {
            var jqControlObj = options["jqControlObj"], toolBarBtns = options["toolBarBtns"];
            var jqToolbar = $(jqControlObj).find("[ptype='toolbar']");
            $.each(toolBarBtns, function (index, item) {
           
                jqToolbar.find("[ptype='" + item + "']").show();
            })
        }
    };
    exports.init = function (options) {
        internal.init(options);
    }
});