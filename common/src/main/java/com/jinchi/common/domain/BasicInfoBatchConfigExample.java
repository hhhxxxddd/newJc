package com.jinchi.common.domain;

import java.util.ArrayList;
import java.util.List;

public class BasicInfoBatchConfigExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    public BasicInfoBatchConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_batch_config
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
     * This method corresponds to the database table basic_info_batch_config
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
     * This method corresponds to the database table basic_info_batch_config
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_batch_config
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
     * This class corresponds to the database table basic_info_batch_config
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

        public Criteria andCodeEqualTo(Integer value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(Integer value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(Integer value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(Integer value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(Integer value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<Integer> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<Integer> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(Integer value1, Integer value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andBatchNumIsNull() {
            addCriterion("batch_num is null");
            return (Criteria) this;
        }

        public Criteria andBatchNumIsNotNull() {
            addCriterion("batch_num is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNumEqualTo(Byte value) {
            addCriterion("batch_num =", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotEqualTo(Byte value) {
            addCriterion("batch_num <>", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumGreaterThan(Byte value) {
            addCriterion("batch_num >", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumGreaterThanOrEqualTo(Byte value) {
            addCriterion("batch_num >=", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumLessThan(Byte value) {
            addCriterion("batch_num <", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumLessThanOrEqualTo(Byte value) {
            addCriterion("batch_num <=", value, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumIn(List<Byte> values) {
            addCriterion("batch_num in", values, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotIn(List<Byte> values) {
            addCriterion("batch_num not in", values, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumBetween(Byte value1, Byte value2) {
            addCriterion("batch_num between", value1, value2, "batchNum");
            return (Criteria) this;
        }

        public Criteria andBatchNumNotBetween(Byte value1, Byte value2) {
            addCriterion("batch_num not between", value1, value2, "batchNum");
            return (Criteria) this;
        }

        public Criteria andHcValueIsNull() {
            addCriterion("HC_value is null");
            return (Criteria) this;
        }

        public Criteria andHcValueIsNotNull() {
            addCriterion("HC_value is not null");
            return (Criteria) this;
        }

        public Criteria andHcValueEqualTo(Float value) {
            addCriterion("HC_value =", value, "hcValue");
            return (Criteria) this;
        }

        public Criteria andHcValueNotEqualTo(Float value) {
            addCriterion("HC_value <>", value, "hcValue");
            return (Criteria) this;
        }

        public Criteria andHcValueGreaterThan(Float value) {
            addCriterion("HC_value >", value, "hcValue");
            return (Criteria) this;
        }

        public Criteria andHcValueGreaterThanOrEqualTo(Float value) {
            addCriterion("HC_value >=", value, "hcValue");
            return (Criteria) this;
        }

        public Criteria andHcValueLessThan(Float value) {
            addCriterion("HC_value <", value, "hcValue");
            return (Criteria) this;
        }

        public Criteria andHcValueLessThanOrEqualTo(Float value) {
            addCriterion("HC_value <=", value, "hcValue");
            return (Criteria) this;
        }

        public Criteria andHcValueIn(List<Float> values) {
            addCriterion("HC_value in", values, "hcValue");
            return (Criteria) this;
        }

        public Criteria andHcValueNotIn(List<Float> values) {
            addCriterion("HC_value not in", values, "hcValue");
            return (Criteria) this;
        }

        public Criteria andHcValueBetween(Float value1, Float value2) {
            addCriterion("HC_value between", value1, value2, "hcValue");
            return (Criteria) this;
        }

        public Criteria andHcValueNotBetween(Float value1, Float value2) {
            addCriterion("HC_value not between", value1, value2, "hcValue");
            return (Criteria) this;
        }

        public Criteria andXdValueIsNull() {
            addCriterion("XD_value is null");
            return (Criteria) this;
        }

        public Criteria andXdValueIsNotNull() {
            addCriterion("XD_value is not null");
            return (Criteria) this;
        }

        public Criteria andXdValueEqualTo(Float value) {
            addCriterion("XD_value =", value, "xdValue");
            return (Criteria) this;
        }

        public Criteria andXdValueNotEqualTo(Float value) {
            addCriterion("XD_value <>", value, "xdValue");
            return (Criteria) this;
        }

        public Criteria andXdValueGreaterThan(Float value) {
            addCriterion("XD_value >", value, "xdValue");
            return (Criteria) this;
        }

        public Criteria andXdValueGreaterThanOrEqualTo(Float value) {
            addCriterion("XD_value >=", value, "xdValue");
            return (Criteria) this;
        }

        public Criteria andXdValueLessThan(Float value) {
            addCriterion("XD_value <", value, "xdValue");
            return (Criteria) this;
        }

        public Criteria andXdValueLessThanOrEqualTo(Float value) {
            addCriterion("XD_value <=", value, "xdValue");
            return (Criteria) this;
        }

        public Criteria andXdValueIn(List<Float> values) {
            addCriterion("XD_value in", values, "xdValue");
            return (Criteria) this;
        }

        public Criteria andXdValueNotIn(List<Float> values) {
            addCriterion("XD_value not in", values, "xdValue");
            return (Criteria) this;
        }

        public Criteria andXdValueBetween(Float value1, Float value2) {
            addCriterion("XD_value between", value1, value2, "xdValue");
            return (Criteria) this;
        }

        public Criteria andXdValueNotBetween(Float value1, Float value2) {
            addCriterion("XD_value not between", value1, value2, "xdValue");
            return (Criteria) this;
        }

        public Criteria andHgValueIsNull() {
            addCriterion("HG_value is null");
            return (Criteria) this;
        }

        public Criteria andHgValueIsNotNull() {
            addCriterion("HG_value is not null");
            return (Criteria) this;
        }

        public Criteria andHgValueEqualTo(Float value) {
            addCriterion("HG_value =", value, "hgValue");
            return (Criteria) this;
        }

        public Criteria andHgValueNotEqualTo(Float value) {
            addCriterion("HG_value <>", value, "hgValue");
            return (Criteria) this;
        }

        public Criteria andHgValueGreaterThan(Float value) {
            addCriterion("HG_value >", value, "hgValue");
            return (Criteria) this;
        }

        public Criteria andHgValueGreaterThanOrEqualTo(Float value) {
            addCriterion("HG_value >=", value, "hgValue");
            return (Criteria) this;
        }

        public Criteria andHgValueLessThan(Float value) {
            addCriterion("HG_value <", value, "hgValue");
            return (Criteria) this;
        }

        public Criteria andHgValueLessThanOrEqualTo(Float value) {
            addCriterion("HG_value <=", value, "hgValue");
            return (Criteria) this;
        }

        public Criteria andHgValueIn(List<Float> values) {
            addCriterion("HG_value in", values, "hgValue");
            return (Criteria) this;
        }

        public Criteria andHgValueNotIn(List<Float> values) {
            addCriterion("HG_value not in", values, "hgValue");
            return (Criteria) this;
        }

        public Criteria andHgValueBetween(Float value1, Float value2) {
            addCriterion("HG_value between", value1, value2, "hgValue");
            return (Criteria) this;
        }

        public Criteria andHgValueNotBetween(Float value1, Float value2) {
            addCriterion("HG_value not between", value1, value2, "hgValue");
            return (Criteria) this;
        }

        public Criteria andBzValueIsNull() {
            addCriterion("BZ_value is null");
            return (Criteria) this;
        }

        public Criteria andBzValueIsNotNull() {
            addCriterion("BZ_value is not null");
            return (Criteria) this;
        }

        public Criteria andBzValueEqualTo(Float value) {
            addCriterion("BZ_value =", value, "bzValue");
            return (Criteria) this;
        }

        public Criteria andBzValueNotEqualTo(Float value) {
            addCriterion("BZ_value <>", value, "bzValue");
            return (Criteria) this;
        }

        public Criteria andBzValueGreaterThan(Float value) {
            addCriterion("BZ_value >", value, "bzValue");
            return (Criteria) this;
        }

        public Criteria andBzValueGreaterThanOrEqualTo(Float value) {
            addCriterion("BZ_value >=", value, "bzValue");
            return (Criteria) this;
        }

        public Criteria andBzValueLessThan(Float value) {
            addCriterion("BZ_value <", value, "bzValue");
            return (Criteria) this;
        }

        public Criteria andBzValueLessThanOrEqualTo(Float value) {
            addCriterion("BZ_value <=", value, "bzValue");
            return (Criteria) this;
        }

        public Criteria andBzValueIn(List<Float> values) {
            addCriterion("BZ_value in", values, "bzValue");
            return (Criteria) this;
        }

        public Criteria andBzValueNotIn(List<Float> values) {
            addCriterion("BZ_value not in", values, "bzValue");
            return (Criteria) this;
        }

        public Criteria andBzValueBetween(Float value1, Float value2) {
            addCriterion("BZ_value between", value1, value2, "bzValue");
            return (Criteria) this;
        }

        public Criteria andBzValueNotBetween(Float value1, Float value2) {
            addCriterion("BZ_value not between", value1, value2, "bzValue");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table basic_info_batch_config
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
     * This class corresponds to the database table basic_info_batch_config
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