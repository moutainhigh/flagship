package com.hzcf.flagship.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MoneymgrRosterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MoneymgrRosterExample() {
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

        public Criteria andEmployeeNoIsNull() {
            addCriterion("employee_no is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoIsNotNull() {
            addCriterion("employee_no is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoEqualTo(String value) {
            addCriterion("employee_no =", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotEqualTo(String value) {
            addCriterion("employee_no <>", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoGreaterThan(String value) {
            addCriterion("employee_no >", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoGreaterThanOrEqualTo(String value) {
            addCriterion("employee_no >=", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoLessThan(String value) {
            addCriterion("employee_no <", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoLessThanOrEqualTo(String value) {
            addCriterion("employee_no <=", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoLike(String value) {
            addCriterion("employee_no like", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotLike(String value) {
            addCriterion("employee_no not like", value, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoIn(List<String> values) {
            addCriterion("employee_no in", values, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotIn(List<String> values) {
            addCriterion("employee_no not in", values, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoBetween(String value1, String value2) {
            addCriterion("employee_no between", value1, value2, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andEmployeeNoNotBetween(String value1, String value2) {
            addCriterion("employee_no not between", value1, value2, "employeeNo");
            return (Criteria) this;
        }

        public Criteria andReferralCodeIsNull() {
            addCriterion("referral_code is null");
            return (Criteria) this;
        }

        public Criteria andReferralCodeIsNotNull() {
            addCriterion("referral_code is not null");
            return (Criteria) this;
        }

        public Criteria andReferralCodeEqualTo(String value) {
            addCriterion("referral_code =", value, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeNotEqualTo(String value) {
            addCriterion("referral_code <>", value, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeGreaterThan(String value) {
            addCriterion("referral_code >", value, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeGreaterThanOrEqualTo(String value) {
            addCriterion("referral_code >=", value, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeLessThan(String value) {
            addCriterion("referral_code <", value, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeLessThanOrEqualTo(String value) {
            addCriterion("referral_code <=", value, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeLike(String value) {
            addCriterion("referral_code like", value, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeNotLike(String value) {
            addCriterion("referral_code not like", value, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeIn(List<String> values) {
            addCriterion("referral_code in", values, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeNotIn(List<String> values) {
            addCriterion("referral_code not in", values, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeBetween(String value1, String value2) {
            addCriterion("referral_code between", value1, value2, "referralCode");
            return (Criteria) this;
        }

        public Criteria andReferralCodeNotBetween(String value1, String value2) {
            addCriterion("referral_code not between", value1, value2, "referralCode");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameIsNull() {
            addCriterion("employee_name is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameIsNotNull() {
            addCriterion("employee_name is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameEqualTo(String value) {
            addCriterion("employee_name =", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotEqualTo(String value) {
            addCriterion("employee_name <>", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameGreaterThan(String value) {
            addCriterion("employee_name >", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameGreaterThanOrEqualTo(String value) {
            addCriterion("employee_name >=", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameLessThan(String value) {
            addCriterion("employee_name <", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameLessThanOrEqualTo(String value) {
            addCriterion("employee_name <=", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameLike(String value) {
            addCriterion("employee_name like", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotLike(String value) {
            addCriterion("employee_name not like", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameIn(List<String> values) {
            addCriterion("employee_name in", values, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotIn(List<String> values) {
            addCriterion("employee_name not in", values, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameBetween(String value1, String value2) {
            addCriterion("employee_name between", value1, value2, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotBetween(String value1, String value2) {
            addCriterion("employee_name not between", value1, value2, "employeeName");
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

        public Criteria andOrgPrincipalIsNull() {
            addCriterion("org_principal is null");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalIsNotNull() {
            addCriterion("org_principal is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalEqualTo(String value) {
            addCriterion("org_principal =", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalNotEqualTo(String value) {
            addCriterion("org_principal <>", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalGreaterThan(String value) {
            addCriterion("org_principal >", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalGreaterThanOrEqualTo(String value) {
            addCriterion("org_principal >=", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalLessThan(String value) {
            addCriterion("org_principal <", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalLessThanOrEqualTo(String value) {
            addCriterion("org_principal <=", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalLike(String value) {
            addCriterion("org_principal like", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalNotLike(String value) {
            addCriterion("org_principal not like", value, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalIn(List<String> values) {
            addCriterion("org_principal in", values, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalNotIn(List<String> values) {
            addCriterion("org_principal not in", values, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalBetween(String value1, String value2) {
            addCriterion("org_principal between", value1, value2, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andOrgPrincipalNotBetween(String value1, String value2) {
            addCriterion("org_principal not between", value1, value2, "orgPrincipal");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentIsNull() {
            addCriterion("level4_department is null");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentIsNotNull() {
            addCriterion("level4_department is not null");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentEqualTo(String value) {
            addCriterion("level4_department =", value, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentNotEqualTo(String value) {
            addCriterion("level4_department <>", value, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentGreaterThan(String value) {
            addCriterion("level4_department >", value, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("level4_department >=", value, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentLessThan(String value) {
            addCriterion("level4_department <", value, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentLessThanOrEqualTo(String value) {
            addCriterion("level4_department <=", value, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentLike(String value) {
            addCriterion("level4_department like", value, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentNotLike(String value) {
            addCriterion("level4_department not like", value, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentIn(List<String> values) {
            addCriterion("level4_department in", values, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentNotIn(List<String> values) {
            addCriterion("level4_department not in", values, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentBetween(String value1, String value2) {
            addCriterion("level4_department between", value1, value2, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4DepartmentNotBetween(String value1, String value2) {
            addCriterion("level4_department not between", value1, value2, "level4Department");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalIsNull() {
            addCriterion("level4_principal is null");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalIsNotNull() {
            addCriterion("level4_principal is not null");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalEqualTo(String value) {
            addCriterion("level4_principal =", value, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalNotEqualTo(String value) {
            addCriterion("level4_principal <>", value, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalGreaterThan(String value) {
            addCriterion("level4_principal >", value, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalGreaterThanOrEqualTo(String value) {
            addCriterion("level4_principal >=", value, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalLessThan(String value) {
            addCriterion("level4_principal <", value, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalLessThanOrEqualTo(String value) {
            addCriterion("level4_principal <=", value, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalLike(String value) {
            addCriterion("level4_principal like", value, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalNotLike(String value) {
            addCriterion("level4_principal not like", value, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalIn(List<String> values) {
            addCriterion("level4_principal in", values, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalNotIn(List<String> values) {
            addCriterion("level4_principal not in", values, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalBetween(String value1, String value2) {
            addCriterion("level4_principal between", value1, value2, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel4PrincipalNotBetween(String value1, String value2) {
            addCriterion("level4_principal not between", value1, value2, "level4Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentIsNull() {
            addCriterion("level5_department is null");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentIsNotNull() {
            addCriterion("level5_department is not null");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentEqualTo(String value) {
            addCriterion("level5_department =", value, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentNotEqualTo(String value) {
            addCriterion("level5_department <>", value, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentGreaterThan(String value) {
            addCriterion("level5_department >", value, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("level5_department >=", value, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentLessThan(String value) {
            addCriterion("level5_department <", value, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentLessThanOrEqualTo(String value) {
            addCriterion("level5_department <=", value, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentLike(String value) {
            addCriterion("level5_department like", value, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentNotLike(String value) {
            addCriterion("level5_department not like", value, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentIn(List<String> values) {
            addCriterion("level5_department in", values, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentNotIn(List<String> values) {
            addCriterion("level5_department not in", values, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentBetween(String value1, String value2) {
            addCriterion("level5_department between", value1, value2, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5DepartmentNotBetween(String value1, String value2) {
            addCriterion("level5_department not between", value1, value2, "level5Department");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalIsNull() {
            addCriterion("level5_principal is null");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalIsNotNull() {
            addCriterion("level5_principal is not null");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalEqualTo(String value) {
            addCriterion("level5_principal =", value, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalNotEqualTo(String value) {
            addCriterion("level5_principal <>", value, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalGreaterThan(String value) {
            addCriterion("level5_principal >", value, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalGreaterThanOrEqualTo(String value) {
            addCriterion("level5_principal >=", value, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalLessThan(String value) {
            addCriterion("level5_principal <", value, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalLessThanOrEqualTo(String value) {
            addCriterion("level5_principal <=", value, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalLike(String value) {
            addCriterion("level5_principal like", value, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalNotLike(String value) {
            addCriterion("level5_principal not like", value, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalIn(List<String> values) {
            addCriterion("level5_principal in", values, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalNotIn(List<String> values) {
            addCriterion("level5_principal not in", values, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalBetween(String value1, String value2) {
            addCriterion("level5_principal between", value1, value2, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andLevel5PrincipalNotBetween(String value1, String value2) {
            addCriterion("level5_principal not between", value1, value2, "level5Principal");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionTypeIsNull() {
            addCriterion("position_type is null");
            return (Criteria) this;
        }

        public Criteria andPositionTypeIsNotNull() {
            addCriterion("position_type is not null");
            return (Criteria) this;
        }

        public Criteria andPositionTypeEqualTo(String value) {
            addCriterion("position_type =", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotEqualTo(String value) {
            addCriterion("position_type <>", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeGreaterThan(String value) {
            addCriterion("position_type >", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("position_type >=", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeLessThan(String value) {
            addCriterion("position_type <", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeLessThanOrEqualTo(String value) {
            addCriterion("position_type <=", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeLike(String value) {
            addCriterion("position_type like", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotLike(String value) {
            addCriterion("position_type not like", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeIn(List<String> values) {
            addCriterion("position_type in", values, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotIn(List<String> values) {
            addCriterion("position_type not in", values, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeBetween(String value1, String value2) {
            addCriterion("position_type between", value1, value2, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotBetween(String value1, String value2) {
            addCriterion("position_type not between", value1, value2, "positionType");
            return (Criteria) this;
        }

        public Criteria andEntryDateIsNull() {
            addCriterion("entry_date is null");
            return (Criteria) this;
        }

        public Criteria andEntryDateIsNotNull() {
            addCriterion("entry_date is not null");
            return (Criteria) this;
        }

        public Criteria andEntryDateEqualTo(Date value) {
            addCriterionForJDBCDate("entry_date =", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("entry_date <>", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateGreaterThan(Date value) {
            addCriterionForJDBCDate("entry_date >", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("entry_date >=", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateLessThan(Date value) {
            addCriterionForJDBCDate("entry_date <", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("entry_date <=", value, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateIn(List<Date> values) {
            addCriterionForJDBCDate("entry_date in", values, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("entry_date not in", values, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("entry_date between", value1, value2, "entryDate");
            return (Criteria) this;
        }

        public Criteria andEntryDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("entry_date not between", value1, value2, "entryDate");
            return (Criteria) this;
        }

        public Criteria andIsPositiveIsNull() {
            addCriterion("is_positive is null");
            return (Criteria) this;
        }

        public Criteria andIsPositiveIsNotNull() {
            addCriterion("is_positive is not null");
            return (Criteria) this;
        }

        public Criteria andIsPositiveEqualTo(String value) {
            addCriterion("is_positive =", value, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveNotEqualTo(String value) {
            addCriterion("is_positive <>", value, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveGreaterThan(String value) {
            addCriterion("is_positive >", value, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveGreaterThanOrEqualTo(String value) {
            addCriterion("is_positive >=", value, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveLessThan(String value) {
            addCriterion("is_positive <", value, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveLessThanOrEqualTo(String value) {
            addCriterion("is_positive <=", value, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveLike(String value) {
            addCriterion("is_positive like", value, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveNotLike(String value) {
            addCriterion("is_positive not like", value, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveIn(List<String> values) {
            addCriterion("is_positive in", values, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveNotIn(List<String> values) {
            addCriterion("is_positive not in", values, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveBetween(String value1, String value2) {
            addCriterion("is_positive between", value1, value2, "isPositive");
            return (Criteria) this;
        }

        public Criteria andIsPositiveNotBetween(String value1, String value2) {
            addCriterion("is_positive not between", value1, value2, "isPositive");
            return (Criteria) this;
        }

        public Criteria andPositiveDateIsNull() {
            addCriterion("positive_date is null");
            return (Criteria) this;
        }

        public Criteria andPositiveDateIsNotNull() {
            addCriterion("positive_date is not null");
            return (Criteria) this;
        }

        public Criteria andPositiveDateEqualTo(Date value) {
            addCriterionForJDBCDate("positive_date =", value, "positiveDate");
            return (Criteria) this;
        }

        public Criteria andPositiveDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("positive_date <>", value, "positiveDate");
            return (Criteria) this;
        }

        public Criteria andPositiveDateGreaterThan(Date value) {
            addCriterionForJDBCDate("positive_date >", value, "positiveDate");
            return (Criteria) this;
        }

        public Criteria andPositiveDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("positive_date >=", value, "positiveDate");
            return (Criteria) this;
        }

        public Criteria andPositiveDateLessThan(Date value) {
            addCriterionForJDBCDate("positive_date <", value, "positiveDate");
            return (Criteria) this;
        }

        public Criteria andPositiveDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("positive_date <=", value, "positiveDate");
            return (Criteria) this;
        }

        public Criteria andPositiveDateIn(List<Date> values) {
            addCriterionForJDBCDate("positive_date in", values, "positiveDate");
            return (Criteria) this;
        }

        public Criteria andPositiveDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("positive_date not in", values, "positiveDate");
            return (Criteria) this;
        }

        public Criteria andPositiveDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("positive_date between", value1, value2, "positiveDate");
            return (Criteria) this;
        }

        public Criteria andPositiveDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("positive_date not between", value1, value2, "positiveDate");
            return (Criteria) this;
        }

        public Criteria andDimissionDateIsNull() {
            addCriterion("dimission_date is null");
            return (Criteria) this;
        }

        public Criteria andDimissionDateIsNotNull() {
            addCriterion("dimission_date is not null");
            return (Criteria) this;
        }

        public Criteria andDimissionDateEqualTo(Date value) {
            addCriterionForJDBCDate("dimission_date =", value, "dimissionDate");
            return (Criteria) this;
        }

        public Criteria andDimissionDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("dimission_date <>", value, "dimissionDate");
            return (Criteria) this;
        }

        public Criteria andDimissionDateGreaterThan(Date value) {
            addCriterionForJDBCDate("dimission_date >", value, "dimissionDate");
            return (Criteria) this;
        }

        public Criteria andDimissionDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dimission_date >=", value, "dimissionDate");
            return (Criteria) this;
        }

        public Criteria andDimissionDateLessThan(Date value) {
            addCriterionForJDBCDate("dimission_date <", value, "dimissionDate");
            return (Criteria) this;
        }

        public Criteria andDimissionDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dimission_date <=", value, "dimissionDate");
            return (Criteria) this;
        }

        public Criteria andDimissionDateIn(List<Date> values) {
            addCriterionForJDBCDate("dimission_date in", values, "dimissionDate");
            return (Criteria) this;
        }

        public Criteria andDimissionDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("dimission_date not in", values, "dimissionDate");
            return (Criteria) this;
        }

        public Criteria andDimissionDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dimission_date between", value1, value2, "dimissionDate");
            return (Criteria) this;
        }

        public Criteria andDimissionDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dimission_date not between", value1, value2, "dimissionDate");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNull() {
            addCriterion("creator_id is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNotNull() {
            addCriterion("creator_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdEqualTo(Integer value) {
            addCriterion("creator_id =", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotEqualTo(Integer value) {
            addCriterion("creator_id <>", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThan(Integer value) {
            addCriterion("creator_id >", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("creator_id >=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThan(Integer value) {
            addCriterion("creator_id <", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("creator_id <=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIn(List<Integer> values) {
            addCriterion("creator_id in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotIn(List<Integer> values) {
            addCriterion("creator_id not in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdBetween(Integer value1, Integer value2) {
            addCriterion("creator_id between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("creator_id not between", value1, value2, "creatorId");
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