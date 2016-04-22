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
                        {display: '机构(科室)分类', code: 'code', width: 120, align: 'left'},
                        {display: '机构(科室)代码', name: 'orgn_ame', width: 120, align: 'left'},
                        {display: '机构(科室)名称', name: 'telecom', width: 120, align: 'left'},
                        {display: '联系电话', name: 'telecom', width: 120, align: 'left'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                dialogParam: {
                    winName: "win_medical_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_medical_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增医疗机构(科室)"},
                    //编辑参数
                    edit: {title: "编辑医疗机构(科室)"},
                    common: {
                        url: 'organization/view/medicalOrgDetail',
                        width: 980,
                        height: 500
                    }
                }
            })
        }

    };
    exports.init = function () {
        internal.init();
    }
});