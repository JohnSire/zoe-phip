/**
 * Created by linqinghuang on 2016/4/22.
 */
define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var BaseTree = require("{staticDir}/BaseTree/baseTree");
    var internal = {
        odiCodeConfig: require("{dir}/JsConfig/oidCodeConfig").oidCodeConfig,
        medicalOrgGrid: null,
        deptTypeName: '',
        deptTypeCode: '',
        init: function () {
            internal.medicalOrgList();
            internal.medicalOrgCategoryTree();
        },
        medicalOrgCategoryTree: function () {
            var treeObj = new BaseTree({
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
                        type: 0,
                        children: []
                    };
                    parentNode["children"] = data.result.dictItemList;
                    treeData.push(parentNode);
                    return treeData;
                },
                treeParam: {
                    nodeWidth: 180,
                    idFieldName: 'id',
                    textFieldName: 'name',
                    checkbox: false,
                    onSelect: function (data) {
                        internal.deptTypeCode = data["data"]["code"];
                        internal.deptTypeName = data["data"]["name"];
                        internal.type = data["data"]["type"] == 0 ? 0 : 1;
                        var medicalOrgGrid = common.getGrid("medicalOrgGrid");
                        if (medicalOrgGrid.get("dataAction") == "local") {
                            internal.medicalOrgGrid.setServer();
                        } else {
                            internal.medicalOrgGrid.reload();
                        }
                    }
                }

            })
        },
        medicalOrgList: function () {
            internal.medicalOrgGrid = new BaseGrid({
                gridId: 'medicalOrgGrid',
                toolsBoxId: 'medicalOrgTools',
                deleteUrl: {
                    deleteInfo: "organization/delMedicalOrgInfo",
                    deleteList: "organization/delMedicalOrgList"
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
                    return {deptTypeCode: internal.deptTypeCode, type: internal.type};
                },
                gridParam: {
                    dataAction: "local",
                    url: 'organization/getMedicalOrgList',
                    columns: [
                        {display: '机构代码', name: 'deptCode', width: 170, align: 'left'},
                        {display: '机构名称', name: 'deptName', width: 250, align: 'left'},
                        {display: '联系电话', name: 'employerTelNo', width: 170, align: 'left'},
                        {display: '操作', isSort: false, width: 150, icons: ['edit', 'del']},
                        {
                            display: '编辑科室', isSort: false, width: 150, align: 'center',
                            render: function (rowdata) {
                                return "<a class='icon-grid icon-grid-setting' title='科室编辑' onclick='javascript:winEditDepartment(\"" + rowdata["deptCode"] + "\")'></a>"
                            }
                        }
                    ],
                    frozen: false,
                    usePage: true,
                    width: "100%",
                    height: "99%"
                },
                dialogParam: {
                    winName: "win_medical_org_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_medical_org_detail_callback",//弹窗回调函数
                    titleKey: "deptName",
                    //新增参数
                    add: {title: "新增医疗机构信息"},
                    //编辑参数
                    edit: {title: "编辑医疗机构信息"},
                    common: {
                        otherUrlParam: function () {
                            return {
                                deptTypeCode: internal.deptTypeCode,
                                deptTypeName: internal.deptTypeName,
                                deptTypeParent: internal.type
                            }
                        },
                        url: 'organization/view/medicalOrgDetail',
                        width: 1000,
                        height: 560
                    }
                }
            })
        }

    };
    window.winEditDepartment = function (orgCode) {
        common.dialog({
            title: '科室信息维护',
            url: 'organization/view/medicalDepartmentList?orgCode=' + orgCode,
            width: 1000,
            height: 600
        })
    }
    exports.init = function () {
        internal.init();
    }
});