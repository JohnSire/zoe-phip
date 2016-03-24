define(function (require, exports, module) {
    var internal = {
        req: require("./req.js").req,
        init: function (param) {
            internal.event(param);
            internal.btnStatus(param);
        },
        //状态
        btnStatus: function (param) {
            var btnParam = param.tools.buttons;
            $.each(btnParam, function (index, item) {
                $("#" + item.id).on("click", item.onClick).show();
            });
        },
        //查询框处理
        searchBox: function (param, changeCallback, searchCallback) {
            var searchBoxParam = param.tools.searchbox;
            $.each(searchBoxParam, function (index, item) {
                switch (item["type"]) {
                    case "select":
                        var jqLi = $("<li></li>").addClass("list");
                        var jqLabel = $("<label></label>").addClass("label").text(item["descr"] + ":");
                        var jqSelect = $("<select></select>").addClass("select").attr({ name: item["name"] }).on("change", function () {
                            var value = $(this).val();
                            if (typeof (changeCallback) == "function") {
                                changeCallback(value);
                            }
                        });
                        if (item["data"].length > 0) {
                            var optionItems = item["data"];
                            for (var i = 0; i < optionItems.length; i++) {
                                var jqOption = $("<option></option>").attr({ value: optionItems[i][item["value"]] }).text(optionItems[i][item["text"]]);
                                jqSelect.append(jqOption);
                            }
                            jqLi.append(jqLabel).append(jqSelect);
                        }
                        $("#gridTools").append(jqLi);
                        break;
                    case "text":
                        var jqLi = $("<li></li>").addClass("list");
                        var jqLabel = $("<label></label>").addClass("label").text(item["descr"] + ":");
                        var jqInput = $("<input/>").attr({ type: "text", name: item["name"] }).addClass("text");
                        var jqBtn = $("<input/>").addClass("btn btn-search").attr({ type: "button", value: "搜索" }).on("click", function () {
                            if (typeof (searchCallback) == "function") {
                                searchCallback();
                            }
                        });
                        jqLi.append(jqLabel).append(jqInput).append(jqBtn);
                        $("#gridTools").append(jqLi);
                        break;
                }
            });
        },
        event: function (param) {
            //添加
            $("#btnAdd").on("click", function () {
                var dialogParam = $.extend(true, param.dialogParam.common, param.dialogParam.add);
                var top = common.getTopWindowDom();
                top.win_base_attr_dialog = $.ligerDialog.open(dialogParam)
            });
            //删除
            $("#btnDelete").on("click", function () {
                var gridObj = liger.get("grid");
                var rowArray = gridObj.getSelectedRows();
                var ids = "";
                $.each(rowArray, function (index, item) {
                    ids += "," + item["Id"];
                });
                if (ids.length > 0) {
                    ids = ids.slice(1, ids.length);
                    internal.req.deleteList({ ids: ids, url: param.deleteUrl.deleteList }, function (data) {
                      
                        if (gridObj.options.page == 1) {
                            gridObj.reload();
                        }
                        else {
                            gridObj.changePage("first");
                        }
                    });
                } else {
                    common.jsmsg('请选择要删除的记录！', '', 'Error');
                }
            });
            //刷新
            $("#btnRefresh").on("click", function () {
                var gridObj = liger.get("grid");
                gridObj.reload();
            });
        }
    }
    exports.init = function (param) {
        internal.init(param);
    };
    exports.searchBox = function (param, changeCallback, searchCallback) {
        internal.searchBox(param, changeCallback, searchCallback);
    }
});