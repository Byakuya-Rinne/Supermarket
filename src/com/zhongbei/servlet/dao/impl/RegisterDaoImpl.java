package com.zhongbei.servlet.dao.impl;

import com.zhongbei.servlet.dao.IRegisterDao;
import com.zhongbei.servlet.entity.User;
import com.zhongbei.servlet.utils.JDBCUtilsDruid;
import com.zhongbei.servlet.utils.MD5Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class RegisterDaoImpl implements IRegisterDao {
    @Override
    public boolean saveUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        String url = "jdbc:mysql://localhost:3306/admin";
        String SQLUsername = "root";
        String SQLPassword = "123456";

        try {
            conn = JDBCUtilsDruid.getConnection();
            System.out.println("成功连接到MySQL数据库！");

            String sql = "insert into admin(username,password) value(?,?)";
            String newPwdAndSalt = getNewPwd(user.getPassword(), null);
            ps = conn.prepareStatement(sql);

            //把sql中的第1位问号换成user.getUsername()
            ps.setString(1, user.getUsername());
            ps.setString(2, newPwdAndSalt.split(" ")[0]);
            ps.setInt(2, Integer.valueOf(newPwdAndSalt.split(" ")[1]));

            int result = ps.executeUpdate();

            return result > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtilsDruid.close(ps, conn);
        }

        return false;
    }

    @Override
    public User findUserByUsernameAndPwd(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtilsDruid.getConnection();
            System.out.println("Dao层findUserByUsernameAndPwd方法的getConnection成功");
            String sql = "select * from admin where username=?";
            ps = conn.prepareStatement(sql);
            System.out.println("成功连接到MySQL");
            ps.setString(1, username);

            rs = ps.executeQuery();
            User newUser = null;
            while (rs.next()) {
                newUser = new User(rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("salt")
                );
            }

            return newUser;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtilsDruid.close(ps, conn);
        }

        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }


    public String getNewPwd(String password, Integer salt) {
        if (salt == null) {
            salt = new Random().nextInt(1000) + 1000;
        }
        String newPwd = MD5Utils.md5Digest(password, salt);
        return newPwd+" "+salt;
    }


}
