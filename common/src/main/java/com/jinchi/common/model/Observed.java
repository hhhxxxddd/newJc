package com.jinchi.common.model;

/**
 * @author：XudongHu
 * @className:taskObserved 审核流程 被观察者
 * @description:
 * @date:14:36 2019/2/27
 * @Modifer:
 */
public interface Observed {
    void registerObserver(Observer o); //观察者注册
    void removeObserver(Observer o);  //观察者移除
    void notifyObserver();                //观察者通知
}
