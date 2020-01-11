package com.jinchi.common.dto.fireMage;

import com.jinchi.common.domain.FireMageTestItems;

public class FireMageTestItemsDTO extends FireMageTestItems {

    private boolean flag;

    private Float values = null;

    public FireMageTestItemsDTO(Long code, String name, String unit, boolean flag,Float values) {
        super(code, name, unit);
        this.flag = flag;
        this.values = values;
    }
    public FireMageTestItemsDTO(){

    }

    public FireMageTestItemsDTO(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Float getValues() {
        return values;
    }

    public void setValues(Float values) {
        this.values = values;
    }
}
