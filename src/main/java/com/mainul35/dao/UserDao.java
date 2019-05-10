package com.mainul35.dao;

import com.mainul35.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserDao {
    public List<User> list();

    User addUser(String name);
}
