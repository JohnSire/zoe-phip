define(function (require, exports, module) {
    var internal = {
        req: require("./req.js").req,
        vaildform: null,
        init: function () {
            //获取id:实体的主键//type:edit编辑,否则为添加
            var id = common.getParamFromUrl("id"), type = common.getParamFromUrl("type"), categoryId = common.getParamFromUrl("categoryId");
            internal.vaildform = $("#baseAttrForm").Validform({ tiptype: 5 });
            internal.onBindInfo(id, type);
            internal.onSave(id, type, categoryId);
        },
        //绑定界面实体
        onBindInfo: function (id, type) {
            //编辑
            if (type == "edit") {
                internal.req.dictItem.getInfo(id, function (data) {
                    $("#baseAttrForm").json2form(data.result);
                });
            }
        },
        //点击保存按钮事件
        onSave: function (id, type, categoryId) {
            var top = common.getTopWindowDom();
            top.win_dict_item_callback = function (reloadGrid) {
                //封装的方法，用来获取表单的数据
                var data = $("#baseAttrForm").form2json();
                data["FkSystemDictCategoryId"] = categoryId;
                if (internal.vaildform.check()) {
                    if (type == "edit") {
                        //修改
                        data["Id"] = id;//要修改的数据的Id
                        internal.req.dictItem.updateInfo(data, function (result) {
                            if (result.IsSuccess) {
                                var top = common.getTopWindowDom();
                                reloadGrid();
                                top.win_dict_item_dialog.close();
                            }

                        })
                    } else {
                        //新增
                        internal.req.dictItem.addInfo(data, function (result) {
                            if (result.IsSuccess) {
                                var top = common.getTopWindowDom();
                                reloadGrid();
                                top.win_dict_item_dialog.close();
                            }

                        })
                    }
                }
                else {
                    var errormsg = $($(".Validform_error")[0]).attr("errormsg");
                    if (errormsg && errormsg.length > 0) {
                        common.jsmsg(errormsg, "Error");
                    }
                }
            }
        }
    };
    exports.init = function () {
        internal.init();
    }
});