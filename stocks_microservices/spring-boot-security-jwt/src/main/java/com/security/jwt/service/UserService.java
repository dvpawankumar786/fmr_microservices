package com.security.jwt.service;


import java.util.List;

import com.security.jwt.model.User;
import com.security.jwt.model.UserDto;

public interface UserService {

    User save(UserDto user,int ranCustid);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
