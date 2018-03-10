package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class RiskLoanM1Overdue {
    private Long id;

    private Date recordDate;

    private String orgNo;

    private String productNo;

    private BigDecimal overduePrincipalInterest;

    private BigDecimal totalPrincipalInterest;

    private BigDecimal m1Value;

    private Integer oberdueNum;

    private Integer loanNum;

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

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public BigDecimal getOverduePrincipalInterest() {
        return overduePrincipalInterest;
    }

    public void setOverduePrincipalInterest(BigDecimal overduePrincipalInterest) {
        this.overduePrincipalInterest = overduePrincipalInterest;
    }

    public BigDecimal getTotalPrincipalInterest() {
        return totalPrincipalInterest;
    }

    public void setTotalPrincipalInterest(BigDecimal totalPrincipalInterest) {
        this.totalPrincipalInterest = totalPrincipalInterest;
    }

    public BigDecimal getM1Value() {
        return m1Value;
    }

    public void setM1Value(BigDecimal m1Value) {
        this.m1Value = m1Value;
    }

    public Integer getOberdueNum() {
        return oberdueNum;
    }

    public void setOberdueNum(Integer oberdueNum) {
        this.oberdueNum = oberdueNum;
    }

    public Integer getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(Integer loanNum) {
        this.loanNum = loanNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}