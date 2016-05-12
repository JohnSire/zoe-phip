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
                        name: 'deptTypeCode',//绑定value的值
                        parentName: 'deptTypeName',//绑定name的值
                        displayField: 'name',
                        valueField: 'code',
                        param: {"codeSystem": oidCodeConfig.orgClassification},//ajax参数
                        selectParam: {
                            multiselect: false,
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
                    var req = new Request("area/getAreaTopNode")
                    req.get({
                        isTip: false,
                        async: true,
                        success: function (data) {
                            var provincePid = data.result && data.result.id ? data.result.id : "";
                            /*选择省份*/
                            $("#selProvince").select({
                                name: 'provinceCode',
                                display: 'provinceCodeName',
                                ajaxParam: {
                                    url: 'area/getAreaListByPid',//url 请求的地址
                                    data: {pid: provincePid},
                                },
                                value: 'code',//值
                                text: 'name'//展示的内容

                            })
                            /*选择*/
                            $("#selCity").select({
                                name: 'cityCode',
                                display: 'cityCodeName',
                                ajaxParam: {
                                    url: 'area/getAreaListByPid',//url 请求的地址
                                    data: {pid: 'F2565E55804540AD8E23C059E0E3D593'},
                                },
                                value: 'code',//值
                                text: 'name'//展示的内容
                            })
                            /*选择区县*/
                            $("#selCounty").select({
                                name: 'countyCode',
                                display: 'countyCodeName',
                                ajaxParam: {
                                    url: 'area/getAreaListByPid',//url 请求的地址
                                    data: {pid: '9BC2CEB4B2EE47488703F5A45EB998E0'},
                                },
                                value: 'code',//�
                                text: 'name'//展示的内�
                            })
                            /*选择乡镇*/
                            $("#selStreet").select({
                                name: 'streetCode',
                                display: 'streetCodeName',
                                ajaxParam: {
                                    url: 'area/getAreaListByPid',//url 请求的地址
                                    data: {pid: '95beeb26-1825-4f37-87b0-70fcc160f248'},
                                },
                                value: 'code',//值
                                text: 'name'//展示的内容
                            })
                            /*选择街道*/
                            $("#selNeighborhood").select({
                                name: 'neighborhoodCode',
                                display: 'neighborhoodCodeName',
                                ajaxParam: {
                                    url: 'area/getAreaListByPid',//url 请求的地址
                                    data: {pid: 'e6bda006-95a6-44ae-8570-143856d310d3'},
                                },
                                value: 'code',//值
                                text: 'name'//展示的内容
                            })
                        }
                    });


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