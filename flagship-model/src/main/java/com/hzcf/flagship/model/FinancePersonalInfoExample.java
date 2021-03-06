package com.hzcf.flagship.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FinancePersonalInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinancePersonalInfoExample() {
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

        public Criteria andSalesNumIsNull() {
            addCriterion("sales_num is null");
            return (Criteria) this;
        }

        public Criteria andSalesNumIsNotNull() {
            addCriterion("sales_num is not null");
            return (Criteria) this;
        }

        public Criteria andSalesNumEqualTo(Integer value) {
            addCriterion("sales_num =", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumNotEqualTo(Integer value) {
            addCriterion("sales_num <>", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumGreaterThan(Integer value) {
            addCriterion("sales_num >", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_num >=", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumLessThan(Integer value) {
            addCriterion("sales_num <", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumLessThanOrEqualTo(Integer value) {
            addCriterion("sales_num <=", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumIn(List<Integer> values) {
            addCriterion("sales_num in", values, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumNotIn(List<Integer> values) {
            addCriterion("sales_num not in", values, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumBetween(Integer value1, Integer value2) {
            addCriterion("sales_num between", value1, value2, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sales_num not between", value1, value2, "salesNum");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumIsNull() {
            addCriterion("total_emp_num is null");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumIsNotNull() {
            addCriterion("total_emp_num is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumEqualTo(Integer value) {
            addCriterion("total_emp_num =", value, "totalEmpNum");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumNotEqualTo(Integer value) {
            addCriterion("total_emp_num <>", value, "totalEmpNum");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumGreaterThan(Integer value) {
            addCriterion("total_emp_num >", value, "totalEmpNum");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_emp_num >=", value, "totalEmpNum");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumLessThan(Integer value) {
            addCriterion("total_emp_num <", value, "totalEmpNum");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumLessThanOrEqualTo(Integer value) {
            addCriterion("total_emp_num <=", value, "totalEmpNum");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumIn(List<Integer> values) {
            addCriterion("total_emp_num in", values, "totalEmpNum");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumNotIn(List<Integer> values) {
            addCriterion("total_emp_num not in", values, "totalEmpNum");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumBetween(Integer value1, Integer value2) {
            addCriterion("total_emp_num between", value1, value2, "totalEmpNum");
            return (Criteria) this;
        }

        public Criteria andTotalEmpNumNotBetween(Integer value1, Integer value2) {
            addCriterion("total_emp_num not between", value1, value2, "totalEmpNum");
            return (Criteria) this;
        }

        public Criteria andTeamNumIsNull() {
            addCriterion("team_num is null");
            return (Criteria) this;
        }

        public Criteria andTeamNumIsNotNull() {
            addCriterion("team_num is not null");
            return (Criteria) this;
        }

        public Criteria andTeamNumEqualTo(Integer value) {
            addCriterion("team_num =", value, "teamNum");
            return (Criteria) this;
        }

        public Criteria andTeamNumNotEqualTo(Integer value) {
            addCriterion("team_num <>", value, "teamNum");
            return (Criteria) this;
        }

        public Criteria andTeamNumGreaterThan(Integer value) {
            addCriterion("team_num >", value, "teamNum");
            return (Criteria) this;
        }

        public Criteria andTeamNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_num >=", value, "teamNum");
            return (Criteria) this;
        }

        public Criteria andTeamNumLessThan(Integer value) {
            addCriterion("team_num <", value, "teamNum");
            return (Criteria) this;
        }

        public Criteria andTeamNumLessThanOrEqualTo(Integer value) {
            addCriterion("team_num <=", value, "teamNum");
            return (Criteria) this;
        }

        public Criteria andTeamNumIn(List<Integer> values) {
            addCriterion("team_num in", values, "teamNum");
            return (Criteria) this;
        }

        public Criteria andTeamNumNotIn(List<Integer> values) {
            addCriterion("team_num not in", values, "teamNum");
            return (Criteria) this;
        }

        public Criteria andTeamNumBetween(Integer value1, Integer value2) {
            addCriterion("team_num between", value1, value2, "teamNum");
            return (Criteria) this;
        }

        public Criteria andTeamNumNotBetween(Integer value1, Integer value2) {
            addCriterion("team_num not between", value1, value2, "teamNum");
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

        public Criteria andMonthPlanIsNull() {
            addCriterion("month_plan is null");
            return (Criteria) this;
        }

        public Criteria andMonthPlanIsNotNull() {
            addCriterion("month_plan is not null");
            return (Criteria) this;
        }

        public Criteria andMonthPlanEqualTo(Integer value) {
            addCriterion("month_plan =", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanNotEqualTo(Integer value) {
            addCriterion("month_plan <>", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanGreaterThan(Integer value) {
            addCriterion("month_plan >", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanGreaterThanOrEqualTo(Integer value) {
            addCriterion("month_plan >=", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanLessThan(Integer value) {
            addCriterion("month_plan <", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanLessThanOrEqualTo(Integer value) {
            addCriterion("month_plan <=", value, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanIn(List<Integer> values) {
            addCriterion("month_plan in", values, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanNotIn(List<Integer> values) {
            addCriterion("month_plan not in", values, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanBetween(Integer value1, Integer value2) {
            addCriterion("month_plan between", value1, value2, "monthPlan");
            return (Criteria) this;
        }

        public Criteria andMonthPlanNotBetween(Integer value1, Integer value2) {
            addCriterion("month_plan not between", value1, value2, "monthPlan");
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