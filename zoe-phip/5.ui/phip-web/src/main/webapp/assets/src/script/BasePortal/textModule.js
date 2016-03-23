//文本模块
define(function (require, exports, module) {
    var internal = {
        module: require("../BasePortal/module.js").module,
        add: function (options) {
            var jqControlObj = options["jqControlObj"];
            var jqModule = internal.module.build({
                onResize: function (id, moduleContentWidth, moduleContentHeight) {

                },
                onSetting: function (id) {
                    var top = common.getTopWindowDom();
                    top.win_base_attr_dialog = $.ligerDialog.open({
                        title: '文本配置',
                        url: webRoot + "BasePortalMain/TextProps",
                        width: 800,
                        height: 600,
                        buttons: [
                             {
                                 text: '确定', onclick: function (item, dialog) {
                                     if (typeof (top.win_base_attr_callback) == "function") {
                                         var html = top.win_base_attr_callback();
                                         internal.buildText(jqModule, { text: html });
                                         dialog.close();
                                     }
                                 }
                             },
                            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
                        ]
                    });

                   
                },
                onDrill: function () {

                },
                mtype: "text"
            });
            var jqContent = $(jqControlObj).find("[ptype='content']");
            jqContent.append(jqModule);
        },
        buildText: function (jqModule, options) {
            var moduleContentHeight = $(jqModule).innerHeight() - $(jqModule).find(".y-layout-col-header").outerHeight(),
                      moduleContentWidth = $(jqModule).innerWidth();
            var jqContent = $(jqModule).find(".y-layout-col-content").width(moduleContentWidth).height(moduleContentHeight);
            var text = options["text"];
            var Money = Math.ceil(Math.random() * 100);
            var Many = Math.ceil(Math.random() * 10);
            var jqText = internal.converHtml(text, { Money: Money, Many: Many });
            jqContent.empty().append(jqText);
        },
        converHtml: function (htmlContent, data) {
            return htmlContent.replace(/\{#(\w+)#\}/g, function (m, i) {
                var text = "<a style='color:red;'>" + data[i] + "</a>";
                return text;
            });
        }

    }
    exports.textModule = {
        add: function (options) {
            internal.add(options);
        }
    }
});