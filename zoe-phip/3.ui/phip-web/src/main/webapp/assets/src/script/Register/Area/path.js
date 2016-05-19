/**
 * Created by linqinghuang on 2016/5/5.
 */

define(function (require, exports, module) {
    var internal = {
        req: require("./req").req,
        init: function (callback, drillNodeCallback) {
            internal.buildPath(callback, drillNodeCallback);
        },
        buildPath: function (callback, drillNodeCallback) {
            internal.req.getFirstAreaObj(function (data) {
                if (data.result && data.result.id) {
                    var parentPathNode = {id: data.result.id, name: data.result.name};
                    //添加顶级节点
                    internal.addNode(parentPathNode, function (nodeId) {
                        if (typeof(drillNodeCallback) == "function") {
                            drillNodeCallback(nodeId);
                        }
                    }, true);
                    if (typeof(callback) == "function") {
                        callback(data.result.id);
                    }

                }
            });
        },
        addNode: function (node, drillNodeCallback, isFirst) {
            var jqPath = $("#gridTools").find('[op="custom"]');
            if (!isFirst) {
                var jqArrow = $("<span></span>").addClass("arrow").html("&gt;");
                var jqNode = $("<a></a>").addClass("link").text(node.name).data("nodeId", node.id).on("click", function () {
                    if (typeof (drillNodeCallback) == "function") {
                        var id = $(this).data("nodeId");
                        $(this).nextAll().remove();
                        drillNodeCallback(id);
                    }
                });
                jqPath.find(".y-layout-position").append(jqArrow).append(jqNode);
            } else {
                var jqPosition = $("<p></p>").addClass("y-layout-position");
                var jqPositionIcon = $("<span></span>").addClass("icon-position");
                var jqNode = $("<a></a>").addClass("link").text(node.name).data("nodeId", node.id).on("click", function () {
                    if (typeof (drillNodeCallback) == "function") {
                        var id = $(this).data("nodeId");
                        $(this).nextAll().remove();
                        drillNodeCallback(id);
                    }
                });
                jqPosition.append(jqPositionIcon).append(jqNode);
                jqPath.append(jqPosition);
            }
        }
    };
    exports.path = {
        init: function (callback, drillNodeCallback) {
            internal.init(callback, drillNodeCallback);
        },
        addNode: function (node, drillNodeCallback, isFirst) {
            internal.addNode(node, drillNodeCallback, isFirst);
        }
    };
})