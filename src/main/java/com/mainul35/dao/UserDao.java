package com.mainul35.dao;

import com.mainul35.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserDao extends UserDetailsService{
    public List<User> list();

    User addUser(User user);
}
