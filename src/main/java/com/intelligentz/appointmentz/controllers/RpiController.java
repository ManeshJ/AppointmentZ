package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.constants.RpiPinConstants;
import com.intelligentz.appointmentz.database.DBConnection;
import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.handler.RpiHandler;
import com.intelligentz.appointmentz.model.Rpi;
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
public class RpiController {
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    public Rpi getRpiOfRoom(String roomId) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, PropertyVetoException {
        Rpi rpi = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1;
            SQL1 = "SELECT * FROM db_bro.rpi WHERE room_id = ?";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, roomId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String auth = resultSet.getString("auth");
                String serial = resultSet.getString("serial");
                rpi = new Rpi(serial, auth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return rpi;
    }

    public int getCurrentNumber(String serial) throws ClassNotFoundException, SQLException, IOException, PropertyVetoException {
//        con = new connectToDB();
//        if(con.connect()) {
//            Connection connection = con.getConnection();
//            Class.forName("com.mysql.jdbc.Driver");
//            String SQL1;
//            SQL1 = "UPDATE appointmentz.rpi_current_num SET current_no = ? WHERE  serial = ?";
//            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
//            preparedStmt.setInt(1, newNumber);
//            preparedStmt.setString(2, serial);
//            preparedStmt.execute(SQL1);
//            connection.close();
//        }
        String room_id = getRpiRoomId(serial);
        SessionController sessionController = new SessionController();
        return sessionController.getCurrentNumberOfRoom(room_id);
    }

    public void setRpiCurrentNumber(String serial, int newNumber) throws SQLException, ClassNotFoundException, IdeabizException, IOException, PropertyVetoException, InstantiationException, IllegalAccessException {
        String room_id = getRpiRoomId(serial);
        Rpi rpi = getRpiOfRoom(room_id);
        new SessionController().setCurrentNumberOfRoom(room_id,newNumber);
        new RpiHandler().updateRpiPin(rpi.getSerial(),rpi.getAuth(), RpiPinConstants.INTURRUPT_PIN, RpiPinConstants.ACTION_ON);
    }

    private String getRpiRoomId(String serial) throws ClassNotFoundException, IOException, PropertyVetoException {
        String room_id = null;
        try{
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1 = "SELECT * FROM db_bro.rpi WHERE serial = ?";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, serial);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room_id = resultSet.getString("room_id");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }

        return room_id;
    }
    public void decreaseRpiCurrentNumber(String serial) throws SQLException, ClassNotFoundException, IdeabizException, IOException, PropertyVetoException, InstantiationException, IllegalAccessException {
        String room_id = getRpiRoomId(serial);
        Rpi rpi = getRpiOfRoom(room_id);
        int current_no = new SessionController().getCurrentNumberOfRoom(room_id);
        new SessionController().setCurrentNumberOfRoom(room_id,current_no-1);
        new RpiHandler().updateRpiPin(rpi.getSerial(),rpi.getAuth(), RpiPinConstants.INTURRUPT_PIN, RpiPinConstants.ACTION_ON);
    }


}
