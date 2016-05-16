/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {

            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                    winName: "win_cda_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_cda_detail_callback",//弹窗回调函数
                    getUrl: 'cda/getCdaInfo',//
                    addUrl: 'cda/addCdaInfo',//新增接口Url
                    updateUrl: 'cda/updateCdaInfo',//修改接口Url
                    loadPageEvent: function () {
                        //数据来源
                        $("#fkSourceId").select({
                            name: 'code',
                            display: 'name',
                            ajaxParam: {
                                url: 'source/getSourceList',//url 请求的地址
                            },
                            value: 'code',//值
                            text: 'name'//展示的内容
                        });

                        //档案类别
                        internal.selectList.dialog('sysDict', {

                            target: $("#archivesType"),
                            name: 'archivesType',
                            parentName: 'parentName',
                            valueField: 'id',
                            displayField: 'name',
                            fkNullContent: '无',
                            selectParam: {
                                isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                                treeVaildMsg: '父级分类不能是其本身!',
                                multiselect: false,
                                param: {"categoryId": "CDA"}
                            },


                        });
                    }
                }
            );
        }
    };
    exports.init = function () {
        internal.init();
    }
})
;