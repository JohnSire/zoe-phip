package com.zoe.phip.model.demo;

import com.zoe.phip.model.base.BaseEntity;

import javax.persistence.Column;

public class Country extends BaseEntity {

    @Column(name = "COUNTRYNAME")
    private String countryname;

    @Column(name = "COUNTRYCODE")
    private String countrycode;

    /**
     * @return COUNTRYNAME
     */
    public String getCountryname() {
        return countryname;
    }

    /**
     * @param countryname
     */
    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    /**
     * @return COUNTRYCODE
     */
    public String getCountrycode() {
        return countrycode;
    }

    /**
     * @param countrycode
     */
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
}