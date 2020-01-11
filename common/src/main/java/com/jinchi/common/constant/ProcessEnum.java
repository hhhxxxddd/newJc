package com.jinchi.common.constant;

public enum ProcessEnum {

    MONCRYSTAL("单晶体",1),
    MIXTURE("混合盐",2),
    COMPOUND("合成",3),
    AGEING("陈化",4),
    DRYING("烘干",5),
    OTHER("其他",6),
    STOCKIN("入库量",7),
    TANK("罐区",8),
    WORKSHOP("车间",9),
    CONSUMPTION("辅材消耗量",10);

    private String processName; //工序名称
    private Integer processId;   //工序id

    ProcessEnum(String processName,Integer processId){
        this.processId = processId;
        this.processName = processName;
    }
    public Integer getProcessId() {
        return processId;
    }

    public String getProcessName(){return processName;}
}
