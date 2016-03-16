/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function(require,exports,module){
    var internal={
        //选中节点时添加
        addItem:function(data){
            var jqUl=$("#pane-list-selected");
            var jqLi=$('<li></li>').addClass("list");
            var jqClose=$('<a></a>').addClass("close");



        },
        //取消选中节点时删除
        deleteItem:function(){

        },
        //获取选项列表的值
        getItemsData:function(){
            var data=[];
            $("#pane-list-selected").find("li").each(function(){

            })
        }
    }
})
