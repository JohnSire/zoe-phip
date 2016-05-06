/**
 * Created by linqinghuang on 2016/5/6.
 */
/**
 * Created by zhangxingcai on 2016/5/5 0005.
 */
define(function (require, exports, module) {
    var internal = {
        medicalOrg: {
            winName: 'win_medical_org_select_list',
            title: '机构选择列表',
            selectParam: {
                storage: [],
                displayField: 'deptName',
                valueField: 'deptCode',
                gridParam: {
                    url: webRoot + 'organization/getMedicalOrgList?type=0',
                    columns: [
                        {display: '机构代码', name: 'deptCode', width: 150, align: 'left'},
                        {display: '机构名称', name: 'deptName', width: 280, align: 'left'}
                    ],
                    usePager: true,
                    height: 250
                },
                multiselect: true,//是否多选:true为多选，false为单选
                winCallback: 'win_medical_org_list_callback'
            }
        }
    }
    exports.medicalOrg = internal.medicalOrg;
});