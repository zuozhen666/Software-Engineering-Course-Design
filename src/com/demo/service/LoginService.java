package com.demo.service;

import com.demo.entity.User;

public interface LoginService {
    public User login(String username, String password);
}
