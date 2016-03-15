/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    //工具管理
    var internal = {
        btns: {
            "add": '<a class="icon-grid icon-grid-add" title="添加" nssbtn="btnAdd"></a>',
            "edit": '<a class="icon-grid icon-grid-edit" title="编辑" nssbtn="btnEdit"></a>',
            "del": '<a class="icon-grid icon-grid-del" title="删除" nssbtn="btnDel"></a>',
            "refresh": '<a class="icon-grid icon-grid-refresh" title="刷新" nssbtn="btnRefresh"></a>'
        },
        btnBuild: function (options) {
            var btnbox = options["tools"]["btnbox"];
            var jqBtnBox = $(".NSS-Tree-Tools-Box");
            $.each(btnbox, function (index, item) {
                switch (index) {
                    case "add":
                    case "edit":
                    case "del":
                    case "refresh":
                        var jqBtn = $(internal.btns[index]).on("click", function () {
                            internal.event[index](options);
                        });
                        $(jqBtnBox).append(jqBtn);
                        break;
                }
            });
            var searchbox = options["tools"]["searchbox"];
        },

        searchBoxBuild: function (options, searchCallback) {
            var searchBoxParam = options[tools]["searchbox"];
            $.each(searchBoxParam, function (index, item) {
                switch (item["type"]) {
                    case "text":
                        var jqLi = $("<li></li>");
                        var jqLabel = $("<label></label>").addClass("label").html(item["label"] + "&nbsp;:&nbsp;");
                        var jqText = $("<input/>").attr({ type: 'text', name: item["name"] });
                        jqLi.append(jqLabel).append(jqText);

                        $(".tools-search-box").append(jqLi);
                        break;
                    case "dropdown":
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

                //搜索框
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
                addParam.button[0]["onclick"] = function (item, dialog) {
                    var top = common.getTopWindowDom();
                    var callback = function () {
                        //树重新加载（或者新增一个节点）
                    }
                    top[options["dialogParam"]["winCallback"]](callback);
                }
                top[options["dialogParam"]["winName"]] = common.dialog(addParam);
            },
            "edit": function (options) {
                var editParam = $.extend(true, {}, options["dialogParam"]["common"], options["dialogParam"]["edit"]);
                editParam["winName"] = options["dialogParam"]["winName"];
                editParam["winCallback"] = options["dialogParam"]["winCallback"];
                editParam.button[0]["onclick"] = function (item, dialog) {
                    var top = common.getTopWindowDom();
                    var callback = function () {
                        //修改树的一个节点
                    }
                    top[options["dialogParam"]["winCallback"]] = common.dialog(editParam);
                }
                top[options["dialogParam"]["winName"]] = common.dialog(editParam)
            },
            "del": function (options) {

            },
            "refresh": function (options) {
                //树重新加载
            }
        }

    }
    exports.tools = {
        btnBuild: internal.btnBuild,
        searchBoxBuild: internal.searchBox
    };
})