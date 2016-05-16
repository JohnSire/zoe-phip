/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var ajaxStore = {
        getCDA: function (id,callback) {
            var req = new Request("cda/getCdaInfo");
            req.get({
                isTip: false,//是否有请求结果消息提示（成功||失败）
                data:{"id":id},
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        }

    }

    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var id=common.getParamFromUrl("id");
            ajaxStore.getCDA(id, function (data) {
                
            })
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                    winName: "win_cda_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_cda_detail_callback",//弹窗回调函数
                    getUrl: 'cda/getCdaInfo',//
                    addUrl: 'cda/addCdaInfo',//新增接口Url
                    updateUrl: 'cda/updateCdaInfo',//修改接口Url
                    loadPageEvent: function () {
                        //数据来源
                        internal.selectList.dialog('source', {

                            target: $("#fkSourceId"),
                            name: 'fkSourceId',
                            parentName: 'parentName',
                            valueField: 'id',
                            displayField: 'name',
                            fkNullContent: '无',
                            selectParam: {
                                isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                                treeVaildMsg: '父级分类不能是其本身!',
                                multiselect: false,
                                param: {"type": 2}
                            }
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
                            }
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