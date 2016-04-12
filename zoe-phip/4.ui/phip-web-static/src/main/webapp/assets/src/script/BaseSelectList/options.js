/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var options = {
        stroage: [],//存储的参数
        url: '',//获取列表的url
        seach: {
            'totalColNum': 2,//将界面总共被分为为几个等列（每个列的宽度为：$("body").innerWidth()/cutColNum);
            'attr': {},//每个查询输入（选择）框都具有的属性都可以在此配置
            'class':'',//每个查询输入（选择）框都具有的属性可以在
            'searchBox': [
                {
                    "label": "列别",
                    "name": "filedType",
                    "type": "select",
                    "url": "",//获取接口的参数
                    "fromOtherParam": {},//来自其他方面的参数（如环境变量、获取其他变量)
                    "show": {"text": "text", "value": "code"},//显示内容跟值
                    "isSearch": true,
                    "colNum": 1,
                    "onChange": function () {
                    },
                    'attr': {}//一些界面特殊的属性可以在此扩展

                },
                {
                    "label": "关键字",
                    "name": "keyWord",//这个需要跟查询条件的code匹对
                    "type": "text",
                    "colNum": 1,
                    'useSearchButton': true, //携带查询按钮
                    'attr': {}//一些界面特殊的属性可以在此扩展
                }
            ]
        },//搜索框
        multiselect: false,//是否多选:true为多选，false为单选
        isTree:false,//是否树形展示表格
        display: '',
        value: '',
        gridParam: {
            url: '',
            columns: [],//列
            checkbox: true//选择框
            //height: 350,//根据界面自动计算高度
            //width: 600,//根据界面自动计算宽度
        },//表格参数
        extendParam: {}//列表查询相关联的扩展参数
    }
    exports.defaultOptions = options;
})