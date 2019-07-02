package com.study.boot.jpa.simple;

/**
 * @author Xingyu Sun
 * @date 2019/5/31 13:52
 */
public class Order extends BaseModel{

    public static void main(String[] args) {
        System.out.println(new Order().tableName1);
        System.out.println(new Order().tableName2);
        System.out.println(new BaseModel().tableName1);
    }
}
