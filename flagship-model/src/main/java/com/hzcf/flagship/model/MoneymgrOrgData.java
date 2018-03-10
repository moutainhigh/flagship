package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class MoneymgrOrgData {
    private Long id;

    private Date recordDate;

    private String orgName;

    private String districtName;

    private String districtPrincipal;

    private BigDecimal monthPlan;

    private Integer counselorNumLastMonth;

    private Integer creatorId;

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

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getDistrictPrincipal() {
        return districtPrincipal;
    }

    public void setDistrictPrincipal(String districtPrincipal) {
        this.districtPrincipal = districtPrincipal == null ? null : districtPrincipal.trim();
    }

    public BigDecimal getMonthPlan() {
        return monthPlan;
    }

    public void setMonthPlan(BigDecimal monthPlan) {
        this.monthPlan = monthPlan;
    }

    public Integer getCounselorNumLastMonth() {
        return counselorNumLastMonth;
    }

    public void setCounselorNumLastMonth(Integer counselorNumLastMonth) {
        this.counselorNumLastMonth = counselorNumLastMonth;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}