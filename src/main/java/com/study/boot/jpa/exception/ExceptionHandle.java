package com.study.boot.jpa.exception;

import com.study.boot.jpa.result.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Xingyu Sun
 * @date 2019/4/9 11:00
 */
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Result<Object>> handle(CustomException e){
        Result<Object> result = new Result<>();
        return ResponseEntity.ok(result.fail(e.getMessage(),e.getCode()));
    }
}
