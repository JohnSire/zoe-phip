define(function (require, exports, module) {
    var internal = {
        deleteList: function (param, callback) {
            $.ligerDialog.confirm('是否删除选中记录？', function (yes) {
                if (yes) {
                    var req = new Request(param.url);
                    req.post({
                        data: { ids: param.ids },
                        success: function (data) {
                            if (typeof (callback) == "function") {
                                callback(data);
                            }
                        }
                    })
                }
            })
        },
        deleteInfo: function (param, callback) {
            $.ligerDialog.confirm('是否删除该记录？', function (yes) {
                if (yes) {
                    var req = new Request(param.url);
                    req.get({
                        data: { id: param.id },
                        success: function (data) {
                            if (typeof (callback) == "function") {
                                callback(data);
                            }
                        }
                    })
                }
            });

        }
    };
    exports.req = internal;
})