package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FinanceRiskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinanceRiskExample() {
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

        public Criteria andManageOfficeIsNull() {
            addCriterion("manage_office is null");
            return (Criteria) this;
        }

        public Criteria andManageOfficeIsNotNull() {
            addCriterion("manage_office is not null");
            return (Criteria) this;
        }

        public Criteria andManageOfficeEqualTo(String value) {
            addCriterion("manage_office =", value, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeNotEqualTo(String value) {
            addCriterion("manage_office <>", value, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeGreaterThan(String value) {
            addCriterion("manage_office >", value, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeGreaterThanOrEqualTo(String value) {
            addCriterion("manage_office >=", value, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeLessThan(String value) {
            addCriterion("manage_office <", value, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeLessThanOrEqualTo(String value) {
            addCriterion("manage_office <=", value, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeLike(String value) {
            addCriterion("manage_office like", value, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeNotLike(String value) {
            addCriterion("manage_office not like", value, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeIn(List<String> values) {
            addCriterion("manage_office in", values, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeNotIn(List<String> values) {
            addCriterion("manage_office not in", values, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeBetween(String value1, String value2) {
            addCriterion("manage_office between", value1, value2, "manageOffice");
            return (Criteria) this;
        }

        public Criteria andManageOfficeNotBetween(String value1, String value2) {
            addCriterion("manage_office not between", value1, value2, "manageOffice");
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

        public Criteria andCM1NumeratorIsNull() {
            addCriterion("C_M1_numerator is null");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorIsNotNull() {
            addCriterion("C_M1_numerator is not null");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorEqualTo(BigDecimal value) {
            addCriterion("C_M1_numerator =", value, "cM1Numerator");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorNotEqualTo(BigDecimal value) {
            addCriterion("C_M1_numerator <>", value, "cM1Numerator");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorGreaterThan(BigDecimal value) {
            addCriterion("C_M1_numerator >", value, "cM1Numerator");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("C_M1_numerator >=", value, "cM1Numerator");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorLessThan(BigDecimal value) {
            addCriterion("C_M1_numerator <", value, "cM1Numerator");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("C_M1_numerator <=", value, "cM1Numerator");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorIn(List<BigDecimal> values) {
            addCriterion("C_M1_numerator in", values, "cM1Numerator");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorNotIn(List<BigDecimal> values) {
            addCriterion("C_M1_numerator not in", values, "cM1Numerator");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("C_M1_numerator between", value1, value2, "cM1Numerator");
            return (Criteria) this;
        }

        public Criteria andCM1NumeratorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("C_M1_numerator not between", value1, value2, "cM1Numerator");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorIsNull() {
            addCriterion("C_M1_denominator is null");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorIsNotNull() {
            addCriterion("C_M1_denominator is not null");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorEqualTo(BigDecimal value) {
            addCriterion("C_M1_denominator =", value, "cM1Denominator");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorNotEqualTo(BigDecimal value) {
            addCriterion("C_M1_denominator <>", value, "cM1Denominator");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorGreaterThan(BigDecimal value) {
            addCriterion("C_M1_denominator >", value, "cM1Denominator");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("C_M1_denominator >=", value, "cM1Denominator");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorLessThan(BigDecimal value) {
            addCriterion("C_M1_denominator <", value, "cM1Denominator");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("C_M1_denominator <=", value, "cM1Denominator");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorIn(List<BigDecimal> values) {
            addCriterion("C_M1_denominator in", values, "cM1Denominator");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorNotIn(List<BigDecimal> values) {
            addCriterion("C_M1_denominator not in", values, "cM1Denominator");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("C_M1_denominator between", value1, value2, "cM1Denominator");
            return (Criteria) this;
        }

        public Criteria andCM1DenominatorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("C_M1_denominator not between", value1, value2, "cM1Denominator");
            return (Criteria) this;
        }

        public Criteria andCM1ValueIsNull() {
            addCriterion("C_M1_value is null");
            return (Criteria) this;
        }

        public Criteria andCM1ValueIsNotNull() {
            addCriterion("C_M1_value is not null");
            return (Criteria) this;
        }

        public Criteria andCM1ValueEqualTo(BigDecimal value) {
            addCriterion("C_M1_value =", value, "cM1Value");
            return (Criteria) this;
        }

        public Criteria andCM1ValueNotEqualTo(BigDecimal value) {
            addCriterion("C_M1_value <>", value, "cM1Value");
            return (Criteria) this;
        }

        public Criteria andCM1ValueGreaterThan(BigDecimal value) {
            addCriterion("C_M1_value >", value, "cM1Value");
            return (Criteria) this;
        }

        public Criteria andCM1ValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("C_M1_value >=", value, "cM1Value");
            return (Criteria) this;
        }

        public Criteria andCM1ValueLessThan(BigDecimal value) {
            addCriterion("C_M1_value <", value, "cM1Value");
            return (Criteria) this;
        }

        public Criteria andCM1ValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("C_M1_value <=", value, "cM1Value");
            return (Criteria) this;
        }

        public Criteria andCM1ValueIn(List<BigDecimal> values) {
            addCriterion("C_M1_value in", values, "cM1Value");
            return (Criteria) this;
        }

        public Criteria andCM1ValueNotIn(List<BigDecimal> values) {
            addCriterion("C_M1_value not in", values, "cM1Value");
            return (Criteria) this;
        }

        public Criteria andCM1ValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("C_M1_value between", value1, value2, "cM1Value");
            return (Criteria) this;
        }

        public Criteria andCM1ValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("C_M1_value not between", value1, value2, "cM1Value");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorIsNull() {
            addCriterion("loss_rate_numerator is null");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorIsNotNull() {
            addCriterion("loss_rate_numerator is not null");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorEqualTo(BigDecimal value) {
            addCriterion("loss_rate_numerator =", value, "lossRateNumerator");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorNotEqualTo(BigDecimal value) {
            addCriterion("loss_rate_numerator <>", value, "lossRateNumerator");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorGreaterThan(BigDecimal value) {
            addCriterion("loss_rate_numerator >", value, "lossRateNumerator");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("loss_rate_numerator >=", value, "lossRateNumerator");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorLessThan(BigDecimal value) {
            addCriterion("loss_rate_numerator <", value, "lossRateNumerator");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("loss_rate_numerator <=", value, "lossRateNumerator");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorIn(List<BigDecimal> values) {
            addCriterion("loss_rate_numerator in", values, "lossRateNumerator");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorNotIn(List<BigDecimal> values) {
            addCriterion("loss_rate_numerator not in", values, "lossRateNumerator");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loss_rate_numerator between", value1, value2, "lossRateNumerator");
            return (Criteria) this;
        }

        public Criteria andLossRateNumeratorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loss_rate_numerator not between", value1, value2, "lossRateNumerator");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorIsNull() {
            addCriterion("loss_rate_denominator is null");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorIsNotNull() {
            addCriterion("loss_rate_denominator is not null");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorEqualTo(BigDecimal value) {
            addCriterion("loss_rate_denominator =", value, "lossRateDenominator");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorNotEqualTo(BigDecimal value) {
            addCriterion("loss_rate_denominator <>", value, "lossRateDenominator");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorGreaterThan(BigDecimal value) {
            addCriterion("loss_rate_denominator >", value, "lossRateDenominator");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("loss_rate_denominator >=", value, "lossRateDenominator");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorLessThan(BigDecimal value) {
            addCriterion("loss_rate_denominator <", value, "lossRateDenominator");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("loss_rate_denominator <=", value, "lossRateDenominator");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorIn(List<BigDecimal> values) {
            addCriterion("loss_rate_denominator in", values, "lossRateDenominator");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorNotIn(List<BigDecimal> values) {
            addCriterion("loss_rate_denominator not in", values, "lossRateDenominator");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loss_rate_denominator between", value1, value2, "lossRateDenominator");
            return (Criteria) this;
        }

        public Criteria andLossRateDenominatorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loss_rate_denominator not between", value1, value2, "lossRateDenominator");
            return (Criteria) this;
        }

        public Criteria andLossRateIsNull() {
            addCriterion("loss_rate is null");
            return (Criteria) this;
        }

        public Criteria andLossRateIsNotNull() {
            addCriterion("loss_rate is not null");
            return (Criteria) this;
        }

        public Criteria andLossRateEqualTo(BigDecimal value) {
            addCriterion("loss_rate =", value, "lossRate");
            return (Criteria) this;
        }

        public Criteria andLossRateNotEqualTo(BigDecimal value) {
            addCriterion("loss_rate <>", value, "lossRate");
            return (Criteria) this;
        }

        public Criteria andLossRateGreaterThan(BigDecimal value) {
            addCriterion("loss_rate >", value, "lossRate");
            return (Criteria) this;
        }

        public Criteria andLossRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("loss_rate >=", value, "lossRate");
            return (Criteria) this;
        }

        public Criteria andLossRateLessThan(BigDecimal value) {
            addCriterion("loss_rate <", value, "lossRate");
            return (Criteria) this;
        }

        public Criteria andLossRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("loss_rate <=", value, "lossRate");
            return (Criteria) this;
        }

        public Criteria andLossRateIn(List<BigDecimal> values) {
            addCriterion("loss_rate in", values, "lossRate");
            return (Criteria) this;
        }

        public Criteria andLossRateNotIn(List<BigDecimal> values) {
            addCriterion("loss_rate not in", values, "lossRate");
            return (Criteria) this;
        }

        public Criteria andLossRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loss_rate between", value1, value2, "lossRate");
            return (Criteria) this;
        }

        public Criteria andLossRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loss_rate not between", value1, value2, "lossRate");
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