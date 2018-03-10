package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class MoneymgrOrgAccumuPerfromancePage {
    private Long id;

    private Date recordDate;

    private String orgName;

    private BigDecimal accumuPerformance;

    private String districtPrincipal;

    private String orgPrincipal;

    private BigDecimal accumuCompleteRate;

    private BigDecimal perCapitaCapacity;

    private BigDecimal perCapitaNewClient;

    private BigDecimal counselorManagerRatio;

    private Integer counselorNum;

    private Integer managerNum;

    private Integer ranking;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public BigDecimal getAccumuPerformance() {
        return accumuPerformance;
    }

    public void setAccumuPerformance(BigDecimal accumuPerformance) {
        this.accumuPerformance = accumuPerformance;
    }

    public String getDistrictPrincipal() {
        return districtPrincipal;
    }

    public void setDistrictPrincipal(String districtPrincipal) {
        this.districtPrincipal = districtPrincipal == null ? null : districtPrincipal.trim();
    }

    public String getOrgPrincipal() {
        return orgPrincipal;
    }

    public void setOrgPrincipal(String orgPrincipal) {
        this.orgPrincipal = orgPrincipal == null ? null : orgPrincipal.trim();
    }

    public BigDecimal getAccumuCompleteRate() {
        return accumuCompleteRate;
    }

    public void setAccumuCompleteRate(BigDecimal accumuCompleteRate) {
        this.accumuCompleteRate = accumuCompleteRate;
    }

    public BigDecimal getPerCapitaCapacity() {
        return perCapitaCapacity;
    }

    public void setPerCapitaCapacity(BigDecimal perCapitaCapacity) {
        this.perCapitaCapacity = perCapitaCapacity;
    }

    public BigDecimal getPerCapitaNewClient() {
        return perCapitaNewClient;
    }

    public void setPerCapitaNewClient(BigDecimal perCapitaNewClient) {
        this.perCapitaNewClient = perCapitaNewClient;
    }

    public BigDecimal getCounselorManagerRatio() {
        return counselorManagerRatio;
    }

    public void setCounselorManagerRatio(BigDecimal counselorManagerRatio) {
        this.counselorManagerRatio = counselorManagerRatio;
    }

    public Integer getCounselorNum() {
        return counselorNum;
    }

    public void setCounselorNum(Integer counselorNum) {
        this.counselorNum = counselorNum;
    }

    public Integer getManagerNum() {
        return managerNum;
    }

    public void setManagerNum(Integer managerNum) {
        this.managerNum = managerNum;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}