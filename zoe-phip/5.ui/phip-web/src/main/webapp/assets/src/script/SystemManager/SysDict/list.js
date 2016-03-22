define(function (require, exports, module) {
    var internal = {
        grid: require("./buildGrid.js"),
        tools: require("./tools.js"),
        init: function () {
            internal.grid.init();
            internal.tools.init()
        }
    };
    exports.init = function () {
        internal.init();
    }
});