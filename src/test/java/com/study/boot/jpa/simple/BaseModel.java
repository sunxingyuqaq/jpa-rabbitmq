package com.study.boot.jpa.simple;

/**
 * @author Xingyu Sun
 * @date 2019/5/31 13:51
 */
public class BaseModel {
    public String tableName1 = "1";
    public String tableName2 = "2";

    public BaseModel() {
        this.tableName1 = Class.class.getSimpleName();
        this.tableName2 = this.getClass().getSimpleName();
    }
}
