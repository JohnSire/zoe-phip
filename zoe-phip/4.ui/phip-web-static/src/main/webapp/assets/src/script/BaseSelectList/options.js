/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var options = {
        winName: "",
        winCallback: "",
        stroage: [],//存储的参数
        multiselect: false,//是否多选:true为多选，false为单选
        isTree:false,//是否树形展示表格
        displayField: '',
        valueField: '',
        gridParam: {
            url: '',
            columns: [],//列
            checkbox: true//选择框
        },//表格参数
        extendParam: {}//列表查询相关联的扩展参数
    }
    exports.defaultOptions = options;
})