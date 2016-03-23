define(function (require, exports, module) {
    var top = common.getTopWindowDom();
    var internal = {
        //提交路径
        submitPath: function (parms) {
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "提交路径",
                url: webRoot + "list/submitPath",
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        qualityTemp: function (parms) {
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "质控模板",
                url: webRoot + "list/QualityTemp?standardId=" + (parms.standardId || ''),
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        collectTemp: function (parms) {
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "采集模板",
                url: webRoot + "list/CollentTemp?standardId=" + (parms.standardId || '') + '&&taskType=' + (parms.taskType || 0),
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        user: function (parms) {//用户
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "用户关联管理",
                url: webRoot + "list/user",
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        menu: function (parms) {//用户
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "菜单关联管理",
                url: webRoot + "list/menu",
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        collectPath: function (parms) {//用户
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "采集路径",
                url: webRoot + "list/CollectPath",
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        set: function (parms) {//数据集
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "数据集选择",
                url: webRoot + "list/Set",
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        versionset: function (parms) {//版本数据集
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "标准表选择",
                url: parms.url,
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        element: function (parms) {//数据元
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "数据元选择",
                url: webRoot + "list/Element",
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        setcolumn: function (parms) {//标准数据集字段
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "数据集字段选择",
                url: parms.url,
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        versionsetcolumn: function (parms) {//版本数据集字段
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "版本数据集字段选择",
                url: parms.url,
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        sttablecolumn: function (parms) {//来源模板表字段
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "来源模板映射列选择",
                url: parms.url,
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        standardDict: function (parms) {//值域
            var id = parms.id;//值域id
            if (!id) {
                id = "";
            }
            //var multiple = parms.multiple ? true : false;
            var dialog = $.ligerDialog.open({

                height: parms.height + 75,
                title: "值域选择",
                url: webRoot + "list/standardDict?id=" + id,
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        var IsSuccess = false;
                        if (typeof (parms.callback) == 'function')
                            var data = top.win_base_list_callback();
                        IsSuccess = parms.callback(data);
                        if (!IsSuccess)
                            dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        exceptValue: function (parms) {//预期值
            var fkTemplateId = parms.fkTemplateId;
            var FkSetId = parms.FkSetId;
            var FkElementId = parms.FkElementId;

            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "预期值选择",
                url: webRoot + "list/ExceptValue?FkSetId=" + FkSetId + "&FkElementId=" + FkElementId + "&fkTemplateId=" + fkTemplateId,
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        var IsSuccess = false;
                        if (typeof (parms.callback) == 'function')
                            var data = top.win_base_list_callback();
                        IsSuccess = parms.callback(data);
                        if (!IsSuccess)
                            dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        regex: function (parms) {//正则表达式

            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "正则表达式选择",
                url: webRoot + "list/Regex",
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        clientJobTables: function (parms) {//采集任务表名
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "表名选择",
                url: webRoot + "List/ClientJobTable?jobPushId=" + parms.jobPushId + "&&multiple=" + parms.multiple,
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        },
        themeTemplate: function (parms) {
            var dialog = $.ligerDialog.open({
                height: parms.height,
                title: "主题视图",
                url: webRoot + "List/ThemeTemplate?multiple=" + parms.multiple,
                width: parms.width,
                buttons: [{
                    text: '确定',
                    onclick: function (item, dialog) {
                        if (typeof (parms.callback) == 'function') { var data = top.win_base_list_callback(); parms.callback(data); }
                        dialog.close();
                    }
                },
                {
                    text: '取消',
                    onclick: function (item, dialog) {
                        dialog.close();
                    }
                }]
            });
        }
    };
    // clear data
    top.list_clientjob_table_storage = [];

    exports.submitPath = function () {//提交路径
        internal.submitPath(arguments[0]);
    };
    exports.qualityTemp = function () {//质控模板
        internal.qualityTemp(arguments[0]);
    };
    exports.user = function () {//质控模板
        internal.user(arguments[0]);
    };
    exports.collectPath = function () {//质控模板
        internal.collectPath(arguments[0]);
    };
    exports.menu = function () {//菜单
        internal.menu(arguments[0]);
    };
    exports.collectTemp = function () {//采集模板
        internal.collectTemp(arguments[0]);
    };
    exports.collectPath = function () {//采集路径
        internal.collectPath(arguments[0]);
    };
    exports.set = function () {//数据集
        internal.set(arguments[0]);
    };
    exports.versionset = function () {
        internal.versionset(arguments[0]);
    };
    exports.setcolumn = function () {
        internal.setcolumn(arguments[0]);
    };
    exports.element = function () {//数据元
        internal.element(arguments[0]);
    }
    exports.versionsetcolumn = function () {
        internal.versionsetcolumn(arguments[0]);
    }
    exports.sttablecolumn = function () {
        internal.sttablecolumn(arguments[0]);
    }
    exports.standardDict = function () {//值域
        internal.standardDict(arguments[0]);
    };
    exports.exceptValue = function () {//预期值
        internal.exceptValue(arguments[0]);
    };
    exports.regex = function () {//正则表达式
        internal.regex(arguments[0]);
    };
    exports.clientJobTables = function () {//采集任务表名
        internal.clientJobTables(arguments[0]);
    };
    exports.themeTemplate = function () {
        internal.themeTemplate(arguments[0]);
    }
});