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
                winName: "win_xmanbase_detail_dialog",//弹窗对象变量名称
                winCallback: "win_xmanbase_detail_callback",//弹窗回调函数
                getUrl: 'personnel/getXmanInfo',//
                addUrl: 'personnel/addXmanInfo',//新增接口Url
                updateUrl: 'personnel/updateXmanInfo',//修改接口Url
                loadPageEvent: function () {
                    //性别
                    $("#selSex").select({
                        name: 'sexCode',
                        display: 'sexCodeName',
                        ajaxParam: {
                            url: 'organization/getMedicalOrgCategoryList',//url 请求的地址
                            data: {codeSystem: oidCodeConfig.sex},
                        },
                        value: 'code',//值
                        text: 'name'//展示的内容
                    });
                    //婚姻状况
                    $("#selMarriage").select({
                        name: 'marriageCode',
                        display: 'marriageName',
                        ajaxParam: {
                            url: 'organization/getMedicalOrgCategoryList',//url 请求的地址
                            data: {codeSystem: oidCodeConfig.maritalStatus},
                        },
                        value: 'code',//值
                        text: 'name'//展示的内容

                    });
                    //医疗保险
                    $("#selCodeSys").select({
                        name: 'codeSysCode',
                        display: 'codeSysName',
                        ajaxParam: {
                            url: 'organization/getMedicalOrgCategoryList',//url 请求的地址
                            data: {codeSystem: oidCodeConfig.healthCareType},
                        },
                        value: 'code',//值
                        text: 'name'//展示的内容

                    });

                    //民族
                    internal.selectList.dialog('dictItem', {
                        target: $("#btnFkNationality"),
                        name: 'nationalityCode',//绑定value值
                        parentName: 'nationalityName',//绑定name值
                        displayField: 'name',
                        valueField: 'code',
                        selectParam: {
                            param: {"codeSystem": oidCodeConfig.national},//ajax参数
                            multiselect: false,
                        }
                    });
                    //发卡机构
                    internal.selectList.dialog('medicalOrg', {
                        target: $("#btnFkXcOrg"),
                        name: 'xcOrgCode',
                        parentName: 'xcOrgName',
                        displayField: 'deptName',
                        valueField: 'deptCode',
                        selectParam: {
                            multiselect: false
                        }
                    });
                    ////建档组织机构
                    internal.selectList.dialog('medicalOrg', {
                        target: $("#btnFkHealthRecordOrg"),
                        name: 'healthRecordOrgCode',
                        parentName: 'healthRecordOrgName',
                        displayField: 'deptName',
                        valueField: 'deptCode',
                        selectParam: {
                            multiselect: false
                        }
                    });
                    //登记机构
                    internal.selectList.dialog('medicalOrg', {
                        target: $("#btnFkOrg"),
                        name: 'orgCode',//绑定value值
                        parentName: 'orgName',//绑定name值
                        displayField: 'deptName',
                        valueField: 'deptCode',
                        selectParam: {
                            multiselect: false
                        }
                    });
                    //职业类别
                    internal.selectList.dialog('dictItem', {
                        target: $("#btnFkOccupation"),
                        name: 'occupationCode',//绑定value值
                        parentName: 'occupationName',//绑定name值
                        displayField: 'name',
                        valueField: 'code',
                        selectParam: {
                            param: {"codeSystem": oidCodeConfig.occupation},//ajax参数
                            multiselect: false
                        }
                    });






                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});