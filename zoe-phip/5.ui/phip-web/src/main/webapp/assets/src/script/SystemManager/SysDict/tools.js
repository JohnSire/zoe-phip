define(function (require, exports, module) {
    var internal = {
        req: require("./req.js").req,
        grid: require("./buildGrid.js"),
        init: function () {
            internal.dictCategory.init();
            internal.dictItem.init();
        },
        dictCategory: {
            init: function () {
                internal.dictCategory.search();
            },
            search: function () {
                $("#btnDictCategorySearch").on("click", function () {
                    var keyWord = $("#txtDictCategoryKeyWod").val();
                    internal.grid.dictCategoryReload();
                })
            }
        },
        dictItem: {
            init: function () {
                internal.dictItem.search();
                internal.dictItem.add();
                internal.dictItem.del();
            },
            search: function () {
                $("#btnDictItemSearch").on("click", function () {
                    var keyWord = $("#txtDictItemKeyWord").val();
                    var gridObj = liger.get("dictCategoryGrid");
                    var rowdata = gridObj.getSelected();
                    if (rowdata && rowdata["id"]) {
                        internal.grid.dictItemReload(rowdata["id"], keyWord);
                    } else {
                        common.jsmsg("请先选择相关字典分类！", "Error");
                    }
                });
            },
            add: function () {
                $("#btnDictItemAdd").on("click", function () {
                    var gridObj = liger.get("dictCategoryGrid");
                    var rowdata = gridObj.getSelected();
                    if (rowdata && rowdata["Id"]) {
                        var top = common.getTopWindowDom();
                        top.win_dict_item_dialog = $.ligerDialog.open({
                            title: '新增字典项',
                            url: webRoot + '/dict/view/item?type=add&&categoryId=' + rowdata["id"],
                            width: 590,
                            height: 200,
                            buttons: [
                                  {
                                      text: '确定', onclick: function (item, dialog) {
                                          var top = common.getTopWindowDom();
                                          if (typeof (top.win_dict_item_callback) == "function") {
                                              var reloadGrid = function () {
                                                  var gridObj = liger.get("dictItemGrid");
                                                  gridObj.reload();
                                              };
                                              top.win_dict_item_callback(reloadGrid);
                                          }
                                      }
                                  },
                                 { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
                            ]
                        })


                    } else {
                        common.jsmsg("请先选择相关字典分类！", "Error");
                    }
                })
            },
            del: function () {
                $("#btnDictItemDelete").on("click", function () {
                    var gridObj = liger.get("dictItemGrid");
                    var rowArray = gridObj.getSelectedRows();
                    var ids = "";
                    $.each(rowArray, function (index, item) {
                        ids += "," + item["Id"];
                    });
                    if (ids.length > 0) {
                        ids = ids.slice(1, ids.length);
                        $.ligerDialog.confirm('是否删除选中记录？', function (yes) {
                            if (yes) {
                                internal.req.dictItem.deleteList(ids, function () {
                                    var gridObj = liger.get("dictItemGrid");
                                    gridObj.reload();
                                });
                            }
                        });

                    } else {
                        common.jsmsg('请选择要删除的记录！', '', 'Error');
                    }
                });
            }

        }
    };
    exports.init = function () {
        internal.init();
    }
});