package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceMonthPerformance {
    private Long id;

    private Date recordDate;

    private BigDecimal monthPerformance;

    private BigDecimal yearPerformance;

    private Integer applyActualQuantity;

    private Integer loanActualQuantity;

    private BigDecimal approvalRate;

    private BigDecimal approvalAverage;

    private BigDecimal contractMoney;

    private BigDecimal serviceChargeRate;

    private BigDecimal arbitrationChargeRate;

    private BigDecimal insuranceRate;

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

    public BigDecimal getMonthPerformance() {
        return monthPerformance;
    }

    public void setMonthPerformance(BigDecimal monthPerformance) {
        this.monthPerformance = monthPerformance;
    }

    public BigDecimal getYearPerformance() {
        return yearPerformance;
    }

    public void setYearPerformance(BigDecimal yearPerformance) {
        this.yearPerformance = yearPerformance;
    }

    public Integer getApplyActualQuantity() {
        return applyActualQuantity;
    }

    public void setApplyActualQuantity(Integer applyActualQuantity) {
        this.applyActualQuantity = applyActualQuantity;
    }

    public Integer getLoanActualQuantity() {
        return loanActualQuantity;
    }

    public void setLoanActualQuantity(Integer loanActualQuantity) {
        this.loanActualQuantity = loanActualQuantity;
    }

    public BigDecimal getApprovalRate() {
        return approvalRate;
    }

    public void setApprovalRate(BigDecimal approvalRate) {
        this.approvalRate = approvalRate;
    }

    public BigDecimal getApprovalAverage() {
        return approvalAverage;
    }

    public void setApprovalAverage(BigDecimal approvalAverage) {
        this.approvalAverage = approvalAverage;
    }

    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(BigDecimal contractMoney) {
        this.contractMoney = contractMoney;
    }

    public BigDecimal getServiceChargeRate() {
        return serviceChargeRate;
    }

    public void setServiceChargeRate(BigDecimal serviceChargeRate) {
        this.serviceChargeRate = serviceChargeRate;
    }

    public BigDecimal getArbitrationChargeRate() {
        return arbitrationChargeRate;
    }

    public void setArbitrationChargeRate(BigDecimal arbitrationChargeRate) {
        this.arbitrationChargeRate = arbitrationChargeRate;
    }

    public BigDecimal getInsuranceRate() {
        return insuranceRate;
    }

    public void setInsuranceRate(BigDecimal insuranceRate) {
        this.insuranceRate = insuranceRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}