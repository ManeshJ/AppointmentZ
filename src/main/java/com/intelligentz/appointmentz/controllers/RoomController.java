package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.DBConnection;
import com.intelligentz.appointmentz.model.Room;
import org.apache.commons.dbutils.DbUtils;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lakshan on 11/16/16.
 */
public class RoomController {
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    public Room getRoomById(String roomId) throws ClassNotFoundException, IOException, PropertyVetoException {
        Room room = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1 = "SELECT * FROM db_bro.room WHERE room_id = ?";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, roomId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String room_number = resultSet.getString("room_number");
                room = new Room(room_number,roomId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return room;
    }
}
