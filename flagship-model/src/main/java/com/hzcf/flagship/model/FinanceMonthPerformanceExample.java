package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FinanceMonthPerformanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinanceMonthPerformanceExample() {
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

        public Criteria andMonthPerformanceIsNull() {
            addCriterion("month_performance is null");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceIsNotNull() {
            addCriterion("month_performance is not null");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceEqualTo(BigDecimal value) {
            addCriterion("month_performance =", value, "monthPerformance");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceNotEqualTo(BigDecimal value) {
            addCriterion("month_performance <>", value, "monthPerformance");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceGreaterThan(BigDecimal value) {
            addCriterion("month_performance >", value, "monthPerformance");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("month_performance >=", value, "monthPerformance");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceLessThan(BigDecimal value) {
            addCriterion("month_performance <", value, "monthPerformance");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("month_performance <=", value, "monthPerformance");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceIn(List<BigDecimal> values) {
            addCriterion("month_performance in", values, "monthPerformance");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceNotIn(List<BigDecimal> values) {
            addCriterion("month_performance not in", values, "monthPerformance");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("month_performance between", value1, value2, "monthPerformance");
            return (Criteria) this;
        }

        public Criteria andMonthPerformanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("month_performance not between", value1, value2, "monthPerformance");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceIsNull() {
            addCriterion("year_performance is null");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceIsNotNull() {
            addCriterion("year_performance is not null");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceEqualTo(BigDecimal value) {
            addCriterion("year_performance =", value, "yearPerformance");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceNotEqualTo(BigDecimal value) {
            addCriterion("year_performance <>", value, "yearPerformance");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceGreaterThan(BigDecimal value) {
            addCriterion("year_performance >", value, "yearPerformance");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("year_performance >=", value, "yearPerformance");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceLessThan(BigDecimal value) {
            addCriterion("year_performance <", value, "yearPerformance");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("year_performance <=", value, "yearPerformance");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceIn(List<BigDecimal> values) {
            addCriterion("year_performance in", values, "yearPerformance");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceNotIn(List<BigDecimal> values) {
            addCriterion("year_performance not in", values, "yearPerformance");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("year_performance between", value1, value2, "yearPerformance");
            return (Criteria) this;
        }

        public Criteria andYearPerformanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("year_performance not between", value1, value2, "yearPerformance");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityIsNull() {
            addCriterion("apply_actual_quantity is null");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityIsNotNull() {
            addCriterion("apply_actual_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityEqualTo(Integer value) {
            addCriterion("apply_actual_quantity =", value, "applyActualQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityNotEqualTo(Integer value) {
            addCriterion("apply_actual_quantity <>", value, "applyActualQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityGreaterThan(Integer value) {
            addCriterion("apply_actual_quantity >", value, "applyActualQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_actual_quantity >=", value, "applyActualQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityLessThan(Integer value) {
            addCriterion("apply_actual_quantity <", value, "applyActualQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("apply_actual_quantity <=", value, "applyActualQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityIn(List<Integer> values) {
            addCriterion("apply_actual_quantity in", values, "applyActualQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityNotIn(List<Integer> values) {
            addCriterion("apply_actual_quantity not in", values, "applyActualQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityBetween(Integer value1, Integer value2) {
            addCriterion("apply_actual_quantity between", value1, value2, "applyActualQuantity");
            return (Criteria) this;
        }

        public Criteria andApplyActualQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_actual_quantity not between", value1, value2, "applyActualQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityIsNull() {
            addCriterion("loan_actual_quantity is null");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityIsNotNull() {
            addCriterion("loan_actual_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityEqualTo(Integer value) {
            addCriterion("loan_actual_quantity =", value, "loanActualQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityNotEqualTo(Integer value) {
            addCriterion("loan_actual_quantity <>", value, "loanActualQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityGreaterThan(Integer value) {
            addCriterion("loan_actual_quantity >", value, "loanActualQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_actual_quantity >=", value, "loanActualQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityLessThan(Integer value) {
            addCriterion("loan_actual_quantity <", value, "loanActualQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("loan_actual_quantity <=", value, "loanActualQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityIn(List<Integer> values) {
            addCriterion("loan_actual_quantity in", values, "loanActualQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityNotIn(List<Integer> values) {
            addCriterion("loan_actual_quantity not in", values, "loanActualQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityBetween(Integer value1, Integer value2) {
            addCriterion("loan_actual_quantity between", value1, value2, "loanActualQuantity");
            return (Criteria) this;
        }

        public Criteria andLoanActualQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_actual_quantity not between", value1, value2, "loanActualQuantity");
            return (Criteria) this;
        }

        public Criteria andApprovalRateIsNull() {
            addCriterion("approval_rate is null");
            return (Criteria) this;
        }

        public Criteria andApprovalRateIsNotNull() {
            addCriterion("approval_rate is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalRateEqualTo(BigDecimal value) {
            addCriterion("approval_rate =", value, "approvalRate");
            return (Criteria) this;
        }

        public Criteria andApprovalRateNotEqualTo(BigDecimal value) {
            addCriterion("approval_rate <>", value, "approvalRate");
            return (Criteria) this;
        }

        public Criteria andApprovalRateGreaterThan(BigDecimal value) {
            addCriterion("approval_rate >", value, "approvalRate");
            return (Criteria) this;
        }

        public Criteria andApprovalRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("approval_rate >=", value, "approvalRate");
            return (Criteria) this;
        }

        public Criteria andApprovalRateLessThan(BigDecimal value) {
            addCriterion("approval_rate <", value, "approvalRate");
            return (Criteria) this;
        }

        public Criteria andApprovalRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("approval_rate <=", value, "approvalRate");
            return (Criteria) this;
        }

        public Criteria andApprovalRateIn(List<BigDecimal> values) {
            addCriterion("approval_rate in", values, "approvalRate");
            return (Criteria) this;
        }

        public Criteria andApprovalRateNotIn(List<BigDecimal> values) {
            addCriterion("approval_rate not in", values, "approvalRate");
            return (Criteria) this;
        }

        public Criteria andApprovalRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("approval_rate between", value1, value2, "approvalRate");
            return (Criteria) this;
        }

        public Criteria andApprovalRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("approval_rate not between", value1, value2, "approvalRate");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageIsNull() {
            addCriterion("approval_average is null");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageIsNotNull() {
            addCriterion("approval_average is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageEqualTo(BigDecimal value) {
            addCriterion("approval_average =", value, "approvalAverage");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageNotEqualTo(BigDecimal value) {
            addCriterion("approval_average <>", value, "approvalAverage");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageGreaterThan(BigDecimal value) {
            addCriterion("approval_average >", value, "approvalAverage");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("approval_average >=", value, "approvalAverage");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageLessThan(BigDecimal value) {
            addCriterion("approval_average <", value, "approvalAverage");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("approval_average <=", value, "approvalAverage");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageIn(List<BigDecimal> values) {
            addCriterion("approval_average in", values, "approvalAverage");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageNotIn(List<BigDecimal> values) {
            addCriterion("approval_average not in", values, "approvalAverage");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("approval_average between", value1, value2, "approvalAverage");
            return (Criteria) this;
        }

        public Criteria andApprovalAverageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("approval_average not between", value1, value2, "approvalAverage");
            return (Criteria) this;
        }

        public Criteria andContractMoneyIsNull() {
            addCriterion("contract_money is null");
            return (Criteria) this;
        }

        public Criteria andContractMoneyIsNotNull() {
            addCriterion("contract_money is not null");
            return (Criteria) this;
        }

        public Criteria andContractMoneyEqualTo(BigDecimal value) {
            addCriterion("contract_money =", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyNotEqualTo(BigDecimal value) {
            addCriterion("contract_money <>", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyGreaterThan(BigDecimal value) {
            addCriterion("contract_money >", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_money >=", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyLessThan(BigDecimal value) {
            addCriterion("contract_money <", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("contract_money <=", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyIn(List<BigDecimal> values) {
            addCriterion("contract_money in", values, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyNotIn(List<BigDecimal> values) {
            addCriterion("contract_money not in", values, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_money between", value1, value2, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("contract_money not between", value1, value2, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateIsNull() {
            addCriterion("service_charge_rate is null");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateIsNotNull() {
            addCriterion("service_charge_rate is not null");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateEqualTo(BigDecimal value) {
            addCriterion("service_charge_rate =", value, "serviceChargeRate");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateNotEqualTo(BigDecimal value) {
            addCriterion("service_charge_rate <>", value, "serviceChargeRate");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateGreaterThan(BigDecimal value) {
            addCriterion("service_charge_rate >", value, "serviceChargeRate");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("service_charge_rate >=", value, "serviceChargeRate");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateLessThan(BigDecimal value) {
            addCriterion("service_charge_rate <", value, "serviceChargeRate");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("service_charge_rate <=", value, "serviceChargeRate");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateIn(List<BigDecimal> values) {
            addCriterion("service_charge_rate in", values, "serviceChargeRate");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateNotIn(List<BigDecimal> values) {
            addCriterion("service_charge_rate not in", values, "serviceChargeRate");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("service_charge_rate between", value1, value2, "serviceChargeRate");
            return (Criteria) this;
        }

        public Criteria andServiceChargeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("service_charge_rate not between", value1, value2, "serviceChargeRate");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateIsNull() {
            addCriterion("arbitration_charge_rate is null");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateIsNotNull() {
            addCriterion("arbitration_charge_rate is not null");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateEqualTo(BigDecimal value) {
            addCriterion("arbitration_charge_rate =", value, "arbitrationChargeRate");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateNotEqualTo(BigDecimal value) {
            addCriterion("arbitration_charge_rate <>", value, "arbitrationChargeRate");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateGreaterThan(BigDecimal value) {
            addCriterion("arbitration_charge_rate >", value, "arbitrationChargeRate");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("arbitration_charge_rate >=", value, "arbitrationChargeRate");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateLessThan(BigDecimal value) {
            addCriterion("arbitration_charge_rate <", value, "arbitrationChargeRate");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("arbitration_charge_rate <=", value, "arbitrationChargeRate");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateIn(List<BigDecimal> values) {
            addCriterion("arbitration_charge_rate in", values, "arbitrationChargeRate");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateNotIn(List<BigDecimal> values) {
            addCriterion("arbitration_charge_rate not in", values, "arbitrationChargeRate");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("arbitration_charge_rate between", value1, value2, "arbitrationChargeRate");
            return (Criteria) this;
        }

        public Criteria andArbitrationChargeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("arbitration_charge_rate not between", value1, value2, "arbitrationChargeRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateIsNull() {
            addCriterion("insurance_rate is null");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateIsNotNull() {
            addCriterion("insurance_rate is not null");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateEqualTo(BigDecimal value) {
            addCriterion("insurance_rate =", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateNotEqualTo(BigDecimal value) {
            addCriterion("insurance_rate <>", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateGreaterThan(BigDecimal value) {
            addCriterion("insurance_rate >", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("insurance_rate >=", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateLessThan(BigDecimal value) {
            addCriterion("insurance_rate <", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("insurance_rate <=", value, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateIn(List<BigDecimal> values) {
            addCriterion("insurance_rate in", values, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateNotIn(List<BigDecimal> values) {
            addCriterion("insurance_rate not in", values, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("insurance_rate between", value1, value2, "insuranceRate");
            return (Criteria) this;
        }

        public Criteria andInsuranceRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("insurance_rate not between", value1, value2, "insuranceRate");
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