/**
 * Created by linqinghuang on 2016/4/13.
 */
define(function(require,exports,module){
    var internal={
        search:function(options,callback){
            $("#btnSearch").on("click",function(){
                var data={};
                if(typeof(callback)=="function"){
                    data["keyWord"]= $.trim($("#txtKey").val());
                    callback(data);
                }
            });
        }
    };

    exports.searchbox=internal;

});