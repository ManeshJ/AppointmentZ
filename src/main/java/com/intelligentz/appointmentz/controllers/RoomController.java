package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.connectToDB;
import com.intelligentz.appointmentz.model.Button;
import com.intelligentz.appointmentz.model.Room;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lakshan on 11/16/16.
 */
public class RoomController {
    connectToDB con;
    public Room getRoom(String roomId) throws ClassNotFoundException, SQLException {
        Room room = null;
        con = new connectToDB();
        if(con.connect()) {
            Connection connection = con.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            String SQL1;
            SQL1 = "SELECT * FROM db_bro.room WHERE room_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
            preparedStmt.setString(1, roomId);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                String room_number = rs.getString("room_number");
                room = new Room(room_number,roomId);
            }
            connection.close();
        }
        return room;
    }
}
