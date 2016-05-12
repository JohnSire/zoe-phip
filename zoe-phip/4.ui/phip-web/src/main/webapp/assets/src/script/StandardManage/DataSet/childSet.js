/**
 * Created by chenzhisen on 2016/5/10.
 */


define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "dataSet/delXmanInfo",
                    deleteList: "dataSet/delXmanList"
                },
                tools: {
                    btnbox: {
                        'custom': {
                            text: "返回 数据集列表", click: function () {

                                var top = common.getTopWindowDom();
                                var link=webRoot+"dataSet/view/dataSetList";
                                top. frames["mainframe"].location.href = link;
                            }
                        },
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },
                // reqInfoKey:'patientId',
                gridParam: {
                    url: 'dataSet/getByChildSet?pid='+common.getParamFromUrl("fkSetId"),
                    columns: [
                        {display: '编码', name: 'code', width: 180, align: 'left'},
                        {display: '名称', name: 'name', width: 180, align: 'left'},
                        //{display: '标准来源名称', name: 'sexCodeName', width: 120, align: 'left'},
                        {display: '描述', name: 'descr', width: 120, align: 'left'},

                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    usePage: true,
                    width: $("body").innerWidth() - 2,
                    height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500
                },
                dialogParam: {
                    winName: "win_xmanbase_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_xmanbase_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增字段"},
                    //编辑参数
                    edit: {title: "编辑字段"},
                    common: {
                        url: 'dataSet/view/columnDetail',
                        width: 680,
                        height: 450
                    }
                }
            })
            var name=decodeURI(common.getParamFromUrl("dataSetName"));
            var html="<p style='margin-top: 9px; '>"+"当前列表为数据集（"+name+"）下的子数据集</p>"
            $("#gridTools").append(html);
            $(".btn-add").parent().unbind();
            internal.selectList.dialog('dataSet', {
                target: $(".btn-add"),
                name: 'pid',
                parentName: 'parentName',
                valueField: 'id',
                displayField: 'name',
                fkNullContent: '根级节点',
                selectParam: {
                    isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                    treeVaildMsg: '父级分类不能是其本身!',
                    multiselect: true
                }
            });
        }

    };
    exports.init = function () {
        internal.init();
    }
});