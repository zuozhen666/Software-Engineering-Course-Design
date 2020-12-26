package com.demo.entity;

/**
 *用户实体类
 */
public class User {

    private String userName;    //用户姓名
    private String userPwd;     //用户密码
    private Integer userAge;    //用户年龄
    private Integer userScores; //用户得分

    public User(String userName, String userPwd, Integer userAge, Integer userScores) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.userAge = userAge;
        this.userScores = userScores;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getUserScores() {
        return userScores;
    }

    public void setUserScores(Integer userScores) {
        this.userScores = userScores;
    }

}
