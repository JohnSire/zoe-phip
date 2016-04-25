/**
 * Created by linqinghuang on 2016/4/22.
 */
define(function (require, exports, module) {
    var internal = {
        req: require("./req").req,
        buildTree: function (options) {
            internal.req.getList({url: options["url"]["getTreeList"]}, function (data) {
                //

                var jqTree = $("#" + options["treeId"]);
                jqTree.ligerTree({
                    data: data.result.rows,
                    idFieldName: 'id',
                    parentIDFieldName: 'fkParentMenuId',
                    textFieldName: 'name',
                    checkbox: false
                })

            });
            //alert(JSON.stringify(options));

        }
    };
    exports.tree = {
        build: function (options) {
            internal.buildTree(options);
        }
    }

})
