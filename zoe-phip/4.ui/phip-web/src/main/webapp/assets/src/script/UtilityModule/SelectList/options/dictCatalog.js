/**
 * Created by fangqin on 2016/4/27.
 */
define(function (require, exports, module) {
    var internal = {
        dictCatalog: {
            winName: 'win_dict_select_list',
            title: '字典分类选择列表',
            selectParam: {
                storage: [],
                displayField: 'name',
                valueField: 'id',
                gridParam: {
                    url: webRoot + '/dict/getDictCatalogList',
                    columns: [
                        {display: '名称', name: 'name', width: 280, align: 'left'},
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
                winCallback: 'win_dict_select_list_callback'
            }
        }
    }
    exports.dictCatalog = internal.dictCatalog;
});