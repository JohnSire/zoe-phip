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
        init: function (callback) {
            var req = new Request("area/getAreaTopNode")
            req.get({
                isTip: false,
                async: true,
                success: function (data) {
                    var provincePid = data.result && data.result.id ? data.result.id : "";
                    /*选择省份*/
                    internal.selProvince = $("#selProvince").select({
                        name: 'provinceCode',
                        display: 'provinceCodeName',
                        ajaxParam: {
                            url: 'area/getAreaListByPid',//url 请求的地址
                            data: {pid: provincePid},
                        },
                        value: 'code',//值
                        text: 'name',//展示的内容
                        onAfterSelected: function (item, newValue, oldValue) {
                            if (newValue != oldValue) {
                                internal.selCity.reset({
                                    ajaxParam: {
                                        data: function () {
                                            return {code: item ? item["code"] : ""}
                                        }
                                    }
                                });
                                if (typeof(callback) == "function") {
                                    callback();
                                }
                            }
                        }
                    })
                    /*选择市*/
                    internal.selCity = $("#selCity").select({
                        name: 'cityCode',
                        display: 'cityCodeName',
                        ajaxParam: {
                            url: 'area/getAreaListByCode',//url 请求的地址
                            data: function () {
                                var value = $("select[name='provinceCode']").val() || "";
                                return {code: value}
                            }
                        },
                        value: 'code',//值
                        text: 'name',//展示的内容
                        onAfterSelected: function (item, newValue, oldValue) {
                            if (newValue != oldValue) {
                                internal.selCounty.reset({
                                    ajaxParam: {
                                        data: function () {
                                            return {code: item ? item["code"] : ""}
                                        }
                                    }
                                });
                                if (typeof(callback) == "function") {
                                    callback();
                                }
                            }
                        }
                    })
                    /*选择区县*/
                    internal.selCounty = $("#selCounty").select({
                        name: 'countyCode',
                        display: 'countyCodeName',
                        ajaxParam: {
                            url: 'area/getAreaListByCode',//url 请求的地址
                            data: function () {
                                var value = $("select[name='cityCode']").val() || "";
                                return {code: value}
                            }
                        },
                        value: 'code',//名称
                        text: 'name',//展示的内�
                        onAfterSelected: function (item, newValue, oldValue) {
                            if (newValue != oldValue) {
                                internal.selStreet.reset({
                                    ajaxParam: {
                                        data: function () {
                                            return {code: item ? item["code"] : ""}
                                        }
                                    }
                                });
                                if (typeof(callback) == "function") {
                                    callback();
                                }

                            }
                        }
                    })
                    /*选择乡镇*/
                    internal.selStreet = $("#selStreet").select({
                        name: 'streetCode',
                        display: 'streetCodeName',
                        ajaxParam: {
                            url: 'area/getAreaListByCode',//url 请求的地址
                            data: function () {
                                var value = $("select[name='countyCode']").val() || "";
                                return {code: value}
                            }
                        },
                        value: 'code',//值
                        text: 'name',//展示的内容
                        onAfterSelected: function (item, newValue, oldValue) {
                            if (newValue != oldValue) {
                                internal.selNeighborhood.reset({
                                    ajaxParam: {
                                        data: function () {
                                            return {code: item ? item["code"] : ""}
                                        }
                                    }
                                });
                                if (typeof(callback) == "function") {
                                    callback();
                                }
                            }
                        }
                    })
                    /*选择街道*/
                    internal.selNeighborhood = $("#selNeighborhood").select({
                        name: 'neighborhoodCode',
                        display: 'neighborhoodCodeName',
                        ajaxParam: {
                            url: 'area/getAreaListByCode',//url 请求的地址
                            data: function () {
                                var value = $("select[name='streetCode']").val() || "";
                                return {code: value}
                            }
                        },
                        value: 'code',//值
                        text: 'name',//展示的内容
                        onAfterSelected: function (item, newValue, oldValue) {
                            if (newValue != oldValue) {
                                if (typeof(callback) == "function") {
                                    callback();
                                }
                            }
                        }
                    })
                }
            });
        }
    }
    exports.init = function (index, callback) {
        internal.init(index, callback);
    }
});