package com.iinaq.springboot.controller;

import com.iinaq.springboot.annotation.LocalLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户管理")
public class UserController {

    @GetMapping("/userInfo")
    @ApiOperation(notes = "获取用户信息", value = "get user info")
    public String getUserInfo(){
        return "getUserInfo";
    }

    @LocalLock(key = "user:arg[0]")
    @PostMapping("/addUser")
    @ApiOperation(notes = "添加用户信息", value = "add user info")
    @ApiImplicitParam(value = "添加用户", name = "add user", dataType = "java.lang.String", required = true)
    public String addUser(String str){
        return "addUser";
    }

    @GetMapping("/users")
    public String get(){
        return "get....";
    }

    @RequiresRoles(value = {"admin","test"},logical = Logical.OR)
    @RequiresPermissions(value = {"user:list","user:query"},logical = Logical.AND)
    @GetMapping("/users/query")
    public String query(){
        return "query...";
    }

    @GetMapping("/users/find")
    public String find(){
        return "find...";
    }
}
