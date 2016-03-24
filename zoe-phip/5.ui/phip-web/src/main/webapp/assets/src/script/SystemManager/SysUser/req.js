define(function (require, exports, module) {
    var internal = {
        updateState: function (id, state, callback) {
            var req = new Request('user/update');
            req.post({
                isTip: false,
                data: { id: id, state: state },
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            });
        }
    };
    exports.req = internal;
});