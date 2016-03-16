/**
 * Created by linqinghuang on 2016/3/14.
 * BaseAttr internal method
 * Copyright (c) zoe 2016
 */
define(function (require, exports, module) {
    var internal = {
        req: require("./req").req,
        param: null,
        vaildformObj: null,
        init: function (param) {
            internal.param = param;
            if (typeof (param["loadPageEvent"]) == "function") {
                param["loadPageEvent"]();
            }
            var stateKey = param["dialogParam"]["stateKey"];//编辑状态Key
            var state = common.getParamFromUrl(stateKey) || "add";//获取编辑状态"add" or "edit";
            var primaryKey = param["dialogParam"]["primaryKey"];//主键用来获取实体的Key
            var primary = common.getParamFromUrl(primaryKey);//获取实体值的参数
            var ajaxData = {};

            $("input[ajaxurl]").each(function () {
                if (state == "edit") {
                    $(this).attr({ "ajaxurl": $(this).attr("ajaxurl") + "?" + primaryKey + "=" + primary });
                }
            });
            internal.vaildformObj = common.vaildform();
            if (state == "edit") {
                ajaxData[primaryKey] = primary;
                internal.onBindInfo(ajaxData, param);
            }
            internal.onSave(ajaxData, state, param);
        },
        onBindInfo: function (ajaxData, param) {
            var ajaxParam = {
                data: ajaxData,
                url: param["getUrl"]
            }
            internal.req.getInfo(ajaxParam, function (data) {
                data= $.parseJSON(data);
                if (typeof (param["beforeBindEvent"]) == "function") {
                    param["beforeBindEvent"](data.result);
                }
                //根据id获取数据，然后绑定上
                param["dialogParam"]["jqForm"].json2form(data.result);

            })
        },
        onSave: function (ajaxData, state, param) {
            var top = common.getTopWindowDom();
            top[internal.param["winCallback"]] = function (callback,submited) {
                var data = param["dialogParam"]["jqForm"].form2json();
                var preOPData = {};
                if (typeof (param["beforeSaveEvent"]) == "function") {
                    preOPData = param["beforeSave"](data);
                }
                data = $.extend(true, {}, data, preOPData || {});
                var url = "";
                if (state == "edit") {
                    data = $.extend(true, {}, data, ajaxData || {});
                    url = param["updateUrl"];
                } else {
                    url = param["addUrl"];
                }
                var ajaxParam = { data: data, url: url }
                if (internal.vaildformObj.check()) {
                    if (typeof (param["customEvent"]) == "function") {
                        param["customEvent"](data);
                        if (typeof (param["afterSaveEvent"]) == "function") {
                            param["afterSaveEvent"](data);
                        }
                    } else {
                        internal.req.editInfo(ajaxParam,
                            function (data) {
                                if($.isFunction(submited)){
                                    submited();
                                }
                                if (typeof (param["afterSaveEvent"]) == "function") {
                                    param["afterSaveEvent"](data);
                                }
                                if (data.isSuccess) {
                                    if (typeof (callback) == "function") {
                                        callback();
                                    }
                                    top[internal.param["winName"]].close();
                                }
                            }
                        )
                    }
                }else{
                    submited();
                }
            }
        }
    };
    exports.init = function (param) {
        internal.init(param);
    }
});