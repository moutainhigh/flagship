package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class AssetDailyPerformance {
    private Long id;

    private Date recordDate;

    private String orgNo;

    private String businessNo;

    private BigDecimal performanceDaily;

    private BigDecimal performanceSum;

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

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo == null ? null : businessNo.trim();
    }

    public BigDecimal getPerformanceDaily() {
        return performanceDaily;
    }

    public void setPerformanceDaily(BigDecimal performanceDaily) {
        this.performanceDaily = performanceDaily;
    }

    public BigDecimal getPerformanceSum() {
        return performanceSum;
    }

    public void setPerformanceSum(BigDecimal performanceSum) {
        this.performanceSum = performanceSum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}