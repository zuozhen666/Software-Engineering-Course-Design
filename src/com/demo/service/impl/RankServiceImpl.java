package com.demo.service.impl;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.repository.impl.UserRepositoryImpl;
import com.demo.service.RankService;

import java.util.List;

public class RankServiceImpl implements RankService{

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public List<User> rank() {
        return userRepository.rank();
    }
}
