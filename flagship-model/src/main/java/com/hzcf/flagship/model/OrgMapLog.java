package com.hzcf.flagship.model;

import java.util.Date;

public class OrgMapLog {
    private Long id;

    private String biOrgName;

    private String rosterOrgName;

    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}