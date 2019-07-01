package com.iinaq.springboot.db;

import com.iinaq.springboot.domain.User;

import java.util.*;

/**
 * @ClassName DbCache
 * @Description TODO
 * @Author zhouzhongshan
 * @Date 2019/7/1  14:54
 **/
public class DbCache {
    public static final Map<String,User> USERS_CACHE = new HashMap<>();

    public static final Map<String,Collection<String>> PERMISSIONS_CACHE = new HashMap<>();

    static {
        USERS_CACHE.put("u1", new User(1L, "u1", "p1", "admin", true));
        USERS_CACHE.put("u2", new User(2L, "u2", "p2", "admin", false));
        USERS_CACHE.put("u3", new User(3L, "u3", "p3", "test", true));

        PERMISSIONS_CACHE.put("admin", Arrays.asList("user:list","user:add","user:edit"));
        PERMISSIONS_CACHE.put("test",Collections.singletonList("user:list"));
    }
}
