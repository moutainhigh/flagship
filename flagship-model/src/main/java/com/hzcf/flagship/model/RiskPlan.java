package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class RiskPlan {
    private Long id;

    private Date recordDate;

    private String orgNo;

    private String orgName;

    private String parentOrgNo;

    private String parentOrgName;

    private BigDecimal cm1Rate;

    private BigDecimal m2PressureRate;

    private BigDecimal m3PressureRate;

    private Integer creator;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getParentOrgNo() {
        return parentOrgNo;
    }

    public void setParentOrgNo(String parentOrgNo) {
        this.parentOrgNo = parentOrgNo == null ? null : parentOrgNo.trim();
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName == null ? null : parentOrgName.trim();
    }

    public BigDecimal getCm1Rate() {
        return cm1Rate;
    }

    public void setCm1Rate(BigDecimal cm1Rate) {
        this.cm1Rate = cm1Rate;
    }

    public BigDecimal getM2PressureRate() {
        return m2PressureRate;
    }

    public void setM2PressureRate(BigDecimal m2PressureRate) {
        this.m2PressureRate = m2PressureRate;
    }

    public BigDecimal getM3PressureRate() {
        return m3PressureRate;
    }

    public void setM3PressureRate(BigDecimal m3PressureRate) {
        this.m3PressureRate = m3PressureRate;
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