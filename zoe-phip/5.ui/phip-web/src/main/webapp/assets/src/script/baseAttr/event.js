define(function (require, exports, module) {
    var internal = {
        req: require("./req.js").req,
        //vaildform: null,
        init: function (param) {
            //获取id:实体的主键//type:edit编辑,否则为添加
            var id = common.getParamFromUrl("id"), type = common.getParamFromUrl("type");
            if (typeof (param.loadPageEvent) == "function") {
                param.loadPageEvent();
            }
            internal.onBindInfo(id, type, param);
            internal.onSave(id, type, param);
        },
        //绑定界面实体
        onBindInfo: function (id, type, param) {
            //编辑
            //$("#baseAttrForm").formInitDate();
            if (type == "edit") {
                param["url"] = param.getUrl;
                internal.req.getInfo(id, param, function (data) {
                    if (typeof (param.beforeBindEvent) == "function") {
                        param.beforeBindEvent(data);
                    }
                    //根据id获取数据，然后绑定上
                    $("#baseAttrForm").json2form(data.result);
                    if (typeof (param.afterBindEvent) == "function") {
                        param.afterBindEvent(data);
                    }
                });
            }

        },
        //点击保存按钮事件
        onSave: function (id, type, param) {

            var top = common.getTopWindowDom();
            top.win_base_attr_callback = function (reloadGrid) {
                //封装的方法，用来获取表单的数据
                var data = $("#baseAttrForm").form2json();

                if (common.validformObj && !common.validformObj.check())
                    return;

                var preOPData = {};//预处理保存参数
                if (typeof (param.beforeSaveEvent) == "function") {
                    preOPData = param.beforeSaveEvent(data, reloadGrid);
                }
                //beforeSaveEvent返回false
                if (preOPData == false)
                    return;
                data = $.extend(true, {}, data, preOPData || {});
                if (type == "edit") {
                    //修改
                    data["Id"] = id;//要修改的数据的Id
                    param["url"] = param.updateUrl;
                } else {
                    //新增
                    param["url"] = param.addUrl;
                }
                if (param["isCustomSubmit"]) {
                    if (typeof (param.customEvent) == "function") {
                        param.customEvent(data);
                    }
                    //不走既定流程，直接关闭窗口
                    top.win_base_attr_dialog.close();
                }
                else {
                    //if (common.validformObj.check()) {
                    internal.req.editInfo(data, param, function(result) {
                        if (typeof (param.afterSaveEvent) == "function") {
                            param.afterSaveEvent(result);
                            if (result.IsSuccess) {
                                var top = common.getTopWindowDom();
                                if (typeof (reloadGrid) == 'function') {
                                    reloadGrid();
                                }

                                top.win_base_attr_dialog.close();
                            }
                        }
                    });
                    //}
                }
            }


        }
    };
    exports.init = function (param) {
        internal.init(param);
    }
});