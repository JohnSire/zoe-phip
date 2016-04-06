/**
 * Created by linqinghuang on 2016/3/15.
 * BaseGrid external param
 * 属性封装参数
 * Copyright (c) zoe 2016
 */
define(function (require, exports, module) {
    var options = {
        gridId: "grid",
        toolsBoxId: "gridTools",
        /*工具条*/
        tools: {
            /* 'add': true, 'del': true, 'refresh': true, 'import': true, 'export': true 系统默认有的按钮，支持我们自定义进行扩展 */
            btnbox: {},
            searchbox: []
        },
        //两个删除接口，一个是删除一个实体，一个是删除列表
        deleteUrl: {
            deleteInfo: "",
            deleteList: ""
        },
        //扩展参数（如分类传入的参数）

        extendParam: function () {
            //return { id: "fff" };
        },
        gridParam: {
            checkbox: true,
            dataAction: "server",
            rownumbers: true,
            parms: {},
            url: "",
            columns: [],
            pageSize: 30,
            root: "rows",
            record: "total",
            //width: '100%',
            //height: '99%',
            usePager: true
        },
        dialogParam: {
            winName: "",
            winCallback: "",
            titleKey: null,//弹窗标题索引 如"编辑用户--张三"其中张三是通过索'userName'获取
            common: {
                otherUrlParam: {},//用来存除id外的其他url参数
                width: 800,
                height: 600,
                buttons: [
                    {
                        //verifySubmit自己的验证提交状态
                        text: "确定",
                        verifySubmit: true,
                        submitText: '提交中...',
                        onclick: function (item, dialog, submited) {
                        }
                    },
                    {
                        text: "取消",
                        onclick: function (item, dialog) {
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
    };
    exports.defaultOptions = options;

})