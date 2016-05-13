/**
 * Created by linqinghuang on 2016/5/12.
 */
define(function (require, exports, module) {
    var internal = {
        selProvince: null,//省下拉
        selCity: null,//市下拉
        selCounty: null,//区县下拉
        selStreet: null,//乡镇
        selNeighborhood: null,//街道
        init: function (index, callback) {
            var req = new Request("area/getAreaTopNode")
            req.get({
                isTip: false,
                async: true,
                success: function (data) {
                    var provincePid = data.result && data.result.id ? data.result.id : "";
                    /*选择省份*/
                    internal.selProvince = $("#selProvince").select({
                        name: internal.config["1"][0]["name"],
                        display: internal.config["1"][0]["display"],
                        ajaxParam: {
                            url: 'area/getAreaListByPid',//url 请求的地址
                            data: {pid: provincePid},
                        },
                        value: 'code',//值
                        text: 'name',//展示的内容
                        onAfterSelected: function (item, newValue, oldValue) {
                            if (newValue != oldValue) {
                                internal.selCity.reset();
                            }
                        }
                    })
                    /*选择市*/
                    internal.selCity = $("#selCity").select({
                        name: internal.config["1"][1]["name"],
                        display: internal.config["1"][1]["display"],
                        ajaxParam: {
                            url: 'area/getAreaListByPid',//url 请求的地址
                            data: {
                                pid: 'F2565E55804540AD8E23C059E0E3D593'
                            },
                        },
                        value: 'code',//值
                        text: 'name',//展示的内容
                        onAfterSelected: function (item, newValue, oldValue) {
                            if (newValue != oldValue) {
                                internal.selCounty.reset();
                            }
                        }
                    })
                    /*选择区县*/
                    internal.selCounty = $("#selCounty").select({
                        name: internal.config["1"][2]["name"],
                        display: internal.config["1"][2]["display"],
                        ajaxParam: {
                            url: 'area/getAreaListByPid',//url 请求的地址
                            data: {pid: '9BC2CEB4B2EE47488703F5A45EB998E0'},
                        },
                        value: 'code',//�
                        text: 'name',//展示的内�
                        onAfterSelected: function (item, newValue, oldValue) {
                            if (newValue != oldValue) {
                                internal.selStreet.reset();
                            }
                        }
                    })
                    /*选择乡镇*/
                    internal.selStreet = $("#selStreet").select({
                        name: internal.config["1"][3]["name"],
                        display: internal.config["1"][3]["display"],
                        ajaxParam: {
                            url: 'area/getAreaListByPid',//url 请求的地址
                            data: {pid: '95beeb26-1825-4f37-87b0-70fcc160f248'},
                        },
                        value: 'code',//值
                        text: 'name',//展示的内容
                        onAfterSelected: function (item, newValue, oldValue) {
                            if (newValue != oldValue) {
                                internal.selNeighborhood.reset();
                            }
                        }
                    })
                    /*选择街道*/
                    internal.selNeighborhood = $("#selNeighborhood").select({
                        name: internal.config["1"][4]["name"],
                        display: internal.config["1"][4]["display"],
                        ajaxParam: {
                            url: 'area/getAreaListByPid',//url 请求的地址
                            data: {pid: 'e6bda006-95a6-44ae-8570-143856d310d3'},
                        },
                        value: 'code',//值
                        text: 'name'//展示的内容

                    })
                }
            });
        },
        config: {
            "1": [
                {name: 'provinceCode', display: 'provinceCodeName'},
                {name: 'cityCode', display: 'cityCodeName'},
                {name: 'countyCode', display: 'countyCodeName'},
                {name: 'streetCode', display: 'streetCodeName'},
                {name: 'neighborhoodCode', display: 'neighborhoodCodeName'}
            ]

        }
    }
    exports.init = function (index, callback) {
        internal.init(index, callback);
    }
});