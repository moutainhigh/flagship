package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceDailyPage {
    private Long id;

    private Date recordDate;

    private String dataName;

    private BigDecimal dataFigureValue;

    private String dataStringValue;

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

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public BigDecimal getDataFigureValue() {
        return dataFigureValue;
    }

    public void setDataFigureValue(BigDecimal dataFigureValue) {
        this.dataFigureValue = dataFigureValue;
    }

    public String getDataStringValue() {
        return dataStringValue;
    }

    public void setDataStringValue(String dataStringValue) {
        this.dataStringValue = dataStringValue == null ? null : dataStringValue.trim();
    }
}