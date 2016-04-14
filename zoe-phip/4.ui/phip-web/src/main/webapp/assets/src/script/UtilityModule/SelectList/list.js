/**
 * Created by linqinghuang on 2016/4/12.
 */
define(function (require, exports, module) {
    var BaseSelectList = require("{staticDir}/BaseSelectList/baseSelectList");
    var internal = {
        options:null,
        top: common.getTopWindowDom(),
        defaultParam: {
            winName: '',
            title: '',
            width: 560,
            height: 600,
            url: '/select/view/list',
            isAllowEmptySelect: true,
            emptyMsg: "请选择相应的列表数据!",
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
                            if (typeof(options["callback"] == "function")) {
                                var data = internal.top[options["selectParam"]["winCallback"]]();
                                if (options.isAllowEmptySelect || data.length > 0) {
                                    options["callback"](data);
                                    dialog.close();
                                } else {
                                    var emptyMsg = options["emptyMsg"];
                                    internal.top.common.jsmsgError(emptyMsg);
                                }
                            }
                            ;
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
    exports.init = function () {
        internal.init();
    };
})