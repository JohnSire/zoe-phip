define(function (require, exports, module) {
    var top = common.getTopWindowDom();
    var treeManager = null;
    var internal = {
        dictCatalogGrid: null,
        dictItemGrid: null,
        grid: require("./buildMenuAccGrid.js"),
        userGrid: require("./buildUserGrid.js"),
        req: require("./req.js").req,
        catalogCode: "MENU_ACC_TYPE",
        catalogId: "",
        init: function () {
            menuTypeTree.init();
            internal.event();
            internal.grid.init();
            internal.userGrid.init();
        },
        event: function () {
            $("#addUser").click(function () {
                seajs.use('{dir}/BaseList/tools.js', function (list) {

                    top.list_user_storage = internal.req.getCurrentUserList(internal.catalogId, '');

                    list.user({
                        height: 500,
                        width: 540,
                        callback: function (data) {
                            var listData = [];
                            if (data) {
                                for (var i = 0; i < data.length; i++) {
                                    listData.push(data[i].Id);
                                }
                            }
                            var req = new Request("/user/addUserAcc");
                            req.post({
                                data: { catalogId: internal.catalogId, ids: listData.join(",") },
                                success: function (data) {
                                    if (data.isSuccess) {
                                        internal.userGrid.loadData(internal.catalogId);
                                    }
                                }
                            });
                        }
                    });
                });
            });
            $("#addMenu").click(function () {
                var gridObj = liger.get("catalogGrid");
                var data = gridObj.getData();
                var top = common.getTopWindowDom();
                top.list_menu_storage = [];
                if (data) {
                    $.each(data, function (index, item) {
                        var menuItem = {
                            Id: item.Id,
                            Name: item.Name
                        };
                        top.list_menu_storage.push(menuItem);
                    });
                }
                seajs.use('{dir}/BaseList/tools.js', function (list) {
                    list.menu({
                        height: 500,
                        width: 540,
                        callback: function (data) {
                            var listData = [];
                            if (data) {
                                $.each(data, function (index, item) {
                                    listData.push(item["id"]);
                                })
                            }
                            var req = new Request("/menu/addMenuAcc");
                            req.post({
                                data: { catalogId: internal.catalogId, ids: listData.join(",") },
                                success: function (data) {
                                    if (data.isSuccess) {
                                        internal.grid.loadData(internal.catalogId);
                                    }
                                }
                            });
                        }
                    });
                });
            });
            $(".btn-search").click(function () {
                internal.userGrid.search(internal.catalogId);
            });
        }
    }
    var menuTypeTree = {
        init: function () {
            var req = new Request("/dict/getItemList");
            req.post({
                isTip: false,
                async: true,
                data: { catalogCode: internal.catalogCode },
                success: function (data) {
                    menuTypeTree.buildTree(data);
                }
            });
        },
        buildTree: function (data) {
            var treeData = [];
            $.each(data.result, function (index, item) {
                var treeItem = {};
                treeItem.text = item.name;
                treeItem.Id = item.id;
                treeData.push(treeItem);
            });
            var treeManager = $("#tree").ligerTree({
                nodeWidth: 180,
                needCancel: false,
                checkbox: false,
                data: [{ text: "权限分类", children: treeData }],
                onBeforeSelect: function (node) {
                    var id = node.data.Id;
                    if (!id)
                        return false;
                    return true;
                },
                onSelect: function (node) {
                    var id = node.data.Id;
                    internal.catalogId = id;
                    internal.grid.loadData(id);
                    internal.userGrid.loadData(id);
                },
                onExpand: function (node) {

                }
            });
            treeManager.selectNode($("[treedataindex='1']")[0]);
        },
        refresh: function () {
            treeManager.clear();
            menuTypeTree.init();
        }
    }
    exports.init = internal.init;
})
