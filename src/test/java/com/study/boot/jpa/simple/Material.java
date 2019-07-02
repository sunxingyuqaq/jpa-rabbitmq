package com.study.boot.jpa.simple;

/**
 * @author Xingyu Sun
 * @date 2019/5/31 10:07
 */
@FunctionalInterface
public interface Material {

    void create();

    default void print(){
        System.out.println("default print");
    }
}
