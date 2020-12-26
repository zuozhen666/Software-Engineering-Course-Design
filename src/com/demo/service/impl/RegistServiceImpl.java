package com.demo.service.impl;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.repository.impl.UserRepositoryImpl;
import com.demo.service.RegistService;

public class RegistServiceImpl implements RegistService {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User registCheck(String username) { return userRepository.registCheck(username); }

    @Override
    public void insert(String username, String password, Integer age) { userRepository.insertUser(username, password, age);}
}
