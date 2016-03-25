define(function (require, exports, module) {
    var internal = {
        getMenuList: function (callback) {
            var req = new Request("/menu/user");
            req.post({
                async: false,
                isTip:false,
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        }
    };
    exports.req = {
        getMenuList:function(callback){
            internal.getMenuList(callback);
        }
            
    }
});