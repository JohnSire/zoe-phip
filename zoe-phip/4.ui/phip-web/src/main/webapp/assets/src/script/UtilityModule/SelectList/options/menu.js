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
        }
    }
    exports.menu = internal.menu;
});