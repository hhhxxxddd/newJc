package com.jinchi.common.domain;

import java.util.ArrayList;
import java.util.List;

public class ProcessTraceabilityExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    public ProcessTraceabilityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
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
     * This method corresponds to the database table processtraceability
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
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
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
     * This class corresponds to the database table processtraceability
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

        public Criteria andSingletracecodeIsNull() {
            addCriterion("SingleTraceCode is null");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeIsNotNull() {
            addCriterion("SingleTraceCode is not null");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeEqualTo(String value) {
            addCriterion("SingleTraceCode =", value, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeNotEqualTo(String value) {
            addCriterion("SingleTraceCode <>", value, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeGreaterThan(String value) {
            addCriterion("SingleTraceCode >", value, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeGreaterThanOrEqualTo(String value) {
            addCriterion("SingleTraceCode >=", value, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeLessThan(String value) {
            addCriterion("SingleTraceCode <", value, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeLessThanOrEqualTo(String value) {
            addCriterion("SingleTraceCode <=", value, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeLike(String value) {
            addCriterion("SingleTraceCode like", value, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeNotLike(String value) {
            addCriterion("SingleTraceCode not like", value, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeIn(List<String> values) {
            addCriterion("SingleTraceCode in", values, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeNotIn(List<String> values) {
            addCriterion("SingleTraceCode not in", values, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeBetween(String value1, String value2) {
            addCriterion("SingleTraceCode between", value1, value2, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andSingletracecodeNotBetween(String value1, String value2) {
            addCriterion("SingleTraceCode not between", value1, value2, "singletracecode");
            return (Criteria) this;
        }

        public Criteria andQualityIsNull() {
            addCriterion("Quality is null");
            return (Criteria) this;
        }

        public Criteria andQualityIsNotNull() {
            addCriterion("Quality is not null");
            return (Criteria) this;
        }

        public Criteria andQualityEqualTo(String value) {
            addCriterion("Quality =", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotEqualTo(String value) {
            addCriterion("Quality <>", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThan(String value) {
            addCriterion("Quality >", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityGreaterThanOrEqualTo(String value) {
            addCriterion("Quality >=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThan(String value) {
            addCriterion("Quality <", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLessThanOrEqualTo(String value) {
            addCriterion("Quality <=", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityLike(String value) {
            addCriterion("Quality like", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotLike(String value) {
            addCriterion("Quality not like", value, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityIn(List<String> values) {
            addCriterion("Quality in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotIn(List<String> values) {
            addCriterion("Quality not in", values, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityBetween(String value1, String value2) {
            addCriterion("Quality between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andQualityNotBetween(String value1, String value2) {
            addCriterion("Quality not between", value1, value2, "quality");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberIsNull() {
            addCriterion("PositiveAndNegativePowderBatchNumber is null");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberIsNotNull() {
            addCriterion("PositiveAndNegativePowderBatchNumber is not null");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberEqualTo(String value) {
            addCriterion("PositiveAndNegativePowderBatchNumber =", value, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberNotEqualTo(String value) {
            addCriterion("PositiveAndNegativePowderBatchNumber <>", value, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberGreaterThan(String value) {
            addCriterion("PositiveAndNegativePowderBatchNumber >", value, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberGreaterThanOrEqualTo(String value) {
            addCriterion("PositiveAndNegativePowderBatchNumber >=", value, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberLessThan(String value) {
            addCriterion("PositiveAndNegativePowderBatchNumber <", value, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberLessThanOrEqualTo(String value) {
            addCriterion("PositiveAndNegativePowderBatchNumber <=", value, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberLike(String value) {
            addCriterion("PositiveAndNegativePowderBatchNumber like", value, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberNotLike(String value) {
            addCriterion("PositiveAndNegativePowderBatchNumber not like", value, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberIn(List<String> values) {
            addCriterion("PositiveAndNegativePowderBatchNumber in", values, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberNotIn(List<String> values) {
            addCriterion("PositiveAndNegativePowderBatchNumber not in", values, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberBetween(String value1, String value2) {
            addCriterion("PositiveAndNegativePowderBatchNumber between", value1, value2, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andPositiveandnegativepowderbatchnumberNotBetween(String value1, String value2) {
            addCriterion("PositiveAndNegativePowderBatchNumber not between", value1, value2, "positiveandnegativepowderbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberIsNull() {
            addCriterion("SolutionBatchNumber is null");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberIsNotNull() {
            addCriterion("SolutionBatchNumber is not null");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberEqualTo(String value) {
            addCriterion("SolutionBatchNumber =", value, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberNotEqualTo(String value) {
            addCriterion("SolutionBatchNumber <>", value, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberGreaterThan(String value) {
            addCriterion("SolutionBatchNumber >", value, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberGreaterThanOrEqualTo(String value) {
            addCriterion("SolutionBatchNumber >=", value, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberLessThan(String value) {
            addCriterion("SolutionBatchNumber <", value, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberLessThanOrEqualTo(String value) {
            addCriterion("SolutionBatchNumber <=", value, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberLike(String value) {
            addCriterion("SolutionBatchNumber like", value, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberNotLike(String value) {
            addCriterion("SolutionBatchNumber not like", value, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberIn(List<String> values) {
            addCriterion("SolutionBatchNumber in", values, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberNotIn(List<String> values) {
            addCriterion("SolutionBatchNumber not in", values, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberBetween(String value1, String value2) {
            addCriterion("SolutionBatchNumber between", value1, value2, "solutionbatchnumber");
            return (Criteria) this;
        }

        public Criteria andSolutionbatchnumberNotBetween(String value1, String value2) {
            addCriterion("SolutionBatchNumber not between", value1, value2, "solutionbatchnumber");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table processtraceability
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
     * This class corresponds to the database table processtraceability
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
