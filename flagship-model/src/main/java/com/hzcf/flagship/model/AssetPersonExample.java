package com.hzcf.flagship.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AssetPersonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AssetPersonExample() {
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

        public Criteria andDepNoIsNull() {
            addCriterion("dep_no is null");
            return (Criteria) this;
        }

        public Criteria andDepNoIsNotNull() {
            addCriterion("dep_no is not null");
            return (Criteria) this;
        }

        public Criteria andDepNoEqualTo(String value) {
            addCriterion("dep_no =", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoNotEqualTo(String value) {
            addCriterion("dep_no <>", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoGreaterThan(String value) {
            addCriterion("dep_no >", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoGreaterThanOrEqualTo(String value) {
            addCriterion("dep_no >=", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoLessThan(String value) {
            addCriterion("dep_no <", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoLessThanOrEqualTo(String value) {
            addCriterion("dep_no <=", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoLike(String value) {
            addCriterion("dep_no like", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoNotLike(String value) {
            addCriterion("dep_no not like", value, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoIn(List<String> values) {
            addCriterion("dep_no in", values, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoNotIn(List<String> values) {
            addCriterion("dep_no not in", values, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoBetween(String value1, String value2) {
            addCriterion("dep_no between", value1, value2, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNoNotBetween(String value1, String value2) {
            addCriterion("dep_no not between", value1, value2, "depNo");
            return (Criteria) this;
        }

        public Criteria andDepNameIsNull() {
            addCriterion("dep_name is null");
            return (Criteria) this;
        }

        public Criteria andDepNameIsNotNull() {
            addCriterion("dep_name is not null");
            return (Criteria) this;
        }

        public Criteria andDepNameEqualTo(String value) {
            addCriterion("dep_name =", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameNotEqualTo(String value) {
            addCriterion("dep_name <>", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameGreaterThan(String value) {
            addCriterion("dep_name >", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameGreaterThanOrEqualTo(String value) {
            addCriterion("dep_name >=", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameLessThan(String value) {
            addCriterion("dep_name <", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameLessThanOrEqualTo(String value) {
            addCriterion("dep_name <=", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameLike(String value) {
            addCriterion("dep_name like", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameNotLike(String value) {
            addCriterion("dep_name not like", value, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameIn(List<String> values) {
            addCriterion("dep_name in", values, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameNotIn(List<String> values) {
            addCriterion("dep_name not in", values, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameBetween(String value1, String value2) {
            addCriterion("dep_name between", value1, value2, "depName");
            return (Criteria) this;
        }

        public Criteria andDepNameNotBetween(String value1, String value2) {
            addCriterion("dep_name not between", value1, value2, "depName");
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

        public Criteria andSalesdepNoIsNull() {
            addCriterion("salesdep_no is null");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoIsNotNull() {
            addCriterion("salesdep_no is not null");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoEqualTo(String value) {
            addCriterion("salesdep_no =", value, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoNotEqualTo(String value) {
            addCriterion("salesdep_no <>", value, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoGreaterThan(String value) {
            addCriterion("salesdep_no >", value, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoGreaterThanOrEqualTo(String value) {
            addCriterion("salesdep_no >=", value, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoLessThan(String value) {
            addCriterion("salesdep_no <", value, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoLessThanOrEqualTo(String value) {
            addCriterion("salesdep_no <=", value, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoLike(String value) {
            addCriterion("salesdep_no like", value, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoNotLike(String value) {
            addCriterion("salesdep_no not like", value, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoIn(List<String> values) {
            addCriterion("salesdep_no in", values, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoNotIn(List<String> values) {
            addCriterion("salesdep_no not in", values, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoBetween(String value1, String value2) {
            addCriterion("salesdep_no between", value1, value2, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNoNotBetween(String value1, String value2) {
            addCriterion("salesdep_no not between", value1, value2, "salesdepNo");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameIsNull() {
            addCriterion("salesdep_name is null");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameIsNotNull() {
            addCriterion("salesdep_name is not null");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameEqualTo(String value) {
            addCriterion("salesdep_name =", value, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameNotEqualTo(String value) {
            addCriterion("salesdep_name <>", value, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameGreaterThan(String value) {
            addCriterion("salesdep_name >", value, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameGreaterThanOrEqualTo(String value) {
            addCriterion("salesdep_name >=", value, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameLessThan(String value) {
            addCriterion("salesdep_name <", value, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameLessThanOrEqualTo(String value) {
            addCriterion("salesdep_name <=", value, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameLike(String value) {
            addCriterion("salesdep_name like", value, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameNotLike(String value) {
            addCriterion("salesdep_name not like", value, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameIn(List<String> values) {
            addCriterion("salesdep_name in", values, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameNotIn(List<String> values) {
            addCriterion("salesdep_name not in", values, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameBetween(String value1, String value2) {
            addCriterion("salesdep_name between", value1, value2, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andSalesdepNameNotBetween(String value1, String value2) {
            addCriterion("salesdep_name not between", value1, value2, "salesdepName");
            return (Criteria) this;
        }

        public Criteria andTeamNameIsNull() {
            addCriterion("team_name is null");
            return (Criteria) this;
        }

        public Criteria andTeamNameIsNotNull() {
            addCriterion("team_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeamNameEqualTo(String value) {
            addCriterion("team_name =", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotEqualTo(String value) {
            addCriterion("team_name <>", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameGreaterThan(String value) {
            addCriterion("team_name >", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameGreaterThanOrEqualTo(String value) {
            addCriterion("team_name >=", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLessThan(String value) {
            addCriterion("team_name <", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLessThanOrEqualTo(String value) {
            addCriterion("team_name <=", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLike(String value) {
            addCriterion("team_name like", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotLike(String value) {
            addCriterion("team_name not like", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameIn(List<String> values) {
            addCriterion("team_name in", values, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotIn(List<String> values) {
            addCriterion("team_name not in", values, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameBetween(String value1, String value2) {
            addCriterion("team_name between", value1, value2, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotBetween(String value1, String value2) {
            addCriterion("team_name not between", value1, value2, "teamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameIsNull() {
            addCriterion("sub_team_name is null");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameIsNotNull() {
            addCriterion("sub_team_name is not null");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameEqualTo(String value) {
            addCriterion("sub_team_name =", value, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameNotEqualTo(String value) {
            addCriterion("sub_team_name <>", value, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameGreaterThan(String value) {
            addCriterion("sub_team_name >", value, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameGreaterThanOrEqualTo(String value) {
            addCriterion("sub_team_name >=", value, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameLessThan(String value) {
            addCriterion("sub_team_name <", value, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameLessThanOrEqualTo(String value) {
            addCriterion("sub_team_name <=", value, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameLike(String value) {
            addCriterion("sub_team_name like", value, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameNotLike(String value) {
            addCriterion("sub_team_name not like", value, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameIn(List<String> values) {
            addCriterion("sub_team_name in", values, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameNotIn(List<String> values) {
            addCriterion("sub_team_name not in", values, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameBetween(String value1, String value2) {
            addCriterion("sub_team_name between", value1, value2, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andSubTeamNameNotBetween(String value1, String value2) {
            addCriterion("sub_team_name not between", value1, value2, "subTeamName");
            return (Criteria) this;
        }

        public Criteria andPersonNoIsNull() {
            addCriterion("person_no is null");
            return (Criteria) this;
        }

        public Criteria andPersonNoIsNotNull() {
            addCriterion("person_no is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNoEqualTo(String value) {
            addCriterion("person_no =", value, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoNotEqualTo(String value) {
            addCriterion("person_no <>", value, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoGreaterThan(String value) {
            addCriterion("person_no >", value, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoGreaterThanOrEqualTo(String value) {
            addCriterion("person_no >=", value, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoLessThan(String value) {
            addCriterion("person_no <", value, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoLessThanOrEqualTo(String value) {
            addCriterion("person_no <=", value, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoLike(String value) {
            addCriterion("person_no like", value, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoNotLike(String value) {
            addCriterion("person_no not like", value, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoIn(List<String> values) {
            addCriterion("person_no in", values, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoNotIn(List<String> values) {
            addCriterion("person_no not in", values, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoBetween(String value1, String value2) {
            addCriterion("person_no between", value1, value2, "personNo");
            return (Criteria) this;
        }

        public Criteria andPersonNoNotBetween(String value1, String value2) {
            addCriterion("person_no not between", value1, value2, "personNo");
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

        public Criteria andDimissionTimeIsNull() {
            addCriterion("dimission_time is null");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeIsNotNull() {
            addCriterion("dimission_time is not null");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeEqualTo(Date value) {
            addCriterionForJDBCDate("dimission_time =", value, "dimissionTime");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("dimission_time <>", value, "dimissionTime");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("dimission_time >", value, "dimissionTime");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dimission_time >=", value, "dimissionTime");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeLessThan(Date value) {
            addCriterionForJDBCDate("dimission_time <", value, "dimissionTime");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dimission_time <=", value, "dimissionTime");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeIn(List<Date> values) {
            addCriterionForJDBCDate("dimission_time in", values, "dimissionTime");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("dimission_time not in", values, "dimissionTime");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dimission_time between", value1, value2, "dimissionTime");
            return (Criteria) this;
        }

        public Criteria andDimissionTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dimission_time not between", value1, value2, "dimissionTime");
            return (Criteria) this;
        }

        public Criteria andCaretorIsNull() {
            addCriterion("caretor is null");
            return (Criteria) this;
        }

        public Criteria andCaretorIsNotNull() {
            addCriterion("caretor is not null");
            return (Criteria) this;
        }

        public Criteria andCaretorEqualTo(Integer value) {
            addCriterion("caretor =", value, "caretor");
            return (Criteria) this;
        }

        public Criteria andCaretorNotEqualTo(Integer value) {
            addCriterion("caretor <>", value, "caretor");
            return (Criteria) this;
        }

        public Criteria andCaretorGreaterThan(Integer value) {
            addCriterion("caretor >", value, "caretor");
            return (Criteria) this;
        }

        public Criteria andCaretorGreaterThanOrEqualTo(Integer value) {
            addCriterion("caretor >=", value, "caretor");
            return (Criteria) this;
        }

        public Criteria andCaretorLessThan(Integer value) {
            addCriterion("caretor <", value, "caretor");
            return (Criteria) this;
        }

        public Criteria andCaretorLessThanOrEqualTo(Integer value) {
            addCriterion("caretor <=", value, "caretor");
            return (Criteria) this;
        }

        public Criteria andCaretorIn(List<Integer> values) {
            addCriterion("caretor in", values, "caretor");
            return (Criteria) this;
        }

        public Criteria andCaretorNotIn(List<Integer> values) {
            addCriterion("caretor not in", values, "caretor");
            return (Criteria) this;
        }

        public Criteria andCaretorBetween(Integer value1, Integer value2) {
            addCriterion("caretor between", value1, value2, "caretor");
            return (Criteria) this;
        }

        public Criteria andCaretorNotBetween(Integer value1, Integer value2) {
            addCriterion("caretor not between", value1, value2, "caretor");
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