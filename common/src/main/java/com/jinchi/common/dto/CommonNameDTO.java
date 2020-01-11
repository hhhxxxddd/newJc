package com.jinchi.common.dto;

/**
 * @author：XudongHu
 * @className:AuthGeneralUserDTO
 * @description: 这个DTO专门给只需要多显示一个String,多为名称的类使用
 * 有些类只需要多传一个NAME 不要为此多写DTO
 * @date:20:25 2018/12/24
 */
public class CommonNameDTO<T> {
    private String name;

    private T detail;

    public String getName() {
        return name;
    }

    public CommonNameDTO setName(String name) {
        this.name = name;
        return this;
    }

    public T getDetail() {
        return detail;
    }

    public CommonNameDTO setDetail(T detail) {
        this.detail = detail;
        return this;
    }

    @Override
    public String toString() {
        return "CommonNameDTO{" +
                "name='" + name + '\'' +
                ", detail=" + detail +
                '}';
    }
}
