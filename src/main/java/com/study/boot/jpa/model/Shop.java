package com.study.boot.jpa.model;

import com.study.boot.jpa.utils.Java8Utils;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author Xingyu Sun
 * @date 2019/4/10 10:22
 */
public class Shop {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop(String name) {
        this.name = name;
    }

    public String getPrice(String product) {
        double v = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, v, code);
    }

    private double calculatePrice(String product) {
        Java8Utils.delay();
        return new Random().nextDouble();
    }

    public double getPrice() {
        return calculatePrice("好的产品");
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                future.complete(price);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }

        }).start();
        return future;
    }
}
