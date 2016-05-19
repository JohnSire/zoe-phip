/**
 * Created by zhangxingcai on 2016/5/9 0009.
 */
define(function (require, exports, module) {
    var internal = {
        domain: {
            winName: 'win_domain_select_list',
            title: '值域选择列表',
            selectParam: {
                stroage: [],
                displayField: 'name',
                valueField: 'id',
                gridParam: {
                    url: webRoot + 'dict/getDictList',
                    columns: [
                        {display: '编码', name: 'code', width: 180, align: 'left'},
                        {display: '名称', name: 'name', width: 220, align: 'left'}
                    ],
                    usePage: true,
                    height: 280
                },
                multiselect: false,//是否多选:true为多选，false为单选
                winCallback: 'win_domain_select_list_callback'
            }
        }
    };
    exports.domain = internal.domain;
})