/**
 * Created by chenzhisen on 2016/5/12.
 */

define(function (require, exports, module) {
    var internal = {
        sysDict: {
            winName: 'win_sysDict_select_list',
            title: '档案类别选择列表',
            selectParam: {
                storage: [],
                displayField: 'name',
                valueField: 'id',
                gridParam: {
                    url: webRoot + 'dict/getItemPageList',
                    columns: [
                        {display: '代码', name: 'code', width: 160, align: 'left'},
                        {display: '名称', name: 'name', width: 180, align: 'left'}
                    ],
                    usePage: true,
                    height: 250
                },
                multiselect: false,//是否多选:true为多选，false为单选
                winCallback: 'win_sysDict_select_list_callback'
            }
        }
    }
    exports.sysDict = internal.sysDict;
});