package com.jinchi.common.domain;

import java.util.ArrayList;
import java.util.List;

public class GoodsInProcessStatisticByProcessTotalExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    public GoodsInProcessStatisticByProcessTotalExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(Long value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(Long value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(Long value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(Long value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(Long value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<Long> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<Long> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(Long value1, Long value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(Long value1, Long value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeIsNull() {
            addCriterion("statistic_code is null");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeIsNotNull() {
            addCriterion("statistic_code is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeEqualTo(Long value) {
            addCriterion("statistic_code =", value, "statisticCode");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeNotEqualTo(Long value) {
            addCriterion("statistic_code <>", value, "statisticCode");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeGreaterThan(Long value) {
            addCriterion("statistic_code >", value, "statisticCode");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("statistic_code >=", value, "statisticCode");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeLessThan(Long value) {
            addCriterion("statistic_code <", value, "statisticCode");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeLessThanOrEqualTo(Long value) {
            addCriterion("statistic_code <=", value, "statisticCode");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeIn(List<Long> values) {
            addCriterion("statistic_code in", values, "statisticCode");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeNotIn(List<Long> values) {
            addCriterion("statistic_code not in", values, "statisticCode");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeBetween(Long value1, Long value2) {
            addCriterion("statistic_code between", value1, value2, "statisticCode");
            return (Criteria) this;
        }

        public Criteria andStatisticCodeNotBetween(Long value1, Long value2) {
            addCriterion("statistic_code not between", value1, value2, "statisticCode");
            return (Criteria) this;
        }

        public Criteria andLineNameIsNull() {
            addCriterion("line_name is null");
            return (Criteria) this;
        }

        public Criteria andLineNameIsNotNull() {
            addCriterion("line_name is not null");
            return (Criteria) this;
        }

        public Criteria andLineNameEqualTo(Integer value) {
            addCriterion("line_name =", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotEqualTo(Integer value) {
            addCriterion("line_name <>", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameGreaterThan(Integer value) {
            addCriterion("line_name >", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameGreaterThanOrEqualTo(Integer value) {
            addCriterion("line_name >=", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLessThan(Integer value) {
            addCriterion("line_name <", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameLessThanOrEqualTo(Integer value) {
            addCriterion("line_name <=", value, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameIn(List<Integer> values) {
            addCriterion("line_name in", values, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotIn(List<Integer> values) {
            addCriterion("line_name not in", values, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameBetween(Integer value1, Integer value2) {
            addCriterion("line_name between", value1, value2, "lineName");
            return (Criteria) this;
        }

        public Criteria andLineNameNotBetween(Integer value1, Integer value2) {
            addCriterion("line_name not between", value1, value2, "lineName");
            return (Criteria) this;
        }

        public Criteria andTotalsIsNull() {
            addCriterion("totals is null");
            return (Criteria) this;
        }

        public Criteria andTotalsIsNotNull() {
            addCriterion("totals is not null");
            return (Criteria) this;
        }

        public Criteria andTotalsEqualTo(Float value) {
            addCriterion("totals =", value, "totals");
            return (Criteria) this;
        }

        public Criteria andTotalsNotEqualTo(Float value) {
            addCriterion("totals <>", value, "totals");
            return (Criteria) this;
        }

        public Criteria andTotalsGreaterThan(Float value) {
            addCriterion("totals >", value, "totals");
            return (Criteria) this;
        }

        public Criteria andTotalsGreaterThanOrEqualTo(Float value) {
            addCriterion("totals >=", value, "totals");
            return (Criteria) this;
        }

        public Criteria andTotalsLessThan(Float value) {
            addCriterion("totals <", value, "totals");
            return (Criteria) this;
        }

        public Criteria andTotalsLessThanOrEqualTo(Float value) {
            addCriterion("totals <=", value, "totals");
            return (Criteria) this;
        }

        public Criteria andTotalsIn(List<Float> values) {
            addCriterion("totals in", values, "totals");
            return (Criteria) this;
        }

        public Criteria andTotalsNotIn(List<Float> values) {
            addCriterion("totals not in", values, "totals");
            return (Criteria) this;
        }

        public Criteria andTotalsBetween(Float value1, Float value2) {
            addCriterion("totals between", value1, value2, "totals");
            return (Criteria) this;
        }

        public Criteria andTotalsNotBetween(Float value1, Float value2) {
            addCriterion("totals not between", value1, value2, "totals");
            return (Criteria) this;
        }

        public Criteria andNiValueIsNull() {
            addCriterion("Ni_value is null");
            return (Criteria) this;
        }

        public Criteria andNiValueIsNotNull() {
            addCriterion("Ni_value is not null");
            return (Criteria) this;
        }

        public Criteria andNiValueEqualTo(Float value) {
            addCriterion("Ni_value =", value, "niValue");
            return (Criteria) this;
        }

        public Criteria andNiValueNotEqualTo(Float value) {
            addCriterion("Ni_value <>", value, "niValue");
            return (Criteria) this;
        }

        public Criteria andNiValueGreaterThan(Float value) {
            addCriterion("Ni_value >", value, "niValue");
            return (Criteria) this;
        }

        public Criteria andNiValueGreaterThanOrEqualTo(Float value) {
            addCriterion("Ni_value >=", value, "niValue");
            return (Criteria) this;
        }

        public Criteria andNiValueLessThan(Float value) {
            addCriterion("Ni_value <", value, "niValue");
            return (Criteria) this;
        }

        public Criteria andNiValueLessThanOrEqualTo(Float value) {
            addCriterion("Ni_value <=", value, "niValue");
            return (Criteria) this;
        }

        public Criteria andNiValueIn(List<Float> values) {
            addCriterion("Ni_value in", values, "niValue");
            return (Criteria) this;
        }

        public Criteria andNiValueNotIn(List<Float> values) {
            addCriterion("Ni_value not in", values, "niValue");
            return (Criteria) this;
        }

        public Criteria andNiValueBetween(Float value1, Float value2) {
            addCriterion("Ni_value between", value1, value2, "niValue");
            return (Criteria) this;
        }

        public Criteria andNiValueNotBetween(Float value1, Float value2) {
            addCriterion("Ni_value not between", value1, value2, "niValue");
            return (Criteria) this;
        }

        public Criteria andCoValueIsNull() {
            addCriterion("Co_value is null");
            return (Criteria) this;
        }

        public Criteria andCoValueIsNotNull() {
            addCriterion("Co_value is not null");
            return (Criteria) this;
        }

        public Criteria andCoValueEqualTo(Float value) {
            addCriterion("Co_value =", value, "coValue");
            return (Criteria) this;
        }

        public Criteria andCoValueNotEqualTo(Float value) {
            addCriterion("Co_value <>", value, "coValue");
            return (Criteria) this;
        }

        public Criteria andCoValueGreaterThan(Float value) {
            addCriterion("Co_value >", value, "coValue");
            return (Criteria) this;
        }

        public Criteria andCoValueGreaterThanOrEqualTo(Float value) {
            addCriterion("Co_value >=", value, "coValue");
            return (Criteria) this;
        }

        public Criteria andCoValueLessThan(Float value) {
            addCriterion("Co_value <", value, "coValue");
            return (Criteria) this;
        }

        public Criteria andCoValueLessThanOrEqualTo(Float value) {
            addCriterion("Co_value <=", value, "coValue");
            return (Criteria) this;
        }

        public Criteria andCoValueIn(List<Float> values) {
            addCriterion("Co_value in", values, "coValue");
            return (Criteria) this;
        }

        public Criteria andCoValueNotIn(List<Float> values) {
            addCriterion("Co_value not in", values, "coValue");
            return (Criteria) this;
        }

        public Criteria andCoValueBetween(Float value1, Float value2) {
            addCriterion("Co_value between", value1, value2, "coValue");
            return (Criteria) this;
        }

        public Criteria andCoValueNotBetween(Float value1, Float value2) {
            addCriterion("Co_value not between", value1, value2, "coValue");
            return (Criteria) this;
        }

        public Criteria andMnValueIsNull() {
            addCriterion("Mn_value is null");
            return (Criteria) this;
        }

        public Criteria andMnValueIsNotNull() {
            addCriterion("Mn_value is not null");
            return (Criteria) this;
        }

        public Criteria andMnValueEqualTo(Float value) {
            addCriterion("Mn_value =", value, "mnValue");
            return (Criteria) this;
        }

        public Criteria andMnValueNotEqualTo(Float value) {
            addCriterion("Mn_value <>", value, "mnValue");
            return (Criteria) this;
        }

        public Criteria andMnValueGreaterThan(Float value) {
            addCriterion("Mn_value >", value, "mnValue");
            return (Criteria) this;
        }

        public Criteria andMnValueGreaterThanOrEqualTo(Float value) {
            addCriterion("Mn_value >=", value, "mnValue");
            return (Criteria) this;
        }

        public Criteria andMnValueLessThan(Float value) {
            addCriterion("Mn_value <", value, "mnValue");
            return (Criteria) this;
        }

        public Criteria andMnValueLessThanOrEqualTo(Float value) {
            addCriterion("Mn_value <=", value, "mnValue");
            return (Criteria) this;
        }

        public Criteria andMnValueIn(List<Float> values) {
            addCriterion("Mn_value in", values, "mnValue");
            return (Criteria) this;
        }

        public Criteria andMnValueNotIn(List<Float> values) {
            addCriterion("Mn_value not in", values, "mnValue");
            return (Criteria) this;
        }

        public Criteria andMnValueBetween(Float value1, Float value2) {
            addCriterion("Mn_value between", value1, value2, "mnValue");
            return (Criteria) this;
        }

        public Criteria andMnValueNotBetween(Float value1, Float value2) {
            addCriterion("Mn_value not between", value1, value2, "mnValue");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table goods_in_process_statistic_by_process_total
     *
     * @mbggenerated
     */
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