package com.hzcf.flagship.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MoneymgrPerformanceDailyPageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MoneymgrPerformanceDailyPageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRecordDateIsNull() {
            addCriterion("record_date is null");
            return (Criteria) this;
        }

        public Criteria andRecordDateIsNotNull() {
            addCriterion("record_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecordDateEqualTo(Date value) {
            addCriterionForJDBCDate("record_date =", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("record_date <>", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThan(Date value) {
            addCriterionForJDBCDate("record_date >", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("record_date >=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThan(Date value) {
            addCriterionForJDBCDate("record_date <", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("record_date <=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateIn(List<Date> values) {
            addCriterionForJDBCDate("record_date in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("record_date not in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("record_date between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("record_date not between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueIsNull() {
            addCriterion("performance_value is null");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueIsNotNull() {
            addCriterion("performance_value is not null");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueEqualTo(Integer value) {
            addCriterion("performance_value =", value, "performanceValue");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueNotEqualTo(Integer value) {
            addCriterion("performance_value <>", value, "performanceValue");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueGreaterThan(Integer value) {
            addCriterion("performance_value >", value, "performanceValue");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("performance_value >=", value, "performanceValue");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueLessThan(Integer value) {
            addCriterion("performance_value <", value, "performanceValue");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueLessThanOrEqualTo(Integer value) {
            addCriterion("performance_value <=", value, "performanceValue");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueIn(List<Integer> values) {
            addCriterion("performance_value in", values, "performanceValue");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueNotIn(List<Integer> values) {
            addCriterion("performance_value not in", values, "performanceValue");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueBetween(Integer value1, Integer value2) {
            addCriterion("performance_value between", value1, value2, "performanceValue");
            return (Criteria) this;
        }

        public Criteria andPerformanceValueNotBetween(Integer value1, Integer value2) {
            addCriterion("performance_value not between", value1, value2, "performanceValue");
            return (Criteria) this;
        }

        public Criteria andCompleteRateIsNull() {
            addCriterion("complete_rate is null");
            return (Criteria) this;
        }

        public Criteria andCompleteRateIsNotNull() {
            addCriterion("complete_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteRateEqualTo(Integer value) {
            addCriterion("complete_rate =", value, "completeRate");
            return (Criteria) this;
        }

        public Criteria andCompleteRateNotEqualTo(Integer value) {
            addCriterion("complete_rate <>", value, "completeRate");
            return (Criteria) this;
        }

        public Criteria andCompleteRateGreaterThan(Integer value) {
            addCriterion("complete_rate >", value, "completeRate");
            return (Criteria) this;
        }

        public Criteria andCompleteRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("complete_rate >=", value, "completeRate");
            return (Criteria) this;
        }

        public Criteria andCompleteRateLessThan(Integer value) {
            addCriterion("complete_rate <", value, "completeRate");
            return (Criteria) this;
        }

        public Criteria andCompleteRateLessThanOrEqualTo(Integer value) {
            addCriterion("complete_rate <=", value, "completeRate");
            return (Criteria) this;
        }

        public Criteria andCompleteRateIn(List<Integer> values) {
            addCriterion("complete_rate in", values, "completeRate");
            return (Criteria) this;
        }

        public Criteria andCompleteRateNotIn(List<Integer> values) {
            addCriterion("complete_rate not in", values, "completeRate");
            return (Criteria) this;
        }

        public Criteria andCompleteRateBetween(Integer value1, Integer value2) {
            addCriterion("complete_rate between", value1, value2, "completeRate");
            return (Criteria) this;
        }

        public Criteria andCompleteRateNotBetween(Integer value1, Integer value2) {
            addCriterion("complete_rate not between", value1, value2, "completeRate");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineIsNull() {
            addCriterion("denges_min_line is null");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineIsNotNull() {
            addCriterion("denges_min_line is not null");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineEqualTo(Integer value) {
            addCriterion("denges_min_line =", value, "dengesMinLine");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineNotEqualTo(Integer value) {
            addCriterion("denges_min_line <>", value, "dengesMinLine");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineGreaterThan(Integer value) {
            addCriterion("denges_min_line >", value, "dengesMinLine");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("denges_min_line >=", value, "dengesMinLine");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineLessThan(Integer value) {
            addCriterion("denges_min_line <", value, "dengesMinLine");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineLessThanOrEqualTo(Integer value) {
            addCriterion("denges_min_line <=", value, "dengesMinLine");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineIn(List<Integer> values) {
            addCriterion("denges_min_line in", values, "dengesMinLine");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineNotIn(List<Integer> values) {
            addCriterion("denges_min_line not in", values, "dengesMinLine");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineBetween(Integer value1, Integer value2) {
            addCriterion("denges_min_line between", value1, value2, "dengesMinLine");
            return (Criteria) this;
        }

        public Criteria andDengesMinLineNotBetween(Integer value1, Integer value2) {
            addCriterion("denges_min_line not between", value1, value2, "dengesMinLine");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineIsNull() {
            addCriterion("denges_warning_line is null");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineIsNotNull() {
            addCriterion("denges_warning_line is not null");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineEqualTo(Integer value) {
            addCriterion("denges_warning_line =", value, "dengesWarningLine");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineNotEqualTo(Integer value) {
            addCriterion("denges_warning_line <>", value, "dengesWarningLine");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineGreaterThan(Integer value) {
            addCriterion("denges_warning_line >", value, "dengesWarningLine");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("denges_warning_line >=", value, "dengesWarningLine");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineLessThan(Integer value) {
            addCriterion("denges_warning_line <", value, "dengesWarningLine");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineLessThanOrEqualTo(Integer value) {
            addCriterion("denges_warning_line <=", value, "dengesWarningLine");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineIn(List<Integer> values) {
            addCriterion("denges_warning_line in", values, "dengesWarningLine");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineNotIn(List<Integer> values) {
            addCriterion("denges_warning_line not in", values, "dengesWarningLine");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineBetween(Integer value1, Integer value2) {
            addCriterion("denges_warning_line between", value1, value2, "dengesWarningLine");
            return (Criteria) this;
        }

        public Criteria andDengesWarningLineNotBetween(Integer value1, Integer value2) {
            addCriterion("denges_warning_line not between", value1, value2, "dengesWarningLine");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineIsNull() {
            addCriterion("denges_well_line is null");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineIsNotNull() {
            addCriterion("denges_well_line is not null");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineEqualTo(Integer value) {
            addCriterion("denges_well_line =", value, "dengesWellLine");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineNotEqualTo(Integer value) {
            addCriterion("denges_well_line <>", value, "dengesWellLine");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineGreaterThan(Integer value) {
            addCriterion("denges_well_line >", value, "dengesWellLine");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("denges_well_line >=", value, "dengesWellLine");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineLessThan(Integer value) {
            addCriterion("denges_well_line <", value, "dengesWellLine");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineLessThanOrEqualTo(Integer value) {
            addCriterion("denges_well_line <=", value, "dengesWellLine");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineIn(List<Integer> values) {
            addCriterion("denges_well_line in", values, "dengesWellLine");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineNotIn(List<Integer> values) {
            addCriterion("denges_well_line not in", values, "dengesWellLine");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineBetween(Integer value1, Integer value2) {
            addCriterion("denges_well_line between", value1, value2, "dengesWellLine");
            return (Criteria) this;
        }

        public Criteria andDengesWellLineNotBetween(Integer value1, Integer value2) {
            addCriterion("denges_well_line not between", value1, value2, "dengesWellLine");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineIsNull() {
            addCriterion("denges_max_line is null");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineIsNotNull() {
            addCriterion("denges_max_line is not null");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineEqualTo(Integer value) {
            addCriterion("denges_max_line =", value, "dengesMaxLine");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineNotEqualTo(Integer value) {
            addCriterion("denges_max_line <>", value, "dengesMaxLine");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineGreaterThan(Integer value) {
            addCriterion("denges_max_line >", value, "dengesMaxLine");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("denges_max_line >=", value, "dengesMaxLine");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineLessThan(Integer value) {
            addCriterion("denges_max_line <", value, "dengesMaxLine");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineLessThanOrEqualTo(Integer value) {
            addCriterion("denges_max_line <=", value, "dengesMaxLine");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineIn(List<Integer> values) {
            addCriterion("denges_max_line in", values, "dengesMaxLine");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineNotIn(List<Integer> values) {
            addCriterion("denges_max_line not in", values, "dengesMaxLine");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineBetween(Integer value1, Integer value2) {
            addCriterion("denges_max_line between", value1, value2, "dengesMaxLine");
            return (Criteria) this;
        }

        public Criteria andDengesMaxLineNotBetween(Integer value1, Integer value2) {
            addCriterion("denges_max_line not between", value1, value2, "dengesMaxLine");
            return (Criteria) this;
        }

        public Criteria andMonthPlanIsNull() {
            addCriterion("month_plan is null");
            return (Criteria) this;
        }

        public Criteria andMonthPlanIsNotNull() {
            addCriterion("month_plan is not null");
            return (Criteria) this;
        }

        public Criteria andMonthPlanEqualTo(Integer value) {
            addCriterion("month_plan =", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanNotEqualTo(Integer value) {
            addCriterion("month_plan <>", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanGreaterThan(Integer value) {
            addCriterion("month_plan >", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanGreaterThanOrEqualTo(Integer value) {
            addCriterion("month_plan >=", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanLessThan(Integer value) {
            addCriterion("month_plan <", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanLessThanOrEqualTo(Integer value) {
            addCriterion("month_plan <=", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanIn(List<Integer> values) {
            addCriterion("month_plan in", values, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanNotIn(List<Integer> values) {
            addCriterion("month_plan not in", values, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanBetween(Integer value1, Integer value2) {
            addCriterion("month_plan between", value1, value2, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanNotBetween(Integer value1, Integer value2) {
            addCriterion("month_plan not between", value1, value2, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andDateScheduleIsNull() {
            addCriterion("date_schedule is null");
            return (Criteria) this;
        }

        public Criteria andDateScheduleIsNotNull() {
            addCriterion("date_schedule is not null");
            return (Criteria) this;
        }

        public Criteria andDateScheduleEqualTo(Integer value) {
            addCriterion("date_schedule =", value, "dateSchedule");
            return (Criteria) this;
        }

        public Criteria andDateScheduleNotEqualTo(Integer value) {
            addCriterion("date_schedule <>", value, "dateSchedule");
            return (Criteria) this;
        }

        public Criteria andDateScheduleGreaterThan(Integer value) {
            addCriterion("date_schedule >", value, "dateSchedule");
            return (Criteria) this;
        }

        public Criteria andDateScheduleGreaterThanOrEqualTo(Integer value) {
            addCriterion("date_schedule >=", value, "dateSchedule");
            return (Criteria) this;
        }

        public Criteria andDateScheduleLessThan(Integer value) {
            addCriterion("date_schedule <", value, "dateSchedule");
            return (Criteria) this;
        }

        public Criteria andDateScheduleLessThanOrEqualTo(Integer value) {
            addCriterion("date_schedule <=", value, "dateSchedule");
            return (Criteria) this;
        }

        public Criteria andDateScheduleIn(List<Integer> values) {
            addCriterion("date_schedule in", values, "dateSchedule");
            return (Criteria) this;
        }

        public Criteria andDateScheduleNotIn(List<Integer> values) {
            addCriterion("date_schedule not in", values, "dateSchedule");
            return (Criteria) this;
        }

        public Criteria andDateScheduleBetween(Integer value1, Integer value2) {
            addCriterion("date_schedule between", value1, value2, "dateSchedule");
            return (Criteria) this;
        }

        public Criteria andDateScheduleNotBetween(Integer value1, Integer value2) {
            addCriterion("date_schedule not between", value1, value2, "dateSchedule");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceIsNull() {
            addCriterion("accumu_performance is null");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceIsNotNull() {
            addCriterion("accumu_performance is not null");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceEqualTo(Integer value) {
            addCriterion("accumu_performance =", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceNotEqualTo(Integer value) {
            addCriterion("accumu_performance <>", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceGreaterThan(Integer value) {
            addCriterion("accumu_performance >", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("accumu_performance >=", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceLessThan(Integer value) {
            addCriterion("accumu_performance <", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceLessThanOrEqualTo(Integer value) {
            addCriterion("accumu_performance <=", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceIn(List<Integer> values) {
            addCriterion("accumu_performance in", values, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceNotIn(List<Integer> values) {
            addCriterion("accumu_performance not in", values, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceBetween(Integer value1, Integer value2) {
            addCriterion("accumu_performance between", value1, value2, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceNotBetween(Integer value1, Integer value2) {
            addCriterion("accumu_performance not between", value1, value2, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateIsNull() {
            addCriterion("accumu_complete_rate is null");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateIsNotNull() {
            addCriterion("accumu_complete_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateEqualTo(Integer value) {
            addCriterion("accumu_complete_rate =", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateNotEqualTo(Integer value) {
            addCriterion("accumu_complete_rate <>", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateGreaterThan(Integer value) {
            addCriterion("accumu_complete_rate >", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("accumu_complete_rate >=", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateLessThan(Integer value) {
            addCriterion("accumu_complete_rate <", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateLessThanOrEqualTo(Integer value) {
            addCriterion("accumu_complete_rate <=", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateIn(List<Integer> values) {
            addCriterion("accumu_complete_rate in", values, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateNotIn(List<Integer> values) {
            addCriterion("accumu_complete_rate not in", values, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateBetween(Integer value1, Integer value2) {
            addCriterion("accumu_complete_rate between", value1, value2, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateNotBetween(Integer value1, Integer value2) {
            addCriterion("accumu_complete_rate not between", value1, value2, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineIsNull() {
            addCriterion("accumu_min_line is null");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineIsNotNull() {
            addCriterion("accumu_min_line is not null");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineEqualTo(Integer value) {
            addCriterion("accumu_min_line =", value, "accumuMinLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineNotEqualTo(Integer value) {
            addCriterion("accumu_min_line <>", value, "accumuMinLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineGreaterThan(Integer value) {
            addCriterion("accumu_min_line >", value, "accumuMinLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("accumu_min_line >=", value, "accumuMinLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineLessThan(Integer value) {
            addCriterion("accumu_min_line <", value, "accumuMinLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineLessThanOrEqualTo(Integer value) {
            addCriterion("accumu_min_line <=", value, "accumuMinLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineIn(List<Integer> values) {
            addCriterion("accumu_min_line in", values, "accumuMinLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineNotIn(List<Integer> values) {
            addCriterion("accumu_min_line not in", values, "accumuMinLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineBetween(Integer value1, Integer value2) {
            addCriterion("accumu_min_line between", value1, value2, "accumuMinLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMinLineNotBetween(Integer value1, Integer value2) {
            addCriterion("accumu_min_line not between", value1, value2, "accumuMinLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineIsNull() {
            addCriterion("accumu_warning_line is null");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineIsNotNull() {
            addCriterion("accumu_warning_line is not null");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineEqualTo(Integer value) {
            addCriterion("accumu_warning_line =", value, "accumuWarningLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineNotEqualTo(Integer value) {
            addCriterion("accumu_warning_line <>", value, "accumuWarningLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineGreaterThan(Integer value) {
            addCriterion("accumu_warning_line >", value, "accumuWarningLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("accumu_warning_line >=", value, "accumuWarningLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineLessThan(Integer value) {
            addCriterion("accumu_warning_line <", value, "accumuWarningLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineLessThanOrEqualTo(Integer value) {
            addCriterion("accumu_warning_line <=", value, "accumuWarningLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineIn(List<Integer> values) {
            addCriterion("accumu_warning_line in", values, "accumuWarningLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineNotIn(List<Integer> values) {
            addCriterion("accumu_warning_line not in", values, "accumuWarningLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineBetween(Integer value1, Integer value2) {
            addCriterion("accumu_warning_line between", value1, value2, "accumuWarningLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWarningLineNotBetween(Integer value1, Integer value2) {
            addCriterion("accumu_warning_line not between", value1, value2, "accumuWarningLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineIsNull() {
            addCriterion("accumu_well_line is null");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineIsNotNull() {
            addCriterion("accumu_well_line is not null");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineEqualTo(Integer value) {
            addCriterion("accumu_well_line =", value, "accumuWellLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineNotEqualTo(Integer value) {
            addCriterion("accumu_well_line <>", value, "accumuWellLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineGreaterThan(Integer value) {
            addCriterion("accumu_well_line >", value, "accumuWellLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("accumu_well_line >=", value, "accumuWellLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineLessThan(Integer value) {
            addCriterion("accumu_well_line <", value, "accumuWellLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineLessThanOrEqualTo(Integer value) {
            addCriterion("accumu_well_line <=", value, "accumuWellLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineIn(List<Integer> values) {
            addCriterion("accumu_well_line in", values, "accumuWellLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineNotIn(List<Integer> values) {
            addCriterion("accumu_well_line not in", values, "accumuWellLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineBetween(Integer value1, Integer value2) {
            addCriterion("accumu_well_line between", value1, value2, "accumuWellLine");
            return (Criteria) this;
        }

        public Criteria andAccumuWellLineNotBetween(Integer value1, Integer value2) {
            addCriterion("accumu_well_line not between", value1, value2, "accumuWellLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineIsNull() {
            addCriterion("accumu_max_line is null");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineIsNotNull() {
            addCriterion("accumu_max_line is not null");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineEqualTo(Integer value) {
            addCriterion("accumu_max_line =", value, "accumuMaxLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineNotEqualTo(Integer value) {
            addCriterion("accumu_max_line <>", value, "accumuMaxLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineGreaterThan(Integer value) {
            addCriterion("accumu_max_line >", value, "accumuMaxLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineGreaterThanOrEqualTo(Integer value) {
            addCriterion("accumu_max_line >=", value, "accumuMaxLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineLessThan(Integer value) {
            addCriterion("accumu_max_line <", value, "accumuMaxLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineLessThanOrEqualTo(Integer value) {
            addCriterion("accumu_max_line <=", value, "accumuMaxLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineIn(List<Integer> values) {
            addCriterion("accumu_max_line in", values, "accumuMaxLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineNotIn(List<Integer> values) {
            addCriterion("accumu_max_line not in", values, "accumuMaxLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineBetween(Integer value1, Integer value2) {
            addCriterion("accumu_max_line between", value1, value2, "accumuMaxLine");
            return (Criteria) this;
        }

        public Criteria andAccumuMaxLineNotBetween(Integer value1, Integer value2) {
            addCriterion("accumu_max_line not between", value1, value2, "accumuMaxLine");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceIsNull() {
            addCriterion("avg_accumu_performance is null");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceIsNotNull() {
            addCriterion("avg_accumu_performance is not null");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceEqualTo(Integer value) {
            addCriterion("avg_accumu_performance =", value, "avgAccumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceNotEqualTo(Integer value) {
            addCriterion("avg_accumu_performance <>", value, "avgAccumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceGreaterThan(Integer value) {
            addCriterion("avg_accumu_performance >", value, "avgAccumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("avg_accumu_performance >=", value, "avgAccumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceLessThan(Integer value) {
            addCriterion("avg_accumu_performance <", value, "avgAccumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceLessThanOrEqualTo(Integer value) {
            addCriterion("avg_accumu_performance <=", value, "avgAccumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceIn(List<Integer> values) {
            addCriterion("avg_accumu_performance in", values, "avgAccumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceNotIn(List<Integer> values) {
            addCriterion("avg_accumu_performance not in", values, "avgAccumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceBetween(Integer value1, Integer value2) {
            addCriterion("avg_accumu_performance between", value1, value2, "avgAccumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAvgAccumuPerformanceNotBetween(Integer value1, Integer value2) {
            addCriterion("avg_accumu_performance not between", value1, value2, "avgAccumuPerformance");
            return (Criteria) this;
        }

        public Criteria andDailyPlanIsNull() {
            addCriterion("daily_plan is null");
            return (Criteria) this;
        }

        public Criteria andDailyPlanIsNotNull() {
            addCriterion("daily_plan is not null");
            return (Criteria) this;
        }

        public Criteria andDailyPlanEqualTo(Integer value) {
            addCriterion("daily_plan =", value, "dailyPlan");
            return (Criteria) this;
        }

        public Criteria andDailyPlanNotEqualTo(Integer value) {
            addCriterion("daily_plan <>", value, "dailyPlan");
            return (Criteria) this;
        }

        public Criteria andDailyPlanGreaterThan(Integer value) {
            addCriterion("daily_plan >", value, "dailyPlan");
            return (Criteria) this;
        }

        public Criteria andDailyPlanGreaterThanOrEqualTo(Integer value) {
            addCriterion("daily_plan >=", value, "dailyPlan");
            return (Criteria) this;
        }

        public Criteria andDailyPlanLessThan(Integer value) {
            addCriterion("daily_plan <", value, "dailyPlan");
            return (Criteria) this;
        }

        public Criteria andDailyPlanLessThanOrEqualTo(Integer value) {
            addCriterion("daily_plan <=", value, "dailyPlan");
            return (Criteria) this;
        }

        public Criteria andDailyPlanIn(List<Integer> values) {
            addCriterion("daily_plan in", values, "dailyPlan");
            return (Criteria) this;
        }

        public Criteria andDailyPlanNotIn(List<Integer> values) {
            addCriterion("daily_plan not in", values, "dailyPlan");
            return (Criteria) this;
        }

        public Criteria andDailyPlanBetween(Integer value1, Integer value2) {
            addCriterion("daily_plan between", value1, value2, "dailyPlan");
            return (Criteria) this;
        }

        public Criteria andDailyPlanNotBetween(Integer value1, Integer value2) {
            addCriterion("daily_plan not between", value1, value2, "dailyPlan");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}