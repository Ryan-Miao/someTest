package com.test.java.thread.concurrency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 学习ThreadLocal
 * Created by mrf on 2016/3/6.
 */
public class ConnectionManager {
    private static Connection connection = null;
    public static Connection openConnection() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection("url","username","password");
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection!=null)
            connection.close();
    }
}

class ConnectionManager2{
    private Connection connection = null;
    public Connection openConnection() throws SQLException {
        if (connection==null){
            connection = DriverManager.getConnection("url","username","password");
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection!=null)
            connection.close();
    }
}
class Dao{
    public void insert() throws SQLException {
        ConnectionManager2 connectionManager2 = new ConnectionManager2();
        Connection connection = connectionManager2.openConnection();
        //使用connection进行操作

        connectionManager2.closeConnection();
    }
}

