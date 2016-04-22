/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var baseTree = new Class();
    var defaultOptions = require("./options").defaultOptions;
    var tools = require("./tools").tools;
    var tree = require("./buildTree").tree;
    baseTree.include({
        //生成
        init: function () {
            var self = this;
            self.initParam = $.extend(true, {}, defaultOptions, arguments[0]);
            var options = $.extend(true, {}, defaultOptions, arguments[0]);
            tools.btnBuild(options);
            tree.build(options);
        },
        //重新加载
        reload: function () {

        }

    })

    module.exports = baseTree;

})