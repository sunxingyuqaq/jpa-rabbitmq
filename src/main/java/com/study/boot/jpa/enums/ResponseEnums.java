package com.study.boot.jpa.enums;

/**
 * @author Xingyu Sun
 * @date 2019/4/9 8:47
 */
public enum  ResponseEnums {

    /**
     * ok
     */
    OK("ok","200"),

    /**
     * fail
     */
    FAIL("fail","500"),

    /**
     * ok
     */

    FORBIDDEN("forbidden","403"),

    /**
     * ok
     */
    NOT_FOUND("no found","404");


    /**
     * msg
     */
    private String message;
    /**
     * code
     */
    private String code;

    ResponseEnums(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
