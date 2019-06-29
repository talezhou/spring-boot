package com.iinaq.springboot.service.impl;

import com.iinaq.springboot.domain.User;
import com.iinaq.springboot.mapper.UserMapper;
import com.iinaq.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserMapper userMapper;
    @Override
    @CachePut(key = "#user.id",value = "user")
    public void save(User user) {
         userMapper.insert(user);
    }

    @Override
    @Cacheable(key = "#id",value = "user")
    public User get(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    @CacheEvict(key = "#id",value = "user")
    public void delete(Long id) {
        userMapper.deleteById(id);
    }
}
