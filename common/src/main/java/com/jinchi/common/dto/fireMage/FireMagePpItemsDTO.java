package com.jinchi.common.dto.fireMage;

import com.jinchi.common.domain.FireMagePpItems;
import com.jinchi.common.domain.FireMageTestItems;

import java.util.List;

public class FireMagePpItemsDTO {

    private FireMagePpItems head;

    private List<FireMageTestItemsDTO> items;

    public FireMagePpItems getHead() {
        return head;
    }

    public void setHead(FireMagePpItems head) {
        this.head = head;
    }

    public List<FireMageTestItemsDTO> getItems() {
        return items;
    }

    public void setItems(List<FireMageTestItemsDTO> items) {
        this.items = items;
    }


}
