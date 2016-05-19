/**
 * Created by linqinghuang on 2016/3/14.
 * BaseAttr plugin
 * 属性弹窗封装
 * Copyright (c) zoe 2016
 */
define(function (require, exports, module) {
    var baseAttr = new Class();
    var event = require("./event");
    var defaultOptions = require("./options").defaultOptions;
    baseAttr.include({
        init: function () {
            var options = $.extend(true, {}, defaultOptions, arguments[0]);
            event.init(options);
        }
    });
    module.exports = baseAttr;
})