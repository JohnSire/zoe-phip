/**
 * Created by linqinghuang on 2016/4/21.
 */
define(function (require, exports, module) {
    var internal = {
        areaGrid: null,
        path: require("./path").path,
        nodeId: null,
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            internal.areaList();
            internal.path.init(function (nodeId) {
                internal.reload(nodeId);
            }, function (nodeId) {
                internal.reload(nodeId);
            });

        },
        areaList: function () {
            var BaseGrid = require("{staticDir}/BaseGrid/baseGrid");
            internal.areaGrid = new BaseGrid({
                gridId: 'grid',
                deleteUrl: {
                    deleteInfo: "area/delAreaInfo",
                    deleteList: "area/delAreaList"
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
                dataAction: "local",
                extendParam: function () {
                    return {pid: internal.nodeId};
                },
                gridParam: {
                    url: 'area/getAreaListByPid',
                    columns: [
                        {display: '代码', name: 'code', width: 160, align: 'left'},
                        {
                            display: '名称', name: 'name', width: 180, align: 'left',
                            render: function (item) {
                                return "<a style='text-decoration:underline' onclick='javascript:drillPath(\"" + item["id"] + "\",\"" + item["name"] + "\")'>" + item["name"] + "</a>";
                            }
                        },
                        {
                            display: '成立时间',
                            name: 'buildTime',
                            width: 120,
                            align: 'left',
                            type: 'date',
                            format: 'yyyy-MM-dd'
                        },
                        {display: '操作', isSort: false, width: 120, icons: ['edit', 'del']}
                    ],
                    frozen: false,
                    usePage: true,
                    width: "100%",
                    height: "99%"//$("body").innerHeight() - $("#dictItemTools").outerHeight() - 76//500

                    /*width: $("body").innerWidth() - 2,
                     height: $("body").innerHeight() - $("#gridTools").outerHeight() - 38//500*/
                },
                dialogParam: {
                    winName: "win_area_detail_dialog",//弹窗对象变量名称
                    winCallback: "win_area_detail_callback",//弹窗回调函数
                    titleKey: "name",
                    //新增参数
                    add: {title: "新增行政区划"},
                    //编辑参数
                    edit: {title: "编辑行政区划"},
                    common: {
                        otherUrlParam: function () {
                            return {pid: internal.nodeId}
                        },
                        url: 'area/view/areadetail',
                        width: 680,
                        height: 450
                    }
                }
            })
        },
        reload: function (nodeId) {
            internal.nodeId = nodeId;
            var itemGrid = liger.get("grid");
            if (itemGrid.get("dataAction") == "local") {
                internal.areaGrid.setServer();
            } else {
                internal.areaGrid.reload();

            }
        }


    };
    window.drillPath = function (nodeId, nodeName) {
        internal.reload(nodeId);
        internal.path.addNode({id: nodeId, name: nodeName}, function (nodeId) {
            internal.reload(nodeId);
        })
    };
    exports.init = function () {
        internal.init();
    }
});