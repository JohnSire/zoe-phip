/**
 * Created by zhangxingcai on 2016/5/5 0005.
 */
define(function (require, exports, module) {
    var internal = {
        organization: {
            winName: 'win_organization_select_list',
            title: '机构选择列表',
            selectParam: {
                storage: [],
                displayField: 'deptName',
                valueField: 'id',
                gridParam: {
                    url: webRoot + 'personnel/getMedDeptList',
                    columns: [
                        {display: '机构代码', name: 'deptCode', width: 150, align: 'left'},
                        {display: '机构名称', name: 'deptName', width: 150, align: 'left'}
                    ],
                    usePager: true,
                    height: 250
                },
                multiselect: true,//是否多选:true为多选，false为单选
                winCallback: 'win_organization_select_list_callback'
            }
        }
    }
    exports.organization = internal.organization;
});