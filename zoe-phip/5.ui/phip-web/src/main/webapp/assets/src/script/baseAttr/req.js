define(function (require, exports, module) {
    var internal = {
        //获取列表中的单条信息
        getInfo: function (id, param, callback) {
            var req = new Request(param.url);
            req.get({
                isTip: false,
                data: { id: id },
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data)
                    }
                }
            })
        },
        editInfo: function (data, param, callbak) {
            var req = new Request(param.url);
            req.post({
                data: data,
                success: function (data) {
                    if (typeof (callbak) == "function") {
                        callbak(data);
                    }
                }
            })
        }
    };
    exports.req = internal;
});