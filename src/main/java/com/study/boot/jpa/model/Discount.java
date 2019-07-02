package com.study.boot.jpa.model;

/**
 * @author Xingyu Sun
 * @date 2019/4/10 10:26
 */
public class Discount {

    public enum Code {
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOUD(20);
        private int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + "price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        return (price * (100 - code.percentage) / 100);
    }
}
