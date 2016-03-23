define(function (require, exports, module) {
    var internal = {
        getTreeList: function (param, callback) {
            var req = new Request(param.url);
            req.get({
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        }
    }
    exports.req = internal;
});