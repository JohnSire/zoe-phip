/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var baseGrid = new Class();
    var defaultOptions = require("./options").defaultOptions;
    var tools = require("./tools").tools;
    var grid = require("./buildGrid").grid;
    baseGrid.include({
        init: function () {
            var self = this;
            self.initParam = $.extend(true, {}, defaultOptions, arguments[0]);
            var options = $.extend(true, {}, defaultOptions, arguments[0]);
            var getExtendParam = options["extendParam"];
            tools.btnBuild(options);
            tools.searchBoxBuild(options, function (param) {
                var gridId = options["gridId"];
                var gridObj = common.getGrid(gridId);
                var extendParam = {};
                if (typeof(getExtendParam) == "function") {
                    extendParam = getExtendParam();
                }
                var ajaxParam = $.extend(true, {}, extendParam, param);
                $.each(ajaxParam, function (index, item) {
                    gridObj.setParm(index, item);
                });
                gridObj.reload();
            });
            grid.build(options);
        },
        setServer: function () {
            var options = $.extend(true, {}, this.initParam);
            options["gridParam"]["dataAction"] = "server";
            options["gridParam"]["url"] = options["gridParam"]["url"];
            grid.build(options);
        },
        reload: function (param) {
            param = param || {};
            var options = $.extend(true, {}, this.initParam);
            var searchBoxParam = options["tools"]["searchbox"];
            var toolsBoxId = options["toolsBoxId"];
            var jqbtnBox = $("#" + toolsBoxId);
            var getExtendParam = options["extendParam"];
            var gridId = options["gridId"];
            var gridObj = common.getGrid(gridId);
            //工具条的条件
            var toolsParam = {};
            $.each(searchBoxParam, function (index, item) {
                toolsParam[item["name"]] = jqbtnBox.find("[name='" + item["name"] + "']").val();
            });
            //扩展参数的查询
            var extendParam = {};
            if (typeof(getExtendParam) == "function") {
                extendParam = getExtendParam();
            }
            var ajaxParam = $.extend(true, {}, extendParam, toolsParam, param);
            $.each(ajaxParam, function (index, item) {
                gridObj.setParm(index, item);
            });
            gridObj.reload();
            //extendParam={};
        }
    })
    module.exports = baseGrid;
});