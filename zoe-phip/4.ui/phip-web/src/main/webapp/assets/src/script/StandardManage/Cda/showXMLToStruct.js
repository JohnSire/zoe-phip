/**
 * Created by chenzhisen on 2016/5/12.
 */

define(function (require, exports, module) {

    var internal = {

        init: function (cdaID, sourceID, type) {
            Ext.onReady(function(){
                //  Ext.BLANK_IMAGE_URL = '<%=rootpath%>/ext/resources/images/default/s.gif';
                var tree = new Ext.tree.TreePanel({
                    region: 'center',
                    //True表示为面板是可收缩的，并自动渲染一个展开/收缩的轮换按钮在头部工具条
                    collapsible: true,
                    title: '结构化预览视图',//标题文本
                    width:"100%",
                    height:350,
                    border : false,//表框
                    autoScroll: true,//自动滚动条
                    animate : true,//动画效果
                    rootVisible: false,//根节点是否可见
                    split: true,
                    loader : new Ext.tree.TreeLoader(),
                    root : new Ext.tree.AsyncTreeNode({
                        text:'我是根节点',
                        children:[{
                            text : '01',
                            children : [
                                { text : '01-01' , leaf : true },
                                { text : '01-02' , leaf : true }
                            ]
                        },{
                            text :'02',
                            children : [
                                { text : '02-01' , leaf : true },
                                { text : '02-02' , leaf : true }
                            ]
                        }]
                    }),
                    listeners: {
                        afterrender: function(node) {
                            tree.expandAll();//展开树
                        }
                    }
                });
                new Ext.Viewport({
                    items: [tree]
                });
            });
        }
    }


    exports.init = function () {
        internal.init();
    }
});