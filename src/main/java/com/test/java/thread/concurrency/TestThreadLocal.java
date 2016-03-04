package com.test.java.thread.concurrency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mrf on 2016/3/4.
 */
public class TestThreadLocal {
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>(){
        public Connection initialValue(){
            try {
                return DriverManager.getConnection("url","username","password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public static Connection getConnection(){
        return connectionHolder.get();
    }
}
