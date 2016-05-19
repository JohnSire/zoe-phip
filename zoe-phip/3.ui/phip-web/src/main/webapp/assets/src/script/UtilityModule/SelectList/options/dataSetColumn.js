/**
 * Created by chenzhisen on 2016/5/9.
 */
define(function (require, exports, module) {
    var internal = {
        dataSetColumn: {
            winName: 'win_dict_catalog_select_list',
            title: '数据集字段选择列表',
            selectParam: {
                stroage: [],
                displayField: 'fieldName',
                valueField: 'id',
                gridParam: {
                    url: webRoot + '/dataSet/getRsSetColumn',
                    columns: [
                        {display: '名称', name: 'fieldName', width: 120, align: 'left'},
                        {display: '编码', name: 'fieldCode', width: 280, align: 'left'}
                    ],
                    usePage: true,

                    height: 250
                },
                multiselect: false,//是否多选:true为多选，false为单选
                winCallback: 'win_setColumn_select_list_callback'
            }
        }
    }
    exports.dataSetColumn = internal.dataSetColumn;
});