/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    //工具管理
    var internal = {
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
                        var jqBtn = $(internal.btns[index]).on("click", function () {
                            internal.event[index](options);
                        });
                        $(jqBtnBox).append(jqBtn);
                        break;
                }
            });

        },
        event: {
            "add": function (options) {
                var addParam = $.extend(true, {}, options["dialogParam"]["common"], options["dialogParam"]["add"]);
                addParam["winName"] = options["dialogParam"]["winName"];
                addParam["winCallback"] = options["dialogParam"]["winCallback"];
                addParam.buttons[0]["onclick"] = function (item, dialog) {
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
                editParam.buttons[0]["onclick"] = function (item, dialog) {
                    var top = common.getTopWindowDom();
                    var callback = function () {
                        //修改树的一个节点
                    }
                    top[options["dialogParam"]["winCallback"]] = common.dialog(editParam);
                }
                top[options["dialogParam"]["winName"]] = common.dialog(editParam)
            },
            "del": function (options) {

            }
        }

    }
    exports.tools = {
        btnBuild: internal.btnBuild,
        searchBoxBuild: internal.searchBox
    };
})