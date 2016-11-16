package com.intelligentz.appointmentz.model;

/**
 * Created by lakshan on 11/16/16.
 */
public class Hospital {
    private String hospital_id;
    private String hospital_name;

    public Hospital() {
    }

    public Hospital(String hospital_id, String hospital_name) {
        this.hospital_id = hospital_id;
        this.hospital_name = hospital_name;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }
}
