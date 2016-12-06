package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.DBConnection;
import com.intelligentz.appointmentz.model.Hospital;
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
public class HospitalController {
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public Hospital getHospitalById(String hospitalId) throws ClassNotFoundException, SQLException, IOException, PropertyVetoException {
        Hospital hospital = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1 = "SELECT hospital_name FROM db_bro.hospital WHERE hospital_id = ?";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, hospitalId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String hospital_name = resultSet.getString("hospital_name");
                hospital = new Hospital(hospitalId,hospital_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return hospital;
    }
}
