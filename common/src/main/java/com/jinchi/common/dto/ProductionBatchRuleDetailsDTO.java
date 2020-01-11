package com.jinchi.common.dto;

import java.util.Date;
import java.util.List;

public class ProductionBatchRuleDetailsDTO {


    private List<String> year;


    private List<String> productionType;


    private List<String> month;


    private List<String> serialNumber;

    private List<String> productionModel;


    private List<String> productionLine;


    private List<String> material;


    private List<String> process;


    private List<String> slotnum;


    private List<String> cellNum;

    public ProductionBatchRuleDetailsDTO() {

    }

    public ProductionBatchRuleDetailsDTO(List<String> year, List<String> productionType, List<String> month, List<String> serialNumber, List<String> productionModel, List<String> productionLine, List<String> material, List<String> process, List<String> slotnum, List<String> cellNum) {
        this.year = year;
        this.productionType = productionType;
        this.month = month;
        this.serialNumber = serialNumber;
        this.productionModel = productionModel;
        this.productionLine = productionLine;
        this.material = material;
        this.process = process;
        this.slotnum = slotnum;
        this.cellNum = cellNum;
    }

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    public List<String> getProductionType() {
        return productionType;
    }

    public void setProductionType(List<String> productionType) {
        this.productionType = productionType;
    }

    public List<String> getMonth() {
        return month;
    }

    public void setMonth(List<String> month) {
        this.month = month;
    }

    public List<String> getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(List<String> serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<String> getProductionModel() {
        return productionModel;
    }

    public void setProductionModel(List<String> productionModel) {
        this.productionModel = productionModel;
    }

    public List<String> getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(List<String> productionLine) {
        this.productionLine = productionLine;
    }

    public List<String> getMaterial() {
        return material;
    }

    public void setMaterial(List<String> material) {
        this.material = material;
    }

    public List<String> getProcess() {
        return process;
    }

    public void setProcess(List<String> process) {
        this.process = process;
    }

    public List<String> getSlotnum() {
        return slotnum;
    }

    public void setSlotnum(List<String> slotnum) {
        this.slotnum = slotnum;
    }

    public List<String> getCellNum() {
        return cellNum;
    }

    public void setCellNum(List<String> cellNum) {
        this.cellNum = cellNum;
    }
}