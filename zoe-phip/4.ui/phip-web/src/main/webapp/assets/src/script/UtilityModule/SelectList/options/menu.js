/**
 * Created by linqinghuang on 2016/4/14.
 */
define(function (require, exports, module) {
    var internal = {
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
                        {display: '名称', name: 'name', width: 280, align: 'left'},
                        {
                            display: '状态',
                            name: 'state',
                            width: 120,
                            align: "center",
                            render: function (rowdata, index, value) {
                                if (value == 1) {
                                    return "<span>启用</span>";
                                } else {
                                    return "<span style='color:red;'>禁用</span>"
                                }
                            }
                        },
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
        }
    }
    exports.menu = internal.menu;
});