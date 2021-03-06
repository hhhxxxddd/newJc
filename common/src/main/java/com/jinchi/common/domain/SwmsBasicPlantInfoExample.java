package com.jinchi.common.domain;

import java.util.ArrayList;
import java.util.List;

public class SwmsBasicPlantInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    public SwmsBasicPlantInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_basic_plant_info
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
     * This method corresponds to the database table SWMS_basic_plant_info
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
     * This method corresponds to the database table SWMS_basic_plant_info
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SWMS_basic_plant_info
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
     * This class corresponds to the database table SWMS_basic_plant_info
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPlantNameIsNull() {
            addCriterion("plant_name is null");
            return (Criteria) this;
        }

        public Criteria andPlantNameIsNotNull() {
            addCriterion("plant_name is not null");
            return (Criteria) this;
        }

        public Criteria andPlantNameEqualTo(String value) {
            addCriterion("plant_name =", value, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameNotEqualTo(String value) {
            addCriterion("plant_name <>", value, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameGreaterThan(String value) {
            addCriterion("plant_name >", value, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameGreaterThanOrEqualTo(String value) {
            addCriterion("plant_name >=", value, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameLessThan(String value) {
            addCriterion("plant_name <", value, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameLessThanOrEqualTo(String value) {
            addCriterion("plant_name <=", value, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameLike(String value) {
            addCriterion("plant_name like", value, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameNotLike(String value) {
            addCriterion("plant_name not like", value, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameIn(List<String> values) {
            addCriterion("plant_name in", values, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameNotIn(List<String> values) {
            addCriterion("plant_name not in", values, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameBetween(String value1, String value2) {
            addCriterion("plant_name between", value1, value2, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantNameNotBetween(String value1, String value2) {
            addCriterion("plant_name not between", value1, value2, "plantName");
            return (Criteria) this;
        }

        public Criteria andPlantCodeIsNull() {
            addCriterion("plant_code is null");
            return (Criteria) this;
        }

        public Criteria andPlantCodeIsNotNull() {
            addCriterion("plant_code is not null");
            return (Criteria) this;
        }

        public Criteria andPlantCodeEqualTo(String value) {
            addCriterion("plant_code =", value, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeNotEqualTo(String value) {
            addCriterion("plant_code <>", value, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeGreaterThan(String value) {
            addCriterion("plant_code >", value, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("plant_code >=", value, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeLessThan(String value) {
            addCriterion("plant_code <", value, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeLessThanOrEqualTo(String value) {
            addCriterion("plant_code <=", value, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeLike(String value) {
            addCriterion("plant_code like", value, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeNotLike(String value) {
            addCriterion("plant_code not like", value, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeIn(List<String> values) {
            addCriterion("plant_code in", values, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeNotIn(List<String> values) {
            addCriterion("plant_code not in", values, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeBetween(String value1, String value2) {
            addCriterion("plant_code between", value1, value2, "plantCode");
            return (Criteria) this;
        }

        public Criteria andPlantCodeNotBetween(String value1, String value2) {
            addCriterion("plant_code not between", value1, value2, "plantCode");
            return (Criteria) this;
        }

        public Criteria andAutoFlagIsNull() {
            addCriterion("auto_flag is null");
            return (Criteria) this;
        }

        public Criteria andAutoFlagIsNotNull() {
            addCriterion("auto_flag is not null");
            return (Criteria) this;
        }

        public Criteria andAutoFlagEqualTo(Boolean value) {
            addCriterion("auto_flag =", value, "autoFlag");
            return (Criteria) this;
        }

        public Criteria andAutoFlagNotEqualTo(Boolean value) {
            addCriterion("auto_flag <>", value, "autoFlag");
            return (Criteria) this;
        }

        public Criteria andAutoFlagGreaterThan(Boolean value) {
            addCriterion("auto_flag >", value, "autoFlag");
            return (Criteria) this;
        }

        public Criteria andAutoFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("auto_flag >=", value, "autoFlag");
            return (Criteria) this;
        }

        public Criteria andAutoFlagLessThan(Boolean value) {
            addCriterion("auto_flag <", value, "autoFlag");
            return (Criteria) this;
        }

        public Criteria andAutoFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("auto_flag <=", value, "autoFlag");
            return (Criteria) this;
        }

        public Criteria andAutoFlagIn(List<Boolean> values) {
            addCriterion("auto_flag in", values, "autoFlag");
            return (Criteria) this;
        }

        public Criteria andAutoFlagNotIn(List<Boolean> values) {
            addCriterion("auto_flag not in", values, "autoFlag");
            return (Criteria) this;
        }

        public Criteria andAutoFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("auto_flag between", value1, value2, "autoFlag");
            return (Criteria) this;
        }

        public Criteria andAutoFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("auto_flag not between", value1, value2, "autoFlag");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SWMS_basic_plant_info
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
     * This class corresponds to the database table SWMS_basic_plant_info
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