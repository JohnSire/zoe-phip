/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var oidCodeConfig = require("{dir}/JsConfig/oidCodeConfig").oidCodeConfig;
            var baseAttr = new BaseAttr({
                winName: "win_medical_org_detail_dialog",//弹窗对象变量名称
                winCallback: "win_medical_org_detail_callback",//弹窗回调函数
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
                        param: {"codeSystem": oidCodeConfig.orgClassification},//ajax参数
                        selectParam: {
                            multiselect: false
                        }
                    });

                    $("#selRoleState").select({
                        localData: true,
                        name: 'roleState'
                    })
                },
                beforeSaveEvent: function (data) {
                    data = $.extend(true, {}, data, {divisionRoot: oidCodeConfig.orgType})
                    return data;
                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});