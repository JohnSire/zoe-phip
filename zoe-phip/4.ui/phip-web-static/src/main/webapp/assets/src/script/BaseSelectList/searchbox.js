/**
 * Created by linqinghuang on 2016/4/13.
 */
define(function (require, exports, module) {
    var internal = {
        search: function (options, callback) {
            $("#btnSearch").on("click", function () {
                var searchParam = {};
                if (typeof(options["searchParam"]) == "function") {
                    searchParam = options["searchParam"]();
                }
                if (typeof(callback) == "function") {
                    callback(searchParam);
                }
            });
        }
    };

    exports.searchbox = internal;

});