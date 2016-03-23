define(function (require, exports, module) {
    var baseGrid = new Class();
    var buildGrid = require("./buildGrid.js");
    var tools = require("./tools.js");
    var defaultOptions = {
        //界面中设计两个删除的接口，一个是删除一个实体，一个是删除列表
        deleteUrl: undefined/*{
            deleteInfo: "",
            deleteList: ""
        }*/,
        //工具条
        tools: {
            buttons: [],
            searchbox: []
        },
        //搜索框
        search: [],
        //表格参数
        gridParam: {
            editBtn: true,
            linkParam: null,//表格关联参数，扩展用来承接关联传来的值{}
            checkbox: true,
            dataAction: "server",
            columns: [],
            pageSize: 30,
            width: '100%',
            height: '99%',
            usePager: true
        },
        //弹窗参数[编辑或新增]
        dialogParam: {
            //公用参数
            common: {
                width: 800,
                height: 600,
                buttons: [
                    {
                        text: '确定', onclick: function (item, dialog) {
                            var top = common.getTopWindowDom();
                            if (typeof (top.win_base_attr_callback) == "function") {
                                var reloadGrid = function () {
                                    var gridObj = liger.get("grid");
                                    gridObj.reload();
                                };
                                top.win_base_attr_callback(reloadGrid);
                            }
                        }
                    },
                    { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
                ]
            },
            //新增参数
            add: {
                title: "新增信息"
            },
            //编辑参数
            edit: {
                title: "编辑信息"
            }
        }
    };

    baseGrid.include({
        __grid: undefined,
        init: function () {

            var me = this;
            var options = $.extend(true, defaultOptions, arguments[0]);

            if (options.gridParam.linkParam) {
                options.gridParam["parms"] = $.extend(true, {}, options.gridParam["parms"] || {}, options.gridParam.linkParam);
            }
            //工具条初始化
            tools.init(options);
            var gridReloadHandler = function () {
                var searchParam = options.tools.searchbox;
                var parms = {};
                if (options.gridParam.linkParam) {
                    $.each(options.gridParam.linkParam, function (key, val) {
                        parms[key] = val;
                    })
                }
                $.each(searchParam, function (index, item) {
                    parms[item["name"]] = $.trim($("[name='" + item["name"] + "']").val());
                });
                me.reload(parms);
            };
            tools.searchBox(options, function () {
                gridReloadHandler();
            }, function () {
                gridReloadHandler();
            });


            this.__grid = buildGrid.init(options);//列表初始化||表格需要用到
            this.run = false;
        },
        reload: function (data) {
            var me = this;
            data && ($.each(data, function (k, v) {
                me.__grid.setParm(k, v);
                ////me.__grid.changePage("first");
            }));
            me.__grid.set("page", 1);
            me.__grid.set("newPage", 1);
            me.__grid.loadData();
        }
    });

    module.exports = baseGrid;
});