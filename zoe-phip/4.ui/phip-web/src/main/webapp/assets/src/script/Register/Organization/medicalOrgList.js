/**
 * Created by linqinghuang on 2016/4/22.
 */
define(function (require, exports, module) {
    var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
    var BaseTree = require("{staticDir}/BaseTree/baseTree");
    var internal = {
        medicalOrgGrid: null,
        init: function () {
            internal.medicalOrgList();
            internal.medicalOrgCategoryTree();
        },
        medicalOrgCategoryTree: function () {
            var treeObj = new BaseTree({
                treeId: 'tree',
                btnBox: 'treeBtns',
                url: {
                    getTreeList: 'organization/getMedicalOrgCategoryTree',
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
                    nodeWidth: 350,
                    idFieldName: 'id',
                    textFieldName: 'name',
                    checkbox: false,
                    onSelect: function (data) {
                        internal.deptTypeCode = data["data"]["code"];
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
                    deleteInfo: "dict/deleteItem",
                    deleteList: "dict/deleteItemList"
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
                        {display: '机构(科室)代码', name: 'deptCode', width: 300, align: 'left'},
                        {display: '机构(科室)名称', name: 'deptName', width: 300, align: 'left'},
                        {display: '联系电话', name: 'employerTelNo', width: 200, align: 'left'},
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    frozen: false,
                    usePage: true,
                    width: "100%",
                    height: "99%"//$("body").innerHeight() - $("#dictItemTools").outerHeight() - 76//500
                },
                dialogParam: {
                    winName: "win_dict_item_dialog",//弹窗对象变量名称
                    winCallback: "win_dict_item_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增医疗机构（科室）信息"},
                    //编辑参数
                    edit: {title: "编辑医疗机构（科室）信息"},
                    common: {
                        otherUrlParam: function () {
                            return {fkSystemDictCategoryId: internal.fkSystemDictCategoryId}
                        },
                        url: 'organization/view/medicalOrgDetail',
                        width: 1000,
                        height: 450
                    }
                }
            })
        }
    };
    exports.init = function () {
        internal.init();
    }
});