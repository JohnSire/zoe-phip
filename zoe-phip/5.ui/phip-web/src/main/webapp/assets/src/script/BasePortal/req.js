define(function (require, exports, module) {
    var internal = {
        getSQLData: function (param, callback) {
            var req = new Request(param["url"]);
            req.post({
                data: param["data"],
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        }
    };
    exports.req = {
        getSQLData: function (param, callback) {
            internal.getSQLData(param, callback);
        }
    }
});