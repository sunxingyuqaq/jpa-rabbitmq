package com.study.boot.jpa.enums;

/**
 * @author Xingyu Sun
 * @date 2019/4/9 11:17
 */
public enum  ExceptionEnums {
    /**
     * 服务异常
     */
    SERVER_ERROR("服务异常","500201"),
    /**
     * 数据库异常
     */
    SQL_ERROR("数据库异常","500202");

    private String msg;
    private String code;

    ExceptionEnums(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
