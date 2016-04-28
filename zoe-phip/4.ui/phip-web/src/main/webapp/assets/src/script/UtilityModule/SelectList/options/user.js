/**
 * Created by linqinghuang on 2016/4/14.
 */
define(function (require, exports, module) {
    var internal = {
        user: {
            winName: 'win_user_select_list',
            title: '用户选择列表',
            selectParam: {
                storage: [],
                displayField: 'loginName',
                valueField: 'id',
                gridParam: {
                    url: 'user/getUserList',
                    columns: [
                        {display: '名称', name: 'name', width: 180, align: 'left'},
                        {display: '登录名', name: 'loginName', width: 220, align: 'left'}
                    ],
                    usePage: true,
                    height: 250
                },
                multiselect: true,//是否多选:true为多选，false为单选
                winCallback: 'win_user_select_list_callback'
            }
        }
    };
    exports.user=internal.user;
})