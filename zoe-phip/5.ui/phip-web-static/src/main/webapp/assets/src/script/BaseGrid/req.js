/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var internal = {
        //删除选中列表
        deleteList: function (param, callback) {
            common.confirm('是否删除选中记录?', function () {
                var req = new Request(param.url);
                req.post({
                    data: { ids: param.ids },
                    success: function (data) {
                        if (typeof (callback) == "function") {
                            callback(data);
                        }
                    }
                })
            })
        },
        deleteInfo: function (param, callback) {
            common.confirm('是否删除该记录?', function () {
                var req = new Request(param.url);
                req.get({
                    data: { id: param.id },
                    success: function (data) {
                        if (typeof (callback) == "function") {
                            callback(data);
                        }
                    }
                })
            })
        }
    }
    exports.req = internal;
})