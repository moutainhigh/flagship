package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class MoneymgrDistrictAccumuPerformancePage {
    private Long id;

    private Date recordDate;

    private String districtName;

    private String principal;

    private Integer orgNum;

    private BigDecimal accumuPerformance;

    private BigDecimal completeRate;

    private BigDecimal perCapitaNewClient;

    private BigDecimal perCapitaCapacity;

    private BigDecimal counselorManagerRatio;

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

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    public Integer getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(Integer orgNum) {
        this.orgNum = orgNum;
    }

    public BigDecimal getAccumuPerformance() {
        return accumuPerformance;
    }

    public void setAccumuPerformance(BigDecimal accumuPerformance) {
        this.accumuPerformance = accumuPerformance;
    }

    public BigDecimal getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(BigDecimal completeRate) {
        this.completeRate = completeRate;
    }

    public BigDecimal getPerCapitaNewClient() {
        return perCapitaNewClient;
    }

    public void setPerCapitaNewClient(BigDecimal perCapitaNewClient) {
        this.perCapitaNewClient = perCapitaNewClient;
    }

    public BigDecimal getPerCapitaCapacity() {
        return perCapitaCapacity;
    }

    public void setPerCapitaCapacity(BigDecimal perCapitaCapacity) {
        this.perCapitaCapacity = perCapitaCapacity;
    }

    public BigDecimal getCounselorManagerRatio() {
        return counselorManagerRatio;
    }

    public void setCounselorManagerRatio(BigDecimal counselorManagerRatio) {
        this.counselorManagerRatio = counselorManagerRatio;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}