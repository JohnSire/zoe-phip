/**
 * Created by linqinghuang on 2016/5/10.
 */
/**
 * Created by linqinghuang on 2016/4/22.
 */
define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var BaseTree = require("{staticDir}/BaseTree/baseTree");
    var internal = {
            odiCodeConfig: require("{dir}/JsConfig/oidCodeConfig").oidCodeConfig,
            medicalDepartmentGrid: null,
            init: function () {
                internal.medicalDepartmentList();
                internal.medicalDepartmentCategoryTree();
            },
            medicalDepartmentCategoryTree: function () {
                var treeObj = new BaseTree({
                    treeId: 'tree',
                    btnBox: 'treeBtns',
                    url: {
                        getTreeList: 'organization/getMedicalOrgCategoryTree?codeSystem=' + internal.odiCodeConfig.department,//internal.odiCodeConfig.orgClassification,
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
                        nodeWidth: 200,
                        idFieldName: 'id',
                        textFieldName: 'name',
                        checkbox: false,
                        onSelect: function (data) {
                            internal.deptTypeCode = data["data"]["code"];
                            internal.deptTypeName = data["data"]["name"];
                            internal.type = data["data"]["type"] == 0 ? 0 : 1;
                            var medicalDepartmentGrid = common.getGrid("medicalDepartmentGrid");
                            if (medicalDepartmentGrid.get("dataAction") == "local") {
                                internal.medicalDepartmentGrid.setServer();
                            } else {
                                internal.medicalDepartmentGrid.reload();
                            }
                        }
                    }

                })
            },
            medicalDepartmentList: function () {
                internal.medicalDepartmentGrid = new BaseGrid({
                        gridId: 'medicalDepartmentGrid',
                        toolsBoxId: 'medicalDepartmentTools',
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
                            var deptParentCode = common.getParamFromUrl("orgCode");
                            return {deptTypeCode: internal.deptTypeCode, type: internal.type, deptParentCode: deptParentCode};
                        },
                        gridParam: {
                            dataAction: "local",
                            url: 'organization/getMedicalDepartmentList',
                            columns: [
                                {display: '科室代码', name: 'deptCode', width: 250, align: 'left'},
                                {display: '科室名称', name: 'deptName', width: 250, align: 'left'},
                                {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                            ],
                            frozen: false,
                            usePage: true,
                            width: "100%",
                            height: "99%"
                        },
                        dialogParam: {
                            winName: "win_medical_department_detail_dialog",//弹窗对象变量名称
                            winCallback: "win_medical_department_detail_callback",//弹窗回调函数
                            titleKey: "deptName",
                            //新增参数
                            add: {
                                title: "新增机构科室信息"
                            },
                            //编辑参数
                            edit: {
                                title: "编辑机构科室信息"
                            },
                            common: {
                                otherUrlParam: function () {
                                    var orgCode = common.getParamFromUrl("orgCode");
                                    return {
                                        deptTypeCode: internal.deptTypeCode,
                                        deptTypeName: internal.deptTypeName,
                                        deptTypeParent: internal.type,
                                        orgCode: orgCode
                                    }
                                },
                                url: 'organization/view/medicalDepartmentDetail',
                                width: 1000,
                                height: 560
                            }
                        }
                    }
                )
            }
        }
        ;
    exports.init = function () {
        internal.init();
    }
})
;