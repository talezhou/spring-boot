package com.iinaq.springboot.controller;

import com.iinaq.springboot.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/sum")
    public String master(Integer num){
        if (num == null){
            throw new CustomException(400,"num不能为空！");
        }
        int i = 10*num;
        return "result : " + i;
    }

}
