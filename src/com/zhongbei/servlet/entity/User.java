package com.zhongbei.servlet.entity;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private Integer salt;
    private Date createTime;

    public User(int id, String username, String password, Integer salt, Date createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.createTime = createTime;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Integer salt, Date createTime) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.createTime = createTime;
    }

    public User(String username, String password, Integer salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt=" + salt +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
