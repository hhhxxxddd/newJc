package com.jinchi.common.dto;

import com.jinchi.common.domain.BasicInfoPrecursorProductionLine;

import java.util.List;

public class GoodInLineProDTO {

    BasicInfoPrecursorProductionLine line;

    String product;

    List<String> products;

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public BasicInfoPrecursorProductionLine getLine() {
        return line;
    }

    public void setLine(BasicInfoPrecursorProductionLine line) {
        this.line = line;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
