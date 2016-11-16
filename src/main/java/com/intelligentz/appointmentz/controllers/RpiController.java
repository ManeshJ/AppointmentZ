package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.connectToDB;
import com.intelligentz.appointmentz.model.Button;
import com.intelligentz.appointmentz.model.Rpi;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by lakshan on 11/16/16.
 */
public class RpiController {
    connectToDB con;
    public Rpi getRpiOfRoom(String roomId) throws ClassNotFoundException, SQLException {
        Rpi rpi =null;
        con = new connectToDB();
        if(con.connect()) {
            Connection connection = con.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            String SQL1;
            SQL1 = "SELECT * FROM appointmentz.rpi WHERE room_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
            preparedStmt.setString(1, roomId);
            ResultSet rs = preparedStmt.executeQuery(SQL1);
            if (rs.next()) {
                String auth = rs.getString("auth");
                String serial = rs.getString("serial");
                rpi = new Rpi(serial,auth);
            }
            connection.close();
        }
        return rpi;
    }
}
