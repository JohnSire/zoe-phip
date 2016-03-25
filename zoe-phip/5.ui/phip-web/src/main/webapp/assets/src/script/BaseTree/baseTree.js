define(function (require, exports, module) {
    var baseTree = new Class();
    var tree = require("./tree.js").tree;

    var req = require("./req.js").req;
    var defaultOptions = {
        deteteUrl: null,
        treeUrl: null,
        //所有ligerTree参数都可以在此使用
        treeParam: {
            nodeWidth: 220,
            checkbox: false
        },
        afterBuildTree: null
    };

    baseTree.include({
        init: function () {
            var me = this;
            var options = $.extend(true, {}, defaultOptions, arguments[0]);
            req.getTreeList({ url: options.treeUrl }, function (data) {
                //#region 待优化提取代码
                var treeData = [];
                $.each(data.result, function (index, item) {
                    var treeItem = {};
                    treeItem.text = item.Name;
                    treeItem.Id = item.Id;
                    treeItem.children = [];
                    if (item.children) {
                        for (var i = 0; i < item.children.length; i++) {
                            var children = { text: item.children[i].Name, Id: item.children[i].Id };
                            treeItem.children.push(children);
                        }
                    }
                    treeData.push(treeItem);
                });
                treeData = [{ text: "客户端类别", children: treeData }],
                //#endregion
                options["treeParam"]["data"] = treeData;
                var treeObj = tree.buildTree(options["treeParam"]);
                if (typeof (options.afterBuildTree) == "function") {
                    options.afterBuildTree(treeObj);
                }

            });
            this.run = false;
        }
    });
    module.exports = baseTree;
})