package com.demo.repository.impl;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.utils.JDBCTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from tb_user where userName = ? and userPwd = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new User(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return user;
    }

    @Override
    public User registCheck(String username) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from tb_user where userName = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new User(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return user;
    }

    @Override
    public void insertUser(String username, String password, Integer age) {
        Connection connection = JDBCTools.getConnection();
        String sql="insert into tb_user (userName,userPwd,userAge,userScores) values(?,?,?,0)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.setInt(3,age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
    }

    @Override
    public List<User> rank() {
        List<User> ans = new ArrayList<User>();
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from tb_user order by userScores desc";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                user = new User(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4));
                ans.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return ans;
    }

    @Override
    public void updateScores(String username, Integer addScores) {
        Connection connection = JDBCTools.getConnection();
        String sql_1 = "select * from tb_user where userName = ?";
        String sql_2 = "update tb_user set userScores = ? where userName = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connection.prepareStatement(sql_1);
            statement.setString(1,username);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = new User(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4));
            }
            Integer Scores = user.getUserScores() + addScores;
            statement = connection.prepareStatement(sql_2);
            statement.setInt(1,Scores);
            statement.setString(2,user.getUserName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
    }
}
