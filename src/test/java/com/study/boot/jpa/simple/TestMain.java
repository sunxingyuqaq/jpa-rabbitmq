package com.study.boot.jpa.simple;

import java.util.UUID;

/**
 * @author Xingyu Sun
 * @date 2019/5/31 14:02
 */
public class TestMain {

    public static void main(String[] args) {
        Model model = new Model();
        model.setName(UUID.randomUUID().toString()+1);
        System.out.println(model.getName().length());
    }
}
