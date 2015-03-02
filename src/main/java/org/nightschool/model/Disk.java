package org.nightschool.model;

public class Disk {
    private int id;
    private String name;
    private String imgUrl;
    private String description;
    private double primaryPrice;
    private double discountedPrice;
    private int stockSize;
    private String shopKeeper;
    private int soldOutCount;

    public Disk() {
    }

    public Disk(int id, String name, String imgUrl, String description, double primaryPrice, double discountedPrice, int stockSize, String shopKeeper, int soldOutCount) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.primaryPrice = primaryPrice;
        this.discountedPrice = discountedPrice;
        this.stockSize = stockSize;
        this.shopKeeper = shopKeeper;
        this.soldOutCount = soldOutCount;
    }

    public Disk(int id, String name, String imgUrl, String description, double primaryPrice, double discountedPrice, int stockSize, String username) {
        this(id,name, imgUrl, description, primaryPrice, discountedPrice, stockSize, username, 0);
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
    public int getSoldOutCount() {
        return soldOutCount;
    }

    public void setSoldOutCount(int soldOutCount) {
        this.soldOutCount = soldOutCount;
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
}
