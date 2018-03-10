package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RiskLoanM1OverdueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RiskLoanM1OverdueExample() {
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

        public Criteria andOverduePrincipalInterestIsNull() {
            addCriterion("overdue_principal_interest is null");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestIsNotNull() {
            addCriterion("overdue_principal_interest is not null");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestEqualTo(BigDecimal value) {
            addCriterion("overdue_principal_interest =", value, "overduePrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestNotEqualTo(BigDecimal value) {
            addCriterion("overdue_principal_interest <>", value, "overduePrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestGreaterThan(BigDecimal value) {
            addCriterion("overdue_principal_interest >", value, "overduePrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_principal_interest >=", value, "overduePrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestLessThan(BigDecimal value) {
            addCriterion("overdue_principal_interest <", value, "overduePrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_principal_interest <=", value, "overduePrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestIn(List<BigDecimal> values) {
            addCriterion("overdue_principal_interest in", values, "overduePrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestNotIn(List<BigDecimal> values) {
            addCriterion("overdue_principal_interest not in", values, "overduePrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_principal_interest between", value1, value2, "overduePrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andOverduePrincipalInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_principal_interest not between", value1, value2, "overduePrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestIsNull() {
            addCriterion("total_principal_interest is null");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestIsNotNull() {
            addCriterion("total_principal_interest is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestEqualTo(BigDecimal value) {
            addCriterion("total_principal_interest =", value, "totalPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestNotEqualTo(BigDecimal value) {
            addCriterion("total_principal_interest <>", value, "totalPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestGreaterThan(BigDecimal value) {
            addCriterion("total_principal_interest >", value, "totalPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_principal_interest >=", value, "totalPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestLessThan(BigDecimal value) {
            addCriterion("total_principal_interest <", value, "totalPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_principal_interest <=", value, "totalPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestIn(List<BigDecimal> values) {
            addCriterion("total_principal_interest in", values, "totalPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestNotIn(List<BigDecimal> values) {
            addCriterion("total_principal_interest not in", values, "totalPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_principal_interest between", value1, value2, "totalPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andTotalPrincipalInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_principal_interest not between", value1, value2, "totalPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andM1ValueIsNull() {
            addCriterion("m1_value is null");
            return (Criteria) this;
        }

        public Criteria andM1ValueIsNotNull() {
            addCriterion("m1_value is not null");
            return (Criteria) this;
        }

        public Criteria andM1ValueEqualTo(BigDecimal value) {
            addCriterion("m1_value =", value, "m1Value");
            return (Criteria) this;
        }

        public Criteria andM1ValueNotEqualTo(BigDecimal value) {
            addCriterion("m1_value <>", value, "m1Value");
            return (Criteria) this;
        }

        public Criteria andM1ValueGreaterThan(BigDecimal value) {
            addCriterion("m1_value >", value, "m1Value");
            return (Criteria) this;
        }

        public Criteria andM1ValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("m1_value >=", value, "m1Value");
            return (Criteria) this;
        }

        public Criteria andM1ValueLessThan(BigDecimal value) {
            addCriterion("m1_value <", value, "m1Value");
            return (Criteria) this;
        }

        public Criteria andM1ValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("m1_value <=", value, "m1Value");
            return (Criteria) this;
        }

        public Criteria andM1ValueIn(List<BigDecimal> values) {
            addCriterion("m1_value in", values, "m1Value");
            return (Criteria) this;
        }

        public Criteria andM1ValueNotIn(List<BigDecimal> values) {
            addCriterion("m1_value not in", values, "m1Value");
            return (Criteria) this;
        }

        public Criteria andM1ValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("m1_value between", value1, value2, "m1Value");
            return (Criteria) this;
        }

        public Criteria andM1ValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("m1_value not between", value1, value2, "m1Value");
            return (Criteria) this;
        }

        public Criteria andOberdueNumIsNull() {
            addCriterion("oberdue_num is null");
            return (Criteria) this;
        }

        public Criteria andOberdueNumIsNotNull() {
            addCriterion("oberdue_num is not null");
            return (Criteria) this;
        }

        public Criteria andOberdueNumEqualTo(Integer value) {
            addCriterion("oberdue_num =", value, "oberdueNum");
            return (Criteria) this;
        }

        public Criteria andOberdueNumNotEqualTo(Integer value) {
            addCriterion("oberdue_num <>", value, "oberdueNum");
            return (Criteria) this;
        }

        public Criteria andOberdueNumGreaterThan(Integer value) {
            addCriterion("oberdue_num >", value, "oberdueNum");
            return (Criteria) this;
        }

        public Criteria andOberdueNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("oberdue_num >=", value, "oberdueNum");
            return (Criteria) this;
        }

        public Criteria andOberdueNumLessThan(Integer value) {
            addCriterion("oberdue_num <", value, "oberdueNum");
            return (Criteria) this;
        }

        public Criteria andOberdueNumLessThanOrEqualTo(Integer value) {
            addCriterion("oberdue_num <=", value, "oberdueNum");
            return (Criteria) this;
        }

        public Criteria andOberdueNumIn(List<Integer> values) {
            addCriterion("oberdue_num in", values, "oberdueNum");
            return (Criteria) this;
        }

        public Criteria andOberdueNumNotIn(List<Integer> values) {
            addCriterion("oberdue_num not in", values, "oberdueNum");
            return (Criteria) this;
        }

        public Criteria andOberdueNumBetween(Integer value1, Integer value2) {
            addCriterion("oberdue_num between", value1, value2, "oberdueNum");
            return (Criteria) this;
        }

        public Criteria andOberdueNumNotBetween(Integer value1, Integer value2) {
            addCriterion("oberdue_num not between", value1, value2, "oberdueNum");
            return (Criteria) this;
        }

        public Criteria andLoanNumIsNull() {
            addCriterion("loan_num is null");
            return (Criteria) this;
        }

        public Criteria andLoanNumIsNotNull() {
            addCriterion("loan_num is not null");
            return (Criteria) this;
        }

        public Criteria andLoanNumEqualTo(Integer value) {
            addCriterion("loan_num =", value, "loanNum");
            return (Criteria) this;
        }

        public Criteria andLoanNumNotEqualTo(Integer value) {
            addCriterion("loan_num <>", value, "loanNum");
            return (Criteria) this;
        }

        public Criteria andLoanNumGreaterThan(Integer value) {
            addCriterion("loan_num >", value, "loanNum");
            return (Criteria) this;
        }

        public Criteria andLoanNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_num >=", value, "loanNum");
            return (Criteria) this;
        }

        public Criteria andLoanNumLessThan(Integer value) {
            addCriterion("loan_num <", value, "loanNum");
            return (Criteria) this;
        }

        public Criteria andLoanNumLessThanOrEqualTo(Integer value) {
            addCriterion("loan_num <=", value, "loanNum");
            return (Criteria) this;
        }

        public Criteria andLoanNumIn(List<Integer> values) {
            addCriterion("loan_num in", values, "loanNum");
            return (Criteria) this;
        }

        public Criteria andLoanNumNotIn(List<Integer> values) {
            addCriterion("loan_num not in", values, "loanNum");
            return (Criteria) this;
        }

        public Criteria andLoanNumBetween(Integer value1, Integer value2) {
            addCriterion("loan_num between", value1, value2, "loanNum");
            return (Criteria) this;
        }

        public Criteria andLoanNumNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_num not between", value1, value2, "loanNum");
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