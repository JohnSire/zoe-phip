/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "",
                    deleteList: ""
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
                    url: '',
                    columns: [
                        {display: '机构代码', code: 'code', width: 120, align: 'left'},
                        {display: '机构名称', name: 'orgn_ame', width: 120, align: 'left'},
                        {display: '联系电话', name: 'telecom', width: 120, align: 'left'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                dialogParam: {
                    winName: "win_health_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_health_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增卫生管理机构"},
                    //编辑参数
                    edit: {title: "编辑卫生管理机构"},
                    common: {
                        url: 'organization/view/healthOrgDetail',
                        width: 1000,
                        height: 580
                    }
                }
            })
        }

    };
    exports.init = function () {
        internal.init();
    }
});