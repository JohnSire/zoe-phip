/**
 * Created by chenzhisen on 2016/5/16.
 */
define(function (require, exports, module) {
    var internal = {
        source: {
            winName: 'win_source_select_list',
            title: '标准来源选择列表',
            selectParam: {
                storage: [],
                displayField: 'name',
                valueField: 'id',
                gridParam: {
                    url: webRoot + 'source/getSourceList',
                    columns: [
                        {display: '代码', name: 'code', width: 160, align: 'left'},
                        {display: '名称', name: 'name', width: 180, align: 'left'}
                    ],
                    usePage: true,
                    height: 250
                },
                multiselect: false,//是否多选:true为多选，false为单选
                winCallback: 'win_source_select_list_callback'
            }
        }
    }
    exports.source = internal.source;
});