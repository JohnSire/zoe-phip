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
            var options = $.extend(true, {}, defaultOptions, arguments[0]);
            tools.btnBuild(options);
            tools.searchBoxBuild(options, function (param) {
                var gridId = options["gridId"];
                var gridObj = common.getGrid(gridId);
                $.each(param, function (index, item) {
                    gridObj.setParm(index, item);
                });
                gridObj.reload();
            });
            grid.build(options);
        },
        reload: function () {},
        search: function () {},
        deleteList: function () {}
    })
    module.exports = baseGrid;
});