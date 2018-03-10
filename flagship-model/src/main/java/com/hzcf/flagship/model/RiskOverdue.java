package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class RiskOverdue {
    private Long id;

    private Date recordDate;

    private String type;

    private String contractNo;

    private String clientName;

    private String orgNo;

    private String teamManager;

    private String clientManager;

    private Integer beginningPeriodAging;

    private Date firstRepaymentDate;

    private Short beginningPeriodFinal;

    private BigDecimal nowPrincipalInterest;

    private String batch;

    private BigDecimal overduePrincipalInterest;

    private String productNo;

    private Integer periods;

    private BigDecimal handAmount;

    private BigDecimal monthRepayment;

    private String mobile;

    private String overdueDate;

    private Integer nowAging;

    private String isReport;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getTeamManager() {
        return teamManager;
    }

    public void setTeamManager(String teamManager) {
        this.teamManager = teamManager == null ? null : teamManager.trim();
    }

    public String getClientManager() {
        return clientManager;
    }

    public void setClientManager(String clientManager) {
        this.clientManager = clientManager == null ? null : clientManager.trim();
    }

    public Integer getBeginningPeriodAging() {
        return beginningPeriodAging;
    }

    public void setBeginningPeriodAging(Integer beginningPeriodAging) {
        this.beginningPeriodAging = beginningPeriodAging;
    }

    public Date getFirstRepaymentDate() {
        return firstRepaymentDate;
    }

    public void setFirstRepaymentDate(Date firstRepaymentDate) {
        this.firstRepaymentDate = firstRepaymentDate;
    }

    public Short getBeginningPeriodFinal() {
        return beginningPeriodFinal;
    }

    public void setBeginningPeriodFinal(Short beginningPeriodFinal) {
        this.beginningPeriodFinal = beginningPeriodFinal;
    }

    public BigDecimal getNowPrincipalInterest() {
        return nowPrincipalInterest;
    }

    public void setNowPrincipalInterest(BigDecimal nowPrincipalInterest) {
        this.nowPrincipalInterest = nowPrincipalInterest;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public BigDecimal getOverduePrincipalInterest() {
        return overduePrincipalInterest;
    }

    public void setOverduePrincipalInterest(BigDecimal overduePrincipalInterest) {
        this.overduePrincipalInterest = overduePrincipalInterest;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public BigDecimal getHandAmount() {
        return handAmount;
    }

    public void setHandAmount(BigDecimal handAmount) {
        this.handAmount = handAmount;
    }

    public BigDecimal getMonthRepayment() {
        return monthRepayment;
    }

    public void setMonthRepayment(BigDecimal monthRepayment) {
        this.monthRepayment = monthRepayment;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getOverdueDate() {
        return overdueDate;
    }

    public void setOverdueDate(String overdueDate) {
        this.overdueDate = overdueDate == null ? null : overdueDate.trim();
    }

    public Integer getNowAging() {
        return nowAging;
    }

    public void setNowAging(Integer nowAging) {
        this.nowAging = nowAging;
    }

    public String getIsReport() {
        return isReport;
    }

    public void setIsReport(String isReport) {
        this.isReport = isReport == null ? null : isReport.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}