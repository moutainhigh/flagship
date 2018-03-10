package com.hzcf.flagship.model;

import java.util.Date;

public class OrgMap {
    private Long id;

    private String biOrgName;

    private String rosterOrgName;

    private String moneymgrOrgName;

    private Integer creatorId;

    private Date createTime;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiOrgName() {
        return biOrgName;
    }

    public void setBiOrgName(String biOrgName) {
        this.biOrgName = biOrgName == null ? null : biOrgName.trim();
    }

    public String getRosterOrgName() {
        return rosterOrgName;
    }

    public void setRosterOrgName(String rosterOrgName) {
        this.rosterOrgName = rosterOrgName == null ? null : rosterOrgName.trim();
    }

    public String getMoneymgrOrgName() {
        return moneymgrOrgName;
    }

    public void setMoneymgrOrgName(String moneymgrOrgName) {
        this.moneymgrOrgName = moneymgrOrgName == null ? null : moneymgrOrgName.trim();
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