define(function (require, exports, module) {
    var internal = {
        getCurrentUserList: function (catalogId, keyWord) {
            var list = [];
            var req = new Request("SystemMenu/GetUserCfg");
            req.post({
                isTip: false,
                async: false,
                data: { catalogId: catalogId, keyWord: keyWord, page: 1, pagesize: 999999999 },
                success: function (data) {
                    if (data && data.Result && data.Result.Total > 0) {
                        for (var item in data.Result.Rows) {
                            var menuItem = {
                                Id: data.Result.Rows[item].Id,
                                Name: data.Result.Rows[item].Name
                            }
                            list.push(menuItem);
                        }
                    }
                }
            });
            return list;
        }
    };
    exports.req = internal;
});