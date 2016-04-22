/**
 * Created by linqinghuang on 2016/4/22.
 */
define(function (require, exports, module) {
    var internal = {
        //删除选中列表
        deleteInfo: function (param, callback) {
            common.confirm('是否删除该记录?', function () {
                var req = new Request(param["url"]);
                req.get({
                    data: {id: param["id"]},
                    success: function (data) {
                        if (typeof (callback) == "function") {
                            callback(data);
                        }
                    }
                })
            })
        },
        getList: function (param, callback) {
            var req = new Request(param["url"]);
            req.get({
                isTip: false,
                success: function (data) {
                    if (typeof(callback) == "function") {
                        callback(data);
                    }
                }
            })
        }

    }
    exports.req = internal;
});