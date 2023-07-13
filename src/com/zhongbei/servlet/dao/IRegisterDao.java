package com.zhongbei.servlet.dao;

import com.zhongbei.servlet.entity.User;

public interface IRegisterDao {
    public  boolean saveUser(User user);

    User findUserByUsernameAndPwd(String username, String password);

    User findUserByUsername(String username);

}
