package com.iinaq.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户管理")
public class UserController {

    @GetMapping("/userInfo")
    @ApiOperation(notes = "获取用户信息", value = "get user info")
    public String getUserInfo(){
        return "getUserInfo";
    }

    @PostMapping("/addUser")
    @ApiOperation(notes = "添加用户信息", value = "add user info")
    @ApiImplicitParam(value = "添加用户", name = "add user", dataType = "java.lang.String", required = true)
    public String addUser(String str){
        return "addUser";
    }
}
