package com.iinaq.springboot.controller;

import com.iinaq.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/getUser")
    public User getUser(@RequestParam("id") Long id){
        String sql = "select * from t_user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        return user;
    }

    @GetMapping("/delUser")
    public int delUser(@RequestParam("id") Long id){
        String sql = "delete from t_user where id = ?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }

    @GetMapping("/addUser")
    public int addUser(@RequestBody User user){
        String sql = "insert into t_user(username,password) values(?,?)";
        int update = jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
        return update;
    }

    @GetMapping("/editUser")
    public int editUser(@RequestBody User user){
        String sql = "update t_usr set username = ? ,password = ? where id = ?";
        return jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getId());
    }
}
