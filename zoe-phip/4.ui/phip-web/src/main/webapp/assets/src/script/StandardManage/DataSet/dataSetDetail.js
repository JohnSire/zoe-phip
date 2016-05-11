/**
 * Created by chenzhisen on 2016/5/6.
 */
define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var id=common.getParamFromUrl("id");
            if(id){
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
                            multiselect: false
                        },
                        param: {"id": id}
                    });
                    //internal.selectList.dialog('dataSetColumn', {
                    //    target: $("#btnDataUpdateColumn"),
                    //    name: 'pid',
                    //    parentName: 'parentName',
                    //    valueField: 'id',
                    //    displayField: 'name',
                    //    fkNullContent: '根级节点',
                    //    selectParam: {
                    //        isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                    //        treeVaildMsg: '父级分类不能是其本身!',
                    //        multiselect: false
                    //    },
                    //    buttonsExtend: [{
                    //        text: '根级节点', onclick: function (item, dialog) {
                    //            $('input[name="pid"]').val(0);
                    //            $("#btnParentCatalog").find(".text-line-content").text("根级节点");
                    //            dialog.close();
                    //        }
                    //    }]
                    //});

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