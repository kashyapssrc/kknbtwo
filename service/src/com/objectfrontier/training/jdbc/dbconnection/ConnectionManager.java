package com.objectfrontier.training.jdbc.dbconnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionManager {

    static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig("resources\\com\\objectfrontier\\training\\properties\\db.properties");
        config.setMaximumPoolSize(3);
        config.setAutoCommit(false);
        dataSource = new HikariDataSource(config);
    }

    public Connection get() throws SQLException {

        Connection connection = dataSource.getConnection();
        return connection;
    }

//    public static Connection establishConnection() throws SQLException {

//        Properties dbProperties = new Properties();
//        Connection dbConnection = null;
//
//        try {
//            ConnectionManager db = new ConnectionManager();
//            Class<? extends ConnectionManager> classForDb = db.getClass();
//            InputStream dbInputFile = classForDb.getClassLoader()
//                                                .getResourceAsStream("com\\objectfrontier\\training\\properties\\db.properties");
//
//            dbProperties.load(dbInputFile);
//            dbConnection = DriverManager.getConnection(dbProperties.getProperty("db_url"), 
//                                                       dbProperties.getProperty("db_username"), 
//                                                       dbProperties.getProperty("db_password"));
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//        }
//        dbConnection.setAutoCommit(false);
//        return dbConnection;  
//    }

    public void close(Connection connection, boolean flag) throws SQLException {

        if (flag == true) {
            connection.commit();
        } else { 
            connection.rollback();
        }
        connection.close();
    }

    public static void main(String[] args) throws SQLException {
        ConnectionManager c = new ConnectionManager();
        Connection con = c.get();
        System.out.println(con);
    }

}
