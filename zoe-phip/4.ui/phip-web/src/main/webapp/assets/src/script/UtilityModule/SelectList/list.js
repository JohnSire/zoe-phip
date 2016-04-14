/**
 * Created by linqinghuang on 2016/4/12.
 */
define(function (require, exports, module) {
    var BaseSelectList = require("{staticDir}/BaseSelectList/baseSelectList");
    var internal = {
        top: common.getTopWindowDom(),
        //调用
        invoke: function (fnName, options) {
            var options = $.extend(true, {}, internal["fn"][fnName], options);
            internal.top["win_select_param"] = options;
            internal.top[options["winName"]] = common.dialog({
                title:options["winTitle"],
                url: '../select/view/list',
                width: 560,
                height: 600,
                buttons: [
                    {
                        text: '确定', onclick: function (item, dialog) {
                        if (typeof(options["callback"] == "function")) {
                            var data = internal.top[options["winCallback"]]();
                            options["callback"](data)
                        };
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

            var options = $.extend(true, {}, internal.top["win_select_param"]);
            //通用的对外方法
            var selectList = new BaseSelectList(options);
            internal.top[options["winCallback"]] = function () {
                var data = selectList.getSelected();
                return data;
            }


        },
        fn: {
            user: {
                winName: 'win_user_select_list',
                winCallback: 'win_user_select_list_callback',
                winTitle:'用户选择列表',
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
                multiselect: true//是否多选:true为多选，false为单选
            },
            menu: {
                winName: 'win_menu_select_list',
                winCallback: 'win_menu_select_list_callback',
                winTitle:'菜单选择列表',
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
                multiselect: true//是否多选:true为多选，false为单选
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