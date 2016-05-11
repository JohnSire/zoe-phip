/**
 * Created by chenzhisen on 2016/5/9.
 */
define(function (require, exports, module) {
    var internal = {
        dataElement: {
            winName: 'win_dict_catalog_select_list',
            title: '数据元选择列表',
            selectParam: {
                storage: [],
                displayField: 'name',
                valueField: 'id',
                gridParam: {
                    url: webRoot + '/element/getElementList',
                    columns: [
                        {display: '名称', name: 'name', width: 120, align: 'left'},
                        {display: '编码', name: 'code', width: 280, align: 'left'}
                    ],
                    usePage: true,
                    height: 250
                },
                multiselect: false,//是否多选:true为多选，false为单选
                winCallback: 'win_ele_select_list_callback'
            }
        }
    }
    exports.dataElement = internal.dataElement;
});