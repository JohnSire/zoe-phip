define(function (require, exports, module) {
    var request = require("./req.js").req;
    var BaseGrid = require("../../baseGrid/baseGrid.js");

    var internal = {
        init: function () {
            internal.__grid = new BaseGrid({
                //工具条
                tools: {
                    buttons: [{
                        id: "btnMenuConfig", onClick: function () { internal.menuTree(); }//导入事件
                    }],
                    searchbox: [
                        { descr: '关键字', name: 'keyWord', type: 'text' }
                    ]
                },
                //表格参数
                gridParam: {
                    checkbox: false,
                    height: "100%",
                    heightDiff:29,
                    url: webRoot + 'SystemMenu/GetMenuList',
                    columns: [
                    { display: '名称', name: 'Name', width: 120, align: 'left' },
                    { display: '编码', name: 'Code', width: 100, align: 'left' },
                    { display: '地址', name: 'Address', align: 'left' },
                    { display: '创建时间', name: 'CreateAt', width: 120, align: 'left', type: 'date' },
                    { display: '创建人', name: 'CreateBy', width: 120, align: 'left' },
                    {
                        display: '状态',
                        name: 'State',
                        width: 80,
                        align: "center",
                        render: function (rowdata, index, value) {
                            if (value == 1) {
                                return '<a href="javascript:changeState(\'' + rowdata.Id + '\', 0);" class="btn-switch-outer"><span class="btn-switch btn-switch-on"><b class="btn-switch-inner"></b></span></a>';
                            } else {
                                return '<a href="javascript:changeState(\'' + rowdata.Id + '\', 1);" class="btn-switch-outer"><span class="btn-switch btn-switch-off"><b class="btn-switch-inner"></b></span></a>';
                            }
                        }
                    }
                    ],
                    usePager: false,
                    //alternatingRow: false,
                    tree: {
                        columnId: 'Id',
                        columnName: 'Name',
                        idField: 'Id',
                        parentIDField: 'FkParentMenuId'
                    }

                },
                //弹出参数配置
                dialogParam: {
                    common: {
                        width: 390,
                        height: 288,
                        url: webRoot + 'SystemMenu/menuDetail'
                    },
                    add: {
                        title: '新增菜单信息'
                    },
                    edit: {
                        title: '编辑菜单信息'
                    }
                }
            });
            $("#btnDelete").hide();
        },
        changeState: function (id, state) {
            common.confirm('您确定切换状态吗?', '菜单管理', function () {

                request.updateState(id, state, function () {
                    common.jsmsgSuccess('状态切换成功!');
                    internal.__grid.reload();
                });

            });
        },
        menuTree: function () {
            var top = common.getTopWindowDom();
            top.win_menu_tree_dialog = $.ligerDialog.open({
                title: '调整菜单结构',
                url: webRoot + 'SystemMenu/MenuTree',
                width: 460,
                height: 500,
                buttons: [
                    {
                        text: '确定',
                        onclick: function (item, dialog) {
                            var top = common.getTopWindowDom();
                            if (typeof (top.win_menu_tree_callback) == "function") {
                                var reloadGrid = function () {
                                    var gridObj = liger.get("grid");
                                    gridObj.reload();
                                };
                                top.win_menu_tree_callback(reloadGrid);
                            }
                        }
                    },
                    {
                        text: '取消',
                        onclick: function (item, dialog) {
                            dialog.close();
                        }
                    }
                ]
            });
        }
    };
    window.changeState = internal.changeState;

    exports.init = function () {
        internal.init();
    }
});