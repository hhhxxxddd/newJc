package com.jinchi.common.domain;

import java.util.ArrayList;
import java.util.List;

public class BasicInfoRawmaterialExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    public BasicInfoRawmaterialExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_rawmaterial
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
     * This method corresponds to the database table basic_info_rawmaterial
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
     * This method corresponds to the database table basic_info_rawmaterial
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_rawmaterial
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
     * This class corresponds to the database table basic_info_rawmaterial
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

        public Criteria andMaterialNameIsNull() {
            addCriterion("material_name is null");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIsNotNull() {
            addCriterion("material_name is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialNameEqualTo(String value) {
            addCriterion("material_name =", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotEqualTo(String value) {
            addCriterion("material_name <>", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameGreaterThan(String value) {
            addCriterion("material_name >", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameGreaterThanOrEqualTo(String value) {
            addCriterion("material_name >=", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLessThan(String value) {
            addCriterion("material_name <", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLessThanOrEqualTo(String value) {
            addCriterion("material_name <=", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLike(String value) {
            addCriterion("material_name like", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotLike(String value) {
            addCriterion("material_name not like", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIn(List<String> values) {
            addCriterion("material_name in", values, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotIn(List<String> values) {
            addCriterion("material_name not in", values, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameBetween(String value1, String value2) {
            addCriterion("material_name between", value1, value2, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotBetween(String value1, String value2) {
            addCriterion("material_name not between", value1, value2, "materialName");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(Byte value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(Byte value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(Byte value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(Byte value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(Byte value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<Byte> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<Byte> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(Byte value1, Byte value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeIsNull() {
            addCriterion("phase_type is null");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeIsNotNull() {
            addCriterion("phase_type is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeEqualTo(Byte value) {
            addCriterion("phase_type =", value, "phaseType");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeNotEqualTo(Byte value) {
            addCriterion("phase_type <>", value, "phaseType");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeGreaterThan(Byte value) {
            addCriterion("phase_type >", value, "phaseType");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("phase_type >=", value, "phaseType");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeLessThan(Byte value) {
            addCriterion("phase_type <", value, "phaseType");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeLessThanOrEqualTo(Byte value) {
            addCriterion("phase_type <=", value, "phaseType");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeIn(List<Byte> values) {
            addCriterion("phase_type in", values, "phaseType");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeNotIn(List<Byte> values) {
            addCriterion("phase_type not in", values, "phaseType");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeBetween(Byte value1, Byte value2) {
            addCriterion("phase_type between", value1, value2, "phaseType");
            return (Criteria) this;
        }

        public Criteria andPhaseTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("phase_type not between", value1, value2, "phaseType");
            return (Criteria) this;
        }

        public Criteria andPickingTypeIsNull() {
            addCriterion("picking_type is null");
            return (Criteria) this;
        }

        public Criteria andPickingTypeIsNotNull() {
            addCriterion("picking_type is not null");
            return (Criteria) this;
        }

        public Criteria andPickingTypeEqualTo(Byte value) {
            addCriterion("picking_type =", value, "pickingType");
            return (Criteria) this;
        }

        public Criteria andPickingTypeNotEqualTo(Byte value) {
            addCriterion("picking_type <>", value, "pickingType");
            return (Criteria) this;
        }

        public Criteria andPickingTypeGreaterThan(Byte value) {
            addCriterion("picking_type >", value, "pickingType");
            return (Criteria) this;
        }

        public Criteria andPickingTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("picking_type >=", value, "pickingType");
            return (Criteria) this;
        }

        public Criteria andPickingTypeLessThan(Byte value) {
            addCriterion("picking_type <", value, "pickingType");
            return (Criteria) this;
        }

        public Criteria andPickingTypeLessThanOrEqualTo(Byte value) {
            addCriterion("picking_type <=", value, "pickingType");
            return (Criteria) this;
        }

        public Criteria andPickingTypeIn(List<Byte> values) {
            addCriterion("picking_type in", values, "pickingType");
            return (Criteria) this;
        }

        public Criteria andPickingTypeNotIn(List<Byte> values) {
            addCriterion("picking_type not in", values, "pickingType");
            return (Criteria) this;
        }

        public Criteria andPickingTypeBetween(Byte value1, Byte value2) {
            addCriterion("picking_type between", value1, value2, "pickingType");
            return (Criteria) this;
        }

        public Criteria andPickingTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("picking_type not between", value1, value2, "pickingType");
            return (Criteria) this;
        }

        public Criteria andTypesCodeIsNull() {
            addCriterion("types_code is null");
            return (Criteria) this;
        }

        public Criteria andTypesCodeIsNotNull() {
            addCriterion("types_code is not null");
            return (Criteria) this;
        }

        public Criteria andTypesCodeEqualTo(Integer value) {
            addCriterion("types_code =", value, "typesCode");
            return (Criteria) this;
        }

        public Criteria andTypesCodeNotEqualTo(Integer value) {
            addCriterion("types_code <>", value, "typesCode");
            return (Criteria) this;
        }

        public Criteria andTypesCodeGreaterThan(Integer value) {
            addCriterion("types_code >", value, "typesCode");
            return (Criteria) this;
        }

        public Criteria andTypesCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("types_code >=", value, "typesCode");
            return (Criteria) this;
        }

        public Criteria andTypesCodeLessThan(Integer value) {
            addCriterion("types_code <", value, "typesCode");
            return (Criteria) this;
        }

        public Criteria andTypesCodeLessThanOrEqualTo(Integer value) {
            addCriterion("types_code <=", value, "typesCode");
            return (Criteria) this;
        }

        public Criteria andTypesCodeIn(List<Integer> values) {
            addCriterion("types_code in", values, "typesCode");
            return (Criteria) this;
        }

        public Criteria andTypesCodeNotIn(List<Integer> values) {
            addCriterion("types_code not in", values, "typesCode");
            return (Criteria) this;
        }

        public Criteria andTypesCodeBetween(Integer value1, Integer value2) {
            addCriterion("types_code between", value1, value2, "typesCode");
            return (Criteria) this;
        }

        public Criteria andTypesCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("types_code not between", value1, value2, "typesCode");
            return (Criteria) this;
        }

        public Criteria andNiFlagIsNull() {
            addCriterion("ni_flag is null");
            return (Criteria) this;
        }

        public Criteria andNiFlagIsNotNull() {
            addCriterion("ni_flag is not null");
            return (Criteria) this;
        }

        public Criteria andNiFlagEqualTo(Boolean value) {
            addCriterion("ni_flag =", value, "niFlag");
            return (Criteria) this;
        }

        public Criteria andNiFlagNotEqualTo(Boolean value) {
            addCriterion("ni_flag <>", value, "niFlag");
            return (Criteria) this;
        }

        public Criteria andNiFlagGreaterThan(Boolean value) {
            addCriterion("ni_flag >", value, "niFlag");
            return (Criteria) this;
        }

        public Criteria andNiFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("ni_flag >=", value, "niFlag");
            return (Criteria) this;
        }

        public Criteria andNiFlagLessThan(Boolean value) {
            addCriterion("ni_flag <", value, "niFlag");
            return (Criteria) this;
        }

        public Criteria andNiFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("ni_flag <=", value, "niFlag");
            return (Criteria) this;
        }

        public Criteria andNiFlagIn(List<Boolean> values) {
            addCriterion("ni_flag in", values, "niFlag");
            return (Criteria) this;
        }

        public Criteria andNiFlagNotIn(List<Boolean> values) {
            addCriterion("ni_flag not in", values, "niFlag");
            return (Criteria) this;
        }

        public Criteria andNiFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("ni_flag between", value1, value2, "niFlag");
            return (Criteria) this;
        }

        public Criteria andNiFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("ni_flag not between", value1, value2, "niFlag");
            return (Criteria) this;
        }

        public Criteria andCoFlagIsNull() {
            addCriterion("co_flag is null");
            return (Criteria) this;
        }

        public Criteria andCoFlagIsNotNull() {
            addCriterion("co_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCoFlagEqualTo(Boolean value) {
            addCriterion("co_flag =", value, "coFlag");
            return (Criteria) this;
        }

        public Criteria andCoFlagNotEqualTo(Boolean value) {
            addCriterion("co_flag <>", value, "coFlag");
            return (Criteria) this;
        }

        public Criteria andCoFlagGreaterThan(Boolean value) {
            addCriterion("co_flag >", value, "coFlag");
            return (Criteria) this;
        }

        public Criteria andCoFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("co_flag >=", value, "coFlag");
            return (Criteria) this;
        }

        public Criteria andCoFlagLessThan(Boolean value) {
            addCriterion("co_flag <", value, "coFlag");
            return (Criteria) this;
        }

        public Criteria andCoFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("co_flag <=", value, "coFlag");
            return (Criteria) this;
        }

        public Criteria andCoFlagIn(List<Boolean> values) {
            addCriterion("co_flag in", values, "coFlag");
            return (Criteria) this;
        }

        public Criteria andCoFlagNotIn(List<Boolean> values) {
            addCriterion("co_flag not in", values, "coFlag");
            return (Criteria) this;
        }

        public Criteria andCoFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("co_flag between", value1, value2, "coFlag");
            return (Criteria) this;
        }

        public Criteria andCoFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("co_flag not between", value1, value2, "coFlag");
            return (Criteria) this;
        }

        public Criteria andMnFlagIsNull() {
            addCriterion("mn_flag is null");
            return (Criteria) this;
        }

        public Criteria andMnFlagIsNotNull() {
            addCriterion("mn_flag is not null");
            return (Criteria) this;
        }

        public Criteria andMnFlagEqualTo(Boolean value) {
            addCriterion("mn_flag =", value, "mnFlag");
            return (Criteria) this;
        }

        public Criteria andMnFlagNotEqualTo(Boolean value) {
            addCriterion("mn_flag <>", value, "mnFlag");
            return (Criteria) this;
        }

        public Criteria andMnFlagGreaterThan(Boolean value) {
            addCriterion("mn_flag >", value, "mnFlag");
            return (Criteria) this;
        }

        public Criteria andMnFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("mn_flag >=", value, "mnFlag");
            return (Criteria) this;
        }

        public Criteria andMnFlagLessThan(Boolean value) {
            addCriterion("mn_flag <", value, "mnFlag");
            return (Criteria) this;
        }

        public Criteria andMnFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("mn_flag <=", value, "mnFlag");
            return (Criteria) this;
        }

        public Criteria andMnFlagIn(List<Boolean> values) {
            addCriterion("mn_flag in", values, "mnFlag");
            return (Criteria) this;
        }

        public Criteria andMnFlagNotIn(List<Boolean> values) {
            addCriterion("mn_flag not in", values, "mnFlag");
            return (Criteria) this;
        }

        public Criteria andMnFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("mn_flag between", value1, value2, "mnFlag");
            return (Criteria) this;
        }

        public Criteria andMnFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("mn_flag not between", value1, value2, "mnFlag");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table basic_info_rawmaterial
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
     * This class corresponds to the database table basic_info_rawmaterial
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