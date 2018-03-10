package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MoneymgrOrgAccumuPerfromancePageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MoneymgrOrgAccumuPerfromancePageExample() {
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

        public Criteria andOrgNameIsNull() {
            addCriterion("org_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("org_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("org_name =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("org_name <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("org_name >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("org_name >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("org_name <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("org_name <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("org_name like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("org_name not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("org_name in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("org_name not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("org_name between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("org_name not between", value1, value2, "orgName");
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

        public Criteria andAccumuPerformanceEqualTo(BigDecimal value) {
            addCriterion("accumu_performance =", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceNotEqualTo(BigDecimal value) {
            addCriterion("accumu_performance <>", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceGreaterThan(BigDecimal value) {
            addCriterion("accumu_performance >", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("accumu_performance >=", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceLessThan(BigDecimal value) {
            addCriterion("accumu_performance <", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("accumu_performance <=", value, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceIn(List<BigDecimal> values) {
            addCriterion("accumu_performance in", values, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceNotIn(List<BigDecimal> values) {
            addCriterion("accumu_performance not in", values, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accumu_performance between", value1, value2, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andAccumuPerformanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accumu_performance not between", value1, value2, "accumuPerformance");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalIsNull() {
            addCriterion("district_principal is null");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalIsNotNull() {
            addCriterion("district_principal is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalEqualTo(String value) {
            addCriterion("district_principal =", value, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalNotEqualTo(String value) {
            addCriterion("district_principal <>", value, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalGreaterThan(String value) {
            addCriterion("district_principal >", value, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalGreaterThanOrEqualTo(String value) {
            addCriterion("district_principal >=", value, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalLessThan(String value) {
            addCriterion("district_principal <", value, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalLessThanOrEqualTo(String value) {
            addCriterion("district_principal <=", value, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalLike(String value) {
            addCriterion("district_principal like", value, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalNotLike(String value) {
            addCriterion("district_principal not like", value, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalIn(List<String> values) {
            addCriterion("district_principal in", values, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalNotIn(List<String> values) {
            addCriterion("district_principal not in", values, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalBetween(String value1, String value2) {
            addCriterion("district_principal between", value1, value2, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andDistrictPrincipalNotBetween(String value1, String value2) {
            addCriterion("district_principal not between", value1, value2, "districtPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalIsNull() {
            addCriterion("org_principal is null");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalIsNotNull() {
            addCriterion("org_principal is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalEqualTo(String value) {
            addCriterion("org_principal =", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalNotEqualTo(String value) {
            addCriterion("org_principal <>", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalGreaterThan(String value) {
            addCriterion("org_principal >", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalGreaterThanOrEqualTo(String value) {
            addCriterion("org_principal >=", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalLessThan(String value) {
            addCriterion("org_principal <", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalLessThanOrEqualTo(String value) {
            addCriterion("org_principal <=", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalLike(String value) {
            addCriterion("org_principal like", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalNotLike(String value) {
            addCriterion("org_principal not like", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalIn(List<String> values) {
            addCriterion("org_principal in", values, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalNotIn(List<String> values) {
            addCriterion("org_principal not in", values, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalBetween(String value1, String value2) {
            addCriterion("org_principal between", value1, value2, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalNotBetween(String value1, String value2) {
            addCriterion("org_principal not between", value1, value2, "orgPrincipal");
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

        public Criteria andAccumuCompleteRateEqualTo(BigDecimal value) {
            addCriterion("accumu_complete_rate =", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateNotEqualTo(BigDecimal value) {
            addCriterion("accumu_complete_rate <>", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateGreaterThan(BigDecimal value) {
            addCriterion("accumu_complete_rate >", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("accumu_complete_rate >=", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateLessThan(BigDecimal value) {
            addCriterion("accumu_complete_rate <", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("accumu_complete_rate <=", value, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateIn(List<BigDecimal> values) {
            addCriterion("accumu_complete_rate in", values, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateNotIn(List<BigDecimal> values) {
            addCriterion("accumu_complete_rate not in", values, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accumu_complete_rate between", value1, value2, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andAccumuCompleteRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accumu_complete_rate not between", value1, value2, "accumuCompleteRate");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityIsNull() {
            addCriterion("per_capita_capacity is null");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityIsNotNull() {
            addCriterion("per_capita_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityEqualTo(BigDecimal value) {
            addCriterion("per_capita_capacity =", value, "perCapitaCapacity");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityNotEqualTo(BigDecimal value) {
            addCriterion("per_capita_capacity <>", value, "perCapitaCapacity");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityGreaterThan(BigDecimal value) {
            addCriterion("per_capita_capacity >", value, "perCapitaCapacity");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("per_capita_capacity >=", value, "perCapitaCapacity");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityLessThan(BigDecimal value) {
            addCriterion("per_capita_capacity <", value, "perCapitaCapacity");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("per_capita_capacity <=", value, "perCapitaCapacity");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityIn(List<BigDecimal> values) {
            addCriterion("per_capita_capacity in", values, "perCapitaCapacity");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityNotIn(List<BigDecimal> values) {
            addCriterion("per_capita_capacity not in", values, "perCapitaCapacity");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("per_capita_capacity between", value1, value2, "perCapitaCapacity");
            return (Criteria) this;
        }

        public Criteria andPerCapitaCapacityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("per_capita_capacity not between", value1, value2, "perCapitaCapacity");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientIsNull() {
            addCriterion("per_capita_new_client is null");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientIsNotNull() {
            addCriterion("per_capita_new_client is not null");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientEqualTo(BigDecimal value) {
            addCriterion("per_capita_new_client =", value, "perCapitaNewClient");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientNotEqualTo(BigDecimal value) {
            addCriterion("per_capita_new_client <>", value, "perCapitaNewClient");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientGreaterThan(BigDecimal value) {
            addCriterion("per_capita_new_client >", value, "perCapitaNewClient");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("per_capita_new_client >=", value, "perCapitaNewClient");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientLessThan(BigDecimal value) {
            addCriterion("per_capita_new_client <", value, "perCapitaNewClient");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientLessThanOrEqualTo(BigDecimal value) {
            addCriterion("per_capita_new_client <=", value, "perCapitaNewClient");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientIn(List<BigDecimal> values) {
            addCriterion("per_capita_new_client in", values, "perCapitaNewClient");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientNotIn(List<BigDecimal> values) {
            addCriterion("per_capita_new_client not in", values, "perCapitaNewClient");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("per_capita_new_client between", value1, value2, "perCapitaNewClient");
            return (Criteria) this;
        }

        public Criteria andPerCapitaNewClientNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("per_capita_new_client not between", value1, value2, "perCapitaNewClient");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioIsNull() {
            addCriterion("counselor_manager_ratio is null");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioIsNotNull() {
            addCriterion("counselor_manager_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioEqualTo(BigDecimal value) {
            addCriterion("counselor_manager_ratio =", value, "counselorManagerRatio");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioNotEqualTo(BigDecimal value) {
            addCriterion("counselor_manager_ratio <>", value, "counselorManagerRatio");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioGreaterThan(BigDecimal value) {
            addCriterion("counselor_manager_ratio >", value, "counselorManagerRatio");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("counselor_manager_ratio >=", value, "counselorManagerRatio");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioLessThan(BigDecimal value) {
            addCriterion("counselor_manager_ratio <", value, "counselorManagerRatio");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("counselor_manager_ratio <=", value, "counselorManagerRatio");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioIn(List<BigDecimal> values) {
            addCriterion("counselor_manager_ratio in", values, "counselorManagerRatio");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioNotIn(List<BigDecimal> values) {
            addCriterion("counselor_manager_ratio not in", values, "counselorManagerRatio");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("counselor_manager_ratio between", value1, value2, "counselorManagerRatio");
            return (Criteria) this;
        }

        public Criteria andCounselorManagerRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("counselor_manager_ratio not between", value1, value2, "counselorManagerRatio");
            return (Criteria) this;
        }

        public Criteria andCounselorNumIsNull() {
            addCriterion("counselor_num is null");
            return (Criteria) this;
        }

        public Criteria andCounselorNumIsNotNull() {
            addCriterion("counselor_num is not null");
            return (Criteria) this;
        }

        public Criteria andCounselorNumEqualTo(Integer value) {
            addCriterion("counselor_num =", value, "counselorNum");
            return (Criteria) this;
        }

        public Criteria andCounselorNumNotEqualTo(Integer value) {
            addCriterion("counselor_num <>", value, "counselorNum");
            return (Criteria) this;
        }

        public Criteria andCounselorNumGreaterThan(Integer value) {
            addCriterion("counselor_num >", value, "counselorNum");
            return (Criteria) this;
        }

        public Criteria andCounselorNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("counselor_num >=", value, "counselorNum");
            return (Criteria) this;
        }

        public Criteria andCounselorNumLessThan(Integer value) {
            addCriterion("counselor_num <", value, "counselorNum");
            return (Criteria) this;
        }

        public Criteria andCounselorNumLessThanOrEqualTo(Integer value) {
            addCriterion("counselor_num <=", value, "counselorNum");
            return (Criteria) this;
        }

        public Criteria andCounselorNumIn(List<Integer> values) {
            addCriterion("counselor_num in", values, "counselorNum");
            return (Criteria) this;
        }

        public Criteria andCounselorNumNotIn(List<Integer> values) {
            addCriterion("counselor_num not in", values, "counselorNum");
            return (Criteria) this;
        }

        public Criteria andCounselorNumBetween(Integer value1, Integer value2) {
            addCriterion("counselor_num between", value1, value2, "counselorNum");
            return (Criteria) this;
        }

        public Criteria andCounselorNumNotBetween(Integer value1, Integer value2) {
            addCriterion("counselor_num not between", value1, value2, "counselorNum");
            return (Criteria) this;
        }

        public Criteria andManagerNumIsNull() {
            addCriterion("manager_num is null");
            return (Criteria) this;
        }

        public Criteria andManagerNumIsNotNull() {
            addCriterion("manager_num is not null");
            return (Criteria) this;
        }

        public Criteria andManagerNumEqualTo(Integer value) {
            addCriterion("manager_num =", value, "managerNum");
            return (Criteria) this;
        }

        public Criteria andManagerNumNotEqualTo(Integer value) {
            addCriterion("manager_num <>", value, "managerNum");
            return (Criteria) this;
        }

        public Criteria andManagerNumGreaterThan(Integer value) {
            addCriterion("manager_num >", value, "managerNum");
            return (Criteria) this;
        }

        public Criteria andManagerNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("manager_num >=", value, "managerNum");
            return (Criteria) this;
        }

        public Criteria andManagerNumLessThan(Integer value) {
            addCriterion("manager_num <", value, "managerNum");
            return (Criteria) this;
        }

        public Criteria andManagerNumLessThanOrEqualTo(Integer value) {
            addCriterion("manager_num <=", value, "managerNum");
            return (Criteria) this;
        }

        public Criteria andManagerNumIn(List<Integer> values) {
            addCriterion("manager_num in", values, "managerNum");
            return (Criteria) this;
        }

        public Criteria andManagerNumNotIn(List<Integer> values) {
            addCriterion("manager_num not in", values, "managerNum");
            return (Criteria) this;
        }

        public Criteria andManagerNumBetween(Integer value1, Integer value2) {
            addCriterion("manager_num between", value1, value2, "managerNum");
            return (Criteria) this;
        }

        public Criteria andManagerNumNotBetween(Integer value1, Integer value2) {
            addCriterion("manager_num not between", value1, value2, "managerNum");
            return (Criteria) this;
        }

        public Criteria andRankingIsNull() {
            addCriterion("ranking is null");
            return (Criteria) this;
        }

        public Criteria andRankingIsNotNull() {
            addCriterion("ranking is not null");
            return (Criteria) this;
        }

        public Criteria andRankingEqualTo(Integer value) {
            addCriterion("ranking =", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotEqualTo(Integer value) {
            addCriterion("ranking <>", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThan(Integer value) {
            addCriterion("ranking >", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingGreaterThanOrEqualTo(Integer value) {
            addCriterion("ranking >=", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLessThan(Integer value) {
            addCriterion("ranking <", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingLessThanOrEqualTo(Integer value) {
            addCriterion("ranking <=", value, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingIn(List<Integer> values) {
            addCriterion("ranking in", values, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotIn(List<Integer> values) {
            addCriterion("ranking not in", values, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingBetween(Integer value1, Integer value2) {
            addCriterion("ranking between", value1, value2, "ranking");
            return (Criteria) this;
        }

        public Criteria andRankingNotBetween(Integer value1, Integer value2) {
            addCriterion("ranking not between", value1, value2, "ranking");
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