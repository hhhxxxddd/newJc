package com.jinchi.common.dto;

import java.util.Objects;

public class KeyforProRawStandard {
    Integer productId;
    Integer classId;

    public KeyforProRawStandard(Integer productId, Integer classId) {
        this.productId = productId;
        this.classId = classId;
    }

    //重写equals方法用于map键的唯一性判断
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyforProRawStandard that = (KeyforProRawStandard) o;
        return productId==that.productId &&
                classId==that.classId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, classId);
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "KeyforProRawStandard{" +
                "productId=" + productId +
                ", classId=" + classId +
                '}';
    }
}
