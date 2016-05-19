/**
 * Created by linqinghuang on 2016/4/22.
 */
define(function (require, exports, module) {
    var internal = {
        req: require("./req").req,
        buildTree: function (options) {
            var treeParam = options["treeParam"];
            internal.req.getList({url: options["url"]["getTreeList"]}, function (data) {
                var jqTree = $("#" + options["treeId"]);
                if (typeof (options["renderData"]) == "function") {
                    data = options["renderData"](data);
                }
                var treeData = data;
                treeParam["data"] = treeData;
                jqTree.ligerTree(treeParam);
            });
        }
    };
    exports.tree = {
        build: function (options) {
            internal.buildTree(options);
        }
    }

})
