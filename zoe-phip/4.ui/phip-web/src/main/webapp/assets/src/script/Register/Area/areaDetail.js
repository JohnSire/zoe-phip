/**
 * Created by linqinghuang on 2016/4/21.
 */
define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_area_detail_dialog",//弹窗对象变量名称
                winCallback: "win_area_detail_callback",//弹窗回调函数
                getUrl: 'area/getAreaInfo',//
                addUrl: 'area/addAreaInfo',//新增接口Url
                updateUrl: 'area/updateAreaInfo',//修改接口Url
                loadPageEvent: function () {
                    $(".btn-switch-outer").btnSwitch({name: 'state'});
                    internal.selectList.dialog('area', {
                        target: $("#btnArea"),
                        name: 'pid',
                        parentName: 'parentName',
                        valueField: 'id',
                        displayField: 'name',
                        fkNullContent: '根级节点',
                        selectParam: {
                            isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                            treeVaildMsg: '父级分类不能是其本身!',
                            multiselect: false
                        },
                        buttonsExtend: [{
                            text: '根级节点', onclick: function (item, dialog) {
                                $('input[name="pid"]').val(0);
                                $("#btnArea").find(".text-line-content").text("根级节点");
                                dialog.close();
                            }
                        }]
                    });
                }
            })
        }
    }


    exports.init = function () {
        internal.init()
    }
})
