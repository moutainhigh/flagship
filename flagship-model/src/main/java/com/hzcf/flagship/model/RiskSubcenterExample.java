package com.hzcf.flagship.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RiskSubcenterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RiskSubcenterExample() {
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

        public Criteria andSubcenterNoIsNull() {
            addCriterion("subcenter_no is null");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoIsNotNull() {
            addCriterion("subcenter_no is not null");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoEqualTo(String value) {
            addCriterion("subcenter_no =", value, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoNotEqualTo(String value) {
            addCriterion("subcenter_no <>", value, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoGreaterThan(String value) {
            addCriterion("subcenter_no >", value, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoGreaterThanOrEqualTo(String value) {
            addCriterion("subcenter_no >=", value, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoLessThan(String value) {
            addCriterion("subcenter_no <", value, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoLessThanOrEqualTo(String value) {
            addCriterion("subcenter_no <=", value, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoLike(String value) {
            addCriterion("subcenter_no like", value, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoNotLike(String value) {
            addCriterion("subcenter_no not like", value, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoIn(List<String> values) {
            addCriterion("subcenter_no in", values, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoNotIn(List<String> values) {
            addCriterion("subcenter_no not in", values, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoBetween(String value1, String value2) {
            addCriterion("subcenter_no between", value1, value2, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andSubcenterNoNotBetween(String value1, String value2) {
            addCriterion("subcenter_no not between", value1, value2, "subcenterNo");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNull() {
            addCriterion("short_name is null");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNotNull() {
            addCriterion("short_name is not null");
            return (Criteria) this;
        }

        public Criteria andShortNameEqualTo(String value) {
            addCriterion("short_name =", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotEqualTo(String value) {
            addCriterion("short_name <>", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThan(String value) {
            addCriterion("short_name >", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("short_name >=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThan(String value) {
            addCriterion("short_name <", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThanOrEqualTo(String value) {
            addCriterion("short_name <=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLike(String value) {
            addCriterion("short_name like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotLike(String value) {
            addCriterion("short_name not like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameIn(List<String> values) {
            addCriterion("short_name in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotIn(List<String> values) {
            addCriterion("short_name not in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameBetween(String value1, String value2) {
            addCriterion("short_name between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotBetween(String value1, String value2) {
            addCriterion("short_name not between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNull() {
            addCriterion("manager_name is null");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNotNull() {
            addCriterion("manager_name is not null");
            return (Criteria) this;
        }

        public Criteria andManagerNameEqualTo(String value) {
            addCriterion("manager_name =", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotEqualTo(String value) {
            addCriterion("manager_name <>", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThan(String value) {
            addCriterion("manager_name >", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("manager_name >=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThan(String value) {
            addCriterion("manager_name <", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThanOrEqualTo(String value) {
            addCriterion("manager_name <=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLike(String value) {
            addCriterion("manager_name like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotLike(String value) {
            addCriterion("manager_name not like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameIn(List<String> values) {
            addCriterion("manager_name in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotIn(List<String> values) {
            addCriterion("manager_name not in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameBetween(String value1, String value2) {
            addCriterion("manager_name between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotBetween(String value1, String value2) {
            addCriterion("manager_name not between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNoIsNull() {
            addCriterion("manager_no is null");
            return (Criteria) this;
        }

        public Criteria andManagerNoIsNotNull() {
            addCriterion("manager_no is not null");
            return (Criteria) this;
        }

        public Criteria andManagerNoEqualTo(String value) {
            addCriterion("manager_no =", value, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoNotEqualTo(String value) {
            addCriterion("manager_no <>", value, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoGreaterThan(String value) {
            addCriterion("manager_no >", value, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoGreaterThanOrEqualTo(String value) {
            addCriterion("manager_no >=", value, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoLessThan(String value) {
            addCriterion("manager_no <", value, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoLessThanOrEqualTo(String value) {
            addCriterion("manager_no <=", value, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoLike(String value) {
            addCriterion("manager_no like", value, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoNotLike(String value) {
            addCriterion("manager_no not like", value, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoIn(List<String> values) {
            addCriterion("manager_no in", values, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoNotIn(List<String> values) {
            addCriterion("manager_no not in", values, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoBetween(String value1, String value2) {
            addCriterion("manager_no between", value1, value2, "managerNo");
            return (Criteria) this;
        }

        public Criteria andManagerNoNotBetween(String value1, String value2) {
            addCriterion("manager_no not between", value1, value2, "managerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameIsNull() {
            addCriterion("credit_manager_name is null");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameIsNotNull() {
            addCriterion("credit_manager_name is not null");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameEqualTo(String value) {
            addCriterion("credit_manager_name =", value, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameNotEqualTo(String value) {
            addCriterion("credit_manager_name <>", value, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameGreaterThan(String value) {
            addCriterion("credit_manager_name >", value, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("credit_manager_name >=", value, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameLessThan(String value) {
            addCriterion("credit_manager_name <", value, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameLessThanOrEqualTo(String value) {
            addCriterion("credit_manager_name <=", value, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameLike(String value) {
            addCriterion("credit_manager_name like", value, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameNotLike(String value) {
            addCriterion("credit_manager_name not like", value, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameIn(List<String> values) {
            addCriterion("credit_manager_name in", values, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameNotIn(List<String> values) {
            addCriterion("credit_manager_name not in", values, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameBetween(String value1, String value2) {
            addCriterion("credit_manager_name between", value1, value2, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNameNotBetween(String value1, String value2) {
            addCriterion("credit_manager_name not between", value1, value2, "creditManagerName");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoIsNull() {
            addCriterion("credit_manager_no is null");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoIsNotNull() {
            addCriterion("credit_manager_no is not null");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoEqualTo(String value) {
            addCriterion("credit_manager_no =", value, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoNotEqualTo(String value) {
            addCriterion("credit_manager_no <>", value, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoGreaterThan(String value) {
            addCriterion("credit_manager_no >", value, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoGreaterThanOrEqualTo(String value) {
            addCriterion("credit_manager_no >=", value, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoLessThan(String value) {
            addCriterion("credit_manager_no <", value, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoLessThanOrEqualTo(String value) {
            addCriterion("credit_manager_no <=", value, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoLike(String value) {
            addCriterion("credit_manager_no like", value, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoNotLike(String value) {
            addCriterion("credit_manager_no not like", value, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoIn(List<String> values) {
            addCriterion("credit_manager_no in", values, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoNotIn(List<String> values) {
            addCriterion("credit_manager_no not in", values, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoBetween(String value1, String value2) {
            addCriterion("credit_manager_no between", value1, value2, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andCreditManagerNoNotBetween(String value1, String value2) {
            addCriterion("credit_manager_no not between", value1, value2, "creditManagerNo");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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