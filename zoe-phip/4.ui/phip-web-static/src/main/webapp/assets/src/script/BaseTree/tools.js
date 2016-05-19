/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    //工具管理
    var internal = {
        req: require("./req").req,
        btns: {
            "add": '<a class="icon-grid icon-grid-add" title="添加"></a>',
            "edit": '<a class="icon-grid icon-grid-edit" title="编辑"></a>',
            "del": '<a class="icon-grid icon-grid-del" title="删除"></a>',
        },
        btnBuild: function (options) {
            var btnbox = options["tools"]["btns"];
            var jqBtnBox = $("#" + options["btnBox"]);
            $.each(btnbox, function (index, item) {
                switch (index) {
                    case "add":
                    case "edit":
                    case "del":
                        if (options["tools"]["btns"][index]) {
                            var jqBtn = $(internal.btns[index]).on("click", function () {
                                internal.event[index](options);
                            });
                            $(jqBtnBox).append(jqBtn);
                        }
                        break;
                }
            });

        },
        event: {
            "add": function (options) {
                var addParam = $.extend(true, {}, options["dialogParam"]["common"], options["dialogParam"]["add"]);
                addParam["winName"] = options["dialogParam"]["winName"];
                addParam["winCallback"] = options["dialogParam"]["winCallback"];

                var treeObj = liger.get(options["treeId"]);
                if (treeObj) {
                    var data = treeObj.getSelected();
                    var validate = options["validate"]["add"];
                    if (validate["isValidate"]) {
                        var isPassValidate = true;
                        if (typeof (validate["fn"]) == "function" && data) {
                            isPassValidate = validate["fn"](data["data"]);
                        }
                        if (!isPassValidate) {
                            return;
                        }
                    }
                }


                addParam.buttons[0]["onclick"] = function (item, dialog) {
                    var top = common.getTopWindowDom();
                    var callback = function () {
                        internal.req.getList({url: options["url"]["getTreeList"]}, function (data) {
                            var treeId = options["treeId"];
                            var treeData = data.result.rows;
                            var treeObj = $("#" + treeId).ligerGetTreeManager();
                            treeObj.reloadNode(null, treeData);
                        });
                        //树重新加载（或者新增一个节点）
                    }
                    top[options["dialogParam"]["winCallback"]](callback);
                }

                var urlParam = "";
                if (typeof(addParam["otherUrlParam"]) == "function") {
                    $.each(addParam["otherUrlParam"](), function (key, value) {
                        urlParam += key + "=" + value + "&&";
                    });
                }
                addParam["url"] = addParam["url"] + "?" + urlParam;
                top[options["dialogParam"]["winName"]] = common.dialog(addParam);
            },
            "edit": function (options) {
                var editParam = $.extend(true, {}, options["dialogParam"]["common"], options["dialogParam"]["edit"]);
                editParam["winName"] = options["dialogParam"]["winName"];
                editParam["winCallback"] = options["dialogParam"]["winCallback"];
                var treeObj = liger.get(options["treeId"]);
                var id = "";
                if (treeObj) {
                    var data = treeObj.getSelected();
                    if (!data) {
                        common.jsmsgError("请选择相应的节点!");
                        return;
                    }
                    var validate = options["validate"]["edit"];
                    if (validate["isValidate"]) {
                        var isisPassValidate = true;
                        if (typeof (validate["fn"]) == "function") {
                            isPassValidate = validate["fn"](data["data"]);
                        }
                        if (!isPassValidate) {
                            return;
                        }
                    }
                    id = data["data"]["id"];
                }
                editParam["url"] = editParam["url"] + "?state=edit&&id=" + id;


                editParam.buttons[0]["onclick"] = function (item, dialog) {
                    var top = common.getTopWindowDom();
                    var callback = function () {
                        //修改树的一个节点
                        internal.req.getList({url: options["url"]["getTreeList"]}, function (data) {
                            var treeId = options["treeId"];
                            var treeData = data.result.rows;
                            var treeObj = $("#" + treeId).ligerGetTreeManager();
                            treeObj.reloadNode(null, treeData);
                        });
                    }
                    top[options["dialogParam"]["winCallback"]](callback);
                }
                top[options["dialogParam"]["winName"]] = common.dialog(editParam)
            },
            "del": function (options) {
                var delParam = $.extend(true, {}, options);

                var treeObj = liger.get(options["treeId"]);
                var id = "";
                if (treeObj) {
                    var data = treeObj.getSelected();
                    if (!data) {
                        common.jsmsgError("请选择要删除的节点!");
                        return;
                    }
                    var validate = options["validate"]["del"];
                    if (validate["isValidate"]) {
                        var isisPassValidate = true;
                        if (typeof (validate["fn"]) == "function") {
                            isPassValidate = validate["fn"](data["data"]);
                        }
                        if (!isPassValidate) {
                            return;
                        }
                    }
                    id = data["data"]["id"];
                }
                var param = {
                    id: id,
                    url: options["url"]["delTreeInfo"]
                }
                internal.req.deleteInfo(param, function (data) {
                    internal.req.getList({url: options["url"]["getTreeList"]}, function (data) {
                        var treeId = options["treeId"];
                        var treeData = data.result.rows;
                        var treeObj = $("#" + treeId).ligerGetTreeManager();
                        treeObj.reloadNode(null, treeData);
                    });
                })

            }
        }

    }
    exports.tools = {
        btnBuild: internal.btnBuild,
        searchBoxBuild: internal.searchBox
    };
})