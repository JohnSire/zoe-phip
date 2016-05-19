/**
 * Created by linqinghuang on 2016/5/12.
 */
define(function (require, exports, module) {
    var internal = {
        medicalDept: {
            winName: 'win_medical_dept_select_list',
            title: '科室选择列表',
            selectParam: {
                stroage: [],
                displayField: 'deptName',
                valueField: 'deptCode',
                gridParam: {
                    url: webRoot + 'organization/getMedicalDepartmentList',
                    columns: [
                        {display: '科室代码', name: 'deptCode', width: 150, align: 'left'},
                        {display: '科室名称', name: 'deptName', width: 280, align: 'left'}
                    ],
                    usePager: true,
                    height: 250
                },
                multiselect: true,//是否多选:true为多选，false为单选
                winCallback: 'win_medical_dept_list_callback'
            }
        }
    }
    exports.medicalDept = internal.medicalDept;
});