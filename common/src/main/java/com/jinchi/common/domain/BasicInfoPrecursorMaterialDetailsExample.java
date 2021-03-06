package com.jinchi.common.domain;

import java.util.ArrayList;
import java.util.List;

public class BasicInfoPrecursorMaterialDetailsExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    public BasicInfoPrecursorMaterialDetailsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_precursor_material_details
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
     * This method corresponds to the database table basic_info_precursor_material_details
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
     * This method corresponds to the database table basic_info_precursor_material_details
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basic_info_precursor_material_details
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
     * This class corresponds to the database table basic_info_precursor_material_details
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

        public Criteria andValueTypeIsNull() {
            addCriterion("value_type is null");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNotNull() {
            addCriterion("value_type is not null");
            return (Criteria) this;
        }

        public Criteria andValueTypeEqualTo(Byte value) {
            addCriterion("value_type =", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotEqualTo(Byte value) {
            addCriterion("value_type <>", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThan(Byte value) {
            addCriterion("value_type >", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("value_type >=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThan(Byte value) {
            addCriterion("value_type <", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThanOrEqualTo(Byte value) {
            addCriterion("value_type <=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeIn(List<Byte> values) {
            addCriterion("value_type in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotIn(List<Byte> values) {
            addCriterion("value_type not in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeBetween(Byte value1, Byte value2) {
            addCriterion("value_type between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("value_type not between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andTypesIsNull() {
            addCriterion("types is null");
            return (Criteria) this;
        }

        public Criteria andTypesIsNotNull() {
            addCriterion("types is not null");
            return (Criteria) this;
        }

        public Criteria andTypesEqualTo(Byte value) {
            addCriterion("types =", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesNotEqualTo(Byte value) {
            addCriterion("types <>", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesGreaterThan(Byte value) {
            addCriterion("types >", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesGreaterThanOrEqualTo(Byte value) {
            addCriterion("types >=", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesLessThan(Byte value) {
            addCriterion("types <", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesLessThanOrEqualTo(Byte value) {
            addCriterion("types <=", value, "types");
            return (Criteria) this;
        }

        public Criteria andTypesIn(List<Byte> values) {
            addCriterion("types in", values, "types");
            return (Criteria) this;
        }

        public Criteria andTypesNotIn(List<Byte> values) {
            addCriterion("types not in", values, "types");
            return (Criteria) this;
        }

        public Criteria andTypesBetween(Byte value1, Byte value2) {
            addCriterion("types between", value1, value2, "types");
            return (Criteria) this;
        }

        public Criteria andTypesNotBetween(Byte value1, Byte value2) {
            addCriterion("types not between", value1, value2, "types");
            return (Criteria) this;
        }

        public Criteria andProcessCodeIsNull() {
            addCriterion("process_code is null");
            return (Criteria) this;
        }

        public Criteria andProcessCodeIsNotNull() {
            addCriterion("process_code is not null");
            return (Criteria) this;
        }

        public Criteria andProcessCodeEqualTo(Integer value) {
            addCriterion("process_code =", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotEqualTo(Integer value) {
            addCriterion("process_code <>", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeGreaterThan(Integer value) {
            addCriterion("process_code >", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("process_code >=", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeLessThan(Integer value) {
            addCriterion("process_code <", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeLessThanOrEqualTo(Integer value) {
            addCriterion("process_code <=", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeIn(List<Integer> values) {
            addCriterion("process_code in", values, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotIn(List<Integer> values) {
            addCriterion("process_code not in", values, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeBetween(Integer value1, Integer value2) {
            addCriterion("process_code between", value1, value2, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("process_code not between", value1, value2, "processCode");
            return (Criteria) this;
        }

        public Criteria andMnIsNull() {
            addCriterion("Mn is null");
            return (Criteria) this;
        }

        public Criteria andMnIsNotNull() {
            addCriterion("Mn is not null");
            return (Criteria) this;
        }

        public Criteria andMnEqualTo(Byte value) {
            addCriterion("Mn =", value, "mn");
            return (Criteria) this;
        }

        public Criteria andMnNotEqualTo(Byte value) {
            addCriterion("Mn <>", value, "mn");
            return (Criteria) this;
        }

        public Criteria andMnGreaterThan(Byte value) {
            addCriterion("Mn >", value, "mn");
            return (Criteria) this;
        }

        public Criteria andMnGreaterThanOrEqualTo(Byte value) {
            addCriterion("Mn >=", value, "mn");
            return (Criteria) this;
        }

        public Criteria andMnLessThan(Byte value) {
            addCriterion("Mn <", value, "mn");
            return (Criteria) this;
        }

        public Criteria andMnLessThanOrEqualTo(Byte value) {
            addCriterion("Mn <=", value, "mn");
            return (Criteria) this;
        }

        public Criteria andMnIn(List<Byte> values) {
            addCriterion("Mn in", values, "mn");
            return (Criteria) this;
        }

        public Criteria andMnNotIn(List<Byte> values) {
            addCriterion("Mn not in", values, "mn");
            return (Criteria) this;
        }

        public Criteria andMnBetween(Byte value1, Byte value2) {
            addCriterion("Mn between", value1, value2, "mn");
            return (Criteria) this;
        }

        public Criteria andMnNotBetween(Byte value1, Byte value2) {
            addCriterion("Mn not between", value1, value2, "mn");
            return (Criteria) this;
        }

        public Criteria andNiIsNull() {
            addCriterion("Ni is null");
            return (Criteria) this;
        }

        public Criteria andNiIsNotNull() {
            addCriterion("Ni is not null");
            return (Criteria) this;
        }

        public Criteria andNiEqualTo(Byte value) {
            addCriterion("Ni =", value, "ni");
            return (Criteria) this;
        }

        public Criteria andNiNotEqualTo(Byte value) {
            addCriterion("Ni <>", value, "ni");
            return (Criteria) this;
        }

        public Criteria andNiGreaterThan(Byte value) {
            addCriterion("Ni >", value, "ni");
            return (Criteria) this;
        }

        public Criteria andNiGreaterThanOrEqualTo(Byte value) {
            addCriterion("Ni >=", value, "ni");
            return (Criteria) this;
        }

        public Criteria andNiLessThan(Byte value) {
            addCriterion("Ni <", value, "ni");
            return (Criteria) this;
        }

        public Criteria andNiLessThanOrEqualTo(Byte value) {
            addCriterion("Ni <=", value, "ni");
            return (Criteria) this;
        }

        public Criteria andNiIn(List<Byte> values) {
            addCriterion("Ni in", values, "ni");
            return (Criteria) this;
        }

        public Criteria andNiNotIn(List<Byte> values) {
            addCriterion("Ni not in", values, "ni");
            return (Criteria) this;
        }

        public Criteria andNiBetween(Byte value1, Byte value2) {
            addCriterion("Ni between", value1, value2, "ni");
            return (Criteria) this;
        }

        public Criteria andNiNotBetween(Byte value1, Byte value2) {
            addCriterion("Ni not between", value1, value2, "ni");
            return (Criteria) this;
        }

        public Criteria andCoIsNull() {
            addCriterion("Co is null");
            return (Criteria) this;
        }

        public Criteria andCoIsNotNull() {
            addCriterion("Co is not null");
            return (Criteria) this;
        }

        public Criteria andCoEqualTo(Byte value) {
            addCriterion("Co =", value, "co");
            return (Criteria) this;
        }

        public Criteria andCoNotEqualTo(Byte value) {
            addCriterion("Co <>", value, "co");
            return (Criteria) this;
        }

        public Criteria andCoGreaterThan(Byte value) {
            addCriterion("Co >", value, "co");
            return (Criteria) this;
        }

        public Criteria andCoGreaterThanOrEqualTo(Byte value) {
            addCriterion("Co >=", value, "co");
            return (Criteria) this;
        }

        public Criteria andCoLessThan(Byte value) {
            addCriterion("Co <", value, "co");
            return (Criteria) this;
        }

        public Criteria andCoLessThanOrEqualTo(Byte value) {
            addCriterion("Co <=", value, "co");
            return (Criteria) this;
        }

        public Criteria andCoIn(List<Byte> values) {
            addCriterion("Co in", values, "co");
            return (Criteria) this;
        }

        public Criteria andCoNotIn(List<Byte> values) {
            addCriterion("Co not in", values, "co");
            return (Criteria) this;
        }

        public Criteria andCoBetween(Byte value1, Byte value2) {
            addCriterion("Co between", value1, value2, "co");
            return (Criteria) this;
        }

        public Criteria andCoNotBetween(Byte value1, Byte value2) {
            addCriterion("Co not between", value1, value2, "co");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagIsNull() {
            addCriterion("ammonia_flag is null");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagIsNotNull() {
            addCriterion("ammonia_flag is not null");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagEqualTo(Byte value) {
            addCriterion("ammonia_flag =", value, "ammoniaFlag");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagNotEqualTo(Byte value) {
            addCriterion("ammonia_flag <>", value, "ammoniaFlag");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagGreaterThan(Byte value) {
            addCriterion("ammonia_flag >", value, "ammoniaFlag");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("ammonia_flag >=", value, "ammoniaFlag");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagLessThan(Byte value) {
            addCriterion("ammonia_flag <", value, "ammoniaFlag");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagLessThanOrEqualTo(Byte value) {
            addCriterion("ammonia_flag <=", value, "ammoniaFlag");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagIn(List<Byte> values) {
            addCriterion("ammonia_flag in", values, "ammoniaFlag");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagNotIn(List<Byte> values) {
            addCriterion("ammonia_flag not in", values, "ammoniaFlag");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagBetween(Byte value1, Byte value2) {
            addCriterion("ammonia_flag between", value1, value2, "ammoniaFlag");
            return (Criteria) this;
        }

        public Criteria andAmmoniaFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("ammonia_flag not between", value1, value2, "ammoniaFlag");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagIsNull() {
            addCriterion("alkali_flag is null");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagIsNotNull() {
            addCriterion("alkali_flag is not null");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagEqualTo(Byte value) {
            addCriterion("alkali_flag =", value, "alkaliFlag");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagNotEqualTo(Byte value) {
            addCriterion("alkali_flag <>", value, "alkaliFlag");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagGreaterThan(Byte value) {
            addCriterion("alkali_flag >", value, "alkaliFlag");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("alkali_flag >=", value, "alkaliFlag");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagLessThan(Byte value) {
            addCriterion("alkali_flag <", value, "alkaliFlag");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagLessThanOrEqualTo(Byte value) {
            addCriterion("alkali_flag <=", value, "alkaliFlag");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagIn(List<Byte> values) {
            addCriterion("alkali_flag in", values, "alkaliFlag");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagNotIn(List<Byte> values) {
            addCriterion("alkali_flag not in", values, "alkaliFlag");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagBetween(Byte value1, Byte value2) {
            addCriterion("alkali_flag between", value1, value2, "alkaliFlag");
            return (Criteria) this;
        }

        public Criteria andAlkaliFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("alkali_flag not between", value1, value2, "alkaliFlag");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table basic_info_precursor_material_details
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
     * This class corresponds to the database table basic_info_precursor_material_details
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