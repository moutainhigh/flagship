package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RiskCm1DetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RiskCm1DetailExample() {
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

        public Criteria andContractNoIsNull() {
            addCriterion("contract_no is null");
            return (Criteria) this;
        }

        public Criteria andContractNoIsNotNull() {
            addCriterion("contract_no is not null");
            return (Criteria) this;
        }

        public Criteria andContractNoEqualTo(String value) {
            addCriterion("contract_no =", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotEqualTo(String value) {
            addCriterion("contract_no <>", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThan(String value) {
            addCriterion("contract_no >", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThanOrEqualTo(String value) {
            addCriterion("contract_no >=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThan(String value) {
            addCriterion("contract_no <", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThanOrEqualTo(String value) {
            addCriterion("contract_no <=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLike(String value) {
            addCriterion("contract_no like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotLike(String value) {
            addCriterion("contract_no not like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoIn(List<String> values) {
            addCriterion("contract_no in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotIn(List<String> values) {
            addCriterion("contract_no not in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoBetween(String value1, String value2) {
            addCriterion("contract_no between", value1, value2, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotBetween(String value1, String value2) {
            addCriterion("contract_no not between", value1, value2, "contractNo");
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

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
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

        public Criteria andIsOverdueIsNull() {
            addCriterion("is_overdue is null");
            return (Criteria) this;
        }

        public Criteria andIsOverdueIsNotNull() {
            addCriterion("is_overdue is not null");
            return (Criteria) this;
        }

        public Criteria andIsOverdueEqualTo(Integer value) {
            addCriterion("is_overdue =", value, "isOverdue");
            return (Criteria) this;
        }

        public Criteria andIsOverdueNotEqualTo(Integer value) {
            addCriterion("is_overdue <>", value, "isOverdue");
            return (Criteria) this;
        }

        public Criteria andIsOverdueGreaterThan(Integer value) {
            addCriterion("is_overdue >", value, "isOverdue");
            return (Criteria) this;
        }

        public Criteria andIsOverdueGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_overdue >=", value, "isOverdue");
            return (Criteria) this;
        }

        public Criteria andIsOverdueLessThan(Integer value) {
            addCriterion("is_overdue <", value, "isOverdue");
            return (Criteria) this;
        }

        public Criteria andIsOverdueLessThanOrEqualTo(Integer value) {
            addCriterion("is_overdue <=", value, "isOverdue");
            return (Criteria) this;
        }

        public Criteria andIsOverdueIn(List<Integer> values) {
            addCriterion("is_overdue in", values, "isOverdue");
            return (Criteria) this;
        }

        public Criteria andIsOverdueNotIn(List<Integer> values) {
            addCriterion("is_overdue not in", values, "isOverdue");
            return (Criteria) this;
        }

        public Criteria andIsOverdueBetween(Integer value1, Integer value2) {
            addCriterion("is_overdue between", value1, value2, "isOverdue");
            return (Criteria) this;
        }

        public Criteria andIsOverdueNotBetween(Integer value1, Integer value2) {
            addCriterion("is_overdue not between", value1, value2, "isOverdue");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameIsNull() {
            addCriterion("business_unit_name is null");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameIsNotNull() {
            addCriterion("business_unit_name is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameEqualTo(String value) {
            addCriterion("business_unit_name =", value, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameNotEqualTo(String value) {
            addCriterion("business_unit_name <>", value, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameGreaterThan(String value) {
            addCriterion("business_unit_name >", value, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("business_unit_name >=", value, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameLessThan(String value) {
            addCriterion("business_unit_name <", value, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameLessThanOrEqualTo(String value) {
            addCriterion("business_unit_name <=", value, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameLike(String value) {
            addCriterion("business_unit_name like", value, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameNotLike(String value) {
            addCriterion("business_unit_name not like", value, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameIn(List<String> values) {
            addCriterion("business_unit_name in", values, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameNotIn(List<String> values) {
            addCriterion("business_unit_name not in", values, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameBetween(String value1, String value2) {
            addCriterion("business_unit_name between", value1, value2, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNameNotBetween(String value1, String value2) {
            addCriterion("business_unit_name not between", value1, value2, "businessUnitName");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoIsNull() {
            addCriterion("business_unit_no is null");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoIsNotNull() {
            addCriterion("business_unit_no is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoEqualTo(String value) {
            addCriterion("business_unit_no =", value, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoNotEqualTo(String value) {
            addCriterion("business_unit_no <>", value, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoGreaterThan(String value) {
            addCriterion("business_unit_no >", value, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoGreaterThanOrEqualTo(String value) {
            addCriterion("business_unit_no >=", value, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoLessThan(String value) {
            addCriterion("business_unit_no <", value, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoLessThanOrEqualTo(String value) {
            addCriterion("business_unit_no <=", value, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoLike(String value) {
            addCriterion("business_unit_no like", value, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoNotLike(String value) {
            addCriterion("business_unit_no not like", value, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoIn(List<String> values) {
            addCriterion("business_unit_no in", values, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoNotIn(List<String> values) {
            addCriterion("business_unit_no not in", values, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoBetween(String value1, String value2) {
            addCriterion("business_unit_no between", value1, value2, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andBusinessUnitNoNotBetween(String value1, String value2) {
            addCriterion("business_unit_no not between", value1, value2, "businessUnitNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNameIsNull() {
            addCriterion("district_name is null");
            return (Criteria) this;
        }

        public Criteria andDistrictNameIsNotNull() {
            addCriterion("district_name is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictNameEqualTo(String value) {
            addCriterion("district_name =", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotEqualTo(String value) {
            addCriterion("district_name <>", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameGreaterThan(String value) {
            addCriterion("district_name >", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameGreaterThanOrEqualTo(String value) {
            addCriterion("district_name >=", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameLessThan(String value) {
            addCriterion("district_name <", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameLessThanOrEqualTo(String value) {
            addCriterion("district_name <=", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameLike(String value) {
            addCriterion("district_name like", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotLike(String value) {
            addCriterion("district_name not like", value, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameIn(List<String> values) {
            addCriterion("district_name in", values, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotIn(List<String> values) {
            addCriterion("district_name not in", values, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameBetween(String value1, String value2) {
            addCriterion("district_name between", value1, value2, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNameNotBetween(String value1, String value2) {
            addCriterion("district_name not between", value1, value2, "districtName");
            return (Criteria) this;
        }

        public Criteria andDistrictNoIsNull() {
            addCriterion("district_no is null");
            return (Criteria) this;
        }

        public Criteria andDistrictNoIsNotNull() {
            addCriterion("district_no is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictNoEqualTo(String value) {
            addCriterion("district_no =", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoNotEqualTo(String value) {
            addCriterion("district_no <>", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoGreaterThan(String value) {
            addCriterion("district_no >", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoGreaterThanOrEqualTo(String value) {
            addCriterion("district_no >=", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoLessThan(String value) {
            addCriterion("district_no <", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoLessThanOrEqualTo(String value) {
            addCriterion("district_no <=", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoLike(String value) {
            addCriterion("district_no like", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoNotLike(String value) {
            addCriterion("district_no not like", value, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoIn(List<String> values) {
            addCriterion("district_no in", values, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoNotIn(List<String> values) {
            addCriterion("district_no not in", values, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoBetween(String value1, String value2) {
            addCriterion("district_no between", value1, value2, "districtNo");
            return (Criteria) this;
        }

        public Criteria andDistrictNoNotBetween(String value1, String value2) {
            addCriterion("district_no not between", value1, value2, "districtNo");
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

        public Criteria andClientManagerIsNull() {
            addCriterion("client_manager is null");
            return (Criteria) this;
        }

        public Criteria andClientManagerIsNotNull() {
            addCriterion("client_manager is not null");
            return (Criteria) this;
        }

        public Criteria andClientManagerEqualTo(String value) {
            addCriterion("client_manager =", value, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerNotEqualTo(String value) {
            addCriterion("client_manager <>", value, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerGreaterThan(String value) {
            addCriterion("client_manager >", value, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerGreaterThanOrEqualTo(String value) {
            addCriterion("client_manager >=", value, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerLessThan(String value) {
            addCriterion("client_manager <", value, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerLessThanOrEqualTo(String value) {
            addCriterion("client_manager <=", value, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerLike(String value) {
            addCriterion("client_manager like", value, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerNotLike(String value) {
            addCriterion("client_manager not like", value, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerIn(List<String> values) {
            addCriterion("client_manager in", values, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerNotIn(List<String> values) {
            addCriterion("client_manager not in", values, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerBetween(String value1, String value2) {
            addCriterion("client_manager between", value1, value2, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientManagerNotBetween(String value1, String value2) {
            addCriterion("client_manager not between", value1, value2, "clientManager");
            return (Criteria) this;
        }

        public Criteria andClientNameIsNull() {
            addCriterion("client_name is null");
            return (Criteria) this;
        }

        public Criteria andClientNameIsNotNull() {
            addCriterion("client_name is not null");
            return (Criteria) this;
        }

        public Criteria andClientNameEqualTo(String value) {
            addCriterion("client_name =", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotEqualTo(String value) {
            addCriterion("client_name <>", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameGreaterThan(String value) {
            addCriterion("client_name >", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameGreaterThanOrEqualTo(String value) {
            addCriterion("client_name >=", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameLessThan(String value) {
            addCriterion("client_name <", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameLessThanOrEqualTo(String value) {
            addCriterion("client_name <=", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameLike(String value) {
            addCriterion("client_name like", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotLike(String value) {
            addCriterion("client_name not like", value, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameIn(List<String> values) {
            addCriterion("client_name in", values, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotIn(List<String> values) {
            addCriterion("client_name not in", values, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameBetween(String value1, String value2) {
            addCriterion("client_name between", value1, value2, "clientName");
            return (Criteria) this;
        }

        public Criteria andClientNameNotBetween(String value1, String value2) {
            addCriterion("client_name not between", value1, value2, "clientName");
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

        public Criteria andPeriodsIsNull() {
            addCriterion("periods is null");
            return (Criteria) this;
        }

        public Criteria andPeriodsIsNotNull() {
            addCriterion("periods is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodsEqualTo(Integer value) {
            addCriterion("periods =", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsNotEqualTo(Integer value) {
            addCriterion("periods <>", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsGreaterThan(Integer value) {
            addCriterion("periods >", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsGreaterThanOrEqualTo(Integer value) {
            addCriterion("periods >=", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsLessThan(Integer value) {
            addCriterion("periods <", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsLessThanOrEqualTo(Integer value) {
            addCriterion("periods <=", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsIn(List<Integer> values) {
            addCriterion("periods in", values, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsNotIn(List<Integer> values) {
            addCriterion("periods not in", values, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsBetween(Integer value1, Integer value2) {
            addCriterion("periods between", value1, value2, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsNotBetween(Integer value1, Integer value2) {
            addCriterion("periods not between", value1, value2, "periods");
            return (Criteria) this;
        }

        public Criteria andHandAmountIsNull() {
            addCriterion("hand_amount is null");
            return (Criteria) this;
        }

        public Criteria andHandAmountIsNotNull() {
            addCriterion("hand_amount is not null");
            return (Criteria) this;
        }

        public Criteria andHandAmountEqualTo(BigDecimal value) {
            addCriterion("hand_amount =", value, "handAmount");
            return (Criteria) this;
        }

        public Criteria andHandAmountNotEqualTo(BigDecimal value) {
            addCriterion("hand_amount <>", value, "handAmount");
            return (Criteria) this;
        }

        public Criteria andHandAmountGreaterThan(BigDecimal value) {
            addCriterion("hand_amount >", value, "handAmount");
            return (Criteria) this;
        }

        public Criteria andHandAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("hand_amount >=", value, "handAmount");
            return (Criteria) this;
        }

        public Criteria andHandAmountLessThan(BigDecimal value) {
            addCriterion("hand_amount <", value, "handAmount");
            return (Criteria) this;
        }

        public Criteria andHandAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("hand_amount <=", value, "handAmount");
            return (Criteria) this;
        }

        public Criteria andHandAmountIn(List<BigDecimal> values) {
            addCriterion("hand_amount in", values, "handAmount");
            return (Criteria) this;
        }

        public Criteria andHandAmountNotIn(List<BigDecimal> values) {
            addCriterion("hand_amount not in", values, "handAmount");
            return (Criteria) this;
        }

        public Criteria andHandAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hand_amount between", value1, value2, "handAmount");
            return (Criteria) this;
        }

        public Criteria andHandAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hand_amount not between", value1, value2, "handAmount");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateIsNull() {
            addCriterion("first_repayment_date is null");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateIsNotNull() {
            addCriterion("first_repayment_date is not null");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateEqualTo(Date value) {
            addCriterionForJDBCDate("first_repayment_date =", value, "firstRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("first_repayment_date <>", value, "firstRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateGreaterThan(Date value) {
            addCriterionForJDBCDate("first_repayment_date >", value, "firstRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("first_repayment_date >=", value, "firstRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateLessThan(Date value) {
            addCriterionForJDBCDate("first_repayment_date <", value, "firstRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("first_repayment_date <=", value, "firstRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateIn(List<Date> values) {
            addCriterionForJDBCDate("first_repayment_date in", values, "firstRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("first_repayment_date not in", values, "firstRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("first_repayment_date between", value1, value2, "firstRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andFirstRepaymentDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("first_repayment_date not between", value1, value2, "firstRepaymentDate");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestIsNull() {
            addCriterion("now_principal_interest is null");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestIsNotNull() {
            addCriterion("now_principal_interest is not null");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestEqualTo(BigDecimal value) {
            addCriterion("now_principal_interest =", value, "nowPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestNotEqualTo(BigDecimal value) {
            addCriterion("now_principal_interest <>", value, "nowPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestGreaterThan(BigDecimal value) {
            addCriterion("now_principal_interest >", value, "nowPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("now_principal_interest >=", value, "nowPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestLessThan(BigDecimal value) {
            addCriterion("now_principal_interest <", value, "nowPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("now_principal_interest <=", value, "nowPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestIn(List<BigDecimal> values) {
            addCriterion("now_principal_interest in", values, "nowPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestNotIn(List<BigDecimal> values) {
            addCriterion("now_principal_interest not in", values, "nowPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("now_principal_interest between", value1, value2, "nowPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andNowPrincipalInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("now_principal_interest not between", value1, value2, "nowPrincipalInterest");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentIsNull() {
            addCriterion("month_repayment is null");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentIsNotNull() {
            addCriterion("month_repayment is not null");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentEqualTo(BigDecimal value) {
            addCriterion("month_repayment =", value, "monthRepayment");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentNotEqualTo(BigDecimal value) {
            addCriterion("month_repayment <>", value, "monthRepayment");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentGreaterThan(BigDecimal value) {
            addCriterion("month_repayment >", value, "monthRepayment");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("month_repayment >=", value, "monthRepayment");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentLessThan(BigDecimal value) {
            addCriterion("month_repayment <", value, "monthRepayment");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("month_repayment <=", value, "monthRepayment");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentIn(List<BigDecimal> values) {
            addCriterion("month_repayment in", values, "monthRepayment");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentNotIn(List<BigDecimal> values) {
            addCriterion("month_repayment not in", values, "monthRepayment");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("month_repayment between", value1, value2, "monthRepayment");
            return (Criteria) this;
        }

        public Criteria andMonthRepaymentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("month_repayment not between", value1, value2, "monthRepayment");
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