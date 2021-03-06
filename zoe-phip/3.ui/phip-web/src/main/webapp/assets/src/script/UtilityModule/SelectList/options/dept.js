/**
 * Created by zhangxingcai on 2016/5/5 0005.
 */
define(function (require, exports, module) {
    var internal = {
        dept: {
            winName: 'win_dept_select_list',
            title: '科室选择列表',
            selectParam: {
                stroage: [],
                displayField: 'name',
                valueField: 'id',
                gridParam: {
                    url: webRoot + 'personnel/getMedDeptList',
                    columns: [
                        {display: '科室代码', name: 'affiliatedOrgCode', width: 150, align: 'left'},
                        {display: '科室名称', name: 'affiliatedOrgName', width: 150, align: 'left'}
                    ],
                    usePager: true,
                    height: 250
                },
                multiselect: true,//是否多选:true为多选，false为单选
                winCallback: 'win_dept_select_list_callback'
            }
        }
    }
    exports.dept = internal.dept;
});