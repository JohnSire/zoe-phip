/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var internal = {
        req: require("./req").req,
        param: null,
        build: function (options) {
            internal.param = options;
            var gridId = options["gridId"];
            var jqGrid = $("#" + gridId);
            var extendParam = options["extendParam"]();
            var ajaxParam = options["gridParam"]["parms"];
            ajaxParam = $.extend(true, {}, ajaxParam, extendParam);
            options["gridParam"]["parms"] = ajaxParam;
            //#region 列处理
            var columns = options["gridParam"]["columns"];
            var columnArray = [];
            $.each(columns, function (index, item) {
                if (item["icons"]) {
                    item["render"] = function (rowdata, rowindex, value) {
                        var h = "";
                        for (var i = 0; i < item["icons"].length; i++) {
                            var iconParam = {};
                            if (item["iconsParam"] && item["iconsParam"][item["icons"][i]]) {
                                iconParam = item["iconsParam"][item["icons"][i]];
                            }
                            h += internal.columnBtn[item["icons"][i]](rowdata, iconParam, item);
                        }
                        return h;
                    }
                }
                columnArray.push(item);
            });
            options["gridParam"]["columns"] = columnArray;
            common.grid(jqGrid, options["gridParam"]);
        },
        deleteList: function (options) {
            var gridId = options["gridId"];
            var gridObj = common.getGrid(gridId);
            var selectRows = gridObj.getSelectedRows();
            var ids = "";
            if (selectRows.length > 0) {
                $.each(selectRows, function (index, item) {
                    ids += "," + item["id"] + ""
                });
                if (ids.length > 0) {
                    ids = ids.substr(1, ids.length);
                }
                internal.req.deleteList({url: options["deleteUrl"]["deleteList"], ids: ids}, function () {
                    var gridId = options["gridId"];
                    var gridObj = common.getGrid(gridId);
                    gridObj.reload();
                });
            } else {
                common.jsmsgError("请选择要删除的记录！");
            }
        },
        columnBtn: {
            edit: function (rowdata) {
                var titleDescr = "";
                if (internal.param["dialogParam"]["titleKey"]) {
                    titleDescr = rowdata[internal.param["dialogParam"]["titleKey"]];
                }
                var str = "<a class='icon-grid icon-grid-edit' title='编辑' onclick='javascript:winEditGridRow(\"" + rowdata.id + "\",\"" + titleDescr + "\")'></a> ";
                return str;
            },
            del: function (rowdata) {
                var str = "<a class='icon-grid icon-grid-del' title='删除' onclick='javascript:winDeleteGridRow(\"" + rowdata.id + "\")'></a> ";
                return str;
            },
            switch: function (rowdata, iconParam, columnInfo) {
                var b = new Base64();
                var url = b.encode(iconParam["url"]);
                if (rowdata[columnInfo["name"]] == iconParam["switchOn"]) {
                    return '<a href="javascript:gridChangeSwitch(\'' + iconParam["primaryKey"] + '\',\'' + rowdata[iconParam["primaryKey"]] + '\',\'' + columnInfo["name"] + '\', \'' + iconParam["switchOff"] + '\', \'' + url + '\', \'' + iconParam["confirmMeg"] + '\');" class="btn-switch-outer"><span switch_id="' + rowdata[iconParam["primaryKey"]] + '" class="btn-switch btn-switch-on"><b class="btn-switch-inner"></b></span></a>';
                }
                else {
                    return '<a href="javascript:gridChangeSwitch(\'' + iconParam["primaryKey"] + '\',\'' + rowdata[iconParam["primaryKey"]] + '\',\'' + columnInfo["name"] + '\', \'' + iconParam["switchOn"] + '\', \'' + url + '\', \'' + iconParam["confirmMeg"] + '\');" class="btn-switch-outer"><span switch_id="' + rowdata[iconParam["primaryKey"]] + '" class="btn-switch btn-switch-off"><b class="btn-switch-inner"></b></span></a>';
                }
            }

        }
    }
    window.winEditGridRow = function (id, titleDescr) {
        var dialogParam = internal.param["dialogParam"];
        var editParam = $.extend(true, {}, internal.param["dialogParam"]["common"], internal.param["dialogParam"]["edit"]);
        if (titleDescr) {
            editParam["title"] = editParam["title"] + "--" + titleDescr;
        }
        editParam["url"] = editParam["url"] + "?state=edit&&id=" + id;

        var urlParam="";
        if(typeof(editParam["otherUrlParam"])=="function") {
            $.each(editParam["otherUrlParam"](), function (key, value) {
                urlParam += "&&" + key + "=" + value;
            });
        }
        editParam["url"]=editParam["url"]+urlParam;

        //submited(提交非进行时状态改变方法）
        editParam.buttons[0]["onclick"] = function (item, dialog, submited) {
            var top = common.getTopWindowDom();
            var callback = function () {
                var gridId = internal.param["gridId"];
                var gridObj = common.getGrid(gridId);
                gridObj.reload();
            }
            top[internal.param["dialogParam"]["winCallback"]](callback, submited);
        }
        top[internal.param["dialogParam"]["winName"]] = common.dialog(editParam);
    };
    window.winDeleteGridRow = function (id) {
        var param = {
            id: id,
            url: internal.param["deleteUrl"]["deleteInfo"]
        }
        internal.req.deleteInfo(param, function (data) {
            var gridId = internal.param["gridId"];
            var gridObj = common.getGrid(gridId);
            gridObj.reload();
        })
    };
    window.gridChangeSwitch = function (primaryKeyName, primaryKeyValue, switchName, switchValue, url, confirmMeg) {
        common.confirm(confirmMeg, function () {

            var b = new Base64();
            var ajaxUrl = b.decode(url);
            var param = {};
            param[primaryKeyName] = primaryKeyValue;
            param[switchName] = switchValue;

            var req = new Request(ajaxUrl);
            req.post({
                isTip: true,
                data: param,
                success: function (data) {
                    if (data.isSuccess) {
                        var jqBtnSwitch = $("[switch_id='" + primaryKeyValue + "']");
                        if (jqBtnSwitch.hasClass("btn-switch-on")) {
                            jqBtnSwitch.removeClass("btn-switch-on").addClass("btn-switch-off");
                        } else {
                            jqBtnSwitch.removeClass("btn-switch-off").addClass("btn-switch-on");
                        }
                    }
                }
            });
        });

    };
    exports.grid = {
        build: internal.build,
        deleteList: internal.deleteList
    }
});