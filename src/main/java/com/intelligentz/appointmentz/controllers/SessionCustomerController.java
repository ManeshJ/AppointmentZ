package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.DBConnection;
import com.intelligentz.appointmentz.model.SessonCustomer;
import org.apache.commons.dbutils.DbUtils;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by lakshan on 11/16/16.
 */
public class SessionCustomerController {
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public ArrayList<SessonCustomer> getSessionCustomersForCurrentNumber(String sessionId, int currentNumer) throws ClassNotFoundException, SQLException, IOException, PropertyVetoException {
        ArrayList<SessonCustomer> sessonCustomers = new ArrayList<>();
        try {
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1 = "SELECT * FROM db_bro.session_customers WHERE session_id = ? AND appointment_num > ? AND appointment_num <= ?";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, sessionId);
            preparedStatement.setInt(2, currentNumer);
            preparedStatement.setInt(3, currentNumer + 5);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String mobile = resultSet.getString("mobile");
                int appointment_num = resultSet.getInt("appointment_num");
                SessonCustomer sessonCustomer = new SessonCustomer(mobile,appointment_num);
                sessonCustomers.add(sessonCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return sessonCustomers;
    }
}
