package com.study.boot.jpa.exception;

import com.study.boot.jpa.enums.ExceptionEnums;

/**
 * @author Xingyu Sun
 * @date 2019/4/9 11:14
 */
public class CustomException extends RuntimeException{

    private String message;
    private String code;

    public CustomException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public CustomException(ExceptionEnums exceptionEnums){
        this.message = exceptionEnums.getMsg();
        this.code = exceptionEnums.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
