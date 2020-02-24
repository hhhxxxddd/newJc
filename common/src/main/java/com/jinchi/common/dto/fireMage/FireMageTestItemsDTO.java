package com.jinchi.common.dto.fireMage;

import com.jinchi.common.domain.FireMageTestItems;

public class FireMageTestItemsDTO extends FireMageTestItems {

    private boolean flag;

    private Object values = null;

    public FireMageTestItemsDTO(Long code, String name, String unit, boolean flag,Object values) {
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

    public Object getValues() {
        return values;
    }

    public void setValues(Object values) {
        this.values = values;
    }
}
