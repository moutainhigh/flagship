package com.hzcf.flagship.model;

import java.util.Date;

public class AssetPerson {
    private Long id;

    /**
     *所属事业部编号
     */
    private String depNo;

    /**
     *所属事业部名称
     */
    private String depName;

    /**
     *姓名
     */
    private String name;

    /**
     *区域编号
     */
    private String districtNo;

    /**
     *区域名称
     */
    private String districtName;

    /**
     *营业部编号
     */
    private String salesdepNo;

    /**
     *营业部名称
     */
    private String salesdepName;

    /**
     *团队名称
     */
    private String teamName;

    /**
     *小团名称
     */
    private String subTeamName;

    /**
     *员工编号
     */
    private String personNo;

    /**
     *手机号
     */
    private String mobile;

    /**
     *离职时间
     */
    private Date dimissionTime;

    /**
     *创建人
     */
    private Integer caretor;

    /**
     *创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo == null ? null : depNo.trim();
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName == null ? null : depName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(String districtNo) {
        this.districtNo = districtNo == null ? null : districtNo.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getSalesdepNo() {
        return salesdepNo;
    }

    public void setSalesdepNo(String salesdepNo) {
        this.salesdepNo = salesdepNo == null ? null : salesdepNo.trim();
    }

    public String getSalesdepName() {
        return salesdepName;
    }

    public void setSalesdepName(String salesdepName) {
        this.salesdepName = salesdepName == null ? null : salesdepName.trim();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getSubTeamName() {
        return subTeamName;
    }

    public void setSubTeamName(String subTeamName) {
        this.subTeamName = subTeamName == null ? null : subTeamName.trim();
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo == null ? null : personNo.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getDimissionTime() {
        return dimissionTime;
    }

    public void setDimissionTime(Date dimissionTime) {
        this.dimissionTime = dimissionTime;
    }

    public Integer getCaretor() {
        return caretor;
    }

    public void setCaretor(Integer caretor) {
        this.caretor = caretor;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}