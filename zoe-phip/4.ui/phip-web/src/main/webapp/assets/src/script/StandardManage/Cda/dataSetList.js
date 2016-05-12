/**
 * Created by chenzhisen on 2016/5/6.
 */

define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "personnel/delXmanInfo",
                    deleteList: "personnel/delXmanList"
                },
                tools: {
                    btnbox: {
                        'custom': {
                            text: "返回 CDA列表", click: function () {

                                var top = common.getTopWindowDom();
                                var link = webRoot + "cda/view/cdaList";
                                top.frames["mainframe"].location.href = link;
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
                    url: 'personnel/getXmanList',
                    columns: [
                        {display: '数据集标识', name: 'code', width: 120, align: 'left'},
                        {display: '数据集名称', name: 'name', width: 120, align: 'left'},
                        {display: '描述', name: 'descr', width: 120, align: 'left'},


                        {display: '操作', isSort: false, width: 120, icons: ['del']}
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
                    add: {title: "新增数据集"},
                    //编辑参数
                    edit: {title: "编辑数据集"},
                    common: {
                        url: '#',
                        width: 680,
                        height: 350
                    }
                }
            })
            var name = decodeURI(common.getParamFromUrl("cdaName"));
            var html = "<p style='margin-top: 9px; '>" + "当前列表为CDA（" + name + "）下的数据集</p>"
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