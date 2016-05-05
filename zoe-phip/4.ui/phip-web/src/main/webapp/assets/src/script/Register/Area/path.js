/**
 * Created by linqinghuang on 2016/5/5.
 */

define(function (require, exports, module) {
    var internal = {
        req: require("./req").req,
        init: function (callback) {
            internal.buildPath();
        },
        buildPath: function () {
            internal.req.getFirstAreaObj(function (data) {
                if (data.result && data.result.id) {
                    var parentPathNode = {id: data.result.id, name: data.result.name};
                    //添加顶级节点
                    internal.addNode(parentPathNode, true);
                }
            });
        },
        addNode: function (node, isFirst) {
            var jqPath = $("#gridTools").find('[op="custom"]');
            if (!isFirst) {
                var jqArrow = $("<span></span>").addClass("arrow").html("&gt;");
                var jqNode = $("<a></a>").addClass("link").text(node.name).data("pathId", node.id);
                jqPath.find(".y-layout-position").append(jqArrow).append(jqNode);
            } else {
                var jqPosition = $("<p></p>").addClass("y-layout-position");
                var jqPositionIcon = $("<span></span>").addClass("icon-position");
                var jqNode = $("<a></a>").addClass("link").text(node.name).data("pathId", node.id);
                jqPosition.append(jqPositionIcon).append(jqNode);
                jqPath.append(jqPosition);
            }
        },
        getUpPath: function () {

        },
        addPath: function () {

        }
    };
    exports.path = {
        init: function (callback) {
            internal.init(callback);
        }
    };
})