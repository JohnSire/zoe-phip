/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        areaRelevance: require("{dir}/UtilityModule/AreaRelevance/area"),
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var oidCodeConfig = require("{dir}/JsConfig/oidCodeConfig").oidCodeConfig;
            var baseAttr = new BaseAttr({
                winName: "win_medical_department_detail_dialog",//弹窗对象变量名称
                winCallback: "win_medical_department_detail_callback",//弹窗回调函数
                getUrl: 'organization/getMedicalOrgInfo',//
                addUrl: 'organization/addMedicalOrgInfo',//新增接口Url
                updateUrl: 'organization/updateMedicalOrgInfo',//修改接口Url
                loadPageEvent: function () {
                    internal.selectList.dialog('dictItem', {
                        target: $("#btnDeptType"),
                        name: 'deptTypeCode',//绑定value值
                        parentName: 'deptTypeName',//绑定name值
                        displayField: 'name',
                        valueField: 'code',

                        selectParam: {
                            multiselect: false,
                            param: {"codeSystem": oidCodeConfig.department},//ajax参数
                            storage: function () {
                                var deptTypeParent = common.getParamFromUrl("deptTypeParent");
                                if (deptTypeParent == 1) {
                                    var data = [];
                                    var deptTypeCode = common.getParamFromUrl("deptTypeCode");
                                    var deptTypeName = common.getParamFromUrl("deptTypeName");
                                    if (deptTypeCode && deptTypeCode != "null") {
                                        var info = {
                                            deptTypeCode: deptTypeCode,
                                            deptTypeName: decodeURIComponent(deptTypeName)
                                        };
                                        data.push(info);
                                    }
                                    return data;
                                }
                                return [];
                            }()
                        }
                    });
                    $("#selRoleState").select({
                        localData: true,
                        name: 'roleState'
                    });
                    internal.areaRelevance.init();

                },
                beforeSaveEvent: function (data) {
                    var orgCode = common.getParamFromUrl("orgCode");
                    data = $.extend(true, {}, data, {divisionRoot: oidCodeConfig.departType, deptParentCode: orgCode})
                    return data;
                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});