/**
 * Created by linqinghuang on 2016/3/14.
 * BaseAttr ajax request
 * depend on ../../lib/custom/request.js
 * 属性弹窗封装ajax请求
 * Copyright (c) zoe 2016
 */
define(function (require, exports, module) {
    var internal = {
        getInfo: function (ajaxParam, callback) {
            var req = new Request(ajaxParam["url"]);
            req.get({
                isTip: false,//是否有请求结果消息提示（成功||失败）
                data: ajaxParam["data"],
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        },
        editInfo: function (ajaxParam, callback) {



            var req = new Request(ajaxParam["url"]);
            req.post({
                data: ajaxParam["data"],
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        }
    }
    exports.req = internal;
});