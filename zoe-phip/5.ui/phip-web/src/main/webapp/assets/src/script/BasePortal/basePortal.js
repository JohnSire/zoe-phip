//模块配置主入口
define(function (require, exports, module) {
    var layout = require("../BasePortal/layout.js").layout;//页面布局
    var defaultOptions = require("../BasePortal/options.js").options;//参数
    var tools = require("../BasePortal/tools.js");//工具条
    var gridModule = require("../BasePortal/gridModule.js").gridModule;//表格模块
    var chartModule = require("../BasePortal/chartModule.js").chartModule;//图表模块
    var basePortal = new Class();
    basePortal.include({
        init: function () {
            var options = $.extend(true, {}, defaultOptions, arguments[0]);
            layout.init(options);
            tools.init(options);
            this.run = false;
        },
        buildChart: function (jqModule, chartProps) {
            chartModule.build(jqModule, chartProps);
        },
        buildGrid: function (jqModule, gridProps) {
            gridModule.build(jqModule, gridProps);
        },
        search: function () {
            layout.search();
        }
    })
    module.exports = basePortal;
});