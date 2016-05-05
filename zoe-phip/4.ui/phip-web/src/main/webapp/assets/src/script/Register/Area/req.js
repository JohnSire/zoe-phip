/**
 * Created by linqinghuang on 2016/5/5.
 */

define(function (require, exports, module) {
    var internal = {
        //获取顶级区域数据
        getFirstAreaObj: function (callback) {
            var req = new Request("area/getAreaTopNode");
            req.get({
                isTip: false,
                success: function (data) {
                    if (typeof(callback) == "function") {
                        callback(data);
                    }
                }
            })
        }
    };
    exports.req = internal;
})