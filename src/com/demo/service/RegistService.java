package com.demo.service;

import com.demo.entity.User;

public interface RegistService {
    public User registCheck(String username);
    public void insert(String username, String password, Integer age);
}
