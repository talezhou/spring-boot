package com.iinaq.springboot.mapper;

import com.iinaq.springboot.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where username = #{username}")
    List<User> selectByUserName(@Param("username") String username);

    void insert(User user);

    @Select("select * from t_user where id = #{id}")
    User selectById(Long id);

    @Delete("delete from t_user where id = #{id}")
    void deleteById(Long id);
}
