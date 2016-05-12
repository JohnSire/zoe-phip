/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var BaseTree = require("{staticDir}/BaseTree/baseTree");
    var internal = {
        odiCodeConfig: require("{dir}/JsConfig/oidCodeConfig").oidCodeConfig,
        medicalStaffGrid: null,
        treeObj: null,
        init: function () {
            internal.medicalStaffList();
            internal.medicalStaffTree();
        },
        medicalStaffTree: function () {
            internal.treeObj = new BaseTree({
                treeId: 'tree',
                btnBox: 'treeBtns',
                url: {
                    getTreeList: 'organization/getMedicalOrgCategoryTree?codeSystem=' + internal.odiCodeConfig.orgClassification,
                },
                renderData: function (data) {
                    var treeData = [];
                    var parentNode = {
                        id: data.result.id,
                        name: data.result.descr,
                        code: data.result.code,
                        type: 1,
                        deptName: data.result.descr,
                        deptCode: data.result.code,
                        children: []
                    };
                    var itemList = [];
                    $.each(data.result.dictItemList, function (index, item) {
                        var info = $.extend(true, {}, item, {
                            children: [],
                            isExpand: false,
                            deptName: item["name"],
                            deptCode: item["code"],
                            type: 1
                        });
                        itemList.push(info);
                    });
                    parentNode["children"] = itemList;
                    treeData.push(parentNode);
                    return treeData;
                },
                treeParam: {
                    nodeWidth: 200,
                    idFieldName: 'id',
                    textFieldName: 'deptName',
                    checkbox: false,
                    onSelect: function (data) {
                        internal.deptCode = data["data"]["deptCode"];
                        internal.deptName = data["data"]["deptName"];
                        internal.type = data["data"]["type"] == 1 ? 1 : 2;
                        var medicalStaffGrid = common.getGrid("medicalStaffGrid");
                        if (medicalStaffGrid.get("dataAction") == "local") {
                            internal.medicalStaffGrid.setServer();
                        } else {
                            internal.medicalStaffGrid.reload();
                        }
                    },
                    onExpand: function (node) {
                        var nodeData = node.data;
                        if (nodeData["children"] && nodeData["children"].length == 0) {
                            var req = new Request("organization/getMedicalOrgList");
                            req.get({
                                isTip: false,
                                data: {type: 1, deptTypeCode: nodeData.deptCode},
                                success: function (data) {
                                    var treeObj = liger.get("tree");
                                    treeObj.append(node.target, data.result.rows);
                                }
                            })
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
                    return {deptCode: internal.deptCode, type: internal.type};
                },
                gridParam: {
                    dataAction: "local",
                    url: 'personnel/getMedStfList',
                    columns: [
                        {display: '编码', name: 'extensionId', width: 100, align: 'left'},
                        {display: '身份证号', name: 'idNo', width: 100, align: 'left'},
                        {display: '姓名', name: 'name', width: 80, align: 'left'},
                        {display: '职务', name: 'technicalName', width: 100, align: 'left'},
                        {display: '机构名称', name: 'name', width: 100, align: 'left'},
                        {display: '科室名称', name: 'affiliatedOrgName', width: 100, align: 'left'},
                        {display: '性别', name: 'genderName', width: 80, align: 'left'},
                        {
                            display: '出生日期', name: 'birthTime', width: 120, align: 'left', type: 'date',
                            format: 'yyyy-MM-dd'
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
                        otherUrlParam: function () {
                            return {
                                deptCode: internal.deptCode,
                                deptName: internal.deptName
                            }
                        },
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