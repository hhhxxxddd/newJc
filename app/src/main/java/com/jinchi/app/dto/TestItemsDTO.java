package com.jinchi.app.dto;

import com.jinchi.app.domain.TestItem;

public class TestItemsDTO extends TestItem {
    String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
