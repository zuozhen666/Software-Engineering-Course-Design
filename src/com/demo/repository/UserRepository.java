package com.demo.repository;

import com.demo.entity.User;

import java.util.List;

public interface UserRepository {

    public User login(String username, String password);

    public User registCheck(String username);

    public void insertUser(String username, String password, Integer age);

    public List<User> rank();

    public void updateScores(String username, Integer addScores);
}
