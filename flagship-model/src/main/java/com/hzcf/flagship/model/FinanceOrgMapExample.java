package com.hzcf.flagship.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FinanceOrgMapExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinanceOrgMapExample() {
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

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andOpeningDateIsNull() {
            addCriterion("opening_date is null");
            return (Criteria) this;
        }

        public Criteria andOpeningDateIsNotNull() {
            addCriterion("opening_date is not null");
            return (Criteria) this;
        }

        public Criteria andOpeningDateEqualTo(Date value) {
            addCriterionForJDBCDate("opening_date =", value, "openingDate");
            return (Criteria) this;
        }

        public Criteria andOpeningDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("opening_date <>", value, "openingDate");
            return (Criteria) this;
        }

        public Criteria andOpeningDateGreaterThan(Date value) {
            addCriterionForJDBCDate("opening_date >", value, "openingDate");
            return (Criteria) this;
        }

        public Criteria andOpeningDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("opening_date >=", value, "openingDate");
            return (Criteria) this;
        }

        public Criteria andOpeningDateLessThan(Date value) {
            addCriterionForJDBCDate("opening_date <", value, "openingDate");
            return (Criteria) this;
        }

        public Criteria andOpeningDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("opening_date <=", value, "openingDate");
            return (Criteria) this;
        }

        public Criteria andOpeningDateIn(List<Date> values) {
            addCriterionForJDBCDate("opening_date in", values, "openingDate");
            return (Criteria) this;
        }

        public Criteria andOpeningDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("opening_date not in", values, "openingDate");
            return (Criteria) this;
        }

        public Criteria andOpeningDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("opening_date between", value1, value2, "openingDate");
            return (Criteria) this;
        }

        public Criteria andOpeningDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("opening_date not between", value1, value2, "openingDate");
            return (Criteria) this;
        }

        public Criteria andPrincipalIsNull() {
            addCriterion("principal is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalIsNotNull() {
            addCriterion("principal is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalEqualTo(String value) {
            addCriterion("principal =", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalNotEqualTo(String value) {
            addCriterion("principal <>", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalGreaterThan(String value) {
            addCriterion("principal >", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalGreaterThanOrEqualTo(String value) {
            addCriterion("principal >=", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalLessThan(String value) {
            addCriterion("principal <", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalLessThanOrEqualTo(String value) {
            addCriterion("principal <=", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalLike(String value) {
            addCriterion("principal like", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalNotLike(String value) {
            addCriterion("principal not like", value, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalIn(List<String> values) {
            addCriterion("principal in", values, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalNotIn(List<String> values) {
            addCriterion("principal not in", values, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalBetween(String value1, String value2) {
            addCriterion("principal between", value1, value2, "principal");
            return (Criteria) this;
        }

        public Criteria andPrincipalNotBetween(String value1, String value2) {
            addCriterion("principal not between", value1, value2, "principal");
            return (Criteria) this;
        }

        public Criteria andAbscissaIsNull() {
            addCriterion("abscissa is null");
            return (Criteria) this;
        }

        public Criteria andAbscissaIsNotNull() {
            addCriterion("abscissa is not null");
            return (Criteria) this;
        }

        public Criteria andAbscissaEqualTo(String value) {
            addCriterion("abscissa =", value, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaNotEqualTo(String value) {
            addCriterion("abscissa <>", value, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaGreaterThan(String value) {
            addCriterion("abscissa >", value, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaGreaterThanOrEqualTo(String value) {
            addCriterion("abscissa >=", value, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaLessThan(String value) {
            addCriterion("abscissa <", value, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaLessThanOrEqualTo(String value) {
            addCriterion("abscissa <=", value, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaLike(String value) {
            addCriterion("abscissa like", value, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaNotLike(String value) {
            addCriterion("abscissa not like", value, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaIn(List<String> values) {
            addCriterion("abscissa in", values, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaNotIn(List<String> values) {
            addCriterion("abscissa not in", values, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaBetween(String value1, String value2) {
            addCriterion("abscissa between", value1, value2, "abscissa");
            return (Criteria) this;
        }

        public Criteria andAbscissaNotBetween(String value1, String value2) {
            addCriterion("abscissa not between", value1, value2, "abscissa");
            return (Criteria) this;
        }

        public Criteria andOrdinateIsNull() {
            addCriterion("ordinate is null");
            return (Criteria) this;
        }

        public Criteria andOrdinateIsNotNull() {
            addCriterion("ordinate is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinateEqualTo(String value) {
            addCriterion("ordinate =", value, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateNotEqualTo(String value) {
            addCriterion("ordinate <>", value, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateGreaterThan(String value) {
            addCriterion("ordinate >", value, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateGreaterThanOrEqualTo(String value) {
            addCriterion("ordinate >=", value, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateLessThan(String value) {
            addCriterion("ordinate <", value, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateLessThanOrEqualTo(String value) {
            addCriterion("ordinate <=", value, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateLike(String value) {
            addCriterion("ordinate like", value, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateNotLike(String value) {
            addCriterion("ordinate not like", value, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateIn(List<String> values) {
            addCriterion("ordinate in", values, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateNotIn(List<String> values) {
            addCriterion("ordinate not in", values, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateBetween(String value1, String value2) {
            addCriterion("ordinate between", value1, value2, "ordinate");
            return (Criteria) this;
        }

        public Criteria andOrdinateNotBetween(String value1, String value2) {
            addCriterion("ordinate not between", value1, value2, "ordinate");
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