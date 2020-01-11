package com.jinchi.app.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:ValidMemoryAddress
 * @description: 记录上传文件的地址
 * @date:14:58 2019/1/13
 * @Modifer:
 */
public enum AddressEnum {

    //上传文件的文件夹
    UNKNOWN_ADDRESS(0,"UNKNOWN_PATH","/unknown/"), //未知路径
    EQUIPMENT_ARCHIVE_ABS_ADDRESS(1,"ARCHIVE_ABS","/equipmentArchive/"), //设备档案
    EQUIPMENT_INSTRUCTOR_ABS_ADDRESS(2,"GUIDE_ABS","/equipmentInstructor/"), //设备指导
    DEVICE_SPOTCHECK_PHOTO(3,"SPOTPHOTO_ABS","/spotCheck/model"),//设备点检模板
    DEVICE_SPOTCHECK_RECORD(4,"SPOTRECORD_ABS","/spotCheck/record");//设备点检模板
    Integer code;
    String name;
    String path;

    private static final Map<Integer,AddressEnum> MAP = new HashMap<>();
    static {
        AddressEnum.values();
        for(AddressEnum addressEnum:AddressEnum.values()){
            MAP.put(addressEnum.getCode(),addressEnum);
        }
    }

    AddressEnum(Integer code, String name, String path) {
        this.code = code;
        this.name = name;
        this.path = path;
    }

    public AddressEnum getByCode(Integer code){
        for (AddressEnum addressEnum : AddressEnum.values()){
            if(code.equals(addressEnum.code))
                return addressEnum;
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public AddressEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddressEnum setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public static String getCurrentPath(Integer code) {
        String canonicalPath = System.getProperty("user.dir");
        return canonicalPath+MAP.getOrDefault(code,AddressEnum.UNKNOWN_ADDRESS).getPath();
    }

    public AddressEnum setPath(String path) {
        this.path = path;
        return this;
    }
}
