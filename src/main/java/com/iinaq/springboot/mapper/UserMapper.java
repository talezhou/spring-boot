package com.iinaq.springboot.mapper;

import com.iinaq.springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where username = #{username}")
    List<User> selectByUserName(@Param("username") String username);

    int insert(User user);
}
