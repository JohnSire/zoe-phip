(function () {
    //获取表单控件值
    $.fn.form2json = function () {
        var obj = {};
        $.each(this.serializeArray(), function (i, o) {
            var n = o.name, v = o.value;
            obj[n] = obj[n] === undefined ? v
                : $.isArray(obj[n]) ? obj[n].concat(v)
                : [obj[n], v];
        });
        return obj;
    }
    //表单绑定值
    $.fn.json2form = function (obj) {
        if (obj == null || typeof (obj) == 'undefined')
            return false;
        var param = obj;
        $.each(obj, function (i, o) {
            var jqObj = $("[name='" + i + "']");
            var type = jqObj.attr("type");
            var isSelect = $(jqObj).is('select');
            if (isSelect) {
                type = "select";
            }
            var edittype = jqObj.attr("edittype");
            switch (type) {
                case "text":
                    if (edittype == "date") {
                        if (o) {
                            o = o.replace("/Date(", "").replace(")/", "");
                            o = new Date(parseInt(o));
                            var year = o.getFullYear();
                            var month = o.getMonth() + 1;
                            var date = o.getDate();
                            var hour = o.getHours();
                            var minute = o.getMinutes();
                            var second = o.getSeconds();
                            o = year + "-" + month + "-" + date;
                        }
                        jqObj.ligerGetDateEditorManager().setValue(o)
                    } else {
                        jqObj.val(o);
                        jqObj.trigger("setValue", obj);
                    }
                    break;
                case "select":
                    jqObj.find("option[value='" + o + "']").attr("selected", "selected");
                    jqObj.val(o);
                    $("select[name='" + i + "']").trigger("change", o);//配合switch插件用；radio值绑定时,switch会自动切换；
                    break;
                case "radio":
                    $("input[name='" + i + "'][value='" + o + "']").prop("checked", true);
                    var checkValue = $("input[name='" + i + "']:checked").val();
                    $("input[name='" + i + "'][value='" + o + "']").trigger("change");//配合switch插件用；radio值绑定时,switch会自动切换；
                    break;
                default:
                    jqObj.val(o);
                    break;
            }
        })
    }
    //遮罩
    $.fn.mask = function (options) {
        var internal = {
            maskList: [],
            defaultOptions: {
                "textAlign": 'center',
                "linheight": '400px',
                "position": 'absolute',
                "left": 0,
                "top": 0,
                "width": "100%",
                "height": "100%",
                "filter": "alpha(opacity=50)",
                "opacity": "0.5",
                "background": "#ccc",
                "overflow": "hidden",
                "zoom": "1",
                "zIndex": 9999,
                "display": "block",
                "text": "数据加载中......",
                "loadingImg": 'http://localhost:1663/Assets/Src/Images/icon_onload.gif'
            },
            init: function (self) {
                internal.build(self);
                internal.show(self);
            },
            getId: function (remark) {
                remark = remark || 'mask';
                var id = remark + (Math.round(Math.random() * 10000));
                return id;
            },
            build: function (self) {
                var loading_img = self.options.loadingImg,
                    dom_loading = $("<img style='display:inline-block'></img>").attr("src", loading_img),
                    dom_span = $("<span></span>").append(dom_loading, self.options.text),
                    div_mask = $("<div></div>").addClass("panelMask").css({
                        "text-align": self.options.textAlign,
                        "line-height": self.options.lineHeight,
                        "position": self.options.position,
                        "left": self.options.left,
                        "top": self.options.top,
                        "width": self.options.width,
                        "height": self.options.height,
                        "filter": self.options.filter,
                        "opacity": self.options.opacity,
                        "background": self.options.background,
                        "overflow": self.options.overflow,
                        "font-size": self.options.fontSize,
                        "*zoom": self.options.zoom,
                        "z-index": self.options.zIndex,
                        "display": self.options.display
                    }).append(dom_span);//遮罩层添加提示
                self.div_mask = div_mask;
                self.div_mask.attr("mask_id", internal.getId());
                internal.maskList.push(self.div_mask);//保存当前遮罩层 
            },
            show: function (self) {
                var elem = self.div_mask;
                var maskId = $(elem).attr("mask_id");
                var maskElem = internal.getMask(maskId);
                elem.css({
                    "width": $(self).innerWidth() + "px",
                    "height": $(self).innerHeight() + "px",
                    "line-height": $(self).innerHeight() + "px",
                    "top": 0,//target.offset().top,
                    "left": 0 //target.offset().left
                });
                if ($(".panelMask", self).length == 0)
                    $(self).append(elem);
                $(maskElem).css("display", "block");
            },
            remove: function () {

            },
            getMask: function (maskId) {

            },
            layout: function () {

            }
        };

        var target = this;
        $(target).each(function () {
            self = this;
            self.options = $.extend(true, {}, internal.defaultOptions, options);
            internal.init(self);
        })
    }
    //select 选择封装
    $.fn.select = function (options) {
        var internal = {
            vaildformObj: common.vaildform(),
            defaultOptions: {
                name: '',//跟实体key对应的name（支持自动绑定）
                isVaild: true,//是否启用验证；结合unit中对vaildform封装的使用
                vaild: {
                    datatype: "*",
                    nullmsg: '必须选择一个选项'
                },
                isAsync: true,//是否异步加载，点击时加载数据，如果已经请求过的就不在请求
                ajaxParam: {
                    url: '',//url 请求的地址
                    data: {}
                },
                data: [],
                preText: '',//预加载显示内容
                value: '',//值
                text: '',//展示的内容
                rows: 6//显示几行，如果超过的则出现滚动条，如果少于不影响
            },
            //插件使用
            init: function (self) {
                internal.render(self);
            },
            //渲染插件
            render: function (self) {
                var jqSelectOuter = $(".simulate-select"), jqSelectInput = $(self).find("input,i"), jqUl = $(self).find(".simulate-select-lists");
                var jqDefaultSelect = $("<select></select>").attr({"name": self["param"]["name"]}).css({display: "none"});

                if (self["param"]["isVaild"]) {
                    $.each(self["param"]["vaild"], function (index, item) {
                        var jqPro = {};
                        jqPro[index] = item;
                        jqDefaultSelect.attr(jqPro);
                    })
                }
                $(self).after(jqDefaultSelect);

                //是否已经加载了数据
                var hasLoadData = false;

                //如果是非异步加载，且有url请求路径,初始化时即加载数据
                if (!self["param"]["isAsync"] && self["param"]["ajaxParam"]["url"]) {
                    internal.req(self.param, function (data) {
                        var data = [{text: "--请选择状态--"}, {value: '1', text: '启用'}, {value: '0', text: '禁用'}];
                        $.each(data, function (index, item) {
                            liRender(item["value"], item["text"]);
                            optionRender(item["value"], item["text"]);
                        });
                        hasLoadData = true;
                    });
                } else if (!self["param"]["ajaxParam"]["url"]) {//如果没有url请求路径,说明界面已经配置好值了，则从界面取值

                }
                jqDefaultSelect.change(function (event, arg) {

                    var value = jqDefaultSelect.find("option[value = '" + arg + "']").val(),
                        text = jqDefaultSelect.find("option[value = '" + arg + "']").text();
                    $(self).find("input").val(text);
                })
                var liRender = function (value, text) {
                    var jqLi = $('<li class="simulate-select-list">--请选择状态--</li>');
                    jqLi.attr({value: value}).text(text);
                    jqLi.on("click", function () {
                        jqSelectInput.val(text);
                        var value = $(this).attr("value") || '';

                        jqDefaultSelect.val(value);
                    });
                    jqUl.append(jqLi);
                }

                var optionRender = function (value, text) {
                    if (value) {
                        var jqOption = $("<option value='" + value + "'>" + text + "</option>");
                    } else {
                        var jqOption = $("<option value=''>" + text + "</option>");
                    }
                    jqDefaultSelect.append(jqOption);
                }

                jqSelectOuter.click(function (e) {
                    e.stopPropagation();
                    //如果是异步加载且有请求路径
                    if (self["param"]["isAsync"] && self["param"]["ajaxParam"] && !hasLoadData) {
                        internal.req(self.param, function (data) {
                            var data = [{text: "--请选择状态--"}, {value: '1', text: '启用'}, {value: '0', text: '禁用'}];
                            $.each(data, function (index, item) {
                                liRender(item["value"], item["text"]);
                                optionRender(item["value"], item["text"]);
                            });
                            hasLoadData = true;
                        })
                    }
                    if (jqUl.is(":hidden")) {
                        jqUl.show();
                    } else {
                        jqUl.hide();
                    }
                })
                $(document).click(function (e) {
                    jqUl.hide();
                });
            },
            //ajax请求
            //need reuqest.js
            req: function (options, callback) {
                options["ajaxParam"]["isTip"] = false;
                options["ajaxParam"]["success"] = function (data) {
                    if (typeof(data) == "string") {
                        data = $.parseJSON(data);
                    }
                    if ($.isFunction(callback) && data.isSuccess) {
                        callback(data.result);
                    }
                }
                var req = new Request(options["ajaxParam"]["url"]);
                req.post(options["ajaxParam"]);
            }
        }
        var target = this;
        $(target).each(function () {
            var self = this;
            self.param = $.extend(true, {}, internal.defaultOptions, options);
            internal.init(self);
        });
    }
    //switch开关按钮
    $.fn.btnSwitch = function (options) {
        var internal = {
            defaultOptions: {
                //跟实体key对应的name（支持自动绑定）
                name: '',
                //switchOff或switchOn为开关时的值：正常情况下值可以是：true,false;0,1;也可以是其他任意值
                switchOff: 0,
                switchOn: 1
            },
            init: function (self) {
                var toggle = $(self).find(".btn-switch")
                var name = self["param"]["name"];
                var jqRadioOn = $('<input  />').attr({
                    type: 'radio',
                    name: name,
                }).val(self["param"]["switchOn"]).css({display: "none"});
                var jqRadioOff = $('<input />').attr({
                    type: 'radio',
                    name: name
                }).val(self["param"]["switchOff"]).css({display: "none"});
                $(self).after(jqRadioOn).after(jqRadioOff);
                //初始化前先判断绑定的值
                if (toggle.hasClass("btn-switch-on")) {
                    $("[name='" + name + "'][value='" + self["param"]["switchOn"] + "']").trigger("click");
                } else {
                    $("[name='" + name + "'][value='" + self["param"]["switchOff"] + "']").trigger("click");
                }
                $('input[name="' + name + '"]').change(function () {
                    var checkValue = $('input[name=' + name + ']:checked').val();
                    if (checkValue == self["param"]["switchOn"]) {
                        toggle.animate({
                            "margin-left": 0
                        }, 500, function () {
                            toggle.removeClass("btn-switch-off").addClass("btn-switch-on");
                        });
                    } else {
                        toggle.animate({
                            "margin-left": -35
                        }, 500, function () {
                            toggle.removeClass("btn-switch-on").addClass("btn-switch-off");
                        });
                    }
                });
                $(self).click(function (e) {
                    if (toggle.hasClass("btn-switch-on")) {
                        toggle.animate({
                            "margin-left": -35
                        }, 500, function () {
                            toggle.removeClass("btn-switch-on").addClass("btn-switch-off");
                        });
                        $("[name='" + name + "'][value='" + self["param"]["switchOff"] + "']").trigger("click");
                    }
                    else {
                        toggle.animate({
                            "margin-left": 0
                        }, 500, function () {
                            toggle.removeClass("btn-switch-off").addClass("btn-switch-on");
                        });
                        $("[name='" + name + "'][value='" + self["param"]["switchOn"] + "']").trigger("click");
                    }
                });
            }
        }
        var target = this;
        $(target).each(function () {
            var self = this;
            self.param = $.extend(true, {}, internal.defaultOptions, options);
            internal.init(self);
        })
    }

})();