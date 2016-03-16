/**
 * Created by linqinghuang on 2016/3/15.
 */

define(function (require, exports, module) {
    var internal = {
        req: require("./req.js").req,
        grid: require("./buildGrid").grid,
        init: function (options) { },
        btns: {
            "add": '<li><input type="button" nssbtn="btnAdd" class="btn btn-add" value="新增"></li>',
            "del": '<li><input type="button" nssbtn="btnDel" class="btn btn-del" value="删除"></li>',
            "refresh": '<li><input type="button" nssbtn="btnRefresh" class="btn btn-refresh" value="刷新" /></li>',
            "import": '<li><input type="button" nssbtn="btnImport" class="btn btn-import" value="导入" /></li>',
            "export": '<li><input type="button" nssbtn="btnExport" class="btn btn-export" value="导出" /></li>',
            "custom": function (text) {
                return '<li><a class="btn-little btn-little-blue"><span class="text-outer">' + text + '<span class="text-inner">' + text + '</span></span></a></li>';
            }
        },
        btnBuild: function (options) {
            var btnbox = options["tools"]["btnbox"];
            var jqbtnBox = $(".NSS-Tools-Outer").find(".tools-btn-box");
            $.each(btnbox, function (index, item) {
                switch (index) {
                    case "add":
                    case "del":
                    case "refresh":
                    case "import":
                    case "export":
                        var jqBtn = $(internal.btns[index]).on("click", function () {
                            internal.event[index](options);
                        });
                        $(".tools-btn-box").append(jqBtn);
                        break;
                    default:
                        var btnHtml = internal.btns["custom"](item["text"]);
                        var jqBtn = $(btnHtml).on("click", function () {
                            item["click"]();
                        });
                        $(".tools-btn-box").append(jqBtn);
                        break;

                };
            });
        },
        searchBox: function (options, searchCallback) {
            var searchBoxParam = options["tools"]["searchbox"];
            $.each(searchBoxParam, function (index, item) {
                switch (item["type"]) {
                    case "text":
                        var jqLi = $("<li></li>");
                        var jqLabel = $("<label></label>").addClass("label").html(item["label"] + "&nbsp;:&nbsp;");
                        var jqText = $("<input/>").attr({ type: 'text', name: item["name"] });
                        jqLi.append(jqLabel).append(jqText);
                        $(".tools-search-box").append(jqLi);
                        break;
                }
            });

            if (searchBoxParam.length > 0) {
                var data = {};
                var jqLi = $("<li></li>").on("click", function () {
                    if (typeof (searchCallback) == "function") {
                        $.each(searchBoxParam, function (index, item) {
                            data[item["name"]] = $(".tools-search-box").find("[name='" + item["name"] + "']").val();
                        });
                        searchCallback(data);
                    }
                });
                var jqBtn = $("<input/>").attr({ type: "button" }).addClass("btn btn-search").val("搜索");
                jqLi.append(jqBtn);
                $(".tools-search-box").append(jqLi);
            }



        },
        event: {
            "add": function (options) {
                var addParam = $.extend(true, {}, options["dialogParam"]["common"], options["dialogParam"]["add"]);
                addParam["winName"] = options["dialogParam"]["winName"];
                addParam["winCallback"] = options["dialogParam"]["winCallback"];
                addParam.buttons[0]["onclick"] = function (item, dialog,submited) {
                    var top = common.getTopWindowDom();
                    var callback = function () {
                        var gridObj = liger.get("gridObj");
                        gridObj.reload();
                    }
                    top[options["dialogParam"]["winCallback"]](callback,submited);
                }
                top[options["dialogParam"]["winName"]] = common.dialog(addParam);
            },
            "del": function (options) {
                internal.grid.deleteList(options);
            },
            "refresh": function (options) { },
            "import": function (options) { },
            "export": function (options) { }
        }
    };
    exports.init = function (options) {
        internal.init(options);
    }
    exports.tools = {
        btnBuild: internal.btnBuild,
        searchBoxBuild: internal.searchBox
    }

});
