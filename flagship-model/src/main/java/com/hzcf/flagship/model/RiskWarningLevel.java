package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class RiskWarningLevel {
    private Long id;

    private Date recordDate;

    private String orgNo;

    private String productNo;

    private String type;

    private String warningLevel;

    private BigDecimal cm1Value;

    private BigDecimal m1Value;

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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(String warningLevel) {
        this.warningLevel = warningLevel == null ? null : warningLevel.trim();
    }

    public BigDecimal getCm1Value() {
        return cm1Value;
    }

    public void setCm1Value(BigDecimal cm1Value) {
        this.cm1Value = cm1Value;
    }

    public BigDecimal getM1Value() {
        return m1Value;
    }

    public void setM1Value(BigDecimal m1Value) {
        this.m1Value = m1Value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}