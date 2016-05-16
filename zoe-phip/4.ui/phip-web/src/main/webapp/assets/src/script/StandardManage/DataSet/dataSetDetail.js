/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var id = common.getParamFromUrl("id");
            if (id) {
                $("#column").show();
            }

            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_set_detail_dialog",//弹窗对象变量名称
                winCallback: "win_set_detail_callback",//弹窗回调函数
                getUrl: 'dataSet/getSetInfo',//
                addUrl: 'dataSet/addSetInfo',//新增接口Url
                updateUrl: 'dataSet/updateSetInfo',//修改接口Url
                loadPageEvent: function () {

                    //数据来源
                    internal.selectList.dialog('source', {

                        target: $("#fkSourceId"),
                        name: 'fkSourceId',
                        parentName: 'sourceName',

                        displayField: 'name',
                        valueField: 'id',
                        fkNullContent: '无',
                        selectParam: {
                            isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                            treeVaildMsg: '父级分类不能是其本身!',
                            multiselect: false,
                            param: {"type":0}
                        }
                    });
                    //业务时间列
                    internal.selectList.dialog('dataSetColumn', {
                        target: $("#btnBusinessColumn"),
                        name: 'bussTimeFieldId',
                        parentName: 'parentName',
                        valueField: 'id',
                        displayField: 'name',
                        fkNullContent: '--请选择业务时间列--',
                        selectParam: {
                            isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                            treeVaildMsg: '父级分类不能是其本身!',
                            multiselect: false,
                            param: {"id": id}

                        },

                    });
                    //数据更新时间列
                    internal.selectList.dialog('dataSetColumn', {
                        target: $("#updateTimeFieldId"),
                        name: 'updateTimeFieldId',
                        parentName: 'parentName',
                        valueField: 'id',
                        displayField: 'name',
                        fkNullContent: '--请选择业务时间列--',
                        selectParam: {
                            isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                            treeVaildMsg: '父级分类不能是其本身!',
                            multiselect: false,
                            param: {"id": id}

                        },

                    });
                    //父级数据集
                    internal.selectList.dialog('dataSet', {
                        target: $("#btnDataSet"),
                        name: 'pid',
                        parentName: 'parentName',
                        valueField: 'id',
                        displayField: 'name',
                        fkNullContent: '--请选择父级数据集--',
                        selectParam: {
                            isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                            treeVaildMsg: '父级分类不能是其本身!',
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