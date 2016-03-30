define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var internal = {
        init: function () {
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                tools: {
                    btnbox: {
                        'custom': {
                            text: "调整菜单结构", click: function () {

                            }
                        },
                        'add': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                gridParam: {
                    checkbox: false,
                    height: "100%",
                    heightDiff: 29,
                    url: webRoot + '/menu/getMenuList',
                    columns: [
                        {display: '名称', name: 'name', width: 120, align: 'left'},
                        {display: '编码', name: 'code', width: 100, align: 'left'},
                        {display: '地址', name: 'address', width: 520, align: 'left'},
                        {display: '创建时间', name: 'createAt', width: 120, align: 'left', type: 'date'},
                        {display: '创建人', name: 'createBy', width: 120, align: 'left'},
                        {display:'状态', name:'state', width:80, align:'center', icons:['switch'], iconsParam:{'switch':{switchOff: 0, switchOn: 1,confirmMeg:'确认修改状态',primaryKey:'id',url:''}}},
                        {display: '操作', isSort: false, width: 120, icons: ['edit']}


                    ],
                    usePager: false,
                    tree: {
                        columnId: 'id',
                        columnName: 'name',
                        idField: 'id',
                        parentIDField: 'fkParentMenuId'
                    }
                },
                dialogParam: {
                    winName: "win_menu_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_menu_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增菜单"},
                    //编辑参数
                    edit: {title: "菜单信息"},
                    common: {
                        url: webRoot + '/menu/detail',
                        width: 360,
                        height: 280
                    }
                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});