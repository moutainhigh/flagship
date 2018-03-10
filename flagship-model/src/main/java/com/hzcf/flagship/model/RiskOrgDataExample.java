package com.hzcf.flagship.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RiskOrgDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RiskOrgDataExample() {
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

        public Criteria andOrgShortNameIsNull() {
            addCriterion("org_short_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameIsNotNull() {
            addCriterion("org_short_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameEqualTo(String value) {
            addCriterion("org_short_name =", value, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameNotEqualTo(String value) {
            addCriterion("org_short_name <>", value, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameGreaterThan(String value) {
            addCriterion("org_short_name >", value, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("org_short_name >=", value, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameLessThan(String value) {
            addCriterion("org_short_name <", value, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameLessThanOrEqualTo(String value) {
            addCriterion("org_short_name <=", value, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameLike(String value) {
            addCriterion("org_short_name like", value, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameNotLike(String value) {
            addCriterion("org_short_name not like", value, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameIn(List<String> values) {
            addCriterion("org_short_name in", values, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameNotIn(List<String> values) {
            addCriterion("org_short_name not in", values, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameBetween(String value1, String value2) {
            addCriterion("org_short_name between", value1, value2, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andOrgShortNameNotBetween(String value1, String value2) {
            addCriterion("org_short_name not between", value1, value2, "orgShortName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameIsNull() {
            addCriterion("principal_name is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameIsNotNull() {
            addCriterion("principal_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameEqualTo(String value) {
            addCriterion("principal_name =", value, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameNotEqualTo(String value) {
            addCriterion("principal_name <>", value, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameGreaterThan(String value) {
            addCriterion("principal_name >", value, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameGreaterThanOrEqualTo(String value) {
            addCriterion("principal_name >=", value, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameLessThan(String value) {
            addCriterion("principal_name <", value, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameLessThanOrEqualTo(String value) {
            addCriterion("principal_name <=", value, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameLike(String value) {
            addCriterion("principal_name like", value, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameNotLike(String value) {
            addCriterion("principal_name not like", value, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameIn(List<String> values) {
            addCriterion("principal_name in", values, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameNotIn(List<String> values) {
            addCriterion("principal_name not in", values, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameBetween(String value1, String value2) {
            addCriterion("principal_name between", value1, value2, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNameNotBetween(String value1, String value2) {
            addCriterion("principal_name not between", value1, value2, "principalName");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoIsNull() {
            addCriterion("principal_no is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoIsNotNull() {
            addCriterion("principal_no is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoEqualTo(String value) {
            addCriterion("principal_no =", value, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoNotEqualTo(String value) {
            addCriterion("principal_no <>", value, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoGreaterThan(String value) {
            addCriterion("principal_no >", value, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoGreaterThanOrEqualTo(String value) {
            addCriterion("principal_no >=", value, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoLessThan(String value) {
            addCriterion("principal_no <", value, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoLessThanOrEqualTo(String value) {
            addCriterion("principal_no <=", value, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoLike(String value) {
            addCriterion("principal_no like", value, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoNotLike(String value) {
            addCriterion("principal_no not like", value, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoIn(List<String> values) {
            addCriterion("principal_no in", values, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoNotIn(List<String> values) {
            addCriterion("principal_no not in", values, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoBetween(String value1, String value2) {
            addCriterion("principal_no between", value1, value2, "principalNo");
            return (Criteria) this;
        }

        public Criteria andPrincipalNoNotBetween(String value1, String value2) {
            addCriterion("principal_no not between", value1, value2, "principalNo");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailIsNull() {
            addCriterion("is_principal_detail is null");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailIsNotNull() {
            addCriterion("is_principal_detail is not null");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailEqualTo(String value) {
            addCriterion("is_principal_detail =", value, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailNotEqualTo(String value) {
            addCriterion("is_principal_detail <>", value, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailGreaterThan(String value) {
            addCriterion("is_principal_detail >", value, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailGreaterThanOrEqualTo(String value) {
            addCriterion("is_principal_detail >=", value, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailLessThan(String value) {
            addCriterion("is_principal_detail <", value, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailLessThanOrEqualTo(String value) {
            addCriterion("is_principal_detail <=", value, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailLike(String value) {
            addCriterion("is_principal_detail like", value, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailNotLike(String value) {
            addCriterion("is_principal_detail not like", value, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailIn(List<String> values) {
            addCriterion("is_principal_detail in", values, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailNotIn(List<String> values) {
            addCriterion("is_principal_detail not in", values, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailBetween(String value1, String value2) {
            addCriterion("is_principal_detail between", value1, value2, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andIsPrincipalDetailNotBetween(String value1, String value2) {
            addCriterion("is_principal_detail not between", value1, value2, "isPrincipalDetail");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingIsNull() {
            addCriterion("principal_send_aging is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingIsNotNull() {
            addCriterion("principal_send_aging is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingEqualTo(Integer value) {
            addCriterion("principal_send_aging =", value, "principalSendAging");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingNotEqualTo(Integer value) {
            addCriterion("principal_send_aging <>", value, "principalSendAging");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingGreaterThan(Integer value) {
            addCriterion("principal_send_aging >", value, "principalSendAging");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingGreaterThanOrEqualTo(Integer value) {
            addCriterion("principal_send_aging >=", value, "principalSendAging");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingLessThan(Integer value) {
            addCriterion("principal_send_aging <", value, "principalSendAging");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingLessThanOrEqualTo(Integer value) {
            addCriterion("principal_send_aging <=", value, "principalSendAging");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingIn(List<Integer> values) {
            addCriterion("principal_send_aging in", values, "principalSendAging");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingNotIn(List<Integer> values) {
            addCriterion("principal_send_aging not in", values, "principalSendAging");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingBetween(Integer value1, Integer value2) {
            addCriterion("principal_send_aging between", value1, value2, "principalSendAging");
            return (Criteria) this;
        }

        public Criteria andPrincipalSendAgingNotBetween(Integer value1, Integer value2) {
            addCriterion("principal_send_aging not between", value1, value2, "principalSendAging");
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

        public Criteria andIsCreditManagerDetailIsNull() {
            addCriterion("is_credit_manager_detail is null");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailIsNotNull() {
            addCriterion("is_credit_manager_detail is not null");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailEqualTo(String value) {
            addCriterion("is_credit_manager_detail =", value, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailNotEqualTo(String value) {
            addCriterion("is_credit_manager_detail <>", value, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailGreaterThan(String value) {
            addCriterion("is_credit_manager_detail >", value, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailGreaterThanOrEqualTo(String value) {
            addCriterion("is_credit_manager_detail >=", value, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailLessThan(String value) {
            addCriterion("is_credit_manager_detail <", value, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailLessThanOrEqualTo(String value) {
            addCriterion("is_credit_manager_detail <=", value, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailLike(String value) {
            addCriterion("is_credit_manager_detail like", value, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailNotLike(String value) {
            addCriterion("is_credit_manager_detail not like", value, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailIn(List<String> values) {
            addCriterion("is_credit_manager_detail in", values, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailNotIn(List<String> values) {
            addCriterion("is_credit_manager_detail not in", values, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailBetween(String value1, String value2) {
            addCriterion("is_credit_manager_detail between", value1, value2, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andIsCreditManagerDetailNotBetween(String value1, String value2) {
            addCriterion("is_credit_manager_detail not between", value1, value2, "isCreditManagerDetail");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingIsNull() {
            addCriterion("manager_send_aging is null");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingIsNotNull() {
            addCriterion("manager_send_aging is not null");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingEqualTo(Integer value) {
            addCriterion("manager_send_aging =", value, "managerSendAging");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingNotEqualTo(Integer value) {
            addCriterion("manager_send_aging <>", value, "managerSendAging");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingGreaterThan(Integer value) {
            addCriterion("manager_send_aging >", value, "managerSendAging");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingGreaterThanOrEqualTo(Integer value) {
            addCriterion("manager_send_aging >=", value, "managerSendAging");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingLessThan(Integer value) {
            addCriterion("manager_send_aging <", value, "managerSendAging");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingLessThanOrEqualTo(Integer value) {
            addCriterion("manager_send_aging <=", value, "managerSendAging");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingIn(List<Integer> values) {
            addCriterion("manager_send_aging in", values, "managerSendAging");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingNotIn(List<Integer> values) {
            addCriterion("manager_send_aging not in", values, "managerSendAging");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingBetween(Integer value1, Integer value2) {
            addCriterion("manager_send_aging between", value1, value2, "managerSendAging");
            return (Criteria) this;
        }

        public Criteria andManagerSendAgingNotBetween(Integer value1, Integer value2) {
            addCriterion("manager_send_aging not between", value1, value2, "managerSendAging");
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