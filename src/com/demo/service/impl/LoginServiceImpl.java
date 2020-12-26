package com.demo.service.impl;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.repository.impl.UserRepositoryImpl;
import com.demo.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }
}
