define(function (require, exports, module) {
    var internal = {
        dictCategory: {

        },
        dictItem: {
            getInfo: function (id, callback) {
                var req = new Request("/dict/getSysDictItemInfo");
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
                var req = new Request("/dict/addItem");
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
                var req = new Request("/dict/updateItem");
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
                var req = new Request("/dict/deleteItem");
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
                var req = new Request("/dict/deleteItemList");
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