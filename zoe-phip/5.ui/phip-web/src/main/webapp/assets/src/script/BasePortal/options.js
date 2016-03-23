//插件参数
define(function (require, exports, module) {
    var internal = {
        options: {
            jqControlObj: $("#basePortal"),//整个控件的对象
            //提供选择有["text","grid", "pie", "bar", "line", "radarMap","container"]-->文本，网格、饼图、柱状图、曲线图、雷达图、容器
            toolBarBtns: [],
            usePageSearchParam: false,//是否启用界面参数
            isShowStateBar: true,//是否显示状态栏
            //界面参数
            pageSearchParam: function () {
                return [];
            },
            //界面的值
            data: {},
            chartParam: {
                url: '',
                onSetting: function (jqModule, chartProps) { }
            },
            gridParam: {
                url: '',
                onSetting: function (jqModule, gridProps) { }
            },
            onSave: null,//保存事件
            onCancel: null//取消事件
        }
    };
    exports.options = internal.options;
});