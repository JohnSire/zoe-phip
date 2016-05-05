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

                    internal.selectList.dialog('menu', {
                        target: $("#btnFkNationality"),
                        name: 'nationalityCode',//绑定value值
                        parentName: 'nationalityName',//绑定name值
                        displayField: 'name',
                        valueField: 'code',
                        selectParam: {
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