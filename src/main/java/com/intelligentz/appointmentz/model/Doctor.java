package com.intelligentz.appointmentz.model;

/**
 * Created by lakshan on 11/16/16.
 */
public class Doctor {
    private String doctor_id;
    private String name;
    private Hospital hospital;

    public Doctor() {
    }

    public Doctor(String doctor_id, String name, Hospital hospital) {
        this.doctor_id = doctor_id;
        this.name = name;
        this.hospital = hospital;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
