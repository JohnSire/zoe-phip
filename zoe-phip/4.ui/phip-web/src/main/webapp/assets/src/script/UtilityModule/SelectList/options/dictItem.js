/**
 * Created by linqinghuang on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        dictItem: {
            winName: 'win_dict_item_select_list',
            title: '字典选择列表',
            selectParam: {
                stroage: [],
                displayField: 'name',
                valueField: 'code',
                gridParam: {
                    url: webRoot + '/organization/getMedicalOrgCategoryList',
                    columns: [
                        {display: '编码', name: 'code', width: 120, align: 'left'},
                        {display: '名称', name: 'name', width: 280, align: 'left'}
                    ],
                    usePager: true,
                    height: 250
                },
                multiselect: false,//是否多选:true为多选，false为单选
                winCallback: 'win_dict_item_select_list_callback'

            }
        }
    }
    exports.dictItem = internal.dictItem;
});