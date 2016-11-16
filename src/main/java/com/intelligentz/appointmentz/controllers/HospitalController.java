package com.intelligentz.appointmentz.controllers;

import com.intelligentz.appointmentz.database.connectToDB;
import com.intelligentz.appointmentz.model.Doctor;
import com.intelligentz.appointmentz.model.Hospital;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lakshan on 11/16/16.
 */
public class HospitalController {
    connectToDB con;
    public Hospital getHospital(String hospitalId) throws ClassNotFoundException, SQLException {
        Hospital hospital = null;
        con = new connectToDB();
        if(con.connect()) {
            Connection connection = con.getConnection();
            Class.forName("com.mysql.jdbc.Driver");
            String SQL1;
            SQL1 = "SELECT hospital_name FROM appointmentz.hospital WHERE hospital_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(SQL1);
            preparedStmt.setString(1, hospitalId);
            ResultSet rs = preparedStmt.executeQuery(SQL1);
            if (rs.next()) {
                String hospital_name = rs.getString("hospital_name");
                hospital = new Hospital(hospitalId,hospital_name);
            }
            connection.close();
        }
        return hospital;
    }
}
