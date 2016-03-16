/**
 * Created by linqinghuang on 2016/3/14.
 * BaseAttr external param
 * 属性封装参数
 * Copyright (c) zoe 2016
 */
define(function (require, exports, module) {
    var options = {
        dialogParam: {
            winName: null,//窗体的变量标识
            winCallback: null,//窗体的保存（确定）的回调函数
            jqForm:null,//弹窗所包含的的表单
            allKeys: [],
            primaryKey: 'id',//主键
            stateKey: 'state'//key：状态类型索引；value为"add" or "edit"
        },
        getUrl: '',//获取绑定值的接口
        addUrl: '',//新增接口Url
        updateUrl: '',//修改接口Url
        loadPageEvent: null,//加载页面
        beforeBindEvent: null,//绑定事件前执行||服务器相应获取数据后，绑定到界面前执行
        beforeSaveEvent: null,//function (data) { return { name: "name" }},//点击保存按钮前回调函数,return回去的值会扩充到提交到服务端的参数里
        customEvent: null,//function (data) { }//启用自定义提交，执行的自定义方法,本方法会替换默认的addUrl或updateUrl所执行的事件
        afterSaveEvent: null//点击保存按钮，服务器返回响应后执行回调函数
    }
    exports.defaultOptions = options;
});
