package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表f_asset_plan(业绩计划表)对应model
 * @author liuxianfa
 */
public class AssetPlan {

    private Long id;

    /**
     * 机构编号
     */
    private String orgNo;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 业务编号
     */
    private String businessNo;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 年份
     */
    private String year;

    /**
     * 月份:一月/二月...当为年计划时标记为年度目标
     */
    private String month;

    /**
     * 月计划或者年计划值(单位为元,保留两位小数)
     */
    private BigDecimal planValue;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 创建时间
     */
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo == null ? null : businessNo.trim();
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public BigDecimal getPlanValue() {
        return planValue;
    }

    public void setPlanValue(BigDecimal planValue) {
        this.planValue = planValue;
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