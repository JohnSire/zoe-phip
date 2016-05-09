/**
 * Created by chenzhisen on 2016/5/9.
 */
define(function (require, exports, module) {
    var internal = {
        dataSetColumn: {
            winName: 'win_dict_catalog_select_list',
            title: '数据集字段选择列表',
            selectParam: {
                storage: [],
                displayField: 'name',
                valueField: 'id',
                gridParam: {
                    url: webRoot + '/dict/dictCatalogListQueryPage',
                    columns: [
                        {display: '名称', name: 'name', width: 120, align: 'left'},
                        {display: '编码', name: 'code', width: 280, align: 'left'}
                    ],
                    usePager: false,
                    tree: {
                        columnId: 'id',
                        columnName: 'name',
                        idField: 'id',
                        parentIDField: 'pid'
                    },
                    height: 250
                },
                multiselect: false,//是否多选:true为多选，false为单选
                winCallback: 'win_dict_catalog_select_list_callback'
            }
        }
    }
    exports.dataSetColumn = internal.dataSetColumn;
});