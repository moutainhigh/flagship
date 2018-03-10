package com.hzcf.flagship.model;

import java.util.Date;

public class FinanceOrgMap {
    private Long id;

    private String biOrgName;

    private String orgShortName;

    private String manageOffice;

    private String province;

    private Date openingDate;

    private String principal;

    private String abscissa;

    private String ordinate;

    private Integer creator;

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

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName == null ? null : orgShortName.trim();
    }

    public String getManageOffice() {
        return manageOffice;
    }

    public void setManageOffice(String manageOffice) {
        this.manageOffice = manageOffice == null ? null : manageOffice.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public String getAbscissa() {
        return abscissa;
    }

    public void setAbscissa(String abscissa) {
        this.abscissa = abscissa == null ? null : abscissa.trim();
    }

    public String getOrdinate() {
        return ordinate;
    }

    public void setOrdinate(String ordinate) {
        this.ordinate = ordinate == null ? null : ordinate.trim();
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