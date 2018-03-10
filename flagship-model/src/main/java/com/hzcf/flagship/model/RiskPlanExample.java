package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RiskPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RiskPlanExample() {
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
            addCriterion("record_date =", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotEqualTo(Date value) {
            addCriterion("record_date <>", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThan(Date value) {
            addCriterion("record_date >", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateGreaterThanOrEqualTo(Date value) {
            addCriterion("record_date >=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThan(Date value) {
            addCriterion("record_date <", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateLessThanOrEqualTo(Date value) {
            addCriterion("record_date <=", value, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateIn(List<Date> values) {
            addCriterion("record_date in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotIn(List<Date> values) {
            addCriterion("record_date not in", values, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateBetween(Date value1, Date value2) {
            addCriterion("record_date between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andRecordDateNotBetween(Date value1, Date value2) {
            addCriterion("record_date not between", value1, value2, "recordDate");
            return (Criteria) this;
        }

        public Criteria andOrgNoIsNull() {
            addCriterion("org_no is null");
            return (Criteria) this;
        }

        public Criteria andOrgNoIsNotNull() {
            addCriterion("org_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNoEqualTo(String value) {
            addCriterion("org_no =", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoNotEqualTo(String value) {
            addCriterion("org_no <>", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoGreaterThan(String value) {
            addCriterion("org_no >", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoGreaterThanOrEqualTo(String value) {
            addCriterion("org_no >=", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoLessThan(String value) {
            addCriterion("org_no <", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoLessThanOrEqualTo(String value) {
            addCriterion("org_no <=", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoLike(String value) {
            addCriterion("org_no like", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoNotLike(String value) {
            addCriterion("org_no not like", value, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoIn(List<String> values) {
            addCriterion("org_no in", values, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoNotIn(List<String> values) {
            addCriterion("org_no not in", values, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoBetween(String value1, String value2) {
            addCriterion("org_no between", value1, value2, "orgNo");
            return (Criteria) this;
        }

        public Criteria andOrgNoNotBetween(String value1, String value2) {
            addCriterion("org_no not between", value1, value2, "orgNo");
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

        public Criteria andParentOrgNoIsNull() {
            addCriterion("parent_org_no is null");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoIsNotNull() {
            addCriterion("parent_org_no is not null");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoEqualTo(String value) {
            addCriterion("parent_org_no =", value, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoNotEqualTo(String value) {
            addCriterion("parent_org_no <>", value, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoGreaterThan(String value) {
            addCriterion("parent_org_no >", value, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoGreaterThanOrEqualTo(String value) {
            addCriterion("parent_org_no >=", value, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoLessThan(String value) {
            addCriterion("parent_org_no <", value, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoLessThanOrEqualTo(String value) {
            addCriterion("parent_org_no <=", value, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoLike(String value) {
            addCriterion("parent_org_no like", value, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoNotLike(String value) {
            addCriterion("parent_org_no not like", value, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoIn(List<String> values) {
            addCriterion("parent_org_no in", values, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoNotIn(List<String> values) {
            addCriterion("parent_org_no not in", values, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoBetween(String value1, String value2) {
            addCriterion("parent_org_no between", value1, value2, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNoNotBetween(String value1, String value2) {
            addCriterion("parent_org_no not between", value1, value2, "parentOrgNo");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameIsNull() {
            addCriterion("parent_org_name is null");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameIsNotNull() {
            addCriterion("parent_org_name is not null");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameEqualTo(String value) {
            addCriterion("parent_org_name =", value, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameNotEqualTo(String value) {
            addCriterion("parent_org_name <>", value, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameGreaterThan(String value) {
            addCriterion("parent_org_name >", value, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("parent_org_name >=", value, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameLessThan(String value) {
            addCriterion("parent_org_name <", value, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameLessThanOrEqualTo(String value) {
            addCriterion("parent_org_name <=", value, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameLike(String value) {
            addCriterion("parent_org_name like", value, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameNotLike(String value) {
            addCriterion("parent_org_name not like", value, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameIn(List<String> values) {
            addCriterion("parent_org_name in", values, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameNotIn(List<String> values) {
            addCriterion("parent_org_name not in", values, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameBetween(String value1, String value2) {
            addCriterion("parent_org_name between", value1, value2, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgNameNotBetween(String value1, String value2) {
            addCriterion("parent_org_name not between", value1, value2, "parentOrgName");
            return (Criteria) this;
        }

        public Criteria andCm1RateIsNull() {
            addCriterion("cm1_rate is null");
            return (Criteria) this;
        }

        public Criteria andCm1RateIsNotNull() {
            addCriterion("cm1_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCm1RateEqualTo(BigDecimal value) {
            addCriterion("cm1_rate =", value, "cm1Rate");
            return (Criteria) this;
        }

        public Criteria andCm1RateNotEqualTo(BigDecimal value) {
            addCriterion("cm1_rate <>", value, "cm1Rate");
            return (Criteria) this;
        }

        public Criteria andCm1RateGreaterThan(BigDecimal value) {
            addCriterion("cm1_rate >", value, "cm1Rate");
            return (Criteria) this;
        }

        public Criteria andCm1RateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cm1_rate >=", value, "cm1Rate");
            return (Criteria) this;
        }

        public Criteria andCm1RateLessThan(BigDecimal value) {
            addCriterion("cm1_rate <", value, "cm1Rate");
            return (Criteria) this;
        }

        public Criteria andCm1RateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cm1_rate <=", value, "cm1Rate");
            return (Criteria) this;
        }

        public Criteria andCm1RateIn(List<BigDecimal> values) {
            addCriterion("cm1_rate in", values, "cm1Rate");
            return (Criteria) this;
        }

        public Criteria andCm1RateNotIn(List<BigDecimal> values) {
            addCriterion("cm1_rate not in", values, "cm1Rate");
            return (Criteria) this;
        }

        public Criteria andCm1RateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cm1_rate between", value1, value2, "cm1Rate");
            return (Criteria) this;
        }

        public Criteria andCm1RateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cm1_rate not between", value1, value2, "cm1Rate");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateIsNull() {
            addCriterion("m2_pressure_rate is null");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateIsNotNull() {
            addCriterion("m2_pressure_rate is not null");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateEqualTo(BigDecimal value) {
            addCriterion("m2_pressure_rate =", value, "m2PressureRate");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateNotEqualTo(BigDecimal value) {
            addCriterion("m2_pressure_rate <>", value, "m2PressureRate");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateGreaterThan(BigDecimal value) {
            addCriterion("m2_pressure_rate >", value, "m2PressureRate");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("m2_pressure_rate >=", value, "m2PressureRate");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateLessThan(BigDecimal value) {
            addCriterion("m2_pressure_rate <", value, "m2PressureRate");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("m2_pressure_rate <=", value, "m2PressureRate");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateIn(List<BigDecimal> values) {
            addCriterion("m2_pressure_rate in", values, "m2PressureRate");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateNotIn(List<BigDecimal> values) {
            addCriterion("m2_pressure_rate not in", values, "m2PressureRate");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("m2_pressure_rate between", value1, value2, "m2PressureRate");
            return (Criteria) this;
        }

        public Criteria andM2PressureRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("m2_pressure_rate not between", value1, value2, "m2PressureRate");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateIsNull() {
            addCriterion("m3_pressure_rate is null");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateIsNotNull() {
            addCriterion("m3_pressure_rate is not null");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateEqualTo(BigDecimal value) {
            addCriterion("m3_pressure_rate =", value, "m3PressureRate");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateNotEqualTo(BigDecimal value) {
            addCriterion("m3_pressure_rate <>", value, "m3PressureRate");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateGreaterThan(BigDecimal value) {
            addCriterion("m3_pressure_rate >", value, "m3PressureRate");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("m3_pressure_rate >=", value, "m3PressureRate");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateLessThan(BigDecimal value) {
            addCriterion("m3_pressure_rate <", value, "m3PressureRate");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("m3_pressure_rate <=", value, "m3PressureRate");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateIn(List<BigDecimal> values) {
            addCriterion("m3_pressure_rate in", values, "m3PressureRate");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateNotIn(List<BigDecimal> values) {
            addCriterion("m3_pressure_rate not in", values, "m3PressureRate");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("m3_pressure_rate between", value1, value2, "m3PressureRate");
            return (Criteria) this;
        }

        public Criteria andM3PressureRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("m3_pressure_rate not between", value1, value2, "m3PressureRate");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Integer value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Integer value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Integer value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Integer value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Integer value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Integer> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Integer> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Integer value1, Integer value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Integer value1, Integer value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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