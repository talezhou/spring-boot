package com.iinaq.springboot.controller;

import com.iinaq.springboot.annotation.DateTime;
import com.iinaq.springboot.domain.Book;
import com.iinaq.springboot.domain.Worker;
import com.iinaq.springboot.groups.Groups;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
public class ValidateController {

    @GetMapping("/read")
    public String read(@NotBlank(message = "name不能为空") @Length(min = 2,max = 10,message = "name必须在{min}-{max}之间") String name){
        return "success";
    }

    @PostMapping("/readBook")
    public String readBook(@Validated @RequestBody Book book){
        return "success";
    }

    @GetMapping("/work")
    public String work(@DateTime(message = "格式错误，正确的格式为： {format}",format = "yyyy-MM-dd HH:mm:ss") String date){
        return "success";
    }

    @GetMapping("/add")
    public String add(@Validated(value = Groups.Default.class) Worker worker){
        return "success";
    }

    @GetMapping("/update")
    public String update(@Validated(value ={Groups.Update.class,Groups.Default.class}) Worker worker){
        return "success";
    }
}
