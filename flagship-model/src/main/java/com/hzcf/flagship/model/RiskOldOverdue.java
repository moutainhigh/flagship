package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class RiskOldOverdue {
    private Long id;

    private String applyNo;

    private String saleDepartment;

    private String clientName;

    private String contractNo;

    private String productName;

    private String productNo;

    private String cardNo;

    private String bank;

    private BigDecimal handAmount;

    private Integer periods;

    private Date initialRepaymentTime;

    private BigDecimal principalAndInterest;

    private BigDecimal penalty;

    private BigDecimal penaltyInterest;

    private BigDecimal breachPenalty;

    private BigDecimal totalRepaymentAmount;

    private String repaymentDate;

    private String teamManager;

    private String clientManager;

    private String oldOrgName;

    private String newOrgName;

    private String orgNo;

    private Integer creator;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    public String getSaleDepartment() {
        return saleDepartment;
    }

    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment == null ? null : saleDepartment.trim();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public BigDecimal getHandAmount() {
        return handAmount;
    }

    public void setHandAmount(BigDecimal handAmount) {
        this.handAmount = handAmount;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Date getInitialRepaymentTime() {
        return initialRepaymentTime;
    }

    public void setInitialRepaymentTime(Date initialRepaymentTime) {
        this.initialRepaymentTime = initialRepaymentTime;
    }

    public BigDecimal getPrincipalAndInterest() {
        return principalAndInterest;
    }

    public void setPrincipalAndInterest(BigDecimal principalAndInterest) {
        this.principalAndInterest = principalAndInterest;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public BigDecimal getPenaltyInterest() {
        return penaltyInterest;
    }

    public void setPenaltyInterest(BigDecimal penaltyInterest) {
        this.penaltyInterest = penaltyInterest;
    }

    public BigDecimal getBreachPenalty() {
        return breachPenalty;
    }

    public void setBreachPenalty(BigDecimal breachPenalty) {
        this.breachPenalty = breachPenalty;
    }

    public BigDecimal getTotalRepaymentAmount() {
        return totalRepaymentAmount;
    }

    public void setTotalRepaymentAmount(BigDecimal totalRepaymentAmount) {
        this.totalRepaymentAmount = totalRepaymentAmount;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate == null ? null : repaymentDate.trim();
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

    public String getOldOrgName() {
        return oldOrgName;
    }

    public void setOldOrgName(String oldOrgName) {
        this.oldOrgName = oldOrgName == null ? null : oldOrgName.trim();
    }

    public String getNewOrgName() {
        return newOrgName;
    }

    public void setNewOrgName(String newOrgName) {
        this.newOrgName = newOrgName == null ? null : newOrgName.trim();
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
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