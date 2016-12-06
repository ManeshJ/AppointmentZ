package com.intelligentz.appointmentz.database;

import com.intelligentz.appointmentz.constants.DatabaseConstants;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.xml.crypto.Data;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lakshan on 11/17/16.
 */
public class DBConnection {
    private static DBConnection dbConnection;
    private BasicDataSource ds;

    private DBConnection() throws IOException, SQLException, PropertyVetoException {
        ds = new BasicDataSource();
        ds.setDriverClassName(DatabaseConstants.DRIVER);
        ds.setUsername(DatabaseConstants.USERNAME);
        ds.setPassword(DatabaseConstants.PASSWORD);
        ds.setUrl(DatabaseConstants.DB_URL+DatabaseConstants.DB_NAME);
        // the settings below are optional -- dbcp can work with defaults
//        ds.setMinIdle(5);
//        ds.setMaxIdle(20);
//        ds.setMaxOpenPreparedStatements(180);

    }
//        private Connection conn;
//        private static DBConnection db;
//        private DBConnection() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
//                Class.forName(DatabaseConstants.DRIVER).newInstance();
//                this.conn = DriverManager.getConnection(DatabaseConstants.DB_URL+DatabaseConstants.DB_NAME,
//                        DatabaseConstants.USERNAME,DatabaseConstants.PASSWORD);
//        }

//        public static synchronized DBConnection getDBConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
//            if ( db == null ) {
//                db = new DBConnection();
//            }
//            return db;
//        }
//
//        public Connection getConnection(){
//            return conn;
//        }

    public static synchronized DBConnection getDBConnection() throws IOException, SQLException, PropertyVetoException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
            return dbConnection;
        } else {
            return dbConnection;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }
}
