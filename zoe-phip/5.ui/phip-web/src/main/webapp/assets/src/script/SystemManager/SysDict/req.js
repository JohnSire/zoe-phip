define(function (require, exports, module) {
    var internal = {
        dictCategory: {

        },
        dictItem: {
            getInfo: function (id, callback) {
                var req = new Request("SystemDict/GetSysDictItemInfo");
                req.post({
                    isTip: false,
                    data: { id: id },
                    success: function (data) {
                        if (typeof (callback) == "function") {
                            callback(data);
                        }
                    }
                })
            },
            addInfo: function (param, callback) {
                var req = new Request("SystemDict/AddSysDictItemInfo");
                req.post({
                    data: param,
                    success: function (data) {
                        if (typeof (callback) == "function") {
                            callback(data);
                        }
                    }
                })
            },
            updateInfo: function (param, callback) {
                var req = new Request("SystemDict/UpdateSysDictItemInfo");
                req.post({
                    data: param,
                    success: function (data) {
                        if (typeof (callback) == "function") {
                            callback(data);
                        }
                    }
                })
            },
            deleteInfo: function (id, callback) {
                var req = new Request("SystemDict/DeleteSysDictItemInfo");
                req.get({
                    data: { id: id },
                    success: function (data) {
                        if (typeof (callback) == "function") {
                            callback(data);
                        }
                    }
                })
            },
            deleteList: function (ids, callback) {
                var req = new Request("SystemDict/DeleteSysDictItemList");
                req.post({
                    data: { ids: ids },
                    success: function (data) {
                        if (typeof (callback) == "function") {
                            callback(data);
                        }
                    }
                })
            }
        }
    };
    exports.req = internal;
});