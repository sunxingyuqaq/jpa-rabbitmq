package com.study.boot.jpa.utils;

import com.study.boot.jpa.model.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Xingyu Sun
 * @date 2019/4/10 10:38
 */
public final class Java8Utils {

    public static void delay(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<Shop> getShops(){
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop("测试11"));
        shops.add(new Shop("测试12"));
        shops.add(new Shop("测试13"));
        shops.add(new Shop("测试14"));
        shops.add(new Shop("测试15"));
        shops.add(new Shop("测试16"));
        shops.add(new Shop("测试17"));
        shops.add(new Shop("测试18"));
        shops.add(new Shop("测试19"));
        return shops;
    }
}
