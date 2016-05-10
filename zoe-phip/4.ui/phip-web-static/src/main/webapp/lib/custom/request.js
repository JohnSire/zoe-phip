//#region Request
(function (exports) {
    /**
     * 数据查询基类 Request
     * 根据页面需求进行封装，统一类名Request，同一实体对象可用get和post进行请求，对应ajax的get和post的请求类型，
     * 且同一实体对象中不能并发多个请求，任何新的get/post操作皆会将停止（abort）之前运行的异步请求。
     * exp
     *   构造实体对象：var req = new Request(actionName, 0);
     *   异步请求：req.get([param]); / req.post([param]);
     *   重载：req.reload();
     */
    // 构建类
    var r = exports.Request = new Class();
    r.extend({
        ajaxUrl: "",
        ajaxParam: {
            isTip: true,
            timeout: 180000,
            success: function (data) {
            }
        }
    });
    r.include({
        init: function (ajaxUrl) {
            /// <summary>数据请求基类</summary>
            /// <param name="ajaxUrl" type="String">请求action名称</param>
            // 初始化参数
            this.setAjaxUrl(ajaxUrl);
            this.request = null;
            this.run = false;
        },
        setAjaxUrl: function (ajaxUrl) {
            this.ajaxUrl = webRoot + ajaxUrl;
        },

        post: function () {
            this.ajax($.extend(true, {type: "POST"}, arguments[0]));
        },
        get: function () {
            this.ajax($.extend(true, {type: "GET"}, arguments[0]));
        },
        ajax: function () {
            if (arguments) {
                this.run = true;
                var url = this.ajaxUrl;
                this.param = $.extend(true, {}, r.ajaxParam, arguments[0]);
                //增加当前时间防止URL重复访问时不访问后台
                var ct = (url.indexOf("?") == -1 ? "?" : "&");
                url += ct + (new Date()).toUTCString();
                var onSuccess = null;
                if (this.param.isTip) {
                    var successObj = $.extend(true, {}, {success: arguments[0].success});
                    var success = function (data) {
                        var msgCss = data.isSuccess ? "Success" : "Error";
                        var msgContext = "";

                        if (data.isSuccess && (data.messages && data.messages.length == 0)) {
                            msgContext = "操作成功!"
                        }


                        if (!data.isSuccess && data.messages && data.messages.length > 0) {
                            $.each(data.messages, function (index, item) {
                                msgContext += item["content"]
                            });
                        }
                        common.getTopWindowDom().common.jsmsg(msgContext, msgCss, function () {
                            successObj.success(data);
                        })
                    };
                    arguments[0].success = success;

                    //新增脚本超时处理
                    var complete = this.param.complete;
                    arguments[0].complete = function (xhr, status) {
                        var sessionStatus = xhr.getResponseHeader('sessionstatus');
                        //null为手动清空cookie时；timeout为超时session 丢失
                        if (sessionStatus == 'timeout' || sessionStatus == null) {
                            //var top = common.getTopWindowDom(); top.location.href = webRoot + "Frame/Login"
                        } else {
                            if ($.isFunction(complete)) {
                                complete();
                            }
                        }
                    }
                }
                this.param = $.extend(true, this.param, {url: url}, arguments[0]);
                this.request = $.ajax(this.param);

            }
        },
        // 重载
        reload: function () {
            /// <summary>重载</summary>
            if (this.run) {
                this.abort();
                this.request = $.ajax(this.param);
            }
        },
        // 退出数据请求
        abort: function () {
            /// <summary>退出数据请求</summary>
            if (this.request) {
                this.request.abort();
            }
        }
    });
})(window);
//#endregion