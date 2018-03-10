package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RiskLoanCm1Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RiskLoanCm1Example() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andProductNoIsNull() {
            addCriterion("product_no is null");
            return (Criteria) this;
        }

        public Criteria andProductNoIsNotNull() {
            addCriterion("product_no is not null");
            return (Criteria) this;
        }

        public Criteria andProductNoEqualTo(String value) {
            addCriterion("product_no =", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotEqualTo(String value) {
            addCriterion("product_no <>", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoGreaterThan(String value) {
            addCriterion("product_no >", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoGreaterThanOrEqualTo(String value) {
            addCriterion("product_no >=", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLessThan(String value) {
            addCriterion("product_no <", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLessThanOrEqualTo(String value) {
            addCriterion("product_no <=", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLike(String value) {
            addCriterion("product_no like", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotLike(String value) {
            addCriterion("product_no not like", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoIn(List<String> values) {
            addCriterion("product_no in", values, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotIn(List<String> values) {
            addCriterion("product_no not in", values, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoBetween(String value1, String value2) {
            addCriterion("product_no between", value1, value2, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotBetween(String value1, String value2) {
            addCriterion("product_no not between", value1, value2, "productNo");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("month is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("month is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(String value) {
            addCriterion("month =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(String value) {
            addCriterion("month <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(String value) {
            addCriterion("month >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(String value) {
            addCriterion("month >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(String value) {
            addCriterion("month <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(String value) {
            addCriterion("month <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLike(String value) {
            addCriterion("month like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotLike(String value) {
            addCriterion("month not like", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<String> values) {
            addCriterion("month in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<String> values) {
            addCriterion("month not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(String value1, String value2) {
            addCriterion("month between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(String value1, String value2) {
            addCriterion("month not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andBatchIsNull() {
            addCriterion("batch is null");
            return (Criteria) this;
        }

        public Criteria andBatchIsNotNull() {
            addCriterion("batch is not null");
            return (Criteria) this;
        }

        public Criteria andBatchEqualTo(String value) {
            addCriterion("batch =", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotEqualTo(String value) {
            addCriterion("batch <>", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchGreaterThan(String value) {
            addCriterion("batch >", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchGreaterThanOrEqualTo(String value) {
            addCriterion("batch >=", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLessThan(String value) {
            addCriterion("batch <", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLessThanOrEqualTo(String value) {
            addCriterion("batch <=", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLike(String value) {
            addCriterion("batch like", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotLike(String value) {
            addCriterion("batch not like", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchIn(List<String> values) {
            addCriterion("batch in", values, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotIn(List<String> values) {
            addCriterion("batch not in", values, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchBetween(String value1, String value2) {
            addCriterion("batch between", value1, value2, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotBetween(String value1, String value2) {
            addCriterion("batch not between", value1, value2, "batch");
            return (Criteria) this;
        }

        public Criteria andPayedNumIsNull() {
            addCriterion("payed_num is null");
            return (Criteria) this;
        }

        public Criteria andPayedNumIsNotNull() {
            addCriterion("payed_num is not null");
            return (Criteria) this;
        }

        public Criteria andPayedNumEqualTo(Integer value) {
            addCriterion("payed_num =", value, "payedNum");
            return (Criteria) this;
        }

        public Criteria andPayedNumNotEqualTo(Integer value) {
            addCriterion("payed_num <>", value, "payedNum");
            return (Criteria) this;
        }

        public Criteria andPayedNumGreaterThan(Integer value) {
            addCriterion("payed_num >", value, "payedNum");
            return (Criteria) this;
        }

        public Criteria andPayedNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("payed_num >=", value, "payedNum");
            return (Criteria) this;
        }

        public Criteria andPayedNumLessThan(Integer value) {
            addCriterion("payed_num <", value, "payedNum");
            return (Criteria) this;
        }

        public Criteria andPayedNumLessThanOrEqualTo(Integer value) {
            addCriterion("payed_num <=", value, "payedNum");
            return (Criteria) this;
        }

        public Criteria andPayedNumIn(List<Integer> values) {
            addCriterion("payed_num in", values, "payedNum");
            return (Criteria) this;
        }

        public Criteria andPayedNumNotIn(List<Integer> values) {
            addCriterion("payed_num not in", values, "payedNum");
            return (Criteria) this;
        }

        public Criteria andPayedNumBetween(Integer value1, Integer value2) {
            addCriterion("payed_num between", value1, value2, "payedNum");
            return (Criteria) this;
        }

        public Criteria andPayedNumNotBetween(Integer value1, Integer value2) {
            addCriterion("payed_num not between", value1, value2, "payedNum");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumIsNull() {
            addCriterion("repayment_num is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumIsNotNull() {
            addCriterion("repayment_num is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumEqualTo(Integer value) {
            addCriterion("repayment_num =", value, "repaymentNum");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumNotEqualTo(Integer value) {
            addCriterion("repayment_num <>", value, "repaymentNum");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumGreaterThan(Integer value) {
            addCriterion("repayment_num >", value, "repaymentNum");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("repayment_num >=", value, "repaymentNum");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumLessThan(Integer value) {
            addCriterion("repayment_num <", value, "repaymentNum");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumLessThanOrEqualTo(Integer value) {
            addCriterion("repayment_num <=", value, "repaymentNum");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumIn(List<Integer> values) {
            addCriterion("repayment_num in", values, "repaymentNum");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumNotIn(List<Integer> values) {
            addCriterion("repayment_num not in", values, "repaymentNum");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumBetween(Integer value1, Integer value2) {
            addCriterion("repayment_num between", value1, value2, "repaymentNum");
            return (Criteria) this;
        }

        public Criteria andRepaymentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("repayment_num not between", value1, value2, "repaymentNum");
            return (Criteria) this;
        }

        public Criteria andCm1ValueIsNull() {
            addCriterion("cm1_value is null");
            return (Criteria) this;
        }

        public Criteria andCm1ValueIsNotNull() {
            addCriterion("cm1_value is not null");
            return (Criteria) this;
        }

        public Criteria andCm1ValueEqualTo(BigDecimal value) {
            addCriterion("cm1_value =", value, "cm1Value");
            return (Criteria) this;
        }

        public Criteria andCm1ValueNotEqualTo(BigDecimal value) {
            addCriterion("cm1_value <>", value, "cm1Value");
            return (Criteria) this;
        }

        public Criteria andCm1ValueGreaterThan(BigDecimal value) {
            addCriterion("cm1_value >", value, "cm1Value");
            return (Criteria) this;
        }

        public Criteria andCm1ValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cm1_value >=", value, "cm1Value");
            return (Criteria) this;
        }

        public Criteria andCm1ValueLessThan(BigDecimal value) {
            addCriterion("cm1_value <", value, "cm1Value");
            return (Criteria) this;
        }

        public Criteria andCm1ValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cm1_value <=", value, "cm1Value");
            return (Criteria) this;
        }

        public Criteria andCm1ValueIn(List<BigDecimal> values) {
            addCriterion("cm1_value in", values, "cm1Value");
            return (Criteria) this;
        }

        public Criteria andCm1ValueNotIn(List<BigDecimal> values) {
            addCriterion("cm1_value not in", values, "cm1Value");
            return (Criteria) this;
        }

        public Criteria andCm1ValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cm1_value between", value1, value2, "cm1Value");
            return (Criteria) this;
        }

        public Criteria andCm1ValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cm1_value not between", value1, value2, "cm1Value");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyIsNull() {
            addCriterion("payed_money is null");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyIsNotNull() {
            addCriterion("payed_money is not null");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyEqualTo(BigDecimal value) {
            addCriterion("payed_money =", value, "payedMoney");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyNotEqualTo(BigDecimal value) {
            addCriterion("payed_money <>", value, "payedMoney");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyGreaterThan(BigDecimal value) {
            addCriterion("payed_money >", value, "payedMoney");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("payed_money >=", value, "payedMoney");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyLessThan(BigDecimal value) {
            addCriterion("payed_money <", value, "payedMoney");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("payed_money <=", value, "payedMoney");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyIn(List<BigDecimal> values) {
            addCriterion("payed_money in", values, "payedMoney");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyNotIn(List<BigDecimal> values) {
            addCriterion("payed_money not in", values, "payedMoney");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payed_money between", value1, value2, "payedMoney");
            return (Criteria) this;
        }

        public Criteria andPayedMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payed_money not between", value1, value2, "payedMoney");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyIsNull() {
            addCriterion("repayment_money is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyIsNotNull() {
            addCriterion("repayment_money is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyEqualTo(BigDecimal value) {
            addCriterion("repayment_money =", value, "repaymentMoney");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyNotEqualTo(BigDecimal value) {
            addCriterion("repayment_money <>", value, "repaymentMoney");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyGreaterThan(BigDecimal value) {
            addCriterion("repayment_money >", value, "repaymentMoney");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("repayment_money >=", value, "repaymentMoney");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyLessThan(BigDecimal value) {
            addCriterion("repayment_money <", value, "repaymentMoney");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("repayment_money <=", value, "repaymentMoney");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyIn(List<BigDecimal> values) {
            addCriterion("repayment_money in", values, "repaymentMoney");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyNotIn(List<BigDecimal> values) {
            addCriterion("repayment_money not in", values, "repaymentMoney");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("repayment_money between", value1, value2, "repaymentMoney");
            return (Criteria) this;
        }

        public Criteria andRepaymentMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("repayment_money not between", value1, value2, "repaymentMoney");
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