package com.hust.seller.dto;

import jakarta.persistence.Column;

public class ProductCartDTO {
    private int productID;
    private String productName;
    private String description ;
    private int price;
    private int quantityID;

    public ProductCartDTO(int productID, String productName, String description, int price, int quantityID) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantityID = quantityID;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
    public int getQuantityID() {
        return quantityID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void setQuantityID(int quantityID) {
        this.quantityID = quantityID;
    }


}
