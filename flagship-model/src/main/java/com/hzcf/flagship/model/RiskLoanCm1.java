package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class RiskLoanCm1 {
    private Long id;

    private Date recordDate;

    private String type;

    private String orgNo;

    private String productNo;

    private String month;

    private String batch;

    private Integer payedNum;

    private Integer repaymentNum;

    private BigDecimal cm1Value;

    private BigDecimal payedMoney;

    private BigDecimal repaymentMoney;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public Integer getPayedNum() {
        return payedNum;
    }

    public void setPayedNum(Integer payedNum) {
        this.payedNum = payedNum;
    }

    public Integer getRepaymentNum() {
        return repaymentNum;
    }

    public void setRepaymentNum(Integer repaymentNum) {
        this.repaymentNum = repaymentNum;
    }

    public BigDecimal getCm1Value() {
        return cm1Value;
    }

    public void setCm1Value(BigDecimal cm1Value) {
        this.cm1Value = cm1Value;
    }

    public BigDecimal getPayedMoney() {
        return payedMoney;
    }

    public void setPayedMoney(BigDecimal payedMoney) {
        this.payedMoney = payedMoney;
    }

    public BigDecimal getRepaymentMoney() {
        return repaymentMoney;
    }

    public void setRepaymentMoney(BigDecimal repaymentMoney) {
        this.repaymentMoney = repaymentMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}