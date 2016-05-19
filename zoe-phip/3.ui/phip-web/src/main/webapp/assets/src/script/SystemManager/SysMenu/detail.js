define(function (require, exports, module) {
    var internal = {
        selectList: require("{dir}/UtilityModule/SelectList/list"),
        init: function () {
            var BaseAttr = require("{staticDir}/BaseAttr/baseAttr");
            var baseAttr = new BaseAttr({
                winName: "win_menu_detail_dialog",//弹窗对象变量名称
                winCallback: "win_menu_detail_callback",//弹窗回调函数
                getUrl: '/menu/getMenuInfo',//
                addUrl: '/menu/addMenuInfo',//新增接口Url
                updateUrl: '/menu/updateMenuInfo',//修改接口Url
                loadPageEvent: function () {
                    $(".btn-switch-outer").btnSwitch({name: 'state'});
                    internal.selectList.dialog('menu', {
                        target: $("#btnFkParent"),
                        name: 'fkParentMenuId',//绑定value值
                        parentName: 'parentName',//绑定name值
                        displayField: 'name',
                        valueField: 'id',
                        fkNullContent: '菜单根节点',
                        selectParam: {
                            isTreeVaild: true,//如果是树节点，父节点不能是其本身验证
                            treeVaildMsg: '父节点不能是其本身!',
                            multiselect: false
                        },
                        buttonsExtend: [{
                            text: '菜单根节点', onclick: function (item, dialog) {
                                $('input[name="fkParentMenuId"]').val(0);
                                $("#btnFkParent").find(".text-line-content").text("菜单根节点");
                                dialog.close();
                            }
                        }]
                    });


                }
            })
        }
    }
    exports.init = function () {
        internal.init();
    }
});