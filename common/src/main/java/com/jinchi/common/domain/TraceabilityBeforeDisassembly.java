package com.jinchi.common.domain;

public class TraceabilityBeforeDisassembly {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column traceabilitybeforedisassembly.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column traceabilitybeforedisassembly.BatteryPackTraceCode
     *
     * @mbggenerated
     */
    private String batterypacktracecode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column traceabilitybeforedisassembly.ModuleTraceCode
     *
     * @mbggenerated
     */
    private String moduletracecode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column traceabilitybeforedisassembly.SingleTraceCode
     *
     * @mbggenerated
     */
    private String singletracecode;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traceabilitybeforedisassembly
     *
     * @mbggenerated
     */
    public TraceabilityBeforeDisassembly(Long id, String batterypacktracecode, String moduletracecode, String singletracecode) {
        this.id = id;
        this.batterypacktracecode = batterypacktracecode;
        this.moduletracecode = moduletracecode;
        this.singletracecode = singletracecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table traceabilitybeforedisassembly
     *
     * @mbggenerated
     */
    public TraceabilityBeforeDisassembly() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column traceabilitybeforedisassembly.id
     *
     * @return the value of traceabilitybeforedisassembly.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column traceabilitybeforedisassembly.id
     *
     * @param id the value for traceabilitybeforedisassembly.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column traceabilitybeforedisassembly.BatteryPackTraceCode
     *
     * @return the value of traceabilitybeforedisassembly.BatteryPackTraceCode
     *
     * @mbggenerated
     */
    public String getBatterypacktracecode() {
        return batterypacktracecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column traceabilitybeforedisassembly.BatteryPackTraceCode
     *
     * @param batterypacktracecode the value for traceabilitybeforedisassembly.BatteryPackTraceCode
     *
     * @mbggenerated
     */
    public void setBatterypacktracecode(String batterypacktracecode) {
        this.batterypacktracecode = batterypacktracecode == null ? null : batterypacktracecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column traceabilitybeforedisassembly.ModuleTraceCode
     *
     * @return the value of traceabilitybeforedisassembly.ModuleTraceCode
     *
     * @mbggenerated
     */
    public String getModuletracecode() {
        return moduletracecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column traceabilitybeforedisassembly.ModuleTraceCode
     *
     * @param moduletracecode the value for traceabilitybeforedisassembly.ModuleTraceCode
     *
     * @mbggenerated
     */
    public void setModuletracecode(String moduletracecode) {
        this.moduletracecode = moduletracecode == null ? null : moduletracecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column traceabilitybeforedisassembly.SingleTraceCode
     *
     * @return the value of traceabilitybeforedisassembly.SingleTraceCode
     *
     * @mbggenerated
     */
    public String getSingletracecode() {
        return singletracecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column traceabilitybeforedisassembly.SingleTraceCode
     *
     * @param singletracecode the value for traceabilitybeforedisassembly.SingleTraceCode
     *
     * @mbggenerated
     */
    public void setSingletracecode(String singletracecode) {
        this.singletracecode = singletracecode == null ? null : singletracecode.trim();
    }
}
