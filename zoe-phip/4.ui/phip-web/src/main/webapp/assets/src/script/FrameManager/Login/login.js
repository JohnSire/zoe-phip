define(function (require, exports, module) {
    var internal = {
        init: function () {
            internal.showMsg("");
            internal.bindEvent();
            var eleUserName = document.getElementById("input-username");
            internal.placeHolder(eleUserName, true);
            var elePassword = document.getElementById("input-password");
            internal.placeHolder(elePassword, true);
        },
        bindEvent: function () {
            //绑定登录事件
            $("#btn-login").click(function () {
                if (internal.validate()) {//验证是否输入用户名密码
                    var rememberPwd = "";
                    if ($("#remember-password").is(":checked")) {
                        rememberPwd = "true";
                    }
                    else {
                        rememberPwd = "false";
                    }
                    var param = {
                        userCode: $("#input-username").val(),
                        userPwd: $("#input-password").val(),
                        rememberPwd: rememberPwd
                    };
                    var req = new Request("/frame/login/auth");
                    req.post({
                        isTip: false,
                        data: param,
                        beforeSend: function () {
                            internal.showMsg("正在登录中。。。");
                        },
                        success: function (data) {
                            if (data.isSuccess) {
                                window.location.href = webRoot + "/frame/index";
                            } else {
                                internal.showMsg(data.Message[0].Content);
                            }
                        },
                        complete: function () {
                        }
                    })
                }
            })
            //输入框事件
            $("#input-username").keyup(function (e) {
                if (e.keyCode == 13) {
                    $("#btn-login").click();
                }
            });
            $("#input-password").keyup(function (e) {
                if (e.keyCode == 13) {
                    $("#btn-login").click();
                }
            });
            if ($("#input-username").val() == "") {
                $("#input-username").focus();
            }
        },
        showMsg: function (msg) {//提示信息
            $("#span-msg").html(msg);
        },
        validate: function () {//前端验证
            if ($.trim($("#input-username").val()).length == 0 || "请输入用户名" == $.trim($("#input-username").val())) {
                internal.showMsg("您还没输入用户名！");
                $("#input-username").focus();
                return false;
            }
            if ($("#input-password").val().length == 0) {
                internal.showMsg("您还没输入密码！");
                $("#input-password").focus();
                return false;
            }
            return true;
        },
        /**
        * @name placeHolder
        * @class 跨浏览器placeHolder,对于不支持原生placeHolder的浏览器，通过value或插入span元素两种方案模拟
        * @param {Object} obj 要应用placeHolder的表单元素对象
        * @param {Boolean} span 是否采用悬浮的span元素方式来模拟placeHolder，默认值false,默认使用value方式模拟
        */
        placeHolder: function (obj, span) {
            if (!obj.getAttribute('placeholder')) return;
            var imitateMode = span === true ? true : false;
            var supportPlaceholder = 'placeholder' in document.createElement('input');
            if (!supportPlaceholder) {
                var defaultValue = obj.getAttribute('placeholder');
                if (!imitateMode) {
                    obj.onfocus = function () {
                        (obj.value == defaultValue) && (obj.value = '');
                        obj.style.color = '';
                    }
                    obj.onblur = function () {
                        if (obj.value == defaultValue) {
                            obj.style.color = '';
                        } else if (obj.value == '') {
                            obj.value = defaultValue;
                            obj.style.color = '#ACA899';
                        }
                    }
                    obj.onblur();
                } else {
                    var placeHolderCont = document.createTextNode(defaultValue);
                    var oWrapper = document.createElement('span');
                    oWrapper.style.cssText = 'position:absolute; color:#ACA899; display:inline-block; overflow:hidden;';
                    oWrapper.className = 'wrap-placeholder';
                    oWrapper.style.fontFamily = getStyle(obj, 'fontFamily');
                    oWrapper.style.fontSize = getStyle(obj, 'fontSize');
                    oWrapper.style.marginLeft = parseInt(getStyle(obj, 'marginLeft')) ? parseInt(getStyle(obj, 'marginLeft')) + 3 + 'px' : 3 + 'px';
                    oWrapper.style.marginTop = parseInt(getStyle(obj, 'marginTop')) ? getStyle(obj, 'marginTop') : 1 + 'px';
                    oWrapper.style.paddingLeft = getStyle(obj, 'paddingLeft');
                    oWrapper.style.width = obj.offsetWidth - parseInt(getStyle(obj, 'marginLeft')) + 'px';
                    oWrapper.style.height = obj.offsetHeight + 'px';
                    oWrapper.style.lineHeight = obj.nodeName.toLowerCase() == 'textarea' ? '' : obj.offsetHeight + 'px';
                    oWrapper.appendChild(placeHolderCont);
                    obj.parentNode.insertBefore(oWrapper, obj);
                    oWrapper.onclick = function () {
                        obj.focus();
                    }
                    //绑定input或onpropertychange事件
                    if (typeof (obj.oninput) == 'object') {
                        obj.addEventListener("input", changeHandler, false);
                    } else {
                        obj.onpropertychange = changeHandler;
                    }
                    function changeHandler() {
                        oWrapper.style.display = obj.value != '' ? 'none' : 'inline-block';
                    }
                    function getStyle(obj, styleName) {
                        var oStyle = null;
                        if (obj.currentStyle) oStyle = obj.currentStyle[styleName];
                        else if (window.getComputedStyle) oStyle = window.getComputedStyle(obj, null)[styleName];
                        return oStyle;
                    }
                }
            }
        }
    };
    exports.init = function () {
        internal.init();
    };
});
