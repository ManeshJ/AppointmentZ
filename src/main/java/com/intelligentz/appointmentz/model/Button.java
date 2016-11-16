package com.intelligentz.appointmentz.model;

/**
 * Created by lakshan on 11/16/16.
 */
public class Button {
    private String doctor_id;
    private String auth;
    private String serial;

    public Button() {
    }

    public Button(String doctor_id, String auth, String serial) {
        this.doctor_id = doctor_id;
        this.auth = auth;
        this.serial = serial;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
