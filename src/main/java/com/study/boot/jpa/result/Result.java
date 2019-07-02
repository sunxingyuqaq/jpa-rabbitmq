package com.study.boot.jpa.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.boot.jpa.enums.ExceptionEnums;
import com.study.boot.jpa.enums.ResponseEnums;

/**
 * @author Xingyu Sun
 * @date 2019/4/9 8:44
 */
public class Result<T> {
    private T data;
    private String message;
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result<T> ok(T data) {
        Result<T> result = new Result<>(data);
        result.setMessage(ResponseEnums.OK.getMessage());
        result.setCode(ResponseEnums.OK.getCode());
        return result;
    }

    public Result(T data, String message, String code) {
        this(data);
        this.message = message;
        this.code = code;
    }

    public Result<T> ok(T data, long total) {
        Result<T> result = new Result<>(data);
        result.setTotal(total);
        result.setMessage(ResponseEnums.OK.getMessage());
        result.setCode(ResponseEnums.OK.getCode());
        return result;
    }

    public Result fail() {
        Result result = new Result();
        result.setData(null);
        result.setMessage(ResponseEnums.FAIL.getMessage());
        result.setCode(ResponseEnums.FAIL.getCode());
        return result;
    }

    public Result<T> fail(ExceptionEnums exceptionEnums) {
        Result<T> result = new Result<>();
        result.setData(null);
        result.setMessage(exceptionEnums.getMsg());
        result.setCode(exceptionEnums.getCode());
        return result;
    }

    public Result<T> fail(String message,String code) {
        Result<T> result = new Result<>();
        result.setData(null);
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
