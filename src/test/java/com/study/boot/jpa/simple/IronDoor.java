package com.study.boot.jpa.simple;

/**
 * @author Xingyu Sun
 * @date 2019/5/31 10:08
 */
public class IronDoor implements Door{

    @Override
    public Material getSource() {
        return () -> System.out.println("11111111111");
    }
}
