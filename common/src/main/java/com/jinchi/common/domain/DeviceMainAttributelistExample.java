package com.jinchi.common.domain;

import java.util.ArrayList;
import java.util.List;

public class DeviceMainAttributelistExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    public DeviceMainAttributelistExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_attributelist
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
     * This method corresponds to the database table device_main_attributelist
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
     * This method corresponds to the database table device_main_attributelist
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table device_main_attributelist
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
     * This class corresponds to the database table device_main_attributelist
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

        public Criteria andAttValueIsNull() {
            addCriterion("att_value is null");
            return (Criteria) this;
        }

        public Criteria andAttValueIsNotNull() {
            addCriterion("att_value is not null");
            return (Criteria) this;
        }

        public Criteria andAttValueEqualTo(String value) {
            addCriterion("att_value =", value, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueNotEqualTo(String value) {
            addCriterion("att_value <>", value, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueGreaterThan(String value) {
            addCriterion("att_value >", value, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueGreaterThanOrEqualTo(String value) {
            addCriterion("att_value >=", value, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueLessThan(String value) {
            addCriterion("att_value <", value, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueLessThanOrEqualTo(String value) {
            addCriterion("att_value <=", value, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueLike(String value) {
            addCriterion("att_value like", value, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueNotLike(String value) {
            addCriterion("att_value not like", value, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueIn(List<String> values) {
            addCriterion("att_value in", values, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueNotIn(List<String> values) {
            addCriterion("att_value not in", values, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueBetween(String value1, String value2) {
            addCriterion("att_value between", value1, value2, "attValue");
            return (Criteria) this;
        }

        public Criteria andAttValueNotBetween(String value1, String value2) {
            addCriterion("att_value not between", value1, value2, "attValue");
            return (Criteria) this;
        }

        public Criteria andMainCodeIsNull() {
            addCriterion("main_code is null");
            return (Criteria) this;
        }

        public Criteria andMainCodeIsNotNull() {
            addCriterion("main_code is not null");
            return (Criteria) this;
        }

        public Criteria andMainCodeEqualTo(Long value) {
            addCriterion("main_code =", value, "mainCode");
            return (Criteria) this;
        }

        public Criteria andMainCodeNotEqualTo(Long value) {
            addCriterion("main_code <>", value, "mainCode");
            return (Criteria) this;
        }

        public Criteria andMainCodeGreaterThan(Long value) {
            addCriterion("main_code >", value, "mainCode");
            return (Criteria) this;
        }

        public Criteria andMainCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("main_code >=", value, "mainCode");
            return (Criteria) this;
        }

        public Criteria andMainCodeLessThan(Long value) {
            addCriterion("main_code <", value, "mainCode");
            return (Criteria) this;
        }

        public Criteria andMainCodeLessThanOrEqualTo(Long value) {
            addCriterion("main_code <=", value, "mainCode");
            return (Criteria) this;
        }

        public Criteria andMainCodeIn(List<Long> values) {
            addCriterion("main_code in", values, "mainCode");
            return (Criteria) this;
        }

        public Criteria andMainCodeNotIn(List<Long> values) {
            addCriterion("main_code not in", values, "mainCode");
            return (Criteria) this;
        }

        public Criteria andMainCodeBetween(Long value1, Long value2) {
            addCriterion("main_code between", value1, value2, "mainCode");
            return (Criteria) this;
        }

        public Criteria andMainCodeNotBetween(Long value1, Long value2) {
            addCriterion("main_code not between", value1, value2, "mainCode");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table device_main_attributelist
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
     * This class corresponds to the database table device_main_attributelist
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