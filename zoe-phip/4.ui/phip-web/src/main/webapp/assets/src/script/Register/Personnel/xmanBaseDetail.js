/**
 * Created by zhangxingcai on 2016/4/22 0022.
 */
define(function (require, exports, module) {
    var internal = {
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
                        isAsync: true,//是否异步加载，点击时加载数据，如果已经请求过的就不在请求
                        ajaxParam: {
                            type: "get",
                            url: 'organization/getMedicalOrgCategoryList',//url 请求的地址
                            data: {codeSystem: oidCodeConfig.sex},
                        },
                        data: [],
                        value: '',//值
                        text: '',//展示的内容
                        rows: 6//显示几行，如果超过的则出现滚动条，如果少于不影响
                    });
                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});