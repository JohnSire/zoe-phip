define(function (require, exports, module) {
    var internal = {
        getList: function (callback) {
            var req = new Request("SystemParam/GetSysParamList");
            req.get({
                isTip: false,
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        },
        updateList: function (param, callback) {
            $.ligerDialog.confirm('是否确认修改系统参数内容', function (yes) {
                if (yes) {
                    var req = new Request("SystemParam/UpdateSysParamList");
                    req.post({
                        data: param,
                        success: function (data) {
                            if (data.IsSuccess) {
                                common.jsmsgSuccess('保存成功!');
                            }
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