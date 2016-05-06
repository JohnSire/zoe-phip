/**
 * Created by linqinghuang on 2016/5/4.
 */

define(function (require, exports, module) {
    var internal = {
            sex: '2.16.156.10011.2.3.3.4',//生理性别代码
            maritalStatus: '2.16.156.10011.2.3.3.5',//婚姻状况
            national: '2.16.156.10011.2.3.3.3',//民族类别
            orgClassification: '2.16.156.10011.2.3.4.1',
            department: '2.16.156.10011.2.3.2.62',//医疗卫生机构业务科室分类与代码表
            occupation: '2.16.156.10011.2.3.3.7',//职业分类与代码
            healthCareType: ''// 医保类型
        }
        ;
    exports.oidCodeConfig = internal;
});
