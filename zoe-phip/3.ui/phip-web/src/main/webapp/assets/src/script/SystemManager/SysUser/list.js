﻿define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "user/delUserInfo",
                    deleteList: "user/delUserList"
                },
                tools: {
                    btnbox: {
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                gridParam: {
                    url: 'user/getUserList',
                    columns: [
                        {display: '名称', name: 'name', width: 120, align: 'left'},
                        {display: '登录名', name: 'loginName', width: 120, align: 'left'},
                        {
                            display: '注册时间',
                            name: 'createAt',
                            width: 120,
                            align: 'left',
                            type: 'date',
                            format: 'yyyy-MM-dd'
                        },
                        {
                            display: '状态',
                            name: 'state',
                            width: 80,
                            align: 'center',
                            icons: ['btnSwitch'],
                            iconsParam: {
                                'btnSwitch': {
                                    switchOff: 0,
                                    switchOn: 1,
                                    confirmMeg: '确认修改用户状态?',
                                    primaryKey: 'id',
                                    url: 'user/updateState'
                                }
                            }
                        },
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                dialogParam: {
                    winName: "win_user_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_user_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增用户"},
                    //编辑参数
                    edit: {title: "编辑用户"},
                    common: {
                        url: 'user/detail',
                        width: 360,
                        height: 260
                    }
                }
            })
        }

    };
    exports.init = function () {
        internal.init();
    }
});