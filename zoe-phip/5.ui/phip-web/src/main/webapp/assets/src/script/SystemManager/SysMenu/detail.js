define(function (require, exports, module) {
    var internal = {
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_menu_detail_dialog",//弹窗对象变量名称
                winCallback: "win_menu_detail_callback",//弹窗回调函数
                getUrl: webRoot + '/menu/getMenuInfo',//
                addUrl: webRoot + '/menu/addMenuInfo',//新增接口Url
                updateUrl: webRoot + '/menu/updateMenuInfo',//修改接口Url
                loadPageEvent: function () {
                    $(".btn-switch-outer").btnSwitch({name: 'state'});
                    $("#btnFkParent").selectDialog({
                        winName:'win_menu_tree_dialog',
                        winCallback:'win_menu_tree_select_callback',
                        name:'fkParentMenuId',
                        fkObj:'parentMenu',
                        dialogParam: {
                            title:'选择父级菜单节点',
                            url: '/menu/menutree?noDrag=1',//弹窗url
                            width: 500,
                            height: 580,
                            buttons: [
                                {
                                    text: "关闭",
                                    onclick: function (item, dialog) {
                                        dialog.close();
                                    }
                                }
                            ]
                        }

                    });
                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});