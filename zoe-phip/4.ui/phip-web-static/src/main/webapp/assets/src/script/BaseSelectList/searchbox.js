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
                    if (typeof(options["param"]) == "function") {
                        var parm = options["param"]();
                        searchParam = $.extend(true, {}, searchParam, parm);
                    } else {
                        searchParam = $.extend(true, {}, searchParam, options["param"]);
                    }
                }
                if (typeof(callback) == "function") {
                    callback(searchParam);
                }
            });
        }

    };

    exports.searchbox = internal;

});