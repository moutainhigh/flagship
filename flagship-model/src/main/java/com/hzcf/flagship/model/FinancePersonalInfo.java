package com.hzcf.flagship.model;

import java.util.Date;

public class FinancePersonalInfo {
    private Long id;

    private String manageOffice;

    private String orgName;

    private Integer salesNum;

    private Integer totalEmpNum;

    private Integer teamNum;

    private Date recordDate;

    private Integer monthPlan;

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

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    public Integer getTotalEmpNum() {
        return totalEmpNum;
    }

    public void setTotalEmpNum(Integer totalEmpNum) {
        this.totalEmpNum = totalEmpNum;
    }

    public Integer getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(Integer teamNum) {
        this.teamNum = teamNum;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getMonthPlan() {
        return monthPlan;
    }

    public void setMonthPlan(Integer monthPlan) {
        this.monthPlan = monthPlan;
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