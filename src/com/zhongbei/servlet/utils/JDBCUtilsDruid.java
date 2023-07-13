package com.zhongbei.servlet.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilsDruid {
    private static DataSource dataSource = null;
    static {
        try {
            Properties pro = new Properties();
            InputStream in = new FileInputStream("D:\\Code\\java\\Supermarket\\src\\resources\\druid.properties");
            pro.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
            System.out.println("dataSource:"+dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() throws SQLException {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("执行Druid的getConnection");
        return dataSource.getConnection();
    }

    public static void close(Statement stmt, Connection conn) {
        close(null, stmt, conn);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close(); // 归还连接
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }



}
