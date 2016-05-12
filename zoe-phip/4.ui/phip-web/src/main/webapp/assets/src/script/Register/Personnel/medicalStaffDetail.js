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
                winName: "win_medicalStaff_detail_dialog",//弹窗对象变量名称
                winCallback: "win_medicalStaff_detail_callback",//弹窗回调函数
                getUrl: 'personnel/getMedStfInfo',//
                addUrl: 'personnel/addMedStaffInfo',//新增接口Url
                updateUrl: 'personnel/updateMedStfInfo',//修改接口Url
                loadPageEvent: function () {
                    $("#selSex").select({
                        name: 'genderCode',
                        display: 'genderName',
                        ajaxParam: {
                            url: 'organization/getMedicalOrgCategoryList',//url 请求的地址
                            data: {codeSystem: oidCodeConfig.sex},
                        },
                        value: 'code',//值
                        text: 'name'//展示的内容
                    });
                    internal.selectList.dialog('organization', {
                        target: $("#btnFkOrganization"),
                        name: 'affiliatedOrgCode',//绑定value值
                        parentName: 'affiliatedOrgName',//绑定name值
                        displayField: 'name',
                        valueField: 'code',
                        selectParam: {
                            multiselect: false,
                            storage: function () {
                                //var deptTypeParent = common.getParamFromUrl("deptTypeParent");
                                //if (deptTypeParent == 1) {
                                var data = [];
                                var affiliatedOrgCode = common.getParamFromUrl("deptCode");
                                var affiliatedOrgName = common.getParamFromUrl("deptName");
                                if (affiliatedOrgCode && affiliatedOrgCode != "null") {
                                    var info = {
                                        affiliatedOrgCode: affiliatedOrgCode,
                                        affiliatedOrgName: decodeURIComponent(affiliatedOrgName)
                                    };
                                    data.push(info);
                                }
                                return data;
                                //}
                                //return [];
                            }()
                        }
                    });




                    //internal.selectList.dialog('dept', {
                    //    target: $("#btnFkAssignedDept"),
                    //    name: 'code',//绑定value值
                    //    parentName: 'name',//绑定name值
                    //    displayField: 'name',
                    //    valueField: 'code',
                    //    selectParam: {
                    //        multiselect: false
                    //    }
                    //});
                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});