package com.hzcf.flagship.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoneymgrEfficiencyPage {
    private Long id;

    private Date recordDate;

    private Integer orgNum;

    private Integer counselorNum;

    private Integer managerNum;

    private BigDecimal perCapitaCapacity;

    private BigDecimal perCapitaNewClient;

    private Integer newClientNum;

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

    public Integer getOrgNum() {
        return orgNum;
    }

    public void setOrgNum(Integer orgNum) {
        this.orgNum = orgNum;
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

    public Integer getNewClientNum() {
        return newClientNum;
    }

    public void setNewClientNum(Integer newClientNum) {
        this.newClientNum = newClientNum;
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