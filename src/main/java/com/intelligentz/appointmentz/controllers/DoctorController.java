package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.DBConnection;
import com.intelligentz.appointmentz.model.Doctor;
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
public class DoctorController {
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    public Doctor getDoctorById(String doctorId) throws ClassNotFoundException, IOException, PropertyVetoException {
        Doctor doctor = null;
        try {
            connection = DBConnection.getDBConnection().getConnection();
            String SQL1 = "SELECT * FROM db_bro.doctor WHERE doctor_id = ?";
            preparedStatement = connection.prepareStatement(SQL1);
            preparedStatement.setString(1, doctorId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String doctor_id = resultSet.getString("doctor_id");
                String hospital_id = resultSet.getString("hospital_id");
                String name = resultSet.getString("name");
                Hospital hospital = new HospitalController().getHospitalById(hospital_id);
                doctor = new Doctor(doctor_id, name, hospital);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(preparedStatement);
            DbUtils.closeQuietly(connection);
        }
        return doctor;
    }
}
