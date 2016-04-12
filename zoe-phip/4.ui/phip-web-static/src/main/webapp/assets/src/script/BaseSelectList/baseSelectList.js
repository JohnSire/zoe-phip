/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var baseSelectList = new Class();
    var defaultOptions = require("./options").defaultOptions;
    var grid = require("./grid").grid;
    baseSelectList.include({
        //初始化
        init: function () {
            var options = $.extend(true, {}, defaultOptions, arguments[0]);
            grid.build(options);
        },
        //保存:data保存返回选中的数据
        save: function (data) {

        }
    })
    module.exports = baseSelectList;
})