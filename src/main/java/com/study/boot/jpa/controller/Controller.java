package com.study.boot.jpa.controller;

import com.study.boot.jpa.entity.User;
import com.study.boot.jpa.enums.ExceptionEnums;
import com.study.boot.jpa.exception.CustomException;
import com.study.boot.jpa.rabbitmq.provider.Sender;
import com.study.boot.jpa.result.Result;
import com.study.boot.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Xingyu Sun
 * @date 2019/4/9 9:01
 */
@RestController
@RequestMapping("user")
public class Controller {

    @Autowired
    private UserService userService;
    @Autowired
    private Sender sender;

    @GetMapping("/all")
    public ResponseEntity<Result<List<User>>> getAllUsers() {
        List<User> all = userService.findAll();
        Result<List<User>> result = new Result<>();
        return ResponseEntity.ok(result.ok(all));
    }

    @GetMapping("/getPage")
    public ResponseEntity<Result<List<User>>> getPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "limit", defaultValue = "10") Integer size) {
        Page<User> userPage = userService.getPage(page, size);
        Result<List<User>> result = new Result<>();
        if (page == 2) {
            throw new CustomException(ExceptionEnums.SERVER_ERROR);
        }
        return ResponseEntity.ok(result.ok(userPage.getContent(), userPage.getTotalElements()));
    }

    @GetMapping("/test")
    public String send() {
        sender.sendMsg();
        return "ok";
    }

    @GetMapping("/test2")
    public ResponseEntity<Result> sendAndReturn() {
        Result<Object> result = new Result<>();
        Object o = sender.sendMsgAndReturn();
        return ResponseEntity.ok(result.ok(o));
    }
}
