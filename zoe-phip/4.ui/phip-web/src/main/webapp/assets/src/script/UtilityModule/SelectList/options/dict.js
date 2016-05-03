/**
 * Created by linqinghuang on 2016/5/3.
 */
/**
 * Created by linqinghuang on 2016/4/14.
 */
define(function (require, exports, module) {
    var internal = {
        dict: {
            winName: 'win_dict_select_list',
            title: '字典选择列表',
            selectParam: {
                storage: [],
                displayField: 'name',
                valueField: 'id',
                gridParam: {
                    url: webRoot + 'dict/getDictList',
                    columns: [
                        {display: '编码', name: 'code', width: 180, align: 'left'},
                        {display: '名称', name: 'name', width: 220, align: 'left'}
                    ],
                    usePage: true,
                    height: 250
                },
                multiselect: false,//是否多选:true为多选，false为单选
                winCallback: 'win_dict_select_list_callback'
            }
        }
    };
    exports.dict = internal.dict;
})