package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceDailyOrg {
    private Long id;

    private Date recordDate;

    private String orgName;

    private BigDecimal loanValue;

    private BigDecimal accumuLoanValue;

    private Integer applyQuantity;

    private BigDecimal accumuApprovalRate;

    private BigDecimal accumuApprovalAverage;

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

    public BigDecimal getLoanValue() {
        return loanValue;
    }

    public void setLoanValue(BigDecimal loanValue) {
        this.loanValue = loanValue;
    }

    public BigDecimal getAccumuLoanValue() {
        return accumuLoanValue;
    }

    public void setAccumuLoanValue(BigDecimal accumuLoanValue) {
        this.accumuLoanValue = accumuLoanValue;
    }

    public Integer getApplyQuantity() {
        return applyQuantity;
    }

    public void setApplyQuantity(Integer applyQuantity) {
        this.applyQuantity = applyQuantity;
    }

    public BigDecimal getAccumuApprovalRate() {
        return accumuApprovalRate;
    }

    public void setAccumuApprovalRate(BigDecimal accumuApprovalRate) {
        this.accumuApprovalRate = accumuApprovalRate;
    }

    public BigDecimal getAccumuApprovalAverage() {
        return accumuApprovalAverage;
    }

    public void setAccumuApprovalAverage(BigDecimal accumuApprovalAverage) {
        this.accumuApprovalAverage = accumuApprovalAverage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}