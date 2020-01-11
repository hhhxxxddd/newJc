package com.jinchi.common.dto;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 22:37
 * @description:
 **/

public class AnodeMatPlcMapDTO {
    Integer code;
    Integer processCode;
    String processName;
    Integer materialCode;
    String materialName;
    Integer plcCode;
    String plcAddress;
    Integer lineCode;
    String name;
    String materialAtt;

    public String getMaterialAtt() {
        return materialAtt;
    }

    public void setMaterialAtt(String materialAtt) {
        this.materialAtt = materialAtt;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getProcessCode() {
        return processCode;
    }

    public void setProcessCode(Integer processCode) {
        this.processCode = processCode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Integer getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(Integer materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getPlcCode() {
        return plcCode;
    }

    public void setPlcCode(Integer plcCode) {
        this.plcCode = plcCode;
    }

    public String getPlcAddress() {
        return plcAddress;
    }

    public void setPlcAddress(String plcAddress) {
        this.plcAddress = plcAddress;
    }

    public Integer getLineCode() {
        return lineCode;
    }

    public void setLineCode(Integer lineCode) {
        this.lineCode = lineCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
