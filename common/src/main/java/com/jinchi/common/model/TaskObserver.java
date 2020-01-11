package com.jinchi.common.model;

/**
 * @author：XudongHu
 * @className:TaskObserver
 * @description:
 * @date:15:12 2019/2/27
 * @Modifer:
 */
public class TaskObserver implements Observer {
    protected Integer type;
    @Override
    public void receive(Object o) {

    }

    public Integer getType() {
        return type;
    }

    public TaskObserver setType(Integer type) {
        this.type = type;
        return this;
    }
}
