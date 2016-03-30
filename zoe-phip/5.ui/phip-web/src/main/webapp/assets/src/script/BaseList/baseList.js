(function (exports) {
    var list = {};
    exports.list = list;
    //#region base
    var base = {
        p: {},
        grid: null,
        init: function (p) {
            // 初始化参数
            base.p = $.extend(true, {}, p || []);
            // 构建表格
            base.buildGrid();
            // 绑定按钮
            base.bindBtn();
            // 初始获取数据
            p.get("", base.loadData);
            // 绑定检索事件
            base.bindSearch();
            base.onSave();
        },
        buildGrid: function () {
            var jq_pane = $("#pane-list").empty(),
                param = {
                    columns: base.p.columns,
                    width: "100%",
                    height: 222,
                    checkbox: true,
                    isChecked: base.isChecked,
                    onCheckRow: base.onChecked,
                    onCheckAllRow: base.onCheckAllRows,
                    usePager: false
                };
            if (base.p.tree) {
                param = $.extend(true, {}, param, { tree: base.p.tree });
            }
            // init grid
            var grid = base.grid = jq_pane.ligerGrid(param);

            if (!base.p.multiple) {
                var chkObj = $("#pane-list .l-grid-hd-cell-checkbox");
                chkObj.empty().removeClass("l-grid-hd-cell-checkbox");
            }
        },
        bindBtn: function () {

        },
        searchEvent: function () {
            var jq_search = $("#txb-key");
            base.grid.reload({ Rows: [] });
            base.p.get($.trim(jq_search.val()), base.loadData);
        },
        bindSearch: function () {

            $("#btn_search").click(function () {
                base.searchEvent();
            });

            //var jq_search = $("#txb-key");
            //// IE only
            //try {
            //    jq_search.bind("input propertychange keyup", function () { base.searchEvent(); });
            //} catch (e) {

            //}
        },
        loadData: function (data) {

            var selectedData = [], newData = [];
            var uniqueField = base.p.uniqueField,
                storage = base.p.storage;
            for (var i = 0; i < storage.length; i++) {
                for (var j = 0; j < data.length; j++) {
                    if (storage[i][uniqueField] == data[j][uniqueField]) {
                        selectedData.push(data[j]);
                        data.splice(j, 1);
                    }
                }
            }
            newData = selectedData.concat(data);
            base.grid.reload({ Rows: newData });
        },
        isChecked: function (rowdata) {
            var uniqueField = base.p.uniqueField,
                storage = base.p.storage;
            for (var i = 0; i < storage.length; i++) {
                if (storage[i][uniqueField] == rowdata[uniqueField]) {

                    base.addItem(rowdata);
                    return true;
                }
            }
            return false;
        },
        onChecked: function (checked, data, rowid, rowdata) {

            $("#pane-list-selected").addClass("changed");//取消是否提示
            // 非多选内容
            if (!base.p.multiple) {
                var uniqueField = base.p.uniqueField,
                    rows = base.grid.getSelectedRows();
                for (var i = 0; i < rows.length; i++) {
                    var curObj = rows[i];
                    if (curObj[uniqueField] != data[uniqueField]) {
                        base.deleteItem(curObj);
                    }
                }
            }
            // 判断
            if (checked) {
                base.recursiveChecked(data);
            }
            else {
                base.deleteRecursiveChecked(data);
            }
        },
        //递归选中节点（在树形选择列表下用到）
        recursiveChecked: function (nodeData) {
            base.addItem(nodeData);
            base.pushData(nodeData);
            if (nodeData["children"]) {
                for (var i in nodeData["children"]) {
                    base.recursiveChecked(nodeData["children"][i]);
                }
            }
        },
        //递归取消选中节点（在树形选择列表下用到）
        deleteRecursiveChecked: function (nodeData) {
            base.deleteItem(nodeData);
            if (nodeData["children"]) {
                for (var i in nodeData["children"]) {
                    base.deleteRecursiveChecked(nodeData["children"][i]);
                }
            }
        },


        onCheckAllRows: function (checked) {
            $("#pane-list-selected").empty();
            $("#pane-list-selected").addClass("changed");//取消是否提示
            base.p.storage = [];
            //如果是全选
            if (checked) {
                var rows = base.grid.getSelectedRows();
                for (var i = 0; i < rows.length; i++) {
                    var curObj = rows[i];
                    base.addItem(curObj);
                    base.pushData(curObj);
                };
            }
        },
        addItem: function (data) {

            var jq_ul = $("#pane-list-selected");
            var itemclass = data[base.p.uniqueField].replace("(", "_").replace(")", "_").replace(/\./g, "_");
            if ($(".item.item-" + itemclass).length > 0) { return; }
            if (!base.p.multiple) { jq_ul.empty(); }

            var jq_li = $("<li/>").appendTo(jq_ul),
                jq_close = $("<a/>").appendTo(jq_li);
            //当class中有括号时换成下划线

            //end
            var _text = data[base.p.displayField];
            if (base.p.titalField != undefined) {
                _text = data[base.p.titalField] + "-" + _text;
            }

            jq_li
                .append(_text)
                .attr("title", _text)
                .addClass("item item-" + itemclass);

            jq_close
                .text("X")
                .addClass("close")
                .data("data", data)
                .click(function () { $("#pane-list-selected").addClass("changed"); base.deleteItem($(this).data("data")); });
        },
        deleteItem: function (data) {
            var uniqueField = base.p.uniqueField,
                storage = base.p.storage;
            // 移除grid中的勾选
            var rows = base.grid.getSelectedRows()
            for (var i = 0; i < rows.length; i++) {
                var curObj = rows[i];
                if (curObj[uniqueField] == data[uniqueField]) {
                    base.grid.unselect(curObj);
                    break;
                }
            }
            //ls去掉class中的括号换为下划线
            var itemclass = data[base.p.uniqueField].replace("(", "_").replace(")", "_").replace(/\./g, "_");
            //end

            // 去除Item
            $("#pane-list-selected li.item-" + itemclass).remove();
            // 移除存储的对象
            for (var i = 0; i < storage.length; i++) {
                if (storage[i][uniqueField] == data[uniqueField]) {
                    storage.splice(i, 1);
                    break;
                }
            }
        },
        pushData: function (data) {
            if (!base.p.multiple) { base.p.storage = []; }
            var uniqueField = base.p.uniqueField, storage = base.p.storage;
            for (var i = 0; i < storage.length; i++) {
                if (storage[i][uniqueField] == data[uniqueField]) {
                    storage.splice(i, 1, data); //如果是有相同的,则替换掉
                    return;
                }
            }
            base.p.storage.push(data);
        },
        //#region 通用字典查询用前端查询（xml格式，用前端查询）
        getWhere: function () {
            if (!base.grid) return null;
            var clause = function (rowdata, rowindex) {
                var key = $("#txb-key").val();
                var uniqueField = base.p.uniqueField;
                var displayField = base.p.displayField;
                return (rowdata[uniqueField].indexOf(key) > -1) || (rowdata[displayField].indexOf(key) > -1);
            }
            return clause;
        },
        localSearch: function () {
            var jq_search = $("#txb-key");
            // IE only
            if ("\v" == "v") { jq_search.bind("propertychange", function () { base.getSearch(); }); }
            else { jq_search[0].addEventListener("input", base.getSearch, false); };
        },
        getSearch: function () {
            base.grid.options.data = $.extend(true, {}, { Rows: top.result });
            base.grid.loadData(base.getWhere());
        },
        //#endregion

        onSave: function () {
            var top = common.getTopWindowDom();
            top.win_base_list_callback = function () {
                return base.p.storage;
            }
        }
    }
    //#endregion
    list.user = function () {
        var top = common.getTopWindowDom();
        if (top.list_user_storage == null) { top.list_user_storage = []; }
        base.init({
            storage: top.list_user_storage,
            multiple: true,
            uniqueField: "id",
            displayField: "name",
            columns: [
                { display: '名称', name: 'name', width: 180, align: 'left' },
                { display: '登录名', name: 'loginName', width: 265, align: 'left' }
            ],
            get: function (key, callback) {
                var url = "/user/list";
                var req = new Request(url);
                req.post({
                    data: { "keyWord": key },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };
    list.menu = function () {
        var top = common.getTopWindowDom();
        if (top.list_menu_storage == null) { top.list_menu_storage = []; }
        base.init({
            storage: top.list_menu_storage,
            multiple: true,
            uniqueField: "id",
            displayField: "name",
            tree: {
                columnId: 'id',
                columnName: 'name',
                idField: 'id',
                parentIDField: 'fkParentMenuId'
            },
            columns: [
                { display: '菜单名称', name: 'name', width: 310, align: 'left' },
                {
                    display: '状态',
                    name: 'state',
                    width: 71,
                    align: "center",
                    render: function (rowdata, index, value) {
                        if (value == 1) {
                            return "<span>启用</span>";
                            //return '<span class="btn-switch btn-switch-on"><b class="btn-switch-inner"></b></span>';
                        } else {
                            return "<span style='color:red;'>禁用</span>"
                            //return '<span class="btn-switch btn-switch-off"><b class="btn-switch-inner"></b></span>';
                        }
                    }
                }
            ],
            get: function (key, callback) {
                var url = "/menu/getMenuList";
                var req = new Request(url);
                req.post({
                    isTip:false,
                    data: { "keyWord": key },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };
    //来源模板表字段选择器
    list.sttablecolumn = function () {
        var top = common.getTopWindowDom();
        if (top.list_sttablecolumn_storage == null) { top.list_sttablecolumn_storage = []; }
        base.init({
            win: top.win_list_sttablecolumn,
            storage: top.list_sttablecolumn_storage,
            callback: top.list_sttablecolumn_callback,
            multiple: false,
            uniqueField: "ColumnCode",
            displayField: "ColumnName",
            columns: [
                { display: '编码', name: 'ColumnCode', width: 120, align: 'left' },
                { display: '名称', name: 'ColumnName', width: 315, align: 'left' }
            ],
            get: function (key, callback) {
                var url = "SourceTemplate/GetSourceTempTableColListByTableCode?table_code=" + $("#hid_mapping_table").val() + "&template_id=" + $("#hid_sourceTemplateId").val();
                var req = new Request(url);
                req.post({
                    data: { "key_word": key },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };
    //标准数据集选择器
    list.set = function () {
        var top = common.getTopWindowDom();
        if (top.list_set_storage == null) { top.list_set_storage = []; }
        base.init({
            win: top.win_list_set,
            storage: top.list_set_storage,
            callback: top.list_set_callback,
            multiple: true,
            uniqueField: "Id",
            displayField: "SetName",
            columns: [
                { display: '名称', name: 'SetCode', width: 120, align: 'left' },
                { display: '编码', name: 'SetName', width: 315, align: 'left' }
            ],
            get: function (key, callback) {
                var url = "Set/GetSetListJson";
                var req = new Request(url);
                req.post({
                    data: { "KeyWord": key },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };
    //版本数据集选择器
    list.versionset = function () {
        var top = common.getTopWindowDom();
        if (top.list_versionset_storage == null) { top.list_versionset_storage = []; }
        base.init({
            win: top.win_list_versionset,
            storage: top.list_versionset_storage,
            callback: top.list_versionset_callback,
            multiple: true,
            uniqueField: "FkSetId",
            displayField: "SetName",
            columns: [
                { display: '名称', name: 'SetName', width: 210, align: 'left' },
                { display: '编码', name: 'SetCode', width: 240, align: 'left' }
            ],
            get: function (key, callback) {
                var url = "StandardVersion/GetSet";
                var req = new Request(url);
                req.post({
                    data: { "fkStandardId": $("#hid_standardId").val(), "key": key },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };
    //标准数据集字段选择器
    list.setcolumn = function () {
        var top = common.getTopWindowDom();
        if (top.list_set_column_storage == null) { top.list_set_column_storage = []; }
        base.init({
            win: top.win_list_set_column,
            storage: top.list_set_column_storage,
            callback: top.list_set_column_callback,
            multiple: true,
            uniqueField: "Id",
            displayField: "ColumnName",
            columns: [
                { display: '名称', name: 'ColumnName', width: 120, align: 'left' },
                { display: '编码', name: 'ColumnCode', width: 315, align: 'left' }
            ],
            get: function (key, callback) {
                var url = "Set/GetRsSetElementList?id=" + $("#hid_setId").val();
                var req = new Request(url);
                req.post({
                    data: { "KeyWord": key },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };
    //数据元选择器
    list.element = function () {
        var top = common.getTopWindowDom();
        if (top.list_element_storage == null) { top.list_element_storage = []; }
        base.init({
            win: top.win_list_element,
            storage: top.list_element_storage,
            callback: top.list_element_callback,
            multiple: false,
            uniqueField: "Id",
            displayField: "Name",
            columns: [
                { display: '名称', name: 'Name', width: 120, align: 'left' },
                { display: '编码', name: 'Code', width: 315, align: 'left' }
            ],
            get: function (key, callback) {
                var url = "DataElement/GetDataList";
                var req = new Request(url);
                req.post({
                    data: { "key": key },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });
    }
    //版本数据集字段选择器
    list.versionsetcolumn = function () {
        var top = common.getTopWindowDom();
        if (top.list_version_set_column_storage == null) { top.list_version_set_column_storage = []; }
        base.init({
            win: top.win_list_version_set_column,
            storage: top.list_version_set_column_storage,
            callback: top.list_version_set_column_callback,
            multiple: true,
            uniqueField: "FkColumnId",
            displayField: "ColumnName",
            columns: [
                { display: '名称', name: 'ColumnName', width: 120, align: 'left' },
                { display: '编码', name: 'ColumnCode', width: 315, align: 'left' }
            ],
            get: function (key, callback) {
                var url = "StandardVersion/GetColumn";
                var req = new Request(url);
                req.post({
                    data: { "fkStandardId": $("#hid_standardId").val(), "fkSetId": $("#hid_standardSetId").val(), "key": key },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };
    //采集路径
    list.collectPath = function () {
        var top = common.getTopWindowDom();
        if (top.list_collect_path_storage == null) { top.list_collect_path_storage = []; }
        base.init({
            storage: top.list_collect_path_storage,
            multiple: false,
            uniqueField: "Id",
            displayField: "Name",
            columns: [
                { display: '路径名称', name: 'Name', width: 120, align: 'left' },
                { display: '编码', name: 'Code', width: 315, align: 'left' }
            ],
            get: function (key, callback) {
                var url = "RouteManage/GetCollectPathList";
                var req = new Request(url);
                req.post({
                    data: { "key": key, page: 1, pageSize: 1000 },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };

    //采集模板
    list.collectTemp = function (listData) {
        var top = common.getTopWindowDom();
        if (top.list_collectTemp_storage == null) { top.list_collectTemp_storage = []; }
        base.init({
            win: top.win_list_collectTemp,
            storage: top.list_collectTemp_storage,
            callback: top.list_collectTemp_callback,
            multiple: false,
            uniqueField: "Id",
            displayField: "TemplateName",
            columns: [
                { display: '模板名称', name: 'TemplateName', align: 'left' },
                { display: '描述', name: 'Descr', align: 'left' },
            ],
            get: function (key, callback) {
                var url = "CollectionTemplate/GetCollectionTemplateList";
                var req = new Request(url);
                req.post({
                    data: $.extend({}, listData, { "keyWord": key }),
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };

    //任务项表名
    list.clientJobTables = function (jobPushId, isMultiple) {
        if (!isMultiple || isMultiple == "undefined") {
            isMultiple = false;
        }
        var top = common.getTopWindowDom();
        if (top.list_clientjob_table_storage == null) { top.list_clientjob_table_storage = []; }
        base.init({
            win: top.win_list_clientjob_table,
            storage: top.list_clientjob_table_storage,
            callback: top.list_clientjob_table_callback,
            multiple: isMultiple,
            uniqueField: "Id",
            displayField: "Name",
            columns: [
                { display: '编码', name: 'Code', align: 'left', width: 150 },
                { display: '名称', name: 'Name', align: 'left', width: 220 }
            ],
            get: function (key, callback) {
                var url = "ClientManager/GetClientJobTables";
                var req = new Request(url);
                req.post({
                    data: { "key": key, clientTaskId: jobPushId, "pagesize": 999999},
                    success: function (data) {
                        var enableData = [];
                        for (var i = 0; i < data.result.total; i++) {
                            enableData.push(data.result.rows[i]);
                        }
                        callback(enableData);
                    }
                });
            }
        });

    };

    //正则表达式
    list.regex = function () {
        var top = common.getTopWindowDom();
        if (top.list_regex_storage == null) { top.list_regex_storage = []; }
        base.init({

            storage: top.list_regex_storage,

            multiple: false,
            uniqueField: "Id",
            displayField: "Name",
            columns: [
                { display: '名称', name: 'Name', align: 'left' },
                { display: '表达式', name: 'ExpressionValue', align: 'left' },
            ],
            get: function (key, callback) {
                var url = "RegEx/GetByKey";
                var req = new Request(url);
                req.post({
                    data: { "key": key },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };
    //where use：1、数据元-值域  2、预期值-值域
    list.standardDict = function () {

        var FkVersionId = "";
        var FkSourceId = "";

        var top = common.getTopWindowDom();

        if (!top.list_dict_storage) { top.list_dict_storage = []; }
        var storage = top.list_dict_storage;
        $.each(storage, function (index, item) {
            FkVersionId = item.FkVersionId;
            FkSourceId = item.FkSourceId;
        })
        var dictId = common.getParamFromUrl("id") ? common.getParamFromUrl("id") : "";

        if (dictId) {
            top.list_dict_storage = [];
            var req = new Request("StandardDict/GetDictCatalogById");
            req.post({
                isTip: false,
                async: false,

                data: { "id": dictId },
                success: function (data) {
                    if (!data.result) return false;
                    var info = data.result;
                    FkVersionId = info.FkVersionId;
                    FkSourceId = info.FkSourceId;
                    var list = [];
                    var item = {};
                    item.Id = info.Id;
                    item.DictName = info.DictName;
                    item.FkVersionId = info.FkVersionId;
                    item.FkSourceId = info.FkSourceId;
                    list.push(item);
                    top.list_dict_storage = list;
                }
            });
        }

        search = $("#btn_search");

        var version = $("<p class='list' id='version' />")
            .append('<label class="label" for="chooseVersion">版本：</label>');
        var chooseVersion = $('<select class="select-big" id="chooseVersion"></select>');
        chooseVersion
            .appendTo(version).
            change(function () {
                $("#btn_search").click();
            });

        var source = $("<p class='list' id='source'/>")
            .append('<label class="label" for="chooseSource">来源：</label>');
        var chooseSource = $('<select  class="select-big" id="chooseSource"></select>');
        chooseSource
            .appendTo(source)
            .change(function () {
                $("#btn_search").click();
            });


        search.parent().before(version).before(source);


        var url = "StandardDict/GetDictVersion";
        var req = new Request(url);
        req.post({
            async: false,
            success: function (data) {
                var list = data.result;
                if (list) {
                    var option = "";
                    $.each(list, function (index, item) {

                        option += "<option value=\"" + item.Id + "\">" + item.VersionName + "</option>"
                    })
                    $("#chooseVersion").append(option);
                }
            }
        });

        var req = new Request("SystemDict/GetSysDictItemListByCode");
        req.post({
            isTip: false,
            async: false,

            data: { "catalogCode": "DICTIONARY_SOURCE" },
            success: function (data) {
                var list = data.result;

                if (list) {
                    var option = "";
                    $.each(list, function (index, item) {

                        option += "<option value=\"" + item.Id + "\">" + item.Name + "</option>"
                    })
                    $("#chooseSource").append(option);
                }
            }
        });

        //  var multiple = common.getParamFromUrl("multiple")=="true" ? true : false;
        var uniqueField = common.getParamFromUrl("uniqueField") == "DictCode" ? "DictCode" : "Id";
        if (FkVersionId) {
            $("#chooseVersion").val(FkVersionId);
            $("#chooseSource").val(FkSourceId);
        }


        base.init({
            storage: top.list_dict_storage,
            multiple: false,
            uniqueField: uniqueField,
            displayField: "DictName",
            columns: [
                { display: '名称', name: 'DictName', align: 'left' },
                { display: '编码', name: 'DictCode', align: 'left' },
            ],
            get: function (key, callback) {
                var url = "StandardDict/GetDictCatalogList";
                var req = new Request(url);
                req.post({
                    data: {
                        "key": key,
                        "fkVersionId": $("#chooseVersion").val(),
                        "fkSourceId": $("#chooseSource").val()
                    },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };

    //质控模板-预期值选择
    list.exceptValue = function () {
        var fkTemplateId = common.getParamFromUrl("fkTemplateId");
        var FkSetId = common.getParamFromUrl("FkSetId");
        var FkElementId = common.getParamFromUrl("FkElementId");
        ExpectTypeValue = [
          { "Code": "0", "Name": "区间" },
          { "Code": "1", "Name": "固定值" },
          { "Code": "2", "Name": "值域" },
          { "Code": "3", "Name": "正则表达式" },
          { "Code": "4", "Name": "查询语句" },
          { "Code": "5", "Name": "数值" }
        ];
        var top = common.getTopWindowDom();
        if (top.list_exceptValue_storage == null) { top.list_exceptValue_storage = []; }
        base.init({

            storage: top.list_exceptValue_storage,

            multiple: false,
            uniqueField: "Id",
            displayField: "TypeName",
            columns: [
                  { display: '类型名', name: 'TypeName', align: 'left', width: "20%" },
                  {
                      display: '预期类型', width: "20%",
                      render: function (item) {
                          var h = "";
                          var list = ExpectTypeValue;
                          $.each(list, function (index, single) {
                              if (item.ExpectType == single.Code - 0) {
                                  h = single.Name;
                                  return false;
                              }
                          })
                          return h;
                      }
                  },
                  {
                      display: '预期值',
                      width: "55%",
                      render: function (item) {
                          var xml;
                          if ($.browser.msie & parseInt($.browser.version) < 9) {// 

                              xml = new ActiveXObject("Microsoft.XMLDOM");
                              xml.async = false;
                              xml.loadXML(item.ExpectValue);
                          } else {
                              xml = $(item.ExpectValue);
                          }
                          var h = getExpectTypeValue(item.ExpectType, xml);
                          return h;
                      }
                  },
            ],
            get: function (key, callback) {
                var url = "ruleManager/GetDqRuleExpectValueByTemplateId";
                var req = new Request(url);
                req.post({
                    data: { "fkTemplateId": fkTemplateId, "key": key, "FkSetId": FkSetId, "fkColumnId": FkElementId },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };

    function getExpectTypeValue(type, xml) {
        var h = "";
        switch (type) {
            case 0://区间
                var list = $(xml).find("interval");
                $.each(list, function (index, item) {
                    var min = $(item).attr("from");
                    var max = $(item).attr("to");
                    h += min + "<=取值<=" + max;
                    if (index != list.length - 1) {
                        h += ",";
                    }
                })
                break;

            case 1://固定值
                var list = $(xml).find("fixed");
                $.each(list, function (index, item) {
                    var value = $(item).attr("value");

                    h += decodeURIComponent(decode(value));
                    //h += value;
                    if (index != list.length - 1) {
                        h += ",";
                    }
                })
                break;

            case 2://值域
                var list = $(xml).find("dictionary");
                $.each(list, function (index, item) {
                    var key = $(item).attr("key");
                    var dictName = $(item).attr("dictName");
                    var code_col = $(item).attr("code-col");
                    var key_col = $(item).attr("key-col");
                    var table = $(item).attr("table");
                    h += key;
                    if (index != list.length - 1) {
                        h += ",";
                    }
                })
                break;


            case 3://正则表达式
                var list = $(xml).find("regex");
                $.each(list, function (index, item) {
                    var value = $(item).attr("value");
                    h += decode(value);
                    //h += value;

                })
                break;
            case 4://查询语句
                var list = $(xml).find("sql");
                $.each(list, function (index, item) {
                    var value = $(item).attr("value");
                    h += decode(value);
                    //h += value;

                })
                break;


            case 5://数值
                var list = $(xml).find("number");
                $.each(list, function (index, item) {
                    var value = $(item).attr("value");
                    h += decode(value);
                    //h += value;
                    if (index != list.length - 1) {
                        h += ",";
                    }
                })
                break;
        }
        return h;
    }


    //提交路径
    list.submitPath = function () {
        var top = common.getTopWindowDom();
        if (top.list_submit_path_storage == null) { top.list_submit_path_storage = []; }
        base.init({
            storage: top.list_submit_path_storage,
            multiple: true,
            uniqueField: "Id",
            displayField: "Name",
            columns: [
                { display: '名称', name: 'Name', align: 'left' },
            ],
            get: function (key, callback) {
                var url = "RouteManage/GetSubmitPathList";
                var req = new Request(url);
                req.post({
                    data: { "key": key, page: 1, pageSize: 1000 },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };

    //质控模板选择
    list.qualityTemp = function (listData) {
        var top = common.getTopWindowDom();
        if (top.list_quality_storage == null) { top.list_quality_storage = []; }
        base.init({
            storage: top.list_quality_storage,
            multiple: false,
            uniqueField: "Id",
            displayField: "Name",
            columns: [
                { display: '名称', name: 'Name', align: 'left' },
            ],
            get: function (key, callback) {
                var url = "QualityControlTemplate/GetByKey";
                var req = new Request(url);
                req.post({
                    data: $.extend({}, listData, { "key": key }),
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });

    };

    //推送模板
    list.pushTemp = function (listData) {
        var top = common.getTopWindowDom();
        if (top.list_push_storage == null) { top.list_push_storage = []; }
        base.init({
            win: top.win_list_job_push_dialog,
            storage: top.list_job_push_storage,
            callback: top.list_job_push_callback,
            multiple: false,
            uniqueField: "Id",
            displayField: "TemplateName",
            columns: [
                { display: '模板名称', name: 'TemplateName', align: 'left' },
                { display: '描述', name: 'Descr', align: 'left' },
            ],
            get: function (key, callback) {
                var url = "CollectionTemplate/GetPushTemplateList";
                var req = new Request(url);
                req.post({
                    data: $.extend({}, listData, { "keyWord": key }),
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });
    }

    list.themeTemplate = function () {
        var top = common.getTopWindowDom();
        if (top.list_theme_template_storage == null) { top.list_theme_template_storage = []; }
        base.init({
            win: top.win_list_theme_template_dialog,
            storage: top.list_theme_template_storage,
            callback: top.list_theme_template_callback,
            multiple: false,
            uniqueField: "Id",
            displayField: "Name",
            columns: [
                { display: '模板名称', name: 'Name', align: 'left', width: 380 },
            ],
            get: function (key, callback) {
                var url = "ThemeViewTemplate/GetByKey";
                var req = new Request(url);
                req.post({
                    data: { "keyWord": key },
                    success: function (data) {
                        if (data.result) {
                            var enableData = [];
                            for (var i = 0; i < data.result.total; i++) {
                                enableData.push(data.result.rows[i]);
                            }
                            callback(enableData);
                        }
                    }
                });
            }
        });
    }

})(window)