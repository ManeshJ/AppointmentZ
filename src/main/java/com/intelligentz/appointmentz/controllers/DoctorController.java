package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.connectToDB;
import com.intelligentz.appointmentz.model.Doctor;
import com.intelligentz.appointmentz.model.Hospital;
import com.mysql.jdbc.Connection;

import javax.print.Doc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by lakshan on 11/16/16.
 */
public class DoctorController {
    connectToDB con;
    public Doctor getDoctor(String doctorId) throws ClassNotFoundException, SQLException {
        Doctor doctor = null;
        con = new connectToDB();
        if(con.connect()) {
            Connection connection = con.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            String SQL1;
            SQL1 = "SELECT * FROM db_bro.doctor WHERE doctor_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
            preparedStmt.setString(1, doctorId);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                String doctor_id = rs.getString("doctor_id");
                String hospital_id = rs.getString("hospital_id");
                String name = rs.getString("name");
                Hospital hospital = new HospitalController().getHospital(hospital_id);
                doctor = new Doctor(doctor_id,name,hospital);
            }
            connection.close();
        }
        return doctor;
    }
}
