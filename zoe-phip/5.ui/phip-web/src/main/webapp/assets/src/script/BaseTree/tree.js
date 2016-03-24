define(function (require, exports, module) {
    var internal = {
        init: function (param) {
            internal.buildTree(param);
        },
        buildTree: function (treeParam) {
            var treeObj = $("#tree").ligerTree(treeParam);
            return treeObj;
        }

    }
    exports.tree = internal;
});