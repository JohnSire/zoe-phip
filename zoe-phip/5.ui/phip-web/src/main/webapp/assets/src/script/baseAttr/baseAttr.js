define(function (require, exports, module) {
    var baseAttr = new Class();
    var event = require("./event.js");
    var options = {
        getUrl: '',//获取实例的接口
        addUrl: '',//新增的接口Url
        updateUrl: '',//修改的接口Url
        loadPageEvent: null,//加载页面
        beforeBindEvent: null, //function (data) { }//绑定事件前执行||服务器相应获取数据后，绑定到界面前执行
        afterBindEvent:null,
        beforeSaveEvent: null, //function (data) { return { sdf: "sdf" }},//点击保存按钮前回调函数,return回去的值会扩充到提交到服务端的参数里
        afterSaveEvent: function (data) { },//点击保存按钮，服务器返回响应后执行回调函数
        isCustomSubmit: false,//是否自定义提交
        customEvent: null,//function (data) { }//启用自定义提交，所执行的自定义方法
        resetSave: null
    }
    baseAttr.include({
        init: function () {
            var param = $.extend(true, {}, options, arguments[0]);
            event.init(param);
            this.run = true;
        }
    });
    module.exports = baseAttr;
});