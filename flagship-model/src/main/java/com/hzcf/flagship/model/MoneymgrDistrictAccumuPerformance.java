package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class MoneymgrDistrictAccumuPerformance {
    private Long id;

    private Date recordDate;

    private String districtName;

    private BigDecimal performanceValue;

    private Integer newClientNum;

    private String principal;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public BigDecimal getPerformanceValue() {
        return performanceValue;
    }

    public void setPerformanceValue(BigDecimal performanceValue) {
        this.performanceValue = performanceValue;
    }

    public Integer getNewClientNum() {
        return newClientNum;
    }

    public void setNewClientNum(Integer newClientNum) {
        this.newClientNum = newClientNum;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}