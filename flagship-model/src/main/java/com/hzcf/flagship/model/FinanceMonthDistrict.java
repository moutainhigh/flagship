package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceMonthDistrict {
    private Long id;

    private Date recordDate;

    private String districtName;

    private BigDecimal loanValue;

    private Integer applyQuantity;

    private BigDecimal approvalRate;

    private BigDecimal approvalAverage;

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

    public BigDecimal getLoanValue() {
        return loanValue;
    }

    public void setLoanValue(BigDecimal loanValue) {
        this.loanValue = loanValue;
    }

    public Integer getApplyQuantity() {
        return applyQuantity;
    }

    public void setApplyQuantity(Integer applyQuantity) {
        this.applyQuantity = applyQuantity;
    }

    public BigDecimal getApprovalRate() {
        return approvalRate;
    }

    public void setApprovalRate(BigDecimal approvalRate) {
        this.approvalRate = approvalRate;
    }

    public BigDecimal getApprovalAverage() {
        return approvalAverage;
    }

    public void setApprovalAverage(BigDecimal approvalAverage) {
        this.approvalAverage = approvalAverage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}