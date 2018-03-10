package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FinanceDailyPerformanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinanceDailyPerformanceExample() {
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

        public Criteria andLoanValueIsNull() {
            addCriterion("loan_value is null");
            return (Criteria) this;
        }

        public Criteria andLoanValueIsNotNull() {
            addCriterion("loan_value is not null");
            return (Criteria) this;
        }

        public Criteria andLoanValueEqualTo(BigDecimal value) {
            addCriterion("loan_value =", value, "loanValue");
            return (Criteria) this;
        }

        public Criteria andLoanValueNotEqualTo(BigDecimal value) {
            addCriterion("loan_value <>", value, "loanValue");
            return (Criteria) this;
        }

        public Criteria andLoanValueGreaterThan(BigDecimal value) {
            addCriterion("loan_value >", value, "loanValue");
            return (Criteria) this;
        }

        public Criteria andLoanValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("loan_value >=", value, "loanValue");
            return (Criteria) this;
        }

        public Criteria andLoanValueLessThan(BigDecimal value) {
            addCriterion("loan_value <", value, "loanValue");
            return (Criteria) this;
        }

        public Criteria andLoanValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("loan_value <=", value, "loanValue");
            return (Criteria) this;
        }

        public Criteria andLoanValueIn(List<BigDecimal> values) {
            addCriterion("loan_value in", values, "loanValue");
            return (Criteria) this;
        }

        public Criteria andLoanValueNotIn(List<BigDecimal> values) {
            addCriterion("loan_value not in", values, "loanValue");
            return (Criteria) this;
        }

        public Criteria andLoanValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loan_value between", value1, value2, "loanValue");
            return (Criteria) this;
        }

        public Criteria andLoanValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loan_value not between", value1, value2, "loanValue");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueIsNull() {
            addCriterion("accumu_loan_value is null");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueIsNotNull() {
            addCriterion("accumu_loan_value is not null");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueEqualTo(BigDecimal value) {
            addCriterion("accumu_loan_value =", value, "accumuLoanValue");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueNotEqualTo(BigDecimal value) {
            addCriterion("accumu_loan_value <>", value, "accumuLoanValue");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueGreaterThan(BigDecimal value) {
            addCriterion("accumu_loan_value >", value, "accumuLoanValue");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("accumu_loan_value >=", value, "accumuLoanValue");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueLessThan(BigDecimal value) {
            addCriterion("accumu_loan_value <", value, "accumuLoanValue");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("accumu_loan_value <=", value, "accumuLoanValue");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueIn(List<BigDecimal> values) {
            addCriterion("accumu_loan_value in", values, "accumuLoanValue");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueNotIn(List<BigDecimal> values) {
            addCriterion("accumu_loan_value not in", values, "accumuLoanValue");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accumu_loan_value between", value1, value2, "accumuLoanValue");
            return (Criteria) this;
        }

        public Criteria andAccumuLoanValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accumu_loan_value not between", value1, value2, "accumuLoanValue");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityIsNull() {
            addCriterion("loan_quantity is null");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityIsNotNull() {
            addCriterion("loan_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityEqualTo(Integer value) {
            addCriterion("loan_quantity =", value, "loanQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityNotEqualTo(Integer value) {
            addCriterion("loan_quantity <>", value, "loanQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityGreaterThan(Integer value) {
            addCriterion("loan_quantity >", value, "loanQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_quantity >=", value, "loanQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityLessThan(Integer value) {
            addCriterion("loan_quantity <", value, "loanQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("loan_quantity <=", value, "loanQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityIn(List<Integer> values) {
            addCriterion("loan_quantity in", values, "loanQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityNotIn(List<Integer> values) {
            addCriterion("loan_quantity not in", values, "loanQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityBetween(Integer value1, Integer value2) {
            addCriterion("loan_quantity between", value1, value2, "loanQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_quantity not between", value1, value2, "loanQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityIsNull() {
            addCriterion("apply_quantity is null");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityIsNotNull() {
            addCriterion("apply_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityEqualTo(Integer value) {
            addCriterion("apply_quantity =", value, "applyQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityNotEqualTo(Integer value) {
            addCriterion("apply_quantity <>", value, "applyQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityGreaterThan(Integer value) {
            addCriterion("apply_quantity >", value, "applyQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_quantity >=", value, "applyQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityLessThan(Integer value) {
            addCriterion("apply_quantity <", value, "applyQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("apply_quantity <=", value, "applyQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityIn(List<Integer> values) {
            addCriterion("apply_quantity in", values, "applyQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityNotIn(List<Integer> values) {
            addCriterion("apply_quantity not in", values, "applyQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityBetween(Integer value1, Integer value2) {
            addCriterion("apply_quantity between", value1, value2, "applyQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_quantity not between", value1, value2, "applyQuantity");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateIsNull() {
            addCriterion("accumu_approval_rate is null");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateIsNotNull() {
            addCriterion("accumu_approval_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateEqualTo(BigDecimal value) {
            addCriterion("accumu_approval_rate =", value, "accumuApprovalRate");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateNotEqualTo(BigDecimal value) {
            addCriterion("accumu_approval_rate <>", value, "accumuApprovalRate");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateGreaterThan(BigDecimal value) {
            addCriterion("accumu_approval_rate >", value, "accumuApprovalRate");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("accumu_approval_rate >=", value, "accumuApprovalRate");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateLessThan(BigDecimal value) {
            addCriterion("accumu_approval_rate <", value, "accumuApprovalRate");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("accumu_approval_rate <=", value, "accumuApprovalRate");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateIn(List<BigDecimal> values) {
            addCriterion("accumu_approval_rate in", values, "accumuApprovalRate");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateNotIn(List<BigDecimal> values) {
            addCriterion("accumu_approval_rate not in", values, "accumuApprovalRate");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accumu_approval_rate between", value1, value2, "accumuApprovalRate");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accumu_approval_rate not between", value1, value2, "accumuApprovalRate");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageIsNull() {
            addCriterion("accumu_approval_average is null");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageIsNotNull() {
            addCriterion("accumu_approval_average is not null");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageEqualTo(BigDecimal value) {
            addCriterion("accumu_approval_average =", value, "accumuApprovalAverage");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageNotEqualTo(BigDecimal value) {
            addCriterion("accumu_approval_average <>", value, "accumuApprovalAverage");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageGreaterThan(BigDecimal value) {
            addCriterion("accumu_approval_average >", value, "accumuApprovalAverage");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("accumu_approval_average >=", value, "accumuApprovalAverage");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageLessThan(BigDecimal value) {
            addCriterion("accumu_approval_average <", value, "accumuApprovalAverage");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("accumu_approval_average <=", value, "accumuApprovalAverage");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageIn(List<BigDecimal> values) {
            addCriterion("accumu_approval_average in", values, "accumuApprovalAverage");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageNotIn(List<BigDecimal> values) {
            addCriterion("accumu_approval_average not in", values, "accumuApprovalAverage");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accumu_approval_average between", value1, value2, "accumuApprovalAverage");
            return (Criteria) this;
        }

        public Criteria andAccumuApprovalAverageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accumu_approval_average not between", value1, value2, "accumuApprovalAverage");
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