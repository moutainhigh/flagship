package com.hzcf.flagship.model;

import java.util.Date;

public class MoneymgrRoster {
    private Long id;

    private String employeeNo;

    private String referralCode;

    private String idCard;

    private String employeeName;

    private String districtName;

    private String orgName;

    private String orgPrincipal;

    private String level4Department;

    private String level4Principal;

    private String level5Department;

    private String level5Principal;

    private String position;

    private String positionType;

    private Date entryDate;

    private String isPositive;

    private Date positiveDate;

    private Date dimissionDate;

    private Integer creatorId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode == null ? null : referralCode.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgPrincipal() {
        return orgPrincipal;
    }

    public void setOrgPrincipal(String orgPrincipal) {
        this.orgPrincipal = orgPrincipal == null ? null : orgPrincipal.trim();
    }

    public String getLevel4Department() {
        return level4Department;
    }

    public void setLevel4Department(String level4Department) {
        this.level4Department = level4Department == null ? null : level4Department.trim();
    }

    public String getLevel4Principal() {
        return level4Principal;
    }

    public void setLevel4Principal(String level4Principal) {
        this.level4Principal = level4Principal == null ? null : level4Principal.trim();
    }

    public String getLevel5Department() {
        return level5Department;
    }

    public void setLevel5Department(String level5Department) {
        this.level5Department = level5Department == null ? null : level5Department.trim();
    }

    public String getLevel5Principal() {
        return level5Principal;
    }

    public void setLevel5Principal(String level5Principal) {
        this.level5Principal = level5Principal == null ? null : level5Principal.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType == null ? null : positionType.trim();
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(String isPositive) {
        this.isPositive = isPositive == null ? null : isPositive.trim();
    }

    public Date getPositiveDate() {
        return positiveDate;
    }

    public void setPositiveDate(Date positiveDate) {
        this.positiveDate = positiveDate;
    }

    public Date getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(Date dimissionDate) {
        this.dimissionDate = dimissionDate;
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