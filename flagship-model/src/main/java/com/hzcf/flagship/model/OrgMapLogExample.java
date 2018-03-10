package com.hzcf.flagship.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrgMapLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrgMapLogExample() {
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

        public Criteria andBiOrgNameIsNull() {
            addCriterion("bi_org_name is null");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameIsNotNull() {
            addCriterion("bi_org_name is not null");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameEqualTo(String value) {
            addCriterion("bi_org_name =", value, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameNotEqualTo(String value) {
            addCriterion("bi_org_name <>", value, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameGreaterThan(String value) {
            addCriterion("bi_org_name >", value, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("bi_org_name >=", value, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameLessThan(String value) {
            addCriterion("bi_org_name <", value, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameLessThanOrEqualTo(String value) {
            addCriterion("bi_org_name <=", value, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameLike(String value) {
            addCriterion("bi_org_name like", value, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameNotLike(String value) {
            addCriterion("bi_org_name not like", value, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameIn(List<String> values) {
            addCriterion("bi_org_name in", values, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameNotIn(List<String> values) {
            addCriterion("bi_org_name not in", values, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameBetween(String value1, String value2) {
            addCriterion("bi_org_name between", value1, value2, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andBiOrgNameNotBetween(String value1, String value2) {
            addCriterion("bi_org_name not between", value1, value2, "biOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameIsNull() {
            addCriterion("roster_org_name is null");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameIsNotNull() {
            addCriterion("roster_org_name is not null");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameEqualTo(String value) {
            addCriterion("roster_org_name =", value, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameNotEqualTo(String value) {
            addCriterion("roster_org_name <>", value, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameGreaterThan(String value) {
            addCriterion("roster_org_name >", value, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("roster_org_name >=", value, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameLessThan(String value) {
            addCriterion("roster_org_name <", value, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameLessThanOrEqualTo(String value) {
            addCriterion("roster_org_name <=", value, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameLike(String value) {
            addCriterion("roster_org_name like", value, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameNotLike(String value) {
            addCriterion("roster_org_name not like", value, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameIn(List<String> values) {
            addCriterion("roster_org_name in", values, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameNotIn(List<String> values) {
            addCriterion("roster_org_name not in", values, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameBetween(String value1, String value2) {
            addCriterion("roster_org_name between", value1, value2, "rosterOrgName");
            return (Criteria) this;
        }

        public Criteria andRosterOrgNameNotBetween(String value1, String value2) {
            addCriterion("roster_org_name not between", value1, value2, "rosterOrgName");
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