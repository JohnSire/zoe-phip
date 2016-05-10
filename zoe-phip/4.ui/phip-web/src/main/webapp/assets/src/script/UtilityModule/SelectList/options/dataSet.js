/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        dataSet: {
            winName: 'win_area_select_list',
            title: '数据集选择列表',
            selectParam: {
                storage: [],
                displayField: 'name',
                valueField: 'id',
                gridParam: {
                    url: webRoot + 'dataSet/getSetList',
                    columns: [
                        {display: '代码', name: 'code', width: 160, align: 'left'},
                        {display: '名称', name: 'name', width: 180, align: 'left'}
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
                winCallback: 'win_area_select_list_callback'
            }
        }
    }
    exports.dataSet = internal.dataSet;
});