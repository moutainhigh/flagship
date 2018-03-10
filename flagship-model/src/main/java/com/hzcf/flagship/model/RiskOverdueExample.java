package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RiskOverdueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RiskOverdueExample() {
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

        public Criteria andTeamManagerIsNull() {
            addCriterion("team_manager is null");
            return (Criteria) this;
        }

        public Criteria andTeamManagerIsNotNull() {
            addCriterion("team_manager is not null");
            return (Criteria) this;
        }

        public Criteria andTeamManagerEqualTo(String value) {
            addCriterion("team_manager =", value, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerNotEqualTo(String value) {
            addCriterion("team_manager <>", value, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerGreaterThan(String value) {
            addCriterion("team_manager >", value, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerGreaterThanOrEqualTo(String value) {
            addCriterion("team_manager >=", value, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerLessThan(String value) {
            addCriterion("team_manager <", value, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerLessThanOrEqualTo(String value) {
            addCriterion("team_manager <=", value, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerLike(String value) {
            addCriterion("team_manager like", value, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerNotLike(String value) {
            addCriterion("team_manager not like", value, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerIn(List<String> values) {
            addCriterion("team_manager in", values, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerNotIn(List<String> values) {
            addCriterion("team_manager not in", values, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerBetween(String value1, String value2) {
            addCriterion("team_manager between", value1, value2, "teamManager");
            return (Criteria) this;
        }

        public Criteria andTeamManagerNotBetween(String value1, String value2) {
            addCriterion("team_manager not between", value1, value2, "teamManager");
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

        public Criteria andBeginningPeriodAgingIsNull() {
            addCriterion("beginning_period_aging is null");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingIsNotNull() {
            addCriterion("beginning_period_aging is not null");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingEqualTo(Integer value) {
            addCriterion("beginning_period_aging =", value, "beginningPeriodAging");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingNotEqualTo(Integer value) {
            addCriterion("beginning_period_aging <>", value, "beginningPeriodAging");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingGreaterThan(Integer value) {
            addCriterion("beginning_period_aging >", value, "beginningPeriodAging");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingGreaterThanOrEqualTo(Integer value) {
            addCriterion("beginning_period_aging >=", value, "beginningPeriodAging");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingLessThan(Integer value) {
            addCriterion("beginning_period_aging <", value, "beginningPeriodAging");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingLessThanOrEqualTo(Integer value) {
            addCriterion("beginning_period_aging <=", value, "beginningPeriodAging");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingIn(List<Integer> values) {
            addCriterion("beginning_period_aging in", values, "beginningPeriodAging");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingNotIn(List<Integer> values) {
            addCriterion("beginning_period_aging not in", values, "beginningPeriodAging");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingBetween(Integer value1, Integer value2) {
            addCriterion("beginning_period_aging between", value1, value2, "beginningPeriodAging");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodAgingNotBetween(Integer value1, Integer value2) {
            addCriterion("beginning_period_aging not between", value1, value2, "beginningPeriodAging");
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

        public Criteria andBeginningPeriodFinalIsNull() {
            addCriterion("beginning_period_final is null");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalIsNotNull() {
            addCriterion("beginning_period_final is not null");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalEqualTo(Short value) {
            addCriterion("beginning_period_final =", value, "beginningPeriodFinal");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalNotEqualTo(Short value) {
            addCriterion("beginning_period_final <>", value, "beginningPeriodFinal");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalGreaterThan(Short value) {
            addCriterion("beginning_period_final >", value, "beginningPeriodFinal");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalGreaterThanOrEqualTo(Short value) {
            addCriterion("beginning_period_final >=", value, "beginningPeriodFinal");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalLessThan(Short value) {
            addCriterion("beginning_period_final <", value, "beginningPeriodFinal");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalLessThanOrEqualTo(Short value) {
            addCriterion("beginning_period_final <=", value, "beginningPeriodFinal");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalIn(List<Short> values) {
            addCriterion("beginning_period_final in", values, "beginningPeriodFinal");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalNotIn(List<Short> values) {
            addCriterion("beginning_period_final not in", values, "beginningPeriodFinal");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalBetween(Short value1, Short value2) {
            addCriterion("beginning_period_final between", value1, value2, "beginningPeriodFinal");
            return (Criteria) this;
        }

        public Criteria andBeginningPeriodFinalNotBetween(Short value1, Short value2) {
            addCriterion("beginning_period_final not between", value1, value2, "beginningPeriodFinal");
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

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andOverdueDateIsNull() {
            addCriterion("overdue_date is null");
            return (Criteria) this;
        }

        public Criteria andOverdueDateIsNotNull() {
            addCriterion("overdue_date is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueDateEqualTo(String value) {
            addCriterion("overdue_date =", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateNotEqualTo(String value) {
            addCriterion("overdue_date <>", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateGreaterThan(String value) {
            addCriterion("overdue_date >", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateGreaterThanOrEqualTo(String value) {
            addCriterion("overdue_date >=", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateLessThan(String value) {
            addCriterion("overdue_date <", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateLessThanOrEqualTo(String value) {
            addCriterion("overdue_date <=", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateLike(String value) {
            addCriterion("overdue_date like", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateNotLike(String value) {
            addCriterion("overdue_date not like", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateIn(List<String> values) {
            addCriterion("overdue_date in", values, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateNotIn(List<String> values) {
            addCriterion("overdue_date not in", values, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateBetween(String value1, String value2) {
            addCriterion("overdue_date between", value1, value2, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateNotBetween(String value1, String value2) {
            addCriterion("overdue_date not between", value1, value2, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andNowAgingIsNull() {
            addCriterion("now_aging is null");
            return (Criteria) this;
        }

        public Criteria andNowAgingIsNotNull() {
            addCriterion("now_aging is not null");
            return (Criteria) this;
        }

        public Criteria andNowAgingEqualTo(Integer value) {
            addCriterion("now_aging =", value, "nowAging");
            return (Criteria) this;
        }

        public Criteria andNowAgingNotEqualTo(Integer value) {
            addCriterion("now_aging <>", value, "nowAging");
            return (Criteria) this;
        }

        public Criteria andNowAgingGreaterThan(Integer value) {
            addCriterion("now_aging >", value, "nowAging");
            return (Criteria) this;
        }

        public Criteria andNowAgingGreaterThanOrEqualTo(Integer value) {
            addCriterion("now_aging >=", value, "nowAging");
            return (Criteria) this;
        }

        public Criteria andNowAgingLessThan(Integer value) {
            addCriterion("now_aging <", value, "nowAging");
            return (Criteria) this;
        }

        public Criteria andNowAgingLessThanOrEqualTo(Integer value) {
            addCriterion("now_aging <=", value, "nowAging");
            return (Criteria) this;
        }

        public Criteria andNowAgingIn(List<Integer> values) {
            addCriterion("now_aging in", values, "nowAging");
            return (Criteria) this;
        }

        public Criteria andNowAgingNotIn(List<Integer> values) {
            addCriterion("now_aging not in", values, "nowAging");
            return (Criteria) this;
        }

        public Criteria andNowAgingBetween(Integer value1, Integer value2) {
            addCriterion("now_aging between", value1, value2, "nowAging");
            return (Criteria) this;
        }

        public Criteria andNowAgingNotBetween(Integer value1, Integer value2) {
            addCriterion("now_aging not between", value1, value2, "nowAging");
            return (Criteria) this;
        }

        public Criteria andIsReportIsNull() {
            addCriterion("is_report is null");
            return (Criteria) this;
        }

        public Criteria andIsReportIsNotNull() {
            addCriterion("is_report is not null");
            return (Criteria) this;
        }

        public Criteria andIsReportEqualTo(String value) {
            addCriterion("is_report =", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportNotEqualTo(String value) {
            addCriterion("is_report <>", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportGreaterThan(String value) {
            addCriterion("is_report >", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportGreaterThanOrEqualTo(String value) {
            addCriterion("is_report >=", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportLessThan(String value) {
            addCriterion("is_report <", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportLessThanOrEqualTo(String value) {
            addCriterion("is_report <=", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportLike(String value) {
            addCriterion("is_report like", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportNotLike(String value) {
            addCriterion("is_report not like", value, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportIn(List<String> values) {
            addCriterion("is_report in", values, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportNotIn(List<String> values) {
            addCriterion("is_report not in", values, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportBetween(String value1, String value2) {
            addCriterion("is_report between", value1, value2, "isReport");
            return (Criteria) this;
        }

        public Criteria andIsReportNotBetween(String value1, String value2) {
            addCriterion("is_report not between", value1, value2, "isReport");
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