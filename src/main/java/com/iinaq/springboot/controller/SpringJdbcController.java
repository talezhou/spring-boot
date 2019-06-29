package com.iinaq.springboot.controller;

import com.iinaq.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpringJdbcController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJdbcController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/queryUsers")
    public List<User> queryUsers(){
        String sql = "select * from t_user";
        List<User> users = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
        return users;
    }


}
