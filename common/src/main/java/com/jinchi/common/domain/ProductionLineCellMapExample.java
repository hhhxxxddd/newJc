package com.jinchi.common.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductionLineCellMapExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    public ProductionLineCellMapExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_line_cell_map
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
     * This method corresponds to the database table production_line_cell_map
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
     * This method corresponds to the database table production_line_cell_map
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table production_line_cell_map
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
     * This class corresponds to the database table production_line_cell_map
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

        public Criteria andCodeEqualTo(Byte value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(Byte value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(Byte value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(Byte value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(Byte value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(Byte value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<Byte> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<Byte> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(Byte value1, Byte value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(Byte value1, Byte value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andProductionLineIsNull() {
            addCriterion("production_line is null");
            return (Criteria) this;
        }

        public Criteria andProductionLineIsNotNull() {
            addCriterion("production_line is not null");
            return (Criteria) this;
        }

        public Criteria andProductionLineEqualTo(String value) {
            addCriterion("production_line =", value, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineNotEqualTo(String value) {
            addCriterion("production_line <>", value, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineGreaterThan(String value) {
            addCriterion("production_line >", value, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineGreaterThanOrEqualTo(String value) {
            addCriterion("production_line >=", value, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineLessThan(String value) {
            addCriterion("production_line <", value, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineLessThanOrEqualTo(String value) {
            addCriterion("production_line <=", value, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineLike(String value) {
            addCriterion("production_line like", value, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineNotLike(String value) {
            addCriterion("production_line not like", value, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineIn(List<String> values) {
            addCriterion("production_line in", values, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineNotIn(List<String> values) {
            addCriterion("production_line not in", values, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineBetween(String value1, String value2) {
            addCriterion("production_line between", value1, value2, "productionLine");
            return (Criteria) this;
        }

        public Criteria andProductionLineNotBetween(String value1, String value2) {
            addCriterion("production_line not between", value1, value2, "productionLine");
            return (Criteria) this;
        }

        public Criteria andCellNumIsNull() {
            addCriterion("cell_num is null");
            return (Criteria) this;
        }

        public Criteria andCellNumIsNotNull() {
            addCriterion("cell_num is not null");
            return (Criteria) this;
        }

        public Criteria andCellNumEqualTo(String value) {
            addCriterion("cell_num =", value, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumNotEqualTo(String value) {
            addCriterion("cell_num <>", value, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumGreaterThan(String value) {
            addCriterion("cell_num >", value, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumGreaterThanOrEqualTo(String value) {
            addCriterion("cell_num >=", value, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumLessThan(String value) {
            addCriterion("cell_num <", value, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumLessThanOrEqualTo(String value) {
            addCriterion("cell_num <=", value, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumLike(String value) {
            addCriterion("cell_num like", value, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumNotLike(String value) {
            addCriterion("cell_num not like", value, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumIn(List<String> values) {
            addCriterion("cell_num in", values, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumNotIn(List<String> values) {
            addCriterion("cell_num not in", values, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumBetween(String value1, String value2) {
            addCriterion("cell_num between", value1, value2, "cellNum");
            return (Criteria) this;
        }

        public Criteria andCellNumNotBetween(String value1, String value2) {
            addCriterion("cell_num not between", value1, value2, "cellNum");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table production_line_cell_map
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
     * This class corresponds to the database table production_line_cell_map
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