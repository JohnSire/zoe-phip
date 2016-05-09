define(function (require, exports, module) {
    var internal = {
        getMenuList: function (callback) {
            var req = new Request("menu/getMenuList");
            req.post({
                async: false,
                isTip: false,
                data:{sortname:"sort",sortorder:"asc"},
                success: function (data) {
                    if (typeof (callback) == "function") {
                        var result = common.pidToChildren(data.result.rows, "id", "fkParentMenuId", "children");
                        //document.write(JSON.stringify(result));
                        var menuData = {result: result};
                        callback(menuData);
                    }
                }
            })
        }
    };
    exports.req = {
        getMenuList: function (callback) {
            internal.getMenuList(callback);
        }

    }
});