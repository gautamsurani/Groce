package com.getprofitam.android.model;

public class BestNewProductModel {

    String productID;
    String name;
    String caption;
    String image;
    String discount;
    String price;
    String mrp;
    String sold_out;

    public BestNewProductModel(String productID, String name, String caption, String image, String discount, String price, String mrp, String sold_out) {
        this.productID = productID;
        this.name = name;
        this.caption = caption;
        this.image = image;
        this.discount = discount;
        this.price = price;
        this.mrp = mrp;
        this.sold_out = sold_out;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getSold_out() {
        return sold_out;
    }

    public void setSold_out(String sold_out) {
        this.sold_out = sold_out;
    }
}
