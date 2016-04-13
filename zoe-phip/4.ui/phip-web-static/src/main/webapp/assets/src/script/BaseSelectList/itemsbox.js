/**
 * Created by linqinghuang on 2016/3/15.
 */
define(function (require, exports, module) {
    var internal = {
        //选中节点时添加
        addItem: function (data, displayField, valueField,delCallback) {
            if($(".item-"+data[valueField]).length==0) {
                var jqUl = $("#pane-list-selected");
                var jqLi = $('<li></li>').addClass("item");
                var jqClose = $('<a></a>').addClass("close").text("X").on("click", function () {
                    if (typeof (delCallback) == "function") {
                        internal.removeItem(data, valueField);
                        delCallback(data);
                    }
                })
                jqLi.addClass("item-" + data[valueField]).attr({title: data[displayField]});
                jqLi.append(jqClose);
                jqLi.append(data[displayField]);
                jqUl.append(jqLi).data("data", data);
            }

        },
        //移除选中节点
        removeItem: function (data, valueField) {
            $(".item-" + data[valueField]).removeData("data").remove();
        },
        //获取选项列表的值
        getItemsData: function () {
            var data = [];
            $("#pane-list-selected").find("li").each(function () {

            })
        },
        addItemList: function (storage, displayField, valueField,delCallback) {
            $.each(storage, function (index, item) {
                internal.addItem(item, displayField, valueField,delCallback);
            })
        },
        removeItemList:function(data,valueField){
            $.each(data,function(index,item){
                internal.removeItem(item,valueField);
            })
        }
    }
    exports.itemsbox = internal;
})
