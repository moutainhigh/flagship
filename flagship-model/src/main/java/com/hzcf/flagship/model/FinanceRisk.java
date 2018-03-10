package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceRisk {
    private Long id;

    private String manageOffice;

    private String orgName;

    private BigDecimal cM1Numerator;

    private BigDecimal cM1Denominator;

    private BigDecimal cM1Value;

    private BigDecimal lossRateNumerator;

    private BigDecimal lossRateDenominator;

    private BigDecimal lossRate;

    private Date recordDate;

    private Integer creator;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManageOffice() {
        return manageOffice;
    }

    public void setManageOffice(String manageOffice) {
        this.manageOffice = manageOffice == null ? null : manageOffice.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public BigDecimal getcM1Numerator() {
        return cM1Numerator;
    }

    public void setcM1Numerator(BigDecimal cM1Numerator) {
        this.cM1Numerator = cM1Numerator;
    }

    public BigDecimal getcM1Denominator() {
        return cM1Denominator;
    }

    public void setcM1Denominator(BigDecimal cM1Denominator) {
        this.cM1Denominator = cM1Denominator;
    }

    public BigDecimal getcM1Value() {
        return cM1Value;
    }

    public void setcM1Value(BigDecimal cM1Value) {
        this.cM1Value = cM1Value;
    }

    public BigDecimal getLossRateNumerator() {
        return lossRateNumerator;
    }

    public void setLossRateNumerator(BigDecimal lossRateNumerator) {
        this.lossRateNumerator = lossRateNumerator;
    }

    public BigDecimal getLossRateDenominator() {
        return lossRateDenominator;
    }

    public void setLossRateDenominator(BigDecimal lossRateDenominator) {
        this.lossRateDenominator = lossRateDenominator;
    }

    public BigDecimal getLossRate() {
        return lossRate;
    }

    public void setLossRate(BigDecimal lossRate) {
        this.lossRate = lossRate;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}