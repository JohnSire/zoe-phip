define(function (require, exports, module) {
    var top = common.getTopWindowDom();
    var internal = {
        del: require("../../baseGrid/req.js").req,
        init: function () {
            internal.buildGrid();
        },
        grid: null,
        catalogId: "",
        buildGrid: function () {
            var gridParam = $.extend(true, {}, internal.gridParam());
            gridParam.url = webRoot + "/menu/getUserCfg";
            gridParam.columns = [
                { display: '名称', name: 'name', isSort: false, align: 'left' },
                { display: '登录名', name: 'loginName', isSort: false, align: 'left' },
                { display: '授权时间', name: 'createAt', width: 120, isSort: false, align: 'left', type: 'date' },
                { display: '授权人', name: 'createBy', width: 150, isSort: false, align: 'left' },
                {
                    display: '操作',
                    isSort: false,
                    width: 60,
                    align: 'center',
                    render: function (rowdata, rowindex, value) {
                        var h = "";
                        h += "<a class='icon-grid icon-grid-del' title='取消关联' onclick='javascript:delUser(\"" + rowdata.competenceId + "\")'></a> ";
                        return h;
                    }
                }
            ];
            internal.grid = $("#userGrid").ligerGrid(gridParam);
        },
        loadData: function (id) {
            if (internal.grid) {
                internal.catalogId = id;
                internal.grid.setParm('catalogId', id);
                internal.grid.setParm('keyWord', $("#txtKeyword").val());
                internal.grid.loadData();
            }
        },
        gridParam: function (param) {
            var gridParam = {
                checkbox: false,
                dataAction: "server",
                columns: [],
                pageSize: 30,
                width: "100%",
                height: "99.5%",
                delayLoad: true,
                usePage: true
            };
            return gridParam;
        },
        delUser: function (id) {
            var param = {};
            param.id = id;
            param.url = "/user/delUserAcc?ids=" + id;
            internal.del.deleteInfo(param, function (data) {
                internal.loadData(internal.catalogId);
            });
        }
    };
    exports.init = function () {
        internal.init();
    }
    exports.loadData = function (id) {
        internal.loadData(id);
    }
    exports.search = function (id) {
        //设置页数为1
        internal.grid.options.newPage = 1;
        internal.loadData(id);
    }
    window.delUser = internal.delUser;
});