package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.DBConnection;
import com.intelligentz.appointmentz.model.Button;
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
public class ButtonController {
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    public Button getButtonBySerial(String serial) throws ClassNotFoundException, IOException, PropertyVetoException {
        Button button = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1 = "SELECT * FROM db_bro.button WHERE serial = ?";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, serial);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String auth = resultSet.getString("auth");
                String doctor_id = resultSet.getString("doctor_id");
                button = new Button(doctor_id, auth, serial);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return button;
    }
}
