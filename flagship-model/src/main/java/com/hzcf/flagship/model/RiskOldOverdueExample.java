package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RiskOldOverdueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RiskOldOverdueExample() {
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

        public Criteria andApplyNoIsNull() {
            addCriterion("apply_no is null");
            return (Criteria) this;
        }

        public Criteria andApplyNoIsNotNull() {
            addCriterion("apply_no is not null");
            return (Criteria) this;
        }

        public Criteria andApplyNoEqualTo(String value) {
            addCriterion("apply_no =", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoNotEqualTo(String value) {
            addCriterion("apply_no <>", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoGreaterThan(String value) {
            addCriterion("apply_no >", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoGreaterThanOrEqualTo(String value) {
            addCriterion("apply_no >=", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoLessThan(String value) {
            addCriterion("apply_no <", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoLessThanOrEqualTo(String value) {
            addCriterion("apply_no <=", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoLike(String value) {
            addCriterion("apply_no like", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoNotLike(String value) {
            addCriterion("apply_no not like", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoIn(List<String> values) {
            addCriterion("apply_no in", values, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoNotIn(List<String> values) {
            addCriterion("apply_no not in", values, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoBetween(String value1, String value2) {
            addCriterion("apply_no between", value1, value2, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoNotBetween(String value1, String value2) {
            addCriterion("apply_no not between", value1, value2, "applyNo");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentIsNull() {
            addCriterion("sale_department is null");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentIsNotNull() {
            addCriterion("sale_department is not null");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentEqualTo(String value) {
            addCriterion("sale_department =", value, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentNotEqualTo(String value) {
            addCriterion("sale_department <>", value, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentGreaterThan(String value) {
            addCriterion("sale_department >", value, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("sale_department >=", value, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentLessThan(String value) {
            addCriterion("sale_department <", value, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentLessThanOrEqualTo(String value) {
            addCriterion("sale_department <=", value, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentLike(String value) {
            addCriterion("sale_department like", value, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentNotLike(String value) {
            addCriterion("sale_department not like", value, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentIn(List<String> values) {
            addCriterion("sale_department in", values, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentNotIn(List<String> values) {
            addCriterion("sale_department not in", values, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentBetween(String value1, String value2) {
            addCriterion("sale_department between", value1, value2, "saleDepartment");
            return (Criteria) this;
        }

        public Criteria andSaleDepartmentNotBetween(String value1, String value2) {
            addCriterion("sale_department not between", value1, value2, "saleDepartment");
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

        public Criteria andCardNoIsNull() {
            addCriterion("card_no is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("card_no is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("card_no =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("card_no <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("card_no >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("card_no >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("card_no <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("card_no <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("card_no like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("card_no not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("card_no in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("card_no not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("card_no between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("card_no not between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andBankIsNull() {
            addCriterion("bank is null");
            return (Criteria) this;
        }

        public Criteria andBankIsNotNull() {
            addCriterion("bank is not null");
            return (Criteria) this;
        }

        public Criteria andBankEqualTo(String value) {
            addCriterion("bank =", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotEqualTo(String value) {
            addCriterion("bank <>", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThan(String value) {
            addCriterion("bank >", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThanOrEqualTo(String value) {
            addCriterion("bank >=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThan(String value) {
            addCriterion("bank <", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThanOrEqualTo(String value) {
            addCriterion("bank <=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLike(String value) {
            addCriterion("bank like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotLike(String value) {
            addCriterion("bank not like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankIn(List<String> values) {
            addCriterion("bank in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotIn(List<String> values) {
            addCriterion("bank not in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankBetween(String value1, String value2) {
            addCriterion("bank between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotBetween(String value1, String value2) {
            addCriterion("bank not between", value1, value2, "bank");
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

        public Criteria andInitialRepaymentTimeIsNull() {
            addCriterion("initial_repayment_time is null");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeIsNotNull() {
            addCriterion("initial_repayment_time is not null");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeEqualTo(Date value) {
            addCriterion("initial_repayment_time =", value, "initialRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeNotEqualTo(Date value) {
            addCriterion("initial_repayment_time <>", value, "initialRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeGreaterThan(Date value) {
            addCriterion("initial_repayment_time >", value, "initialRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("initial_repayment_time >=", value, "initialRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeLessThan(Date value) {
            addCriterion("initial_repayment_time <", value, "initialRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeLessThanOrEqualTo(Date value) {
            addCriterion("initial_repayment_time <=", value, "initialRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeIn(List<Date> values) {
            addCriterion("initial_repayment_time in", values, "initialRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeNotIn(List<Date> values) {
            addCriterion("initial_repayment_time not in", values, "initialRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeBetween(Date value1, Date value2) {
            addCriterion("initial_repayment_time between", value1, value2, "initialRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andInitialRepaymentTimeNotBetween(Date value1, Date value2) {
            addCriterion("initial_repayment_time not between", value1, value2, "initialRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestIsNull() {
            addCriterion("principal_and_interest is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestIsNotNull() {
            addCriterion("principal_and_interest is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestEqualTo(BigDecimal value) {
            addCriterion("principal_and_interest =", value, "principalAndInterest");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestNotEqualTo(BigDecimal value) {
            addCriterion("principal_and_interest <>", value, "principalAndInterest");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestGreaterThan(BigDecimal value) {
            addCriterion("principal_and_interest >", value, "principalAndInterest");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("principal_and_interest >=", value, "principalAndInterest");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestLessThan(BigDecimal value) {
            addCriterion("principal_and_interest <", value, "principalAndInterest");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("principal_and_interest <=", value, "principalAndInterest");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestIn(List<BigDecimal> values) {
            addCriterion("principal_and_interest in", values, "principalAndInterest");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestNotIn(List<BigDecimal> values) {
            addCriterion("principal_and_interest not in", values, "principalAndInterest");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("principal_and_interest between", value1, value2, "principalAndInterest");
            return (Criteria) this;
        }

        public Criteria andPrincipalAndInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("principal_and_interest not between", value1, value2, "principalAndInterest");
            return (Criteria) this;
        }

        public Criteria andPenaltyIsNull() {
            addCriterion("penalty is null");
            return (Criteria) this;
        }

        public Criteria andPenaltyIsNotNull() {
            addCriterion("penalty is not null");
            return (Criteria) this;
        }

        public Criteria andPenaltyEqualTo(BigDecimal value) {
            addCriterion("penalty =", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyNotEqualTo(BigDecimal value) {
            addCriterion("penalty <>", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyGreaterThan(BigDecimal value) {
            addCriterion("penalty >", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty >=", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyLessThan(BigDecimal value) {
            addCriterion("penalty <", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty <=", value, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyIn(List<BigDecimal> values) {
            addCriterion("penalty in", values, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyNotIn(List<BigDecimal> values) {
            addCriterion("penalty not in", values, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty between", value1, value2, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty not between", value1, value2, "penalty");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestIsNull() {
            addCriterion("penalty_interest is null");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestIsNotNull() {
            addCriterion("penalty_interest is not null");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestEqualTo(BigDecimal value) {
            addCriterion("penalty_interest =", value, "penaltyInterest");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestNotEqualTo(BigDecimal value) {
            addCriterion("penalty_interest <>", value, "penaltyInterest");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestGreaterThan(BigDecimal value) {
            addCriterion("penalty_interest >", value, "penaltyInterest");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty_interest >=", value, "penaltyInterest");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestLessThan(BigDecimal value) {
            addCriterion("penalty_interest <", value, "penaltyInterest");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("penalty_interest <=", value, "penaltyInterest");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestIn(List<BigDecimal> values) {
            addCriterion("penalty_interest in", values, "penaltyInterest");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestNotIn(List<BigDecimal> values) {
            addCriterion("penalty_interest not in", values, "penaltyInterest");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty_interest between", value1, value2, "penaltyInterest");
            return (Criteria) this;
        }

        public Criteria andPenaltyInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("penalty_interest not between", value1, value2, "penaltyInterest");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyIsNull() {
            addCriterion("breach_penalty is null");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyIsNotNull() {
            addCriterion("breach_penalty is not null");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyEqualTo(BigDecimal value) {
            addCriterion("breach_penalty =", value, "breachPenalty");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyNotEqualTo(BigDecimal value) {
            addCriterion("breach_penalty <>", value, "breachPenalty");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyGreaterThan(BigDecimal value) {
            addCriterion("breach_penalty >", value, "breachPenalty");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("breach_penalty >=", value, "breachPenalty");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyLessThan(BigDecimal value) {
            addCriterion("breach_penalty <", value, "breachPenalty");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("breach_penalty <=", value, "breachPenalty");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyIn(List<BigDecimal> values) {
            addCriterion("breach_penalty in", values, "breachPenalty");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyNotIn(List<BigDecimal> values) {
            addCriterion("breach_penalty not in", values, "breachPenalty");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("breach_penalty between", value1, value2, "breachPenalty");
            return (Criteria) this;
        }

        public Criteria andBreachPenaltyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("breach_penalty not between", value1, value2, "breachPenalty");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountIsNull() {
            addCriterion("total_repayment_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountIsNotNull() {
            addCriterion("total_repayment_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountEqualTo(BigDecimal value) {
            addCriterion("total_repayment_amount =", value, "totalRepaymentAmount");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountNotEqualTo(BigDecimal value) {
            addCriterion("total_repayment_amount <>", value, "totalRepaymentAmount");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountGreaterThan(BigDecimal value) {
            addCriterion("total_repayment_amount >", value, "totalRepaymentAmount");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_repayment_amount >=", value, "totalRepaymentAmount");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountLessThan(BigDecimal value) {
            addCriterion("total_repayment_amount <", value, "totalRepaymentAmount");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_repayment_amount <=", value, "totalRepaymentAmount");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountIn(List<BigDecimal> values) {
            addCriterion("total_repayment_amount in", values, "totalRepaymentAmount");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountNotIn(List<BigDecimal> values) {
            addCriterion("total_repayment_amount not in", values, "totalRepaymentAmount");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_repayment_amount between", value1, value2, "totalRepaymentAmount");
            return (Criteria) this;
        }

        public Criteria andTotalRepaymentAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_repayment_amount not between", value1, value2, "totalRepaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIsNull() {
            addCriterion("repayment_date is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIsNotNull() {
            addCriterion("repayment_date is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateEqualTo(String value) {
            addCriterion("repayment_date =", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotEqualTo(String value) {
            addCriterion("repayment_date <>", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateGreaterThan(String value) {
            addCriterion("repayment_date >", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_date >=", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLessThan(String value) {
            addCriterion("repayment_date <", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLessThanOrEqualTo(String value) {
            addCriterion("repayment_date <=", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateLike(String value) {
            addCriterion("repayment_date like", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotLike(String value) {
            addCriterion("repayment_date not like", value, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateIn(List<String> values) {
            addCriterion("repayment_date in", values, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotIn(List<String> values) {
            addCriterion("repayment_date not in", values, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateBetween(String value1, String value2) {
            addCriterion("repayment_date between", value1, value2, "repaymentDate");
            return (Criteria) this;
        }

        public Criteria andRepaymentDateNotBetween(String value1, String value2) {
            addCriterion("repayment_date not between", value1, value2, "repaymentDate");
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

        public Criteria andOldOrgNameIsNull() {
            addCriterion("old_org_name is null");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameIsNotNull() {
            addCriterion("old_org_name is not null");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameEqualTo(String value) {
            addCriterion("old_org_name =", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameNotEqualTo(String value) {
            addCriterion("old_org_name <>", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameGreaterThan(String value) {
            addCriterion("old_org_name >", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("old_org_name >=", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameLessThan(String value) {
            addCriterion("old_org_name <", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameLessThanOrEqualTo(String value) {
            addCriterion("old_org_name <=", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameLike(String value) {
            addCriterion("old_org_name like", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameNotLike(String value) {
            addCriterion("old_org_name not like", value, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameIn(List<String> values) {
            addCriterion("old_org_name in", values, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameNotIn(List<String> values) {
            addCriterion("old_org_name not in", values, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameBetween(String value1, String value2) {
            addCriterion("old_org_name between", value1, value2, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andOldOrgNameNotBetween(String value1, String value2) {
            addCriterion("old_org_name not between", value1, value2, "oldOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameIsNull() {
            addCriterion("new_org_name is null");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameIsNotNull() {
            addCriterion("new_org_name is not null");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameEqualTo(String value) {
            addCriterion("new_org_name =", value, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameNotEqualTo(String value) {
            addCriterion("new_org_name <>", value, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameGreaterThan(String value) {
            addCriterion("new_org_name >", value, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("new_org_name >=", value, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameLessThan(String value) {
            addCriterion("new_org_name <", value, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameLessThanOrEqualTo(String value) {
            addCriterion("new_org_name <=", value, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameLike(String value) {
            addCriterion("new_org_name like", value, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameNotLike(String value) {
            addCriterion("new_org_name not like", value, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameIn(List<String> values) {
            addCriterion("new_org_name in", values, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameNotIn(List<String> values) {
            addCriterion("new_org_name not in", values, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameBetween(String value1, String value2) {
            addCriterion("new_org_name between", value1, value2, "newOrgName");
            return (Criteria) this;
        }

        public Criteria andNewOrgNameNotBetween(String value1, String value2) {
            addCriterion("new_org_name not between", value1, value2, "newOrgName");
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