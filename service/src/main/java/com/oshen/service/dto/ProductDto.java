package com.oshen.service.dto;

public class ProductDto {
    private String productName;

    public ProductDto(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
