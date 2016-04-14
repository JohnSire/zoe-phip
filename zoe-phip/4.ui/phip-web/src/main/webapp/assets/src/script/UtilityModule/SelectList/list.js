/**
 * Created by linqinghuang on 2016/4/12.
 */
define(function (require, exports, module) {
    var BaseSelectList = require("{staticDir}/BaseSelectList/baseSelectList");
    var internal = {
        top: common.getTopWindowDom(),
        defaultParam: {
            winName: '',
            title: '',
            width: 560,
            height: 600,
            url:'/select/view/list',
            isAllowEmptySelect: true,
            emptyMsg: "请选择相应的列表数据!",
            selectParam: null,//选择列表参数
            callback: null//回调函数

        },
        //调用
        invoke: function (fnName, options) {
            var options = $.extend(true, {}, internal.defaultParam, internal["fn"][fnName], options);
            var selectParam = $.extend(true, {}, internal["fn"][fnName]["selectParam"], options["selectParam"]);
            internal.top["win_select_list_param"] = selectParam;
            internal.top[options["winName"]] = common.dialog({
                title: options["title"],
                url:options["url"],
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
        fn: {
            user: {
                winName: 'win_user_select_list',
                title: '用户选择列表',
                selectParam: {
                    storage: [],
                    displayField: 'loginName',
                    valueField: 'id',
                    gridParam: {
                        url: webRoot + '/user/getUserList',
                        columns: [
                            {display: '名称', name: 'name', width: 180, align: 'left'},
                            {display: '登录名', name: 'loginName', width: 220, align: 'left'}
                        ],
                        usePage: true,
                        height: 250
                    },
                    multiselect: true,//是否多选:true为多选，false为单选
                    winCallback: 'win_user_select_list_callback'
                }
            },
            menu: {
                winName: 'win_menu_select_list',
                title: '菜单选择列表',
                selectParam: {
                    storage: [],
                    displayField: 'name',
                    valueField: 'id',
                    gridParam: {
                        url: webRoot + '/menu/getMenuList',
                        columns: [
                            {display: '名称', name: 'name', width: 120, align: 'left'},
                            {display: '编码', name: 'code', width: 100, align: 'left'}
                        ],
                        usePager: false,
                        tree: {
                            columnId: 'id',
                            columnName: 'name',
                            idField: 'id',
                            parentIDField: 'fkParentMenuId'
                        },
                        height: 250
                    },
                    multiselect: true,//是否多选:true为多选，false为单选
                    winCallback: 'win_menu_select_list_callback'
                }
            },

        }
    };
    exports.invoke = function (fnName, options) {
        internal.invoke(fnName, options);
    };
    exports.init = function () {
        internal.init();
    };
})