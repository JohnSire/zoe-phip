/**
 * Created by linqinghuang on 2016/4/12.
 */
define(function (require, exports, module) {
    var BaseSelectList = require("{staticDir}/BaseSelectList/baseSelectList");
    var internal = {
        options: null,
        top: common.getTopWindowDom(),
        defaultParam: {
            winName: '',
            title: '',
            width: 560,
            height: 600,
            url: '/select/view/list',
            isAllowEmptySelect: false,//是否允许为空
            emptyMsg: "请选择相应的列表数据!",
            fkNullContent: '',//如果存在外键id，但不存在外键对象时，显示内容
            selectParam: null,//选择列表参数
            callback: null//回调函数
        },

        //调用
        invoke: function (fnName, options) {
            internal.options = options;
            internal.getFn(fnName, function () {
                var options = $.extend(true, {}, internal.defaultParam, internal["fn"][fnName], internal.options);
                var selectParam = $.extend(true, {}, internal["fn"][fnName]["selectParam"], internal.options["selectParam"]);
                internal.top["win_select_list_param"] = selectParam;
                internal.top[options["winName"]] = common.dialog({
                    title: options["title"],
                    url: options["url"],
                    width: options["width"],
                    height: options["height"],
                    buttons: [
                        {
                            text: '确定', onclick: function (item, dialog) {
                            if (typeof(options["callback"]) == "function") {
                                var data = internal.top[options["selectParam"]["winCallback"]]();
                                if (options.isAllowEmptySelect || data.length > 0) {
                                    options["callback"](data);
                                    dialog.close();
                                } else if (data.length == 0) {
                                    var emptyMsg = options["emptyMsg"];
                                    internal.top.common.jsmsgError(emptyMsg);
                                }
                            }
                        }
                        },
                        {
                            text: '取消', onclick: function (item, dialog) {
                            dialog.close();
                        }
                        }
                    ]
                });
            });
        },

        dialog: function (fnName, options) {
            internal.options = options;
            var targetObj = options["target"];
            var name = options.name;//字典名称
            var fkObj = options.fkObj;//外键对象
            var displayField = options.displayField;//显示内容
            //初始化绑定值
            $('input[name="' + name + '"]').on("setValue", function (event, argument) {

                options["selectParam"]["selfId"]=argument["id"];

                $(targetObj).find("input").val(argument[name]);
                if (argument[fkObj]) {
                    $(targetObj).find(".text-line-content").text(argument[fkObj][displayField]);
                } else {
                    //菜单特殊处理
                    if (argument[name] && !argument[fkObj]) {
                        $(targetObj).find(".text-line-content").text(options["fkNullContent"]);
                    }
                }
            });

            $(targetObj).on("click", function () {
                internal.getFn(fnName, function () {
                    var options = $.extend(true, {}, internal.defaultParam, internal["fn"][fnName], internal.options);
                    var selectParam = $.extend(true, {}, internal["fn"][fnName]["selectParam"], internal.options["selectParam"]);
                    selectParam["stroage"] = function () {
                        var data = [];
                        var value = $('input[name="' + name + '"]').val();
                        var text = $(targetObj).find(".text-line-content").text();
                        if (value && text) {
                            var dataInfo = {};
                            var valueField = selectParam["valueField"];
                            var displayField = selectParam["displayField"];
                            dataInfo[valueField] = value;
                            dataInfo[displayField] = text;
                            data.push(dataInfo);
                        }
                        return data;
                    }();
                    internal.top["win_select_list_param"] = selectParam;
                    var buttonsExtend = options["buttonsExtend"] || [];
                    var buttons = [{
                        text: '确定', onclick: function (item, dialog) {
                            if (typeof(options["callback"]) == "function") {
                                var data = internal.top[options["selectParam"]["winCallback"]]();
                                if (options.isAllowEmptySelect || data.length > 0) {
                                    options["callback"](data);
                                    dialog.close();
                                } else if (data.length == 0) {
                                    var emptyMsg = options["emptyMsg"];
                                    internal.top.common.jsmsgError(emptyMsg);
                                }
                            } else {
                                var bindData = function (data) {
                                    if (options.isAllowEmptySelect || data.length > 0) {
                                        var valueField = selectParam["valueField"];
                                        var displayField = selectParam["displayField"];
                                        if (data.length > 0) {
                                            $('input[name="' + options["name"] + '"]').val(data[0][valueField]);
                                            $(targetObj).find(".text-line-content").text(data[0][displayField]);
                                        } else {
                                            $('input[name="' + options["name"] + '"]').val("");
                                            $(targetObj).find(".text-line-content").text("");
                                        }
                                        dialog.close();
                                    } else if (data.length == 0) {
                                        var emptyMsg = options["emptyMsg"];
                                        internal.top.common.jsmsgError(emptyMsg);
                                    }
                                };
                                var data = internal.top[options["selectParam"]["winCallback"]]();
                                bindData(data)
                            }

                        }
                    },
                        {
                            text: '取消', onclick: function (item, dialog) {
                            dialog.close();
                        }
                        }];

                    $.each(buttons, function (index, item) {
                        buttonsExtend.push(item);
                    })

                    internal.top[options["winName"]] = common.dialog({
                        title: options["title"],
                        url: options["url"],
                        width: options["width"],
                        height: options["height"],
                        buttons: buttonsExtend
                    });
                });
            })
        },

        //初始化
        init: function () {
            var selectParam = $.extend(true, {}, internal.top["win_select_list_param"]);
            //通用的对外方法
            var selectList = new BaseSelectList(selectParam);
            internal.top[selectParam["winCallback"]] = function () {
                var data = selectList.getSelected();
                return data;
            }
        },
        fn: {},
        getFn: function (fnName, callback) {
            seajs.use('{dir}/UtilityModule/SelectList/options/' + fnName, function (a) {
                internal.fn[fnName] = a[fnName];
                if (typeof(callback) == "function") {
                    callback();
                }
            });
        }
    };
    exports.invoke = function (fnName, options) {
        internal.invoke(fnName, options);
    };
    exports.dialog = function (fnName, options) {
        internal.dialog(fnName, options);
    }
    exports.init = function () {
        internal.init();
    };
})