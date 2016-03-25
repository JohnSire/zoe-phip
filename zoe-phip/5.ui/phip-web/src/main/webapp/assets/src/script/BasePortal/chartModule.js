//图表模块
define(function (require, exports, module) {
    var internal = {
        options: null,
        unit: require("../BasePortal/unit.js").unit,
        module: require("../BasePortal/module.js").module,
        drillModule: require("../BasePortal/drillModule.js").drillModule,
        req: require("../BasePortal/req.js").req,
        add: function (charttype, options) {
            internal.options = options;
            var jqControlObj = options["jqControlObj"];
            var jqModule = internal.module.build({
                mtype: charttype,
                onResize: function (id, moduleContentWidth, moduleContentHeight) { },
                onSetting: function (id) {
                    internal.unit.onSetting(id, options, "chartParam");
                },
                onDrill: function (id) {
                    var jqParentModule = $("#" + id);
                    jqParentModule.hide();
                    var mtype = jqParentModule.attr("mtype");
                    internal.drillModule.add({ parentId: id, mtype: mtype, title: '钻取视图' }, options);
                }

            });
            var jqContent = $(jqControlObj).find("[ptype='content']");
            jqContent.append(jqModule);

        },
        chart: {
            build: function (jqModule, chartProps, options, drillData) {
                internal.options = internal.options || options || {};
                var charttype = $(jqModule).attr("mtype");
                $(jqModule).data("data", chartProps);
                var moduleContentHeight = $(jqModule).innerHeight() - $(jqModule).find(".y-layout-col-header").outerHeight(),
                    moduleContentWidth = $(jqModule).innerWidth();
                var jqChart = $(jqModule).find(".y-layout-col-content").width(moduleContentWidth).height(moduleContentHeight);
                var title = chartProps["title"];
                var id = $(jqModule).attr("id");
                internal.unit.addPath(jqModule, { id: id, title: title });
                var chartElement = jqChart[0];
                internal.chart.dealParamToData(jqModule, chartProps, drillData, function (options, rows, colLen) {
                    var chartObj = echarts.init(chartElement);
                    chartObj.setOption(options);
                    chartObj.on("click", function (param) {
                        var drillData = drillData || {};
                        var drillFact = "";
                        switch (charttype) {
                            //饼图
                            case "pie":
                                drillData = (colLen == 1 ? rows[param.dataIndex] : rows[0]);
                                drillFact = param["name"];
                                break;
                                //柱状图
                            case "bar":
                                //线图
                            case "line":
                                drillFact = param["seriesName"];
                                drillData = rows[param.dataIndex];
                                break;
                            case "radarMap":
                                break;
                        };
                        var isExistDrill = false;
                        $("[parentId=" + jqModule.attr("id") + "]").each(function (index, item) {
                            var parentProps = $(item).data("data");
                            if (parentProps["drillForm"] == drillFact) {
                                $(item).show();
                                internal.chart.build($(item), $(item).data("data"), options, drillData);
                                isExistDrill = true;
                            }
                        });
                        if (isExistDrill) {
                            $(jqModule).hide();
                        }
                    })
                });
            },
            dealParamToData: function (jqModule, chartPros, drillData, callback) {
                var drillData = drillData || {};
                var charttype = $(jqModule).attr("mtype");
                var chartOptions = {};
                var url = "BasePortalMain/GetGridData";
                var paramListData = internal.unit.getParamList(jqModule, internal.options);
                //#region 获取界面具体值
                var pageSearchData = {};
                $.each(paramListData["pageSearchParam"], function (index, item) {
                    pageSearchData[item["name"]] = item["value"];
                });
                //#endregion
                var sql = internal.unit.convertSQL(chartPros["SQL"], pageSearchData, drillData);
                if (!sql) {
                    return;
                }
                var data = { SQL: sql };
                internal.req.getSQLData({
                    url: url, data: data
                }, function (data) {
                    var result = data.result;
                    if (typeof (result) == "string") {
                        result = $.parseJSON(result);
                    }
                    var rows = result["Rows"];
                    if (rows && rows.length > 0) {
                        var dimInfo = {}, factArray = [];//维度跟事实（值）的存储
                        var columns = chartPros["columns"];
                        $.each(columns, function (index, item) {
                            if (item["isDim"]) {
                                dimInfo = item;
                            } else if (item["isFact"]) {
                                factArray.push(item);
                            }
                        });
                        switch (charttype) {
                            case "pie":
                                //三种情况：
                                //(1)只有一个事实，维度每个值对应的事实值代表饼图的一部分
                                //(2)只有多个事实，取第一行值，第一行每个事实值代表饼图的一部分
                                //(3)如果没有事实，配置有误
                                var data = [];
                                if (factArray.length == 1) {
                                    $.each(rows, function (index, item) {
                                        data.push({ value: item[factArray[0]["name"]], name: item[dimInfo["display"]] })
                                    })
                                } else if (factArray.length > 1) {
                                    var row = rows[0];
                                    $.each(factArray, function (index, item) {
                                        data.push({ name: item["display"], value: row[item["name"]] });
                                    });
                                } else {

                                }
                                chartOptions = {
                                    calculable: false,
                                    tooltip: {
                                        trigger: 'item',
                                        formatter: "{a} <br/>{b} : {c}" + (factArray[0]["unit"] || "") + "({d}%)"
                                    },
                                    series: [{
                                        type: 'pie',
                                        radius: '60%',
                                        center: ['45%', '45%'],
                                        data: data
                                    }]
                                }
                                break;
                            case "line":
                            case "bar":
                                //两种情况：
                                //(1)一个事实，代表一条线，维度每个值对应曲线图上的一个点
                                //(2)如果没有事实，配置有误
                                if (factArray.length > 0) {
                                    var xAxisData = [], seriesData = [];
                                    $.each(rows, function (index, item) {
                                        xAxisData.push(item[dimInfo["name"]]);
                                    });
                                    $.each(factArray, function (index, item) {
                                        var seriesInfo = {
                                            name: item["display"], type: charttype, data: []
                                        }
                                        $.each(rows, function (index1, item1) {
                                            seriesInfo["data"].push(item1[item["name"]]);
                                        });
                                        seriesData.push(seriesInfo);
                                    })
                                    chartOptions = {
                                        tooltip: {
                                            trigger: 'axis',
                                            formatter: function (params, ticket, callback) {
                                                var res = params[0].name;
                                                for (var i = 0, l = params.length; i < l; i++) {
                                                    res += '<br/>' + params[i].seriesName + ' : ' + params[i].value + (factArray[i]["unit"] || "");
                                                }
                                                return res;
                                            }
                                        },
                                        calculable: false,
                                        xAxis: [{
                                            data: xAxisData
                                        }],
                                        yAxis: [{
                                            type: 'value'

                                        }],
                                        series: seriesData
                                    };
                                } else {

                                }
                                break;
                            case "radarMap":
                                var polarIndicator = [], seriesData = [], legendData = [];
                                $.each(rows, function (index1, item1) {
                                    polarIndicator.push({
                                        text: item1[dimInfo["name"]],
                                        max: 150,
                                        axisLabel: {
                                            show: true,
                                            interval: 2,
                                            textStyle: {
                                                color: 'auto'
                                            }
                                        }
                                    })
                                });
                                $.each(factArray, function (index, item) {
                                    legendData.push(item["display"]);
                                    var seriesInfo = { name: item["display"], value: [] }
                                    $.each(rows, function (index1, item1) {
                                        seriesInfo["value"].push(item1[item["name"]]);
                                    });
                                    seriesData.push(seriesInfo);
                                });


                                chartOptions = {
                                    tooltip: {
                                        trigger: 'axis',
                                        formatter: function (params, ticket, callback) {
                                            var res = ""
                                            for (var i = 0, l = params.length; i < l; i++) {
                                                res += '<br/>' + params[i].name + ' : ' + params[i].value + (factArray[i]["unit"] || "");
                                            }
                                            return res;
                                        }
                                    },
                                    legend: {
                                        orient: 'vertical',
                                        x: 'right',
                                        y: 'bottom',
                                        data: legendData
                                    },
                                    polar: [
                                       {
                                           indicator: polarIndicator,
                                           radius: '75%',
                                           center: ['45%', '45%']
                                       }
                                    ],
                                    calculable: false,
                                    series: [
                                        {
                                            type: 'radar',
                                            data: seriesData
                                        }
                                    ]
                                }
                                break;
                        }
                        if (typeof (callback) == "function") {
                            callback(chartOptions, rows, factArray.length);
                        }
                    } else {
                        $(jqModule).find(".y-layout-col-content").empty();
                    }
                })
            }
        }
    }
    exports.chartModule = {
        add: function (charttype, options) {
            internal.add(charttype, options);
        },
        build: function (jqModule, chartProps, options) {
            internal.chart.build(jqModule, chartProps, options);
        }
    }
});