/**
 * Created by linqinghuang on 2016/5/10.
 */
define(function (require, exports, module) {
    var internal = {
        //获取顶级区域数据
        xmanBaseMerge: function (newId, oldId, callback) {
            var req = new Request("personnel/mergeXmanInfo");
            req.post({
                data: {newPatientId: newId, oldPatientId: oldId},
                isTip: true,
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