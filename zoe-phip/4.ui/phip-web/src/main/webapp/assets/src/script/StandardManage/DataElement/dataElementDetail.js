/**
 * Created by zhangxingcai on 2016/5/6 0006.
 */
define(function (require, exports, module) {
    var internal = {
            init: function () {
                var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
                var baseAttr = new BaseAttr({
                        winName: "win_dataElement_detail_dialog",//弹窗对象变量名称
                        winCallback: "win_dataElement_detail_callback",//弹窗回调函数
                        getUrl: 'element/getElementList',//
                        addUrl: 'element/addElementList',//新增接口Url
                        updateUrl: 'element/updateElementList',//修改接口Url/修改的接口Url
                        loadPageEvent: function () {
                            //值域选择
                            $("#chooseDict").click(function () {
                                top.list_dict_storage = JSON.parse(JSON.stringify(internal.list_dict_storage))

                                seajs.use('{dir}/BaseList/tools.js', function (list) {
                                    list.standardDict({
                                        height: 500, width: 540, callback: function (data) {
                                            internal.list_dict_storage = JSON.parse(JSON.stringify(data));
                                            var list = internal.list_dict_storage;

                                            if (list.length == 0) {
                                                $("#dictId").val("");
                                                $("#dictCode").val("");
                                                $("#dictName").val("");
                                            } else {
                                                $.each(list, function (index, item) {

                                                    $("#dictId").val(item.Id);
                                                    $("#dictName").val(item.DictName);
                                                })
                                            }
                                        }
                                    })
                                });

                            });
                        }

                    }
                );
            },
        }
        ;
    exports.init = function () {
        internal.init();
    }
})
;