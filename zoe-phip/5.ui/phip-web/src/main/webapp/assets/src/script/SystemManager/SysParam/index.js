define(function (require, exports, module) {
    var internal = {
        build: require("./build.js"),
        init: function () {
            internal.build.init();
        }
    };
    exports.init = internal.init;
})