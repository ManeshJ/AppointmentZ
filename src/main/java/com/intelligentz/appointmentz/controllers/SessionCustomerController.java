package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.connectToDB;
import com.intelligentz.appointmentz.model.Rpi;
import com.intelligentz.appointmentz.model.SessonCustomer;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by lakshan on 11/16/16.
 */
public class SessionCustomerController {
    connectToDB con;
    public ArrayList<SessonCustomer> getSessionCustomers(String sessionId, int currentNumer) throws ClassNotFoundException, SQLException {
        ArrayList<SessonCustomer> sessonCustomers = new ArrayList<>();
        con = new connectToDB();
        if(con.connect()) {
            Connection connection = con.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            String SQL1;
            SQL1 = "SELECT * FROM db_bro.session_customers WHERE session_id = ? AND appointment_num > ? AND appointment_num <= ?";
            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
            preparedStmt.setString(1, sessionId);
            preparedStmt.setInt(2, currentNumer);
            preparedStmt.setInt(3, currentNumer + 5);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                String mobile = rs.getString("mobile");
                int appointment_num = rs.getInt("appointment_num");
                SessonCustomer sessonCustomer = new SessonCustomer(mobile,appointment_num);
                sessonCustomers.add(sessonCustomer);
            }
            connection.close();
        }
        return sessonCustomers;
    }
}
