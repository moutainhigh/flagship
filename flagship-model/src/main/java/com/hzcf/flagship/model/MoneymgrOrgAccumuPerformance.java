package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class MoneymgrOrgAccumuPerformance {
    private Long id;

    private Date recordDate;

    private String orgName;

    private BigDecimal performanceValue;

    private Integer performanceRanking;

    private Integer newClientNum;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public BigDecimal getPerformanceValue() {
        return performanceValue;
    }

    public void setPerformanceValue(BigDecimal performanceValue) {
        this.performanceValue = performanceValue;
    }

    public Integer getPerformanceRanking() {
        return performanceRanking;
    }

    public void setPerformanceRanking(Integer performanceRanking) {
        this.performanceRanking = performanceRanking;
    }

    public Integer getNewClientNum() {
        return newClientNum;
    }

    public void setNewClientNum(Integer newClientNum) {
        this.newClientNum = newClientNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}