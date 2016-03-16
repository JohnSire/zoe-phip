/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var options = {
        tools: {
            /*'add':true,'edit':true,'del':true,'refresh':true*/
            btnbox: {},
            searchbox: []
        },
        url: {
            getTreeList: ""
        },
        treeParam: {

        },
        dialogParam: {
            winName: "",
            winCallback: "",
            common: {
                width: 800,
                height: 600,
                buttons: [
                    {
                        text: "确定", onclick: function (item, dialog) {

                    }
                    },
                    {
                        text: "取消", onclick: function (item, dialog) {
                        dialog.close();
                    }
                    }
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
    }
    exports.defaultOptions = options;
})