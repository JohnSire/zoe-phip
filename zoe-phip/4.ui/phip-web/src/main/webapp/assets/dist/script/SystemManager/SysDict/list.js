define("modules/SystemManager/SysDict/list", ["{staticDir}/BaseGrid/baseGrid"], function (a, b, c) {
    var d = a("{staticDir}/BaseGrid/baseGrid"), e = {
        fkSystemDictCategoryId: null, init: function () {
            e.categoryList()
        }, categoryList: function () {
            new d({
                gridId: "dictCategoryGrid",
                toolsBoxId: "categoryTools",
                tools: {searchbox: [{label: "关键字", name: "keyWord", type: "text"}]},
                gridParam: {
                    url: webRoot + "/dict/getCategoryList",
                    columns: [{display: "字典编码", name: "code", isSort: !1, width: 160, align: "left"}, {
                        display: "字典名称",
                        name: "name",
                        isSort: !1,
                        width: 200,
                        align: "left"
                    }, {display: "描述", name: "descr", isSort: !1, width: 220, align: "left"}],
                    isSelected: function (a) {
                        return "r1001" == a.__id ? !0 : void 0
                    },
                    onSelectRow: function (a, b, c) {
                        e.fkSystemDictCategoryId = a.id;
                        var d = liger.get("dictItemGrid");
                        d ? (d.set("page", 1), d.set("newPage", 1), d.setParm("categoryId", a.id), d.reload()) : e.itemList(a.id)
                    },
                    checkbox: !1,
                    width: "100%",
                    height: "99%"
                }
            })
        }, itemList: function (a) {
            new d({
                gridId: "dictItemGrid",
                toolsBoxId: "dictItemTools",
                deleteUrl: {deleteInfo: "/dict/deleteItem", deleteList: "/dict/deleteItemList"},
                tools: {btnbox: {add: !0, del: !0}, searchbox: [{label: "关键字", name: "keyWord", type: "text"}]},
                extendParam: function () {
                    return {categoryId: a}
                },
                gridParam: {
                    url: webRoot + "/dict/getItemPageList",
                    columns: [{display: "项编码", name: "code", width: 150, align: "left"}, {
                        display: "项名称",
                        name: "name",
                        width: 400,
                        align: "left"
                    }, {display: "操作", isSort: !1, width: 120, icons: ["edit", "del"]}],
                    frozen: !1,
                    usePage: !0,
                    width: "100%",
                    height: "99%"
                },
                dialogParam: {
                    winName: "win_dict_item_dialog",
                    winCallback: "win_dict_item_callback",
                    titleKey: "name",
                    add: {title: "新增字典项信息"},
                    edit: {title: "编辑字典项信息"},
                    common: {
                        otherUrlParam: function () {
                            return {fkSystemDictCategoryId: e.fkSystemDictCategoryId}
                        }, url: webRoot + "/dict/view/item", width: 590, height: 200
                    }
                }
            })
        }
    };
    b.init = function () {
        e.init()
    }
});