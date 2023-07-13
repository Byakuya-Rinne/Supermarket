package com.zhongbei.servlet.service.impl;

import com.zhongbei.servlet.dao.IRegisterDao;
import com.zhongbei.servlet.dao.impl.RegisterDaoImpl;
import com.zhongbei.servlet.entity.User;
import com.zhongbei.servlet.service.IRegisterService;

public class RegisterServiceImpl implements IRegisterService {
    @Override
    public boolean saveUser(User user) {

        //调用DAO 存数据库
        if (user != null) {
            //用户名密码为空
            if (user.getUsername().equals("") || user.getPassword().equals("")) {
                return false;
            }

            //注册前先查询数据库, 用户是否存在
            IRegisterDao dao = new RegisterDaoImpl();
            System.out.println("创建DaoImpl, 执行findUserByUsername");
            User newUser = dao.findUserByUsername(user.getUsername());
            System.out.println("findUserByUsername成功执行");

            System.out.println("findUserByUsername创建的newUser: " + newUser);
            if (newUser!=null) {
                return false;
            }else {
                // 保存用户数据
                boolean result = dao.saveUser(user);
                return result;
            }





        }


        return false;
    }
}
