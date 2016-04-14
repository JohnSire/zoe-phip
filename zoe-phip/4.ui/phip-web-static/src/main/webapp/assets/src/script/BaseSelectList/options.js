/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var options = {
        stroage: [],//存储的参数
        multiselect: false,//是否多选:true为多选，false为单选
        displayField: '',//内容的字段
        valueField: '',//值的字段
        gridParam: {//表格参数，跟ligerui 一致
            url: '',
            columns: [],//列
            checkbox: true//选择框
        },
        searchParam: function () {//列表查询条件相关参数
            var keyWord=$("#txtKey").val();
            var value=$.trim(keyWord);
            return {"keyWord":value };
        },
        winCallback:''
    }
    exports.defaultOptions = options;
})