package com.hzcf.flagship.model;

import java.util.Date;

/**
 * 分中心表（f_risk_subcenter）
 * @author
 */
public class RiskSubcenter {

    /**
     * 状态为：正常
     */
    public static final String STATUS_NORMAL = "1";
    /**
     * 状态为：已删除（逻辑删除）
     */
    public static final String STATUS_DELETED = "0";

    private Long id;

    /**
     *分中心编号
     */
    private String subcenterNo;

    /**
     *分中心名称
     */
    private String name;

    /**
     *简称(最多5个字)
     */
    private String shortName;

    /**
     *负责人名字
     */
    private String managerName;

    /**
     *负责人员工编号
     */
    private String managerNo;

    /**
     *信贷主管名字(可以为空)
     */
    private String creditManagerName;

    /**
     *信贷主管员工编号(可以为空)
     */
    private String creditManagerNo;

    /**
     *分中心状态:1表示正常,0表示删除
     */
    private String status;

    private Integer creator;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubcenterNo() {
        return subcenterNo;
    }

    public void setSubcenterNo(String subcenterNo) {
        this.subcenterNo = subcenterNo == null ? null : subcenterNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerNo() {
        return managerNo;
    }

    public void setManagerNo(String managerNo) {
        this.managerNo = managerNo == null ? null : managerNo.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    /**
     * @return true if <code>status</code> is <code>STATUS_DELETED</code>;otherwise false;
     */
    public boolean isDeleted(){
        return STATUS_DELETED.equals(this.status);
    }

    /**
     * @return true if <code>status</code> is <code>STATUS_NORMAL</code>;otherwise false;
     */
    public boolean isNormal(){
        return STATUS_NORMAL.equals(this.status);
    }
}