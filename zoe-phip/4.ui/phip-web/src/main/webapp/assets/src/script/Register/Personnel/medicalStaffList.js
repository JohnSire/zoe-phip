/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var BaseTree = require("{staticDir}/BaseTree/baseTree");
    var internal = {
        medicalStaffGrid: null,
        init: function () {
            internal.medicalStaffList();
            internal.medicalStaffTree();
        },
        medicalStaffTree: function () {
            var treeObj = new BaseTree({
                treeId: 'tree',
                btnBox: 'treeBtns',
                url: {
                    getTreeList: 'personnel/getMedDeptList',
                },
                //renderData: function (data) {
                //
                //    var treeData = [];
                //    var parentNode = {
                //        id: "ffff",
                //        deptName: "医疗机构",
                //        deptCode: "0",
                //        type: 0,
                //        children: []
                //    };
                //    parentNode["children"] = data.result.rows;
                //    treeData.push(parentNode);
                //    return treeData;
                //},
                treeParam: {
                    idFieldName: 'deptCode',
                    parentIDFieldName: 'deptParentCode',
                    textFieldName: 'deptName',
                    //idFieldName: 'id',
                    //textFieldName: 'deptName',
                    nodeWidth: 320,
                    checkbox: false,
                    onSelect: function (data) {
                        internal.deptCode = data["data"]["deptCode"];
                        var medicalStaffGrid = common.getGrid("medicalStaffGrid");
                        if (medicalStaffGrid.get("dataAction") == "local") {
                            internal.medicalStaffGrid.setServer();
                        } else {
                            internal.medicalStaffGrid.reload();
                        }
                    }
                }

            })
        },
        medicalStaffList: function () {
            internal.medicalStaffGrid = new BaseGrid({
                gridId: 'medicalStaffGrid',
                toolsBoxId: 'medicalStaffTools',
                deleteUrl: {
                    deleteInfo: "personnel/delMedStfInfo",
                    deleteList: "personnel/delMedStfList"
                },
                tools: {
                    btnbox: {
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                extendParam: function () {
                    return {deptCode: internal.deptCode};
                },
                gridParam: {
                    dataAction: "local",
                    url: 'personnel/getMedStfList',
                    columns: [
                        {display: '身份证号', name: 'idNo', width: 100, align: 'left'},
                        {display: '姓名', name: 'name', width: 80, align: 'left'},
                        {display: '职务', name: 'technicalName', width: 100, align: 'left'},
                        {display: '机构名称', name: 'name', width: 100, align: 'left'},
                        {display: '科室名称', name: 'affiliatedOrgName', width: 100, align: 'left'},
                        {display: '性别', name: 'genderName', width: 80, align: 'left'},
                        {
                            display: '出生日期', name: 'birthTime', width: 120, align: 'left', type: 'date',
                            format: 'yyyy-mm-dd'
                        },
                        {display: '联系电话', name: 'employerTelNo', width: 120, align: 'left'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    frozen: false,
                    usePage: true,
                    width: "100%",
                    height: "99%"//$("body").innerHeight() - $("#dictItemTools").outerHeight() - 76//500
                },
                dialogParam: {
                    winName: "win_medicalStaff_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_medicalStaff_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增医护人员信息"},
                    //编辑参数
                    edit: {title: "编辑医护人员信息"},
                    common: {
                        url: 'personnel/view/medicalstaffdetail',
                        width: 1000,
                        height: 500
                    }
                }
            })
        }
    };
    exports.init = function () {
        internal.init();
    }
});