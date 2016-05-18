/**
 * Created by chenzhisen on 2016/5/6.
 */

define(function (require, exports, module) {
    var top = common.getTopWindowDom();
    var ajaxStore = {
        getSetList: function (fkCdaId, callback) {
            var req = new Request("cda/getSetList");
            req.post({
                isTip: false,//是否有请求结果消息提示（成功||失败）
                data: {"fkCdaId": fkCdaId, "keyWord": ""},
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        },
        updateByCdaId: function (data, callback) {
            var req = new Request("cda/updateByCdaId");
            req.post({
               isTip: true,//是否有请求结果消息提示（成功||失败）
                data: data,
                success: function (data) {
                    if (typeof (callback) == "function") {
                        callback(data);
                    }
                }
            })
        }
    }
    var fkCdaId = common.getParamFromUrl("fkCdaId");
    var setList = [];
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {


            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            var baseGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "cda/delRsCdaInfo",
                    deleteList: "cda/delRsCdaList"
                },
                tools: {
                    btnbox: {
                        'add': true,
                        'del': true
                    },
                    searchbox: [
                        {label: '关键字', name: 'keyWord', type: 'text'}
                    ]
                },

                gridParam: {
                    url: 'cda/getSetList?fkCdaId=' + fkCdaId,
                    columns: [
                        {display: '数据集标识', name: 'code', width: 120, align: 'left'},
                        {display: '数据集名称', name: 'name', width: 150, align: 'left'},
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
                        otherUrlParam: function () {
                            return {fkCdaId: fkCdaId}
                        },
                        url: '#',
                        width: 680,
                        height: 350
                    }
                }
            })
            var name = decodeURI(common.getParamFromUrl("cdaName"));

            var html = '<div op="custom" style="float:left;"><p class="y-layout-position" ><span class="icon-position"></span><a class="link" id="back">' + name + '</a><span class="arrow">&gt;</span><a class="link">关联数据集</a></p></div>'
            $("#gridTools").append(html);

            $("#back").click(function () {
                var top = common.getTopWindowDom();
                var link = webRoot + "cda/view/cdaList";
                top.frames["mainframe"].location.href = link;

            });

            $(".btn-add").parent().unbind();

            top.setList = setList;
            window.setList = setList;
            ajaxStore.getSetList(fkCdaId, function (data) {
                setList = data.result.rows;

                internal.selectSet();
            })


        },
        selectSet: function () {
            internal.selectList.dialog('dataSet', {
                target: $(".btn-add"),
                name: 'pid',
                parentName: 'parentName',
                valueField: 'id',
                displayField: 'name',
                fkNullContent: '根级节点',
                isTextbox: false,
                selectParam: {
                    stroage: function(){
                        return setList;
                    },
                    isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                    treeVaildMsg: '父级分类不能是其本身!',
                    multiselect: true
                },
                callback: function (data) {
                    var newSetList = data;
                    var postdata = {"fkCdaId": fkCdaId};
                    var setIds = "";

                    $.each(data, function (index, item) {
                        if (index == data.length - 1) {
                            setIds += item.id;
                        } else {
                            setIds += item.id + ",";
                        }
                    })
                    postdata.setIds = setIds;

                    ajaxStore.updateByCdaId(postdata, function (data) {
                        setList = newSetList;
                        $(".btn-search").click();
                    });
                }

            });
        }

    };
    exports.init = function () {
        internal.init();
    }
});