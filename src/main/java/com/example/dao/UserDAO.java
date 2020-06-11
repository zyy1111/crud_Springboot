package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {
    void save(User user);
    //mybatis中如果有多个参数需要进行参数的绑定(@Param)
    User login(@Param("username") String username, @Param("password") String password);
}
