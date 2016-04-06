define(function (require, exports, module) {
    var internal = {
        updateState: function (id, state, callback) {
            var req = new Request('/menu/updateState');
            req.post({
                isTip: false,
                data: { id: id, state: state },
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            });
        },
        getList: function (param, callback) {
            var req = new Request("/menu/getMenuList");
            req.get({
                isTip: false,
                data: { keyWord: "" },
                success: function(data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            });
        },
        updateList: function (param, callback) {
            var req = new Request("/menu/updateMenuList");
            req.post({
                data: param,
                success: function(data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            });
        },
        getInfo: function (id, callback) {
            var req = new Request("/menu/getMenuInfo");
            req.get({
                isTip: false,
                //async: false,
                cache: false,
                data: { id: id },
                success: function(data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            });
        }
    };
    exports.req = internal;

})