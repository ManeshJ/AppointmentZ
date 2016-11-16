package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.connectToDB;
import com.intelligentz.appointmentz.model.Button;
import com.intelligentz.appointmentz.model.Hospital;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lakshan on 11/16/16.
 */
public class ButtonController {
    connectToDB con;
    public Button getButton(String serial) throws ClassNotFoundException, SQLException {
        Button button = null;
        con = new connectToDB();
        if(con.connect()) {
            Connection connection = con.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            String SQL1;
            SQL1 = "SELECT * FROM appointmentz.button WHERE serial = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
            preparedStmt.setString(1, serial);
            ResultSet rs = preparedStmt.executeQuery(SQL1);
            if (rs.next()) {
                String auth = rs.getString("auth");
                String doctor_id = rs.getString("doctor_id");
                button = new Button(doctor_id,auth,serial);
            }
            connection.close();
        }
        return button;
    }
}
