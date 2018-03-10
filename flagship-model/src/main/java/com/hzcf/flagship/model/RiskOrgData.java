package com.hzcf.flagship.model;

import java.util.Date;

public class RiskOrgData {
    private Long id;

    private String orgNo;

    private String orgShortName;

    private String principalName;

    private String principalNo;

    private String isPrincipalDetail;

    private Integer principalSendAging;

    private String creditManagerName;

    private String creditManagerNo;

    private String isCreditManagerDetail;

    private Integer managerSendAging;

    private String subcenterNo;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName == null ? null : orgShortName.trim();
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName == null ? null : principalName.trim();
    }

    public String getPrincipalNo() {
        return principalNo;
    }

    public void setPrincipalNo(String principalNo) {
        this.principalNo = principalNo == null ? null : principalNo.trim();
    }

    public String getIsPrincipalDetail() {
        return isPrincipalDetail;
    }

    public void setIsPrincipalDetail(String isPrincipalDetail) {
        this.isPrincipalDetail = isPrincipalDetail == null ? null : isPrincipalDetail.trim();
    }

    public Integer getPrincipalSendAging() {
        return principalSendAging;
    }

    public void setPrincipalSendAging(Integer principalSendAging) {
        this.principalSendAging = principalSendAging;
    }

    public String getCreditManagerName() {
        return creditManagerName;
    }

    public void setCreditManagerName(String creditManagerName) {
        this.creditManagerName = creditManagerName == null ? null : creditManagerName.trim();
    }

    public String getCreditManagerNo() {
        return creditManagerNo;
    }

    public void setCreditManagerNo(String creditManagerNo) {
        this.creditManagerNo = creditManagerNo == null ? null : creditManagerNo.trim();
    }

    public String getIsCreditManagerDetail() {
        return isCreditManagerDetail;
    }

    public void setIsCreditManagerDetail(String isCreditManagerDetail) {
        this.isCreditManagerDetail = isCreditManagerDetail == null ? null : isCreditManagerDetail.trim();
    }

    public Integer getManagerSendAging() {
        return managerSendAging;
    }

    public void setManagerSendAging(Integer managerSendAging) {
        this.managerSendAging = managerSendAging;
    }

    public String getSubcenterNo() {
        return subcenterNo;
    }

    public void setSubcenterNo(String subcenterNo) {
        this.subcenterNo = subcenterNo == null ? null : subcenterNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}