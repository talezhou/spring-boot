package com.iinaq.springboot.service;

import com.iinaq.springboot.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);

    User get(Long id);

    void delete(Long id);
}
