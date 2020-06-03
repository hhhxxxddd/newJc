package com.jinchi.common.domain;

public class ProcessTraceability {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column processtraceability.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column processtraceability.SingleTraceCode
     *
     * @mbggenerated
     */
    private String singletracecode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column processtraceability.Quality
     *
     * @mbggenerated
     */
    private String quality;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column processtraceability.PositiveAndNegativePowderBatchNumber
     *
     * @mbggenerated
     */
    private String positiveandnegativepowderbatchnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column processtraceability.SolutionBatchNumber
     *
     * @mbggenerated
     */
    private String solutionbatchnumber;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    public ProcessTraceability(Long id, String singletracecode, String quality, String positiveandnegativepowderbatchnumber, String solutionbatchnumber) {
        this.id = id;
        this.singletracecode = singletracecode;
        this.quality = quality;
        this.positiveandnegativepowderbatchnumber = positiveandnegativepowderbatchnumber;
        this.solutionbatchnumber = solutionbatchnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table processtraceability
     *
     * @mbggenerated
     */
    public ProcessTraceability() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column processtraceability.id
     *
     * @return the value of processtraceability.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column processtraceability.id
     *
     * @param id the value for processtraceability.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column processtraceability.SingleTraceCode
     *
     * @return the value of processtraceability.SingleTraceCode
     *
     * @mbggenerated
     */
    public String getSingletracecode() {
        return singletracecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column processtraceability.SingleTraceCode
     *
     * @param singletracecode the value for processtraceability.SingleTraceCode
     *
     * @mbggenerated
     */
    public void setSingletracecode(String singletracecode) {
        this.singletracecode = singletracecode == null ? null : singletracecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column processtraceability.Quality
     *
     * @return the value of processtraceability.Quality
     *
     * @mbggenerated
     */
    public String getQuality() {
        return quality;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column processtraceability.Quality
     *
     * @param quality the value for processtraceability.Quality
     *
     * @mbggenerated
     */
    public void setQuality(String quality) {
        this.quality = quality == null ? null : quality.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column processtraceability.PositiveAndNegativePowderBatchNumber
     *
     * @return the value of processtraceability.PositiveAndNegativePowderBatchNumber
     *
     * @mbggenerated
     */
    public String getPositiveandnegativepowderbatchnumber() {
        return positiveandnegativepowderbatchnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column processtraceability.PositiveAndNegativePowderBatchNumber
     *
     * @param positiveandnegativepowderbatchnumber the value for processtraceability.PositiveAndNegativePowderBatchNumber
     *
     * @mbggenerated
     */
    public void setPositiveandnegativepowderbatchnumber(String positiveandnegativepowderbatchnumber) {
        this.positiveandnegativepowderbatchnumber = positiveandnegativepowderbatchnumber == null ? null : positiveandnegativepowderbatchnumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column processtraceability.SolutionBatchNumber
     *
     * @return the value of processtraceability.SolutionBatchNumber
     *
     * @mbggenerated
     */
    public String getSolutionbatchnumber() {
        return solutionbatchnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column processtraceability.SolutionBatchNumber
     *
     * @param solutionbatchnumber the value for processtraceability.SolutionBatchNumber
     *
     * @mbggenerated
     */
    public void setSolutionbatchnumber(String solutionbatchnumber) {
        this.solutionbatchnumber = solutionbatchnumber == null ? null : solutionbatchnumber.trim();
    }
}