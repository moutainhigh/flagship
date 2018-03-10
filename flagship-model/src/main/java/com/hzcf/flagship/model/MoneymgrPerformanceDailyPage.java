package com.hzcf.flagship.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoneymgrPerformanceDailyPage {
    private Long id;

    private Date recordDate;

    private Integer performanceValue;

    private Integer completeRate;

    private Integer dengesMinLine;

    private Integer dengesWarningLine;

    private Integer dengesWellLine;

    private Integer dengesMaxLine;

    private Integer monthPlan;

    private Integer dateSchedule;

    private Integer accumuPerformance;

    private Integer accumuCompleteRate;

    private Integer accumuMinLine;

    private Integer accumuWarningLine;

    private Integer accumuWellLine;

    private Integer accumuMaxLine;

    private Integer avgAccumuPerformance;

    private Integer dailyPlan;

    //ext
    private Integer warningIndex;
    private Integer wellIndex;

    public Integer getWarningIndex() {
        return warningIndex;
    }

    public void setWarningIndex(Integer warningIndex) {
        this.warningIndex = warningIndex;
    }

    public Integer getWellIndex() {
        return wellIndex;
    }

    public void setWellIndex(Integer wellIndex) {
        this.wellIndex = wellIndex;
    }

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

    public Integer getPerformanceValue() {
        return performanceValue;
    }

    public void setPerformanceValue(Integer performanceValue) {
        this.performanceValue = performanceValue;
    }

    public Integer getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(Integer completeRate) {
        this.completeRate = completeRate;
    }

    public Integer getDengesMinLine() {
        return dengesMinLine;
    }

    public void setDengesMinLine(Integer dengesMinLine) {
        this.dengesMinLine = dengesMinLine;
    }

    public Integer getDengesWarningLine() {
        return dengesWarningLine;
    }

    public void setDengesWarningLine(Integer dengesWarningLine) {
        this.dengesWarningLine = dengesWarningLine;
    }

    public Integer getDengesWellLine() {
        return dengesWellLine;
    }

    public void setDengesWellLine(Integer dengesWellLine) {
        this.dengesWellLine = dengesWellLine;
    }

    public Integer getDengesMaxLine() {
        return dengesMaxLine;
    }

    public void setDengesMaxLine(Integer dengesMaxLine) {
        this.dengesMaxLine = dengesMaxLine;
    }

    public Integer getMonthPlan() {
        return monthPlan;
    }

    public void setMonthPlan(Integer monthPlan) {
        this.monthPlan = monthPlan;
    }

    public Integer getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(Integer dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public Integer getAccumuPerformance() {
        return accumuPerformance;
    }

    public void setAccumuPerformance(Integer accumuPerformance) {
        this.accumuPerformance = accumuPerformance;
    }

    public Integer getAccumuCompleteRate() {
        return accumuCompleteRate;
    }

    public void setAccumuCompleteRate(Integer accumuCompleteRate) {
        this.accumuCompleteRate = accumuCompleteRate;
    }

    public Integer getAccumuMinLine() {
        return accumuMinLine;
    }

    public void setAccumuMinLine(Integer accumuMinLine) {
        this.accumuMinLine = accumuMinLine;
    }

    public Integer getAccumuWarningLine() {
        return accumuWarningLine;
    }

    public void setAccumuWarningLine(Integer accumuWarningLine) {
        this.accumuWarningLine = accumuWarningLine;
    }

    public Integer getAccumuWellLine() {
        return accumuWellLine;
    }

    public void setAccumuWellLine(Integer accumuWellLine) {
        this.accumuWellLine = accumuWellLine;
    }

    public Integer getAccumuMaxLine() {
        return accumuMaxLine;
    }

    public void setAccumuMaxLine(Integer accumuMaxLine) {
        this.accumuMaxLine = accumuMaxLine;
    }

    public Integer getAvgAccumuPerformance() {
        return avgAccumuPerformance;
    }

    public void setAvgAccumuPerformance(Integer avgAccumuPerformance) {
        this.avgAccumuPerformance = avgAccumuPerformance;
    }

    public Integer getDailyPlan() {
        return dailyPlan;
    }

    public void setDailyPlan(Integer dailyPlan) {
        this.dailyPlan = dailyPlan;
    }
}