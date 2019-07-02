package com.study.boot.jpa.model;

/**
 * @author Xingyu Sun
 * @date 2019/4/10 10:29
 */
public class Quote {

    private String shopName;
    private double price;
    private Discount.Code discountCode;

    public Quote(String shopName, double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(String s){
        String[] split = s.split(";");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code code = Discount.Code.valueOf(split[2]);
        return new Quote(shopName,price,code);
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(Discount.Code discountCode) {
        this.discountCode = discountCode;
    }
}
