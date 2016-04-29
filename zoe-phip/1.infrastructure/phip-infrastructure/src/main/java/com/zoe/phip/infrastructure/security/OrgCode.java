package com.zoe.phip.infrastructure.security;

/**
 * Created by zengjiyang on 2016/3/29.
 */
public enum OrgCode {
    MedicalInstitution("0101","医疗机构"),
    ThirdMechanism("0103","第三方机构"),
    HealthManagementMechanism ("0102","卫生管理机构");

    String code;
    String name;

    OrgCode(String code, String name)
    {
        this.code=code;
        this.name=name;
    }

    public static OrgCode forValue(String value) {
        for (OrgCode type : values()) {
            if (type.getCode().equals(value))
                return type;
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
