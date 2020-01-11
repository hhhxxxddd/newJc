package com.jinchi.common.model;

import java.util.List;

/**
 * @author：XudongHu
 * @className:taskServer  审核后 通知服务
 * @description:
 * @date:14:40 2019/2/27
 * @Modifer:
 */
public class TaskServer implements Observed {
    //观察者集合
    private List<Observer> listObserver;
    private TaskMessage taskMessage;

    @Override
    public void registerObserver(Observer o) {
        listObserver.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if(!listObserver.isEmpty()) listObserver.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(int i = 0;i<listObserver.size();i++){
            Observer observer = listObserver.get(i);
            observer.receive(taskMessage);
        }
    }

    public void sendMessage(TaskMessage taskMessage){
        this.taskMessage = taskMessage;
        notifyObserver();
    }
}
