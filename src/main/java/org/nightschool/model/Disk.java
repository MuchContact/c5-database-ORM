package org.nightschool.model;

public class Disk {
    private final int id;
    private final String shopKeeper;
    private int stockSize;
    private double discountedPrice;
    private double primaryPrice;
    private String description;
    private String name;
    private String imgUrl;
    private String desc;

    public Disk(int id, String name, String imgUrl, String description, double primaryPrice, double discountedPrice, int stockSize, String username) {
        this.id=id;
        this.name=name;
        this.imgUrl=imgUrl;
        this.description=description;
        this.primaryPrice=primaryPrice;
        this.discountedPrice =discountedPrice;
        this.stockSize=stockSize;
        this.shopKeeper=username;
    }

    public int getId() {
        return id;
    }

    public String getShopKeeper() {
        return shopKeeper;
    }

    public int getStockSize() {
        return stockSize;
    }

    public void setStockSize(int stockSize) {
        this.stockSize = stockSize;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getPrimaryPrice() {
        return primaryPrice;
    }

    public void setPrimaryPrice(double primaryPrice) {
        this.primaryPrice = primaryPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
