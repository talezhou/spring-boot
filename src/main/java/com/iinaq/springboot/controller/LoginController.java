package com.iinaq.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/hello")
    public String hello(){
        log.info("不登陆也可以访问" );
        return "success";
    }

    @GetMapping("/index")
    public String index(){
        log.info("登陆成功了" );
        return "success";
    }

    @GetMapping("/denied")
    public String denied(){
        log.info("权限不足");
        return "denied...";
    }

    @GetMapping("/login")
    public String login(String username, String password, RedirectAttributes modl){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);

        try {
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException e){
            log.error("{}用户不存咋",username);
            usernamePasswordToken.clear();
            return "UnknownAccountException";
        }catch (LockedAccountException e){
            log.error("{}用户已锁定",username);
            usernamePasswordToken.clear();
            return "LockedAccountException";
        }catch (ExcessiveAttemptsException e){
            log.error("{}错误次数过多",username);
            usernamePasswordToken.clear();
            return "ExcessiveAttemptsException";
        }catch (AuthenticationException e){
            log.error("{}验证未通过,堆栈轨迹如下",username,e);
            usernamePasswordToken.clear();
            return "AuthenticationException";
        }
        return "success";
    }
}
