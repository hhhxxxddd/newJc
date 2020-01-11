package com.jinchi.common.dto.fireMage;

import com.jinchi.common.domain.FireMageDetectInfo;

import java.util.List;

public class FireMageDetectInfoDTO {

    private FireMageDetectInfo head;

    private String deptName;

    private String checkPeople;

    private String itemsSpace;

    private String batch;

    private String status;

    public String getStatus() {
        return status;
    };

    public void setStatus(String status) {
        this.status = status;
    }

    private List<FireMageTestItemsDTO> items;

    public FireMageDetectInfo getHead() {
        return head;
    }

    public void setHead(FireMageDetectInfo head) {
        this.head = head;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCheckPeople() {
        return checkPeople;
    }

    public void setCheckPeople(String checkPeople) {
        this.checkPeople = checkPeople;
    }

    public String getItemsSpace() {
        return itemsSpace;
    }

    public void setItemsSpace(String itemsSpace) {
        this.itemsSpace = itemsSpace;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public List<FireMageTestItemsDTO> getItems() {
        return items;
    }

    public void setItems(List<FireMageTestItemsDTO> items) {
        this.items = items;
    }
}
