/*
 * need:jQuery V1.11.1
 */
//#region common function
(function (exports) {
    var com = {
        //获取最顶层的window DOM对象
        getTopWindowDom: function () {
            var obj = window.self;
            var outTimes = 0;
            while (true) {
                //如果取不到，会一直卡下去--默认10次
                if (obj.document.getElementById("flag_top_window")) {
                    return obj;
                }
                obj = obj.window.parent;
                if (outTimes > 10) {
                    return obj;
                }
                outTimes++;
            }
        },
        //获取url参数
        getParamFromUrl: function (name) {
            name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
            var regexS = "[\\?&]" + name + "=([^&#]*)";
            var regex = new RegExp(regexS);
            var results = regex.exec(window.location.href);
            if (results == null) {
                return null;
            }
            else {
                return results[1];
            }
        },
        //判断是否数字
        isNumber: function () {
            return /^[(-?\d+\.\d+)|(-?\d+)|(-?\.\d+)]+$/.test(val + '');
        },
        //阻止冒泡
        stopBubble: function () {
            if (event && event.stopPropagation) {
                event.stopPropagation();
            }
            else {
                window.event.cancelBubble = true;
            }
        },
        //消息提醒
        jsmsg: function (msgtitle, msgcss, callback) {
            $("#msgprint").remove();
            var cssname = "";
            switch (msgcss) {
                case "Success":
                    cssname = "pcent success";
                    break;
                case "Error":
                    cssname = "pcent error";
                    break;
                default:
                    cssname = "pcent warning";
                    break;
            }
            var str = "<div id=\"msgprint\" class=\"" + cssname + "\">" + msgtitle + "</div>";
            $("body").append(str);
            var bodyWidth = $("body").innerWidth(), msgprintWidth = $("#msgprint").outerWidth();
            $("#msgprint").css({
                "left": (bodyWidth - msgprintWidth) / 2 - 2,
                width: msgprintWidth,
                "z-index": 999999999
            });
            $("#msgprint").show();
            //1秒后清除提示
            setTimeout(function () {
                $("#msgprint").fadeOut(1000);
                //如果动画结束则删除节点
                if (!$("#msgprint").is(":animated")) {
                    $("#msgprint").remove();
                }
                if (typeof (callback) == "function") callback();
            }, 1200);
            //执行回调函数
        },
        //正确消息提醒
        jsmsgSuccess: function (msgtitle, callback) {
            com.jsmsg(msgtitle, 'Success', callback);
        },
        //错误消息提醒
        jsmsgError: function (msgtitle, callback) {
            com.jsmsg(msgtitle, 'Error', callback);
        },
        //表单验证
        vaildform: function (jqform) {
            jqform = jqform || $("#baseAttrForm");
            validformObj = $(jqform).Validform({
                tiptype: function (msg, o, cssctl) {
                    //msg：提示信息;
                    //o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
                    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
                    if (!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
                        var objtip = o.obj.siblings(".Validform_checktip");
                        if (objtip.length == 0) {
                            objtip = $('<span></span>').addClass("Validform_checktip");
                            $(objtip).insertAfter(o.obj)
                        }
                        cssctl(objtip, o.type);
                        $(o.obj).focus(function () {
                            if ($(this).hasClass("Validform_error")) {
                                var postion = $(this).offset();
                                var left = postion.left, top = postion.top;
                                $(".text-up-tips").css({
                                    left: left,
                                    top: top - 26,
                                    "z-index": 999
                                }).show().find(".msg").text(msg);
                            }
                        });
                        $(o.obj).blur(function () {
                            $(".text-up-tips").hide();
                        })

                    }
                }
            });
            return validformObj;
        },
        //采用iframe下载方式
        makeDownload: function (url) {
            var iframe = $("#downFrame");
            if (!iframe || iframe.length == 0) {
                iframe = $("<iframe id='downFrame' style='display:none;'></iframe>").appendTo($("body"));
            }
            iframe.prop("src", url);
        },
        //调用方式：数组对象arrayObj.sort(arraySortFun('asc||desc','ws'));其中arrayObj为JSON的数组对象，ws为json中的字段
        arraySortFun: function (order, sortBy) {
            var ordAlpah = (order == "desc") ? '>' : '<';
            var sortFun = new Function('a', 'b', 'return a.' + sortBy + ordAlpah + 'b.' + sortBy + '?1:-1');
            return sortFun;
        },

        //将线性结构转成树形结构的:data为要转换的数据，idKey，为pidKey的关联键， pid是为线性结构标识key，children为转成层级结构后的编码
        pidToChildren: function (data, idKey, pidKey, childrenKey) {
            //筛选出顶级节点的数据
            var result = [];
            $.each(data, function (index, item) {
                var isExist = false;
                $.each(data, function (index1, item1) {
                    //如果这个的父级节点存在，说明它不是顶级节点

                    //alert(item[idKey])
                    //alert(item[pidKey]);

                    if (item[pidKey] == item1[idKey]) {
                        isExist = true;
                    }
                });
                if (!isExist) {
                    result.push(item);
                }
            });
            //根据顶级节点开始组装子级节点
            $.each(result, function (index, item) {
                $.each(data, function (inedx1, item1) {
                    if (item[idKey] == item1[pidKey]) {
                        if (result[index][childrenKey]) {
                            result[index][childrenKey].push(item1);
                        } else {
                            result[index][childrenKey] = [];
                            result[index][childrenKey].push(item1);
                        }
                    }
                })
            })


            return result;
        }

    }
    exports.common = com;
})(window);
//#endregion
