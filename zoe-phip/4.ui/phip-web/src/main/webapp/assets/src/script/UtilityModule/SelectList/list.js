/**
 * Created by linqinghuang on 2016/4/12.
 */
define(function(require,exports,module){
    var BaseSelectList=require("{staticDir}/BaseSelectList/baseSelectList");
    var internal={
        init:function(){
            var selectList=new BaseSelectList({
                winName: "win_user_select_list",
                winCallback: "win_user_select_list_callback",
                stroage: [{id:'3af452e39f5b42f299c610ee61771fa2',loginName:'addfgmin'},{id:'3ef52b0da96a4eef9e9f41fe98b1d795',loginName:'admin'}],//存储的参数
                displayField:'loginName',
                valueField:'id',
                gridParam:{
                    url: webRoot + '/user/getUserList',
                    columns: [
                        {display: '名称', name: 'name', width: 160, align: 'left'},
                        {display: '登录名', name: 'loginName', width: 220, align: 'left'}
                    ],
                    height:245,
                    usePage:true
                },
                multiselect: true//是否多选:true为多选，false为单选
            });
        }
    };
    exports.init=function(){
        internal.init();
    }
})