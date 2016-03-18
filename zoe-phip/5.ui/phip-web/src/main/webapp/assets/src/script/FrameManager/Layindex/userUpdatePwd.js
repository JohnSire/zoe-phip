define(function (require, exports, module) {
    var internal = {
        top: common.getTopWindowDom(),
        init: function () {
            internal.set();
        },
        set: function () {
            internal.top.win_update_user_password = function () {
                var data = $("#baseAttrForm").form2json();
                if (common.validformObj.check()) {
                    var req = new Request("SystemUser/UpdatePassword")
                    req.post({
                        data: data,
                        isTip: false,
                        success: function (data) {
                            if (data.IsSuccess) {
                                common.jsmsgSuccess('密码修改成功，请重新登录！');
                                setTimeout(function () { internal.top.location.href = webRoot + "Frame/Skip"; }, 1000)
                            } else {
                                common.jsmsgError(data.Message[0].Content);
                            }
                        },
                        complete: function () {

                        }
                    });
                }
            }
            
           
        }

    }
    exports.init = function () {
        internal.init();
    }
});